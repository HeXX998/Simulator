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

class RuntimePanel extends JPanel{
	private JLabel label;
	private JTable table;
	private DefaultTableModel tableModel;
	private JScrollPane scroller;
	
	RuntimePanel(){
		label = new JLabel("运行状态", SwingConstants.CENTER);
		label.setFont(new Font(Font.DIALOG, Font.BOLD, 16));
		
		String[] colName = {"发射指令", "执行完毕", "写回结果"};
		tableModel = new DefaultTableModel(null, colName);
		table = new JTable(tableModel);
		table.setRowHeight(25);
		scroller = new JScrollPane(table);
        scroller.setPreferredSize(new Dimension(300, 200));
        
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(label);
        add(scroller);
	}
	
	void addRuntime(String instruction) {
		tableModel.addRow(new String[]{instruction, "No", ""});
	}
	
	void modifyRuntime(int num, String instruction, boolean status, String writeBack) {
		tableModel.setValueAt(instruction, num, 0);
		if(status == true) {
			tableModel.setValueAt("Yes", num, 1);
		}
		else {
			tableModel.setValueAt("No", num, 1);
		}
		tableModel.setValueAt(writeBack, num, 2);
	}
}
