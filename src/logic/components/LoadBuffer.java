package logic.components;

import logic.RenamedValue;
import logic.ReservationStation;
import logic.TomasuloCircuit;
import logic.components.FPAddReservationStation.EntryData;
import logic.components.InstructionMemory.ExecStatus;
import logic.components.InstructionMemory.Instruction;
import logic.components.InstructionMemory.LoadStoreInstruction;

public class LoadBuffer extends ReservationStation { 

	public class EntryData extends ReservationStation.Entry {
		public EntryData(Instruction instruction, ReservationStation rs, int index, int baseAddress, int offset) {
			super(instruction, rs, index);
			this.baseAddress = baseAddress;
			this.offset = offset;
		}
		public int baseAddress; //Note: We assume regular registers will never be change...
		public int offset;
		public int clockRemaining = 3;
		
		@Override
		public boolean readyToExecute() {
			return true;
		}
	}
	
	public EntryData[] entries;
	public LoadBuffer(TomasuloCircuit circuit, int size)
	{
		super(circuit, size);
		entries = new EntryData[size];
	}
	
	public boolean addInstruction(LoadStoreInstruction inst) {
		for(int i = 0; i < size; i++)
		{
			if(entries[i] == null) {
				EntryData entryData = new EntryData(
					inst, this, i, 
					circuit.regularRegisterFile.getData(inst.baseRegister),
					inst.offset);
				entries[i] = entryData;
				circuit.fpRegisterFile.bindUpdatedValue(inst.dataRegister, entryData);
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
							circuit.dataMemory.getData(entries[i].baseAddress + entries[i].offset));
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
		}
	}

	@Override
	public String getName() {
		return "Load";
	}

}
