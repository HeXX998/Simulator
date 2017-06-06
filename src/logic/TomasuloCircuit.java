package logic;

import java.util.HashMap;
import java.util.Iterator;

import logic.components.DataMemory;
import logic.components.FPAddReservationStation;
import logic.components.FPMulReservationStation;
import logic.components.FPRegisterFile;
import logic.components.InstructionMemory;
import logic.components.InstructionMemory.Instruction;
import logic.components.InstructionMemory.LoadStoreInstruction;
import logic.components.LoadBuffer;
import logic.components.Operation;
import logic.components.RegularRegisterFile;
import logic.components.StoreBuffer;

public class TomasuloCircuit {
	class Pin
	{
		public Object value;
	}

	HashMap<String, Component> components;
	HashMap<String, Pin> pins;
	
	public LoadBuffer loadBuffer;
	public StoreBuffer storeBuffer;
	public FPAddReservationStation addReservationStation;
	public FPMulReservationStation mulReservationStation;
	public DataMemory dataMemory;
	public InstructionMemory instructionMemory;
	public FPRegisterFile fpRegisterFile;
	public RegularRegisterFile regularRegisterFile;

	public TomasuloCircuit()
	{
		dataMemory = new DataMemory(this, 256);
		loadBuffer = new LoadBuffer(this, 3);
		storeBuffer = new StoreBuffer(this, 3);
		addReservationStation = new FPAddReservationStation(this, 3);
		mulReservationStation = new FPMulReservationStation(this, 2);
		dataMemory = new DataMemory(this, 256);
		instructionMemory = new InstructionMemory(this, 64);
		fpRegisterFile = new FPRegisterFile(this, 16);
		regularRegisterFile = new RegularRegisterFile(this, 16);
		for(int i = 0; i <regularRegisterFile.getSize(); i++){
			regularRegisterFile.setData(i, i);
		}
		instructionMemory.setInstruction(
				0, instructionMemory.new LoadStoreInstruction(Operation.LOAD, 6, 2, 34));
		instructionMemory.setInstruction(
				1, instructionMemory.new LoadStoreInstruction(Operation.LOAD, 2, 3, 45));
		instructionMemory.setInstruction(
				2, instructionMemory.new FPUInstruction(Operation.MULTIPLICATION, 0, 2, 4));
		instructionMemory.setInstruction(
				3, instructionMemory.new FPUInstruction(Operation.SUBSTRACT, 8, 6, 2));
		instructionMemory.setInstruction(
				4, instructionMemory.new FPUInstruction(Operation.DIVISION, 10, 0, 6));
		instructionMemory.setInstruction(
				5, instructionMemory.new FPUInstruction(Operation.ADD, 6, 8, 2));
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
		Instruction instruction = instructionMemory.getNextInstruction();
		if(instruction == null)
		{
			//TODO: Stop execution
			return;
		}
		switch(instruction.operation)
		{
		case ADD:
		case SUBSTRACT:
			break;
		case DIVISION:
		case MULTIPLICATION:
			break;
		case LOAD:
			if(loadBuffer.addInstruction((LoadStoreInstruction)instruction)) {
				instructionMemory.reportInstructionRunning(instruction);
			}
			break;
		case STORE:
			break;
		default:
			break;
		
		}
	}
	
	public void addPin(String pinName)
	{
		//TODO: Add a pin to the circuit.
	}
}
