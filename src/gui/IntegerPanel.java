package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import logic.components.LoadBuffer;
import logic.components.RegularRegisterFile;

public class IntegerPanel extends JPanel {
	private JLabel label;
	private JTable table;
	private DefaultTableModel tableModel;
	
	protected RegularRegisterFile regularRegisterFile;
	
	IntegerPanel() {
		label = new JLabel("ÕûÐÎ¼Ä´æÆ÷RU", SwingConstants.CENTER);
        label.setFont(new Font(Font.DIALOG, Font.BOLD, 16));
        
        JLabel label1 = new JLabel("¼Ä´æÆ÷ºÅ");
        JLabel label2 = new JLabel("Êý¾Ý");
        
        String[] colName = {"R0", "R1", "R2", "R3", "R4", "R5", "R6", "R7", "R8", "R9", "R10"};
        String[][] rowData = {
        		{"", "", "", "", "", "", "", "", "", "", ""},
        };
        
        tableModel = new DefaultTableModel(rowData, colName);
        table = new JTable(tableModel);
        table.setRowHeight(25);
        JScrollPane scroller = new JScrollPane(table);
        scroller.setPreferredSize(new Dimension(900, 50));
        
        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.Y_AXIS));
        labelPanel.add(label1);
        labelPanel.add(Box.createVerticalStrut(5));
        labelPanel.add(label2);
        
        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BoxLayout(tablePanel, BoxLayout.X_AXIS));
        tablePanel.add(Box.createHorizontalStrut(80));
        tablePanel.add(labelPanel);
        tablePanel.add(Box.createHorizontalStrut(5));
        tablePanel.add(scroller);
        tablePanel.add(Box.createHorizontalStrut(80));
        
        
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(label);
        add(tablePanel);
	}
	
	public void bindRegularRegisterFile(RegularRegisterFile regularRegisterFile)
	{
		this.regularRegisterFile = regularRegisterFile;
		updateFromLogic();
	}
	
	public void updateFromLogic() {
		int size = regularRegisterFile.getSize();
		if(size != tableModel.getColumnCount()) {
			label.setText("Regular registers (0 - " + (size - 1) + ")");
			tableModel.setColumnCount(size);
			String[] identifiers = new String[size];
			for(int i = 0; i < size; i++) {
				identifiers[i] = "R" + i;
			}
			tableModel.setColumnIdentifiers(identifiers);
		}
		for(int i = 0; i < size; i++)
		{
			tableModel.setValueAt(regularRegisterFile.getData(i), 0, i);
		}
	}
	
	
	void modifyIntegerRegister(int num, int value) {
		tableModel.setValueAt(String.valueOf(value), 0, num);
	}
}
