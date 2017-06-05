package logic.components;

import logic.Component;

public class InstructionMemory extends Component {
	
	public abstract class Instruction {
		public Operation operation;
	}
	
	public class FPUInstruction {
		public int source1;
		public int source2;
		public int destination;
	}
	
	public class LoadStoreInstruction {
		public int baseRegister;
		public int offset;
		public int dataRegister;
	}
	
	protected int size;
	protected Instruction[] instructions;
	
	public InstructionMemory(int size)
	{
		this.size = size;
		this.instructions = new Instruction[size];
	}
	
	public void setInstruction(int address, Instruction inst)
	{
		instructions[address] = inst;
	}
	
	public Instruction getInstruction(int address)
	{
		return instructions[address];
	}
	
	public String[] inputPins()
	{
		return new String[] {"data", "address", "is_write"};
	}
	
	public String[] outputPins()
	{
		return new String[] {"value"};
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
