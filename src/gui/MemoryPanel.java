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

public class MemoryPanel extends JPanel{
	private JLabel label;
	private JTable table;
	private DefaultTableModel tableModel;
	private JScrollPane scroller;
	
	MemoryPanel(){
		label = new JLabel("内存 MEM", SwingConstants.CENTER);
		label.setFont(new Font(Font.DIALOG, Font.BOLD, 16));
		
		String[] colName = {"地址", "数据"};
		tableModel = new DefaultTableModel(null, colName);
		table = new JTable(tableModel);
		table.setRowHeight(25);
		scroller = new JScrollPane(table);
        scroller.setPreferredSize(new Dimension(200, 200));
        
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(label);
        add(scroller);
	}
	
	void modifyMemory(int address, float value) {
		for(int i = 0; i <= tableModel.getRowCount() - 1; i++) {
			int rowNum = Integer.parseInt((String)tableModel.getValueAt(i, 0));
			if(rowNum == address) {
				tableModel.removeRow(i);
			}
		}
		
		tableModel.addRow(new String[]{String.valueOf(address), String.valueOf(value)});
	}
}
