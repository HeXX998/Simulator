package logic.components;

import logic.RenamedValue;
import logic.ReservationStation;
import logic.TomasuloCircuit;
import logic.components.FPAddReservationStation.EntryData;
import logic.components.InstructionMemory.Instruction;

public class StoreBuffer extends ReservationStation { 

	public class EntryData extends ReservationStation.Entry {
		public EntryData(Instruction instruction, ReservationStation rs, int index) {
			super(instruction, rs, index);
			// TODO Auto-generated constructor stub
		}
		RenamedValue baseAddress;
		float offset;
		
		@Override
		public boolean readyToExecute() {
			// TODO Auto-generated method stub
			return false;
		}
	}
	
	protected EntryData[] entries;
	public StoreBuffer(TomasuloCircuit circuit, int size)
	{
		super(circuit, size);
		entries = new EntryData[size];
	}
	
	@Override
	public void onClockTick() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void onBroadcast(ReservationStation.Entry entry, float data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getName() {
		return "Store";
	}
}
