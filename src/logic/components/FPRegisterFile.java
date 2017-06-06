package logic.components;

import logic.Component;
import logic.RenamedValue;
import logic.ReservationStation;
import logic.TomasuloCircuit;

public class FPRegisterFile extends Component {
	
	protected int size;
	protected float[] data;
	protected ReservationStation.Entry[] updatedValue;

	public FPRegisterFile(TomasuloCircuit circuit, int size)
	{
		super(circuit);
		this.size = size;
		this.data = new float[size];
		this.updatedValue = new ReservationStation.Entry[size];
	}
	
	public void setData(int address, float value)
	{
		data[address] = value;
	}
	
	public float getData(int address)
	{
		return data[address];
	}
	
	public ReservationStation.Entry getUpdatedValue(int address)
	{
		return updatedValue[address];
	}

	public int getSize() {
		return size;
	}
	
	public void bindUpdatedValue(int address, ReservationStation.Entry entry) {
		updatedValue[address] = entry;
	}

	@Override
	public void onClockTick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onBroadcast(ReservationStation.Entry entry, float data) {
		// TODO Auto-generated method stub
		for(int i = 0; i < size; i++)
		{
			if(updatedValue[i] == null) {
				continue;
			}
			if(updatedValue[i] == entry)
			{
				this.data[i] = data;
				updatedValue[i] = null;
			}
		}
	}

}
