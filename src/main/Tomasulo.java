package main;

import gui.MainGui;
import logic.TomasuloCircuit;
import logic.components.DataMemory;

public class Tomasulo {
	private MainGui mainGui;
	
	public static TomasuloCircuit buildCircuit()
	{
		TomasuloCircuit circuit = new TomasuloCircuit();
		return circuit;
	}
	
	//Example，运行MainGui类观察示例
	public static void main(String[] args) {
		TomasuloCircuit circuit = buildCircuit();
		MainGui mainGui = new MainGui(circuit);
		mainGui.modifyIntegerRegister(7, 7);
		mainGui.setParameter(100, 200, 300);
		for(int i = 0; i <= 10; i++) {
			mainGui.addRuntime("ADDU" + String.valueOf(i));
		}
		mainGui.memoryPanel.bindDataMemory(circuit.dataMemory);
		mainGui.instructionQueuePanel.bindInstructionMemory(circuit.instructionMemory);
		mainGui.loadQueuePanel.bindLoadBuffer(circuit.loadBuffer);
		mainGui.integerPanel.bindRegularRegisterFile(circuit.regularRegisterFile);
		mainGui.floatPanel.bindFPRegisterFile(circuit.fpRegisterFile);
		mainGui.reservationStationPanel.bindReservationStation(
				circuit.addReservationStation, circuit.mulReservationStation);
	}

}
