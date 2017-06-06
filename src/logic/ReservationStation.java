package logic;

import logic.components.InstructionMemory.Instruction;

public abstract class ReservationStation extends Component {
	public abstract class Entry {
		public Instruction instruction;
		public ReservationStation rs;
		public int index;
		public Entry(Instruction instruction, ReservationStation rs, int index) {
			this.instruction = instruction;
			this.rs = rs;
			this.index = index;
		}
		
		public abstract boolean readyToExecute();
		
		@Override
		public String toString() {
			return rs.getName() + "[" + index + "]";
		}
	}
	
	public abstract String getName();
	
	protected int size;
	public ReservationStation(TomasuloCircuit circuit, int size) {
		super(circuit);
		this.size = size;
	}
	
	public int getSize() {
		return size;
	}
}
