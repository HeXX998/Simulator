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
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import logic.ReservationStation.Entry;
import logic.components.FPRegisterFile;
import logic.components.RegularRegisterFile;

public class FloatPanel extends JPanel {
	private JLabel label;
	private JTable table;
	private DefaultTableModel tableModel;
	public FPRegisterFile fpRegisterFile;
	
	FloatPanel() {
		label = new JLabel("浮点寄存器FU", SwingConstants.CENTER);
        label.setFont(new Font(Font.DIALOG, Font.BOLD, 16));
        
        JLabel label1 = new JLabel("寄存器号");
        JLabel label2 = new JLabel("表达式");
        JLabel label3 = new JLabel("数据");
        
        String[] colName = {"F0", "F1", "F2", "F3", "F4", "F5", "F6", "F7", "F8", "F9", "F10"};

        tableModel = new DefaultTableModel(null, colName);
        tableModel.setRowCount(2);
        table = new JTable(tableModel);
        table.setRowHeight(25);
        JScrollPane scroller = new JScrollPane(table);
        scroller.setPreferredSize(new Dimension(900, 75 + 1));

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
	
	public void bindFPRegisterFile(FPRegisterFile fpRegisterFile)
	{
		this.fpRegisterFile = fpRegisterFile;
		updateFromLogic();
	}
	
	public void updateFromLogic() {
		int size = fpRegisterFile.getSize();
		if(size != tableModel.getColumnCount()) {
			label.setText("Float registers (0 - " + (size - 1) + ")");
			tableModel.setColumnCount(size);
			String[] identifiers = new String[size];
			for(int i = 0; i < size; i++) {
				identifiers[i] = "F" + i;
			}
			tableModel.setColumnIdentifiers(identifiers);
		}
		for(int i = 0; i < size; i++)
		{
			tableModel.setValueAt(fpRegisterFile.getData(i), 1, i);
			if(fpRegisterFile.getUpdatedValue(i) != null)
			{
				Entry updatedValue = fpRegisterFile.getUpdatedValue(i);
				tableModel.setValueAt(
						updatedValue.rs.getName() + "[" + updatedValue.index + "]", 0, i);
			}
			else
			{
				tableModel.setValueAt("", 0, i);
			}
		}
	}
}
