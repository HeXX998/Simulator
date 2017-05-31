package logic;

import java.util.HashMap;

public class Circuit {
	HashMap<String, Component> components;
	public Circuit()
	{
		components = new HashMap<String, Component>();
	}
	
	public void addComponent(String name, Component component)
	{
		components.put(name, component);
	}
	
	public Component getComponent(String name)
	{
		return components.get(name);
	}
	
	public void addPin()
	{
		//TODO: Add a pin to the circuit.
	}
}
