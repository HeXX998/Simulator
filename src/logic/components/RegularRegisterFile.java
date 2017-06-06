package logic.components;

import logic.Component;
import logic.RenamedValue;

public class RegularRegisterFile extends Component {
	
	protected int size;
	protected int[] data;

	public RegularRegisterFile(int size)
	{
		this.size = size;
		this.data = new int[size];
	}
	
	public void setData(int address, int value)
	{
		data[address] = value;
	}
	
	public int getData(int address)
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
	public void onBroadcast(int register, float data) {
		// TODO Auto-generated method stub
		
	}

}
