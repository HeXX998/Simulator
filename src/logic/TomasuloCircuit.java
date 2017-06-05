package logic;

import java.util.HashMap;
import java.util.Iterator;

public class TomasuloCircuit {
	class Pin
	{
		public Object value;
	}

	HashMap<String, Component> components;
	HashMap<String, Pin> pins;
	
	public TomasuloCircuit()
	{
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
