package gui;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import logic.components.DataMemory;
import logic.components.FPAddReservationStation;
import logic.components.FPMulReservationStation;
import logic.components.LoadBuffer;
import logic.components.Operation;

public class ReservationStationPanel extends JPanel{
	private JLabel label;
	private JTable table;
	private DefaultTableModel tableModel;
	private JScrollPane scroller;
	
	protected FPAddReservationStation addReservationStation;
	protected FPMulReservationStation mulReservationStation;
	
	ReservationStationPanel(){
		label = new JLabel("Reservation Station", SwingConstants.CENTER);
		label.setFont(new Font(Font.DIALOG, Font.BOLD, 16));
		
		String[] colName = {"Time", "Name", "Busy", "Op", "Vj", "Vk", "Qj", "Qk"};
		/*String[][] rowData = {
				{"", "ADD1", "No", "", "", "", "", "", ""},
				{"", "ADD2", "No", "", "", "", "", "", ""},
				{"", "ADD3", "No", "", "", "", "", "", ""},
				{"", "Mult1", "No", "", "", "", "", "", ""},
				{"", "Mult2", "No", "", "", "", "", "", ""}
		};*/
		
		tableModel = new DefaultTableModel(null, colName);
		table = new JTable(tableModel);
		table.setRowHeight(25);
		scroller = new JScrollPane(table);
        scroller.setPreferredSize(new Dimension(600, 150));
        
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(label);
        add(scroller);
	}
	
	void addReservation(int num, int time, boolean busy, String op, float valueJ, float valueK, int queryJ, int queryK) {
		/*tableModel.setValueAt(String.valueOf(time), num, 0);
		if(busy == true) {
			tableModel.setValueAt("Yes", num, 2);
		}
		else {
			tableModel.setValueAt("No", num, 2);
		}
		
		tableModel.setValueAt(op, num, 3);
		tableModel.setValueAt(String.valueOf(valueJ), num, 4);
		tableModel.setValueAt(String.valueOf(valueK), num, 5);
		tableModel.setValueAt(String.valueOf(queryJ), num, 6);
		tableModel.setValueAt(String.valueOf(queryK), num, 7);*/
	}
	
	void removeReservation(int num) {
		/*tableModel.setValueAt("", num, 0);
		tableModel.setValueAt("No", num, 2);
		for(int i = 3; i <= 7; i++) {
			tableModel.setValueAt("", num, i);
		}*/
	}
	
	public void updateFromLogic() {
		int addRsSize = addReservationStation.getSize();
		int mulRsSize = mulReservationStation.getSize();
		int totalSize = addRsSize + mulRsSize;
		if(totalSize != tableModel.getRowCount()) {
			tableModel.setRowCount(totalSize);
			for(int i = 0; i < addRsSize; i++)
			{
				tableModel.setValueAt("RsAdd" + (i + 1), i, 1);
			}
			for(int i = 0; i < mulRsSize; i++)
			{
				tableModel.setValueAt("RsMul" + (i + 1), i + addRsSize, 1);
			}
		}
		for(int i = 0; i < addRsSize; i++)
		{
			FPAddReservationStation.EntryData entry = addReservationStation.entries[i];
			for(int j = 2; j <= 7; j++) {
				tableModel.setValueAt("", i, j);
			}
			if(entry == null)
			{
				tableModel.setValueAt("No", i, 2);
				continue;
			}
			tableModel.setValueAt("Yes", i, 2);
			tableModel.setValueAt(Operation.OperationAbbr(entry.operation), i, 3);
			if(entry.value1.isAvailable)
				tableModel.setValueAt(entry.value1.value, i, 4);
			else
				tableModel.setValueAt(entry.value1.rsEntry, i, 6);
			if(entry.value2.isAvailable)
				tableModel.setValueAt(entry.value2.value, i, 5);
			else
				tableModel.setValueAt(entry.value2.rsEntry, i, 7);
		}
		for(int i = addRsSize; i < addRsSize + mulRsSize; i++)
		{
			FPMulReservationStation.EntryData entry = mulReservationStation.entries[i - addRsSize];
			for(int j = 2; j <= 7; j++) {
				tableModel.setValueAt("", i, j);
			}
			if(entry == null)
			{
				tableModel.setValueAt("No", i, 2);
				continue;
			}
			tableModel.setValueAt("Yes", i, 2);
			tableModel.setValueAt(Operation.OperationAbbr(entry.operation), i, 3);
			if(entry.value1.isAvailable)
				tableModel.setValueAt(entry.value1.value, i, 4);
			else
				tableModel.setValueAt(entry.value1.rsEntry, i, 6);
			if(entry.value2.isAvailable)
				tableModel.setValueAt(entry.value2.value, i, 5);
			else
				tableModel.setValueAt(entry.value2.rsEntry, i, 7);
		}
	}
	
	public void writeToLogic() {
		
	}
	
	public void bindReservationStation(FPAddReservationStation addRs, FPMulReservationStation mulRs)
	{
		this.addReservationStation = addRs;
		this.mulReservationStation = mulRs;
		updateFromLogic();
	}
	

}
