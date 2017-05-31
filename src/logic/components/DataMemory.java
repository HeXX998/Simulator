package logic.components;
import logic.Component;

public class DataMemory extends Component {
	protected int size;
	protected float[] data;

	public DataMemory(int size)
	{
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
	
	public String[] inputPins()
	{
		return new String[] {"data", "address", "is_write"};
	}
	
	public String[] outputPins()
	{
		return new String[] {"value"};
	}

	public int getSize() {
		return size;
	}

	@Override
	public void onClockTick() {
		data[0] += 0.1;
		// TODO Auto-generated method stub
	}
}
