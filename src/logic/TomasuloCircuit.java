package logic;

import java.util.HashMap;
import java.util.Iterator;

import logic.components.DataMemory;
import logic.components.FPAddReservationStation;
import logic.components.FPMulReservationStation;
import logic.components.FPRegisterFile;
import logic.components.InstructionMemory;
import logic.components.InstructionMemory.FPUInstruction;
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

		for(int i = 0; i < regularRegisterFile.getSize(); i++){
			regularRegisterFile.setData(i, i);
		}
		for(int i = 0; i < dataMemory.getSize(); i++){
			dataMemory.setData(i, i * 0.1f + 0.1f);
		}
		for(int i = 0; i < fpRegisterFile.getSize(); i++){
			fpRegisterFile.setData(i, i * 0.1f + 0.1f);
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
			//TODO: Stop execution when all instructions are done
		}
		else
		{
			switch(instruction.operation)
			{
			case ADD:
			case SUBSTRACT:
				if(addReservationStation.addInstruction((FPUInstruction)instruction)) {
					instructionMemory.reportStatus(instruction, InstructionMemory.ExecStatus.QUEUED);
				}
				break;
			case DIVISION:
			case MULTIPLICATION:
				if(mulReservationStation.addInstruction((FPUInstruction)instruction)) {
					instructionMemory.reportStatus(instruction, InstructionMemory.ExecStatus.QUEUED);
				}
				break;
			case LOAD:
				if(loadBuffer.addInstruction((LoadStoreInstruction)instruction)) {
					instructionMemory.reportStatus(instruction, InstructionMemory.ExecStatus.QUEUED);
				}
				break;
			case STORE:
				break;
			default:
				break;
			}
		}

		loadBuffer.onClockTick();
		storeBuffer.onClockTick();
		addReservationStation.onClockTick();
		mulReservationStation.onClockTick();
	}
	
	public void sendBroadcast(ReservationStation.Entry entry, float result)
	{
		loadBuffer.onBroadcast(entry, result);
		storeBuffer.onBroadcast(entry, result);
		addReservationStation.onBroadcast(entry, result);
		mulReservationStation.onBroadcast(entry, result);
		fpRegisterFile.onBroadcast(entry, result);
	}
	
	public void addPin(String pinName)
	{
		//TODO: Add a pin to the circuit.
	}
}
