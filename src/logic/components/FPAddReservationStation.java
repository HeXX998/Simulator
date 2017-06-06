package logic.components;

import logic.RenamedValue;
import logic.ReservationStation;
import logic.TomasuloCircuit;
import logic.components.InstructionMemory.ExecStatus;
import logic.components.InstructionMemory.FPUInstruction;
import logic.components.InstructionMemory.Instruction;
import logic.components.InstructionMemory.LoadStoreInstruction;
import logic.components.LoadBuffer.EntryData;

public class FPAddReservationStation extends ReservationStation {
	public class EntryData extends ReservationStation.Entry {
		public EntryData(Instruction instruction, ReservationStation rs, int index, Operation op) {
			super(instruction, rs, index);
			this.operation = op;
		}
		public Operation operation;
		public RenamedValue value1;
		public RenamedValue value2;
		int clockRemaining = 5; //TODO : Change this according to FPU Pipeline!

		@Override
		public boolean readyToExecute() {
			return value1.isAvailable && value2.isAvailable;
		}
	}
	public EntryData[] entries;
	public FPAddReservationStation(TomasuloCircuit circuit, int size)
	{
		super(circuit, size);
		entries = new EntryData[size];
	}
	
	public boolean addInstruction(FPUInstruction inst) {
		for(int i = 0; i < size; i++)
		{
			if(entries[i] == null) {
				EntryData entryData = new EntryData(inst, this, i, inst.operation);
				entryData.value1 = new RenamedValue(circuit.fpRegisterFile, inst.source1);
				entryData.value2 = new RenamedValue(circuit.fpRegisterFile, inst.source2);
				entries[i] = entryData;
				circuit.fpRegisterFile.bindUpdatedValue(inst.destination, entryData);
				return true;
			}
		}
		return false;
	}
	
	@Override
	public void onClockTick() {
		for(int i = 0; i < entries.length; i++)
		{
			if(entries[i] == null) {
				continue;
			}
			if(entries[i].instruction.execStatus == ExecStatus.RUNNING)
			{
				entries[i].clockRemaining--;
				if(entries[i].clockRemaining == 0)
				{
					circuit.sendBroadcast(entries[i],
						entries[i].operation == Operation.ADD ?
								entries[i].value1.value + entries[i].value2.value : 
								entries[i].value1.value - entries[i].value2.value
						);
				}
			}
			else if(entries[i].instruction.execStatus == ExecStatus.QUEUED &&
					entries[i].readyToExecute())
			{
				entries[i].instruction.execStatus = ExecStatus.RUNNING;
			}
		}
	}

	@Override
	public void onBroadcast(ReservationStation.Entry entry, float data) {
		for(int i = 0; i < entries.length; i++)
		{
			if(entries[i] == null) {
				continue;
			}
			if(entries[i] == entry) {
				entries[i] = null;
				continue;
			}
			if(entries[i].value1.rsEntry == entry)
			{
				entries[i].value1.isAvailable = true;
				entries[i].value1.value = data;
			}
			if(entries[i].value2.rsEntry == entry)
			{
				entries[i].value2.isAvailable = true;
				entries[i].value2.value = data;
			}
		}
	}
	
	@Override
	public String getName() {
		return "RS.Add";
	}

}
