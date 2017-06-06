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
		
		tableModel = new DefaultTableModel(null, colName);
		table = new JTable(tableModel);
		table.setRowHeight(25);
		scroller = new JScrollPane(table);
        scroller.setPreferredSize(new Dimension(600, 150));
        
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(label);
        add(scroller);
	}
	
	public void updateFromLogic() {
		int addRsSize = addReservationStation.getSize();
		int mulRsSize = mulReservationStation.getSize();
		int totalSize = addRsSize + mulRsSize;
		if(totalSize != tableModel.getRowCount()) {
			tableModel.setRowCount(totalSize);
			for(int i = 0; i < addRsSize; i++)
			{
				tableModel.setValueAt("RsAdd" + i, i, 1);
			}
			for(int i = 0; i < mulRsSize; i++)
			{
				tableModel.setValueAt("RsMul" + i, i + addRsSize, 1);
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
