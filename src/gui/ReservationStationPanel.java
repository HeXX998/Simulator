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

public class ReservationStationPanel extends JPanel{
	private JLabel label;
	private JTable table;
	private DefaultTableModel tableModel;
	private JScrollPane scroller;
	
	ReservationStationPanel(){
		label = new JLabel("Reservation Station", SwingConstants.CENTER);
		label.setFont(new Font(Font.DIALOG, Font.BOLD, 16));
		
		String[] colName = {"Time", "Name", "Busy", "Op", "Vj", "Vk", "Qj", "Qk"};
		String[][] rowData = {
				{"", "ADD1", "No", "", "", "", "", "", ""},
				{"", "ADD2", "No", "", "", "", "", "", ""},
				{"", "ADD3", "No", "", "", "", "", "", ""},
				{"", "Mult1", "No", "", "", "", "", "", ""},
				{"", "Mult2", "No", "", "", "", "", "", ""}
		};
		
		tableModel = new DefaultTableModel(rowData, colName);
		table = new JTable(tableModel);
		table.setRowHeight(25);
		scroller = new JScrollPane(table);
        scroller.setPreferredSize(new Dimension(600, 150));
        
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(label);
        add(scroller);
	}
	
	void addReservation(int num, int time, boolean busy, String op, float valueJ, float valueK, int queryJ, int queryK) {
		tableModel.setValueAt(String.valueOf(time), num, 0);
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
		tableModel.setValueAt(String.valueOf(queryK), num, 7);
	}
	
	void removeReservation(int num) {
		tableModel.setValueAt("", num, 0);
		tableModel.setValueAt("No", num, 2);
		for(int i = 3; i <= 7; i++) {
			tableModel.setValueAt("", num, i);
		}
	}
}
