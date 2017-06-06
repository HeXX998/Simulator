package logic.components;

import logic.RenamedValue;
import logic.ReservationStation;
import logic.components.FPAddReservationStation.EntryData;
import logic.components.InstructionMemory.LoadStoreInstruction;

public class LoadBuffer extends ReservationStation { 

	public class EntryData extends ReservationStation.Entry {
		int baseAddress; //Note: We assume regular registers will never be change...
		int offset;
	}
	
	public EntryData[] entries;
	public LoadBuffer(int size)
	{
		super(size);
		entries = new EntryData[size];
	}
	
	public boolean addInstruction(LoadStoreInstruction inst) {
		EntryData entryData = new EntryData();
		entryData.baseAddress = inst.dataRegister; // TODO: From Regular registers
		entryData.offset = inst.offset;		
		for(int i = 0; i < size; i++)
		{
			if(entries[i] == null) {
				entries[i] = entryData;
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
}
