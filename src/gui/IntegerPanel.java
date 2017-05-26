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

public class IntegerPanel extends JPanel {
	private JLabel label;
	private JTable table;
	private DefaultTableModel tableModel;
	
	IntegerPanel() {
		JLabel label = new JLabel("ÕûÐÎ¼Ä´æÆ÷RU", SwingConstants.CENTER);
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
	
	void modifyIntegerRegister(int num, int value) {
		tableModel.setValueAt(String.valueOf(value), 0, num);
	}
}
