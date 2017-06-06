package logic.components;

import logic.RenamedValue;
import logic.ReservationStation;
import logic.TomasuloCircuit;

public class FPAddReservationStation extends ReservationStation {
	public class EntryData extends ReservationStation.Entry {
		public EntryData(ReservationStation rs, int index) {
			super(rs, index);
			// TODO Auto-generated constructor stub
		}
		Operation operation;
		RenamedValue value1;
		RenamedValue value2;
	}
	protected EntryData[] entries;
	public FPAddReservationStation(TomasuloCircuit circuit, int size)
	{
		super(circuit, size);
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
	
	@Override
	public String getName() {
		return "RS.Add";
	}

}
