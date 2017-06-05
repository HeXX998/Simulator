package logic.components;

import logic.RenamedValue;
import logic.ReservationStation;
import logic.components.FPAddReservationStation.EntryData;

public class StoreBuffer extends ReservationStation { 

	public class EntryData extends ReservationStation.Entry {
		RenamedValue baseAddress;
		float offset;
	}
	
	protected EntryData[] entries;
	public StoreBuffer(int size)
	{
		super(size);
		entries = new EntryData[size];
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
