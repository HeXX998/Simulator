package logic;

public abstract class ReservationStation extends Component {
	public class Entry {
		ReservationStation rs;
		int index;
	}
	public abstract class EntryData {
		
	}
	
	protected int size;
	public ReservationStation(int size) {
		this.size = size;
	}
	
	public int getSize() {
		return size;
	}
}
