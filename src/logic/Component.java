package logic;

import java.util.HashMap;

import logic.TomasuloCircuit;

public abstract class Component {
	protected HashMap<String, TomasuloCircuit.Pin> pinBindings;

	abstract public void onClockTick();
	abstract public void onBroadcast(int register, float data);
	
	public void getInputPinValue() {
		
	}
	
	public void assignOutputPinValue() {
		
	}
}
