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

public class FloatPanel extends JPanel {
	private JLabel label;
	private JTable table;
	private DefaultTableModel tableModel;
	
	FloatPanel() {
		JLabel label = new JLabel("浮点寄存器FU", SwingConstants.CENTER);
        label.setFont(new Font(Font.DIALOG, Font.BOLD, 16));
        
        JLabel label1 = new JLabel("寄存器号");
        JLabel label2 = new JLabel("表达式");
        JLabel label3 = new JLabel("数据");
        
        String[] colName = {"F0", "F1", "F2", "F3", "F4", "F5", "F6", "F7", "F8", "F9", "F10"};
        String[][] rowData = {
        		{"", "", "", "", "", "", "", "", "", "", ""},
        		{"", "", "", "", "", "", "", "", "", "", ""},
        };
        
        tableModel = new DefaultTableModel(rowData, colName);
        table = new JTable(tableModel);
        table.setRowHeight(25);
        JScrollPane scroller = new JScrollPane(table);
        scroller.setPreferredSize(new Dimension(900, 75));
        
        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.Y_AXIS));
        labelPanel.add(label1);
        labelPanel.add(Box.createVerticalStrut(5));
        labelPanel.add(label2);
        labelPanel.add(Box.createVerticalStrut(5));
        labelPanel.add(label3);
        
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
	
	void modifyFloatRegister(int num, String expr, float value){
		tableModel.setValueAt(expr, 0, num);
		tableModel.setValueAt(String.valueOf(value), 1, num);
	}
}
