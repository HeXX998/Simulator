package logic;

import java.util.HashMap;

import logic.TomasuloCircuit;

public abstract class Component {
	protected HashMap<String, TomasuloCircuit.Pin> pinBindings;

	abstract public void onClockTick();
	abstract public void onBroadcast(ReservationStation.Entry entry, float data);
	
	protected TomasuloCircuit circuit;
	
	public Component(TomasuloCircuit circuit) {
		this.circuit = circuit;
	}
	
	public void getInputPinValue() {
		
	}
	
	public void assignOutputPinValue() {
		
	}
}
