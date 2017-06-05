package logic;

import java.util.HashMap;
import java.util.Iterator;

import logic.components.FPAddReservationStation;
import logic.components.FPMulReservationStation;
import logic.components.LoadBuffer;
import logic.components.StoreBuffer;

public class TomasuloCircuit {
	class Pin
	{
		public Object value;
	}

	HashMap<String, Component> components;
	HashMap<String, Pin> pins;
	
	LoadBuffer loadBuffer;
	StoreBuffer storeBuffer;
	FPAddReservationStation addReservationStation;
	FPMulReservationStation mulReservationStation;

	public TomasuloCircuit()
	{
		loadBuffer = new LoadBuffer(3);
		storeBuffer = new StoreBuffer(3);
		addReservationStation = new FPAddReservationStation(3);
		mulReservationStation = new FPMulReservationStation(2);
		components = new HashMap<String, Component>();
		pins = new HashMap<String, Pin>();
	}
	
	public void addComponent(String name, Component component)
	{
		components.put(name, component);
	}
	
	public Component getComponent(String name)
	{
		return components.get(name);
	}
	
	public void tick()
	{
		for (Component components : components.values()) {
			components.onClockTick();
		}
	}
	
	public void addPin(String pinName)
	{
		//TODO: Add a pin to the circuit.
	}
}
