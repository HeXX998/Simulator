package logic;

public abstract class ReservationStation extends Component {
	public class Entry {
		public ReservationStation rs;
		public int index;
		public Entry(ReservationStation rs, int index) {
			this.rs = rs;
			this.index = index;
		}
	}
	public abstract class EntryData {
		
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
