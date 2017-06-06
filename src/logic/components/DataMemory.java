package logic.components;
import logic.Component;
import logic.ReservationStation;
import logic.TomasuloCircuit;

public class DataMemory extends Component {
	protected int size;
	protected float[] data;

	public DataMemory(TomasuloCircuit circuit, int size)
	{
		super(circuit);
		this.size = size;
		this.data = new float[size];
	}
	
	public void setData(int address, float value)
	{
		data[address] = value;
	}
	
	public float getData(int address)
	{
		return data[address];
	}

	public int getSize() {
		return size;
	}

	@Override
	public void onClockTick() {
		// TODO Auto-generated method stub
	}

	@Override
	public void onBroadcast(ReservationStation.Entry entry, float data) {
		// TODO Auto-generated method stub
		
	}
}
