package main;

import gui.MainGui;
import logic.TomasuloCircuit;
import logic.components.DataMemory;

public class Tomasulo {
	private MainGui mainGui;
	
	public static TomasuloCircuit buildCircuit()
	{
		TomasuloCircuit circuit = new TomasuloCircuit();
		DataMemory dataMemory = new DataMemory(256);
		circuit.addComponent("data_memory", dataMemory);
		return circuit;
	}
	
	//Example，运行MainGui类观察示例
	public static void main(String[] args) {
		TomasuloCircuit circuit = buildCircuit();
		MainGui mainGui = new MainGui(circuit);
		mainGui.modifyFloatRegister(5, "Expr5", 100.70f );
		mainGui.modifyIntegerRegister(7, 7);
		for(int i = 0; i <= 10; i++) {
			mainGui.addInstruction("LD", "F" + String.valueOf(i), "34", "D2");
		}
		mainGui.addLoadQueuePanel(1, true, 1234, 100.70f );
		mainGui.addStoreQueuePanel(2, true, 1234, 100.90f );
		mainGui.setParameter(100, 200, 300);
		mainGui.addReservation(4, 1, true, "ADDU", 1.5f, 2.5f, 3, 4);
		mainGui.addReservation(3, 1, true, "LD", 4.5f, 5.5f, 1, 2);
		mainGui.removeReservation(3);
		for(int i = 0; i <= 10; i++) {
			mainGui.addRuntime("ADDU" + String.valueOf(i));
		}
		mainGui.modifyRuntime(5, "LD", true, "HAHA");
		mainGui.memoryPanel.bindDataMemory((DataMemory)circuit.getComponent("data_memory"));
	}

}
