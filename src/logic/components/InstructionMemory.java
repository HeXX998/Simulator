package logic.components;

import logic.Component;
import logic.TomasuloCircuit;

public class InstructionMemory extends Component {
	
	public enum ExecStatus {
		WAITING,
		RUNNING,
		DONE,
	}
	
	public abstract class Instruction {
		public Operation operation;
		public ExecStatus execStatus;
		Instruction(Operation op) {
			this.operation = op;
			this.execStatus = ExecStatus.WAITING;
		}
	}
	
	public class FPUInstruction extends Instruction {
		public int destination;
		public int source1;
		public int source2;
		public FPUInstruction(Operation op, int destination, int source1, int source2)
		{
			super(op);
			this.destination = destination;
			this.source1 = source1;
			this.source2 = source2;
		}
	}
	
	public class LoadStoreInstruction extends Instruction {
		public int dataRegister;
		public int baseRegister;
		public int offset;
		public LoadStoreInstruction(Operation op, int dataRegister, int baseRegister, int offset) {
			super(op);
			this.dataRegister = dataRegister;
			this.baseRegister = baseRegister;
			this.offset = offset;
		}
	}
	
	protected int size;
	protected Instruction[] instructions;
	
	public Instruction getNextInstruction() {
		for(int i = 0; i < size; i++) {
			if(instructions[i] != null &&
			   instructions[i].execStatus == ExecStatus.WAITING)
			{
				return instructions[i];
			}
		}
		return null;
	}
	
	public void reportInstructionRunning(Instruction instruction) {
		instruction.execStatus = ExecStatus.RUNNING;
	}
	
	public InstructionMemory(TomasuloCircuit circuit, int size)
	{
		super(circuit);
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
