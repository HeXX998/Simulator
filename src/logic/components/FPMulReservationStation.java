package logic.components;

import logic.RenamedValue;
import logic.ReservationStation;
import logic.components.FPAddReservationStation.EntryData;

public class FPMulReservationStation extends ReservationStation {
	public class EntryData extends ReservationStation.Entry {
		Operation operation;
		RenamedValue value1;
		RenamedValue value2;
	}
	protected EntryData[] entries;
	public FPMulReservationStation(int size)
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