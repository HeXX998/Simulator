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

class LSQueuePanel extends JPanel{
	private JLabel label;
	private JTable table;
	private DefaultTableModel tableModel;
	private JScrollPane scroller;
	public boolean load;
	
	LSQueuePanel(boolean load) {
		String a = null;
		this.load = load;
		if(load == true){
			a = "Load";
		}
		else {
			a = "Store";
		}
		
		label = new JLabel(a + " Queue", SwingConstants.CENTER);
		label.setFont(new Font(Font.DIALOG, Font.BOLD, 16));
		
		String[] colName = {"", "Busy", "Address", "Cache"};
		String[][] rowData = {
				{a + "1", "No", "", ""},
				{a + "2", "No", "", ""},
				{a + "3", "No", "", ""}
		};
		tableModel = new DefaultTableModel(rowData, colName);
		table = new JTable(tableModel);
		table.setRowHeight(25);
		scroller = new JScrollPane(table);
        scroller.setPreferredSize(new Dimension(300, 100));
        
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(label);
        add(scroller);
	}
	
	void addLSQueue(int num, boolean busy, int address, float value) {
		if(busy == true) {
			tableModel.setValueAt("Yes", num, 1);
		}
		else {
			tableModel.setValueAt("No", num, 1);
		}
		
		tableModel.setValueAt(String.valueOf(address), num, 2);
		tableModel.setValueAt(String.valueOf(value), num, 3);
	}
}
