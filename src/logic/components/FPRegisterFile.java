package logic.components;

import logic.Component;
import logic.RenamedValue;

public class FPRegisterFile extends Component {
	
	protected int size;
	protected float[] data;
	protected RenamedValue[] updatedValue;

	public FPRegisterFile(int size)
	{
		this.size = size;
		this.data = new float[size];
		this.updatedValue = new RenamedValue[size];
	}
	
	public void setData(int address, float value)
	{
		data[address] = value;
	}
	
	public float getData(int address)
	{
		return data[address];
	}
	
	public RenamedValue getUpdatedValue(int address)
	{
		return updatedValue[address];
	}

	public int getSize() {
		return size;
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
