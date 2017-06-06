package logic.components;

import logic.RenamedValue;
import logic.ReservationStation;
import logic.TomasuloCircuit;
import logic.components.FPAddReservationStation.EntryData;
import logic.components.InstructionMemory.FPUInstruction;

public class FPMulReservationStation extends ReservationStation {
	public class EntryData extends ReservationStation.Entry {
		public EntryData(ReservationStation rs, int index, Operation op) {
			super(rs, index);
			this.operation = op;
		}
		public Operation operation;
		public RenamedValue value1;
		public RenamedValue value2;
	}
	public EntryData[] entries;
	public FPMulReservationStation(TomasuloCircuit circuit, int size)
	{
		super(circuit, size);
		entries = new EntryData[size];
	}
	
	public boolean addInstruction(FPUInstruction inst) {
		for(int i = 0; i < size; i++)
		{
			if(entries[i] == null) {
				EntryData entryData = new EntryData(this, i, inst.operation);
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
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onBroadcast(int register, float data) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public String getName() {
		return "RS.Mul";
	}

}
