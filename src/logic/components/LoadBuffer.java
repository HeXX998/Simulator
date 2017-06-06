package logic.components;

import logic.RenamedValue;
import logic.ReservationStation;
import logic.TomasuloCircuit;
import logic.components.FPAddReservationStation.EntryData;
import logic.components.InstructionMemory.LoadStoreInstruction;

public class LoadBuffer extends ReservationStation { 

	public class EntryData extends ReservationStation.Entry {
		public EntryData(ReservationStation rs, int index, int baseAddress, int offset) {
			super(rs, index);
			this.baseAddress = baseAddress;
			this.offset = offset;
		}
		public int baseAddress; //Note: We assume regular registers will never be change...
		public int offset;
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
					this, i, 
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
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void onBroadcast(int register, float data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getName() {
		return "Load";
	}

}
