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
import logic.components.LoadBuffer;
import logic.components.Operation;
import logic.components.InstructionMemory.FPUInstruction;
import logic.components.InstructionMemory.Instruction;
import logic.components.InstructionMemory.LoadStoreInstruction;

public class LSQueuePanel extends JPanel{
	private JLabel label;
	private JTable table;
	private DefaultTableModel tableModel;
	private JScrollPane scroller;
	public boolean load;
	LoadBuffer loadBuffer;
	
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
		
		String[] colName = {"", "Busy", "BaseAddress", "Offset"};
		tableModel = new DefaultTableModel(null, colName);
		table = new JTable(tableModel);
		table.setRowHeight(25);
		scroller = new JScrollPane(table);
        scroller.setPreferredSize(new Dimension(300, 100));
        
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(label);
        add(scroller);
	}
	
	public void bindLoadBuffer(LoadBuffer loadBuffer)
	{
		this.loadBuffer = loadBuffer;
		updateFromLogic();
	}
	
	public void updateFromLogic() {
		int loadQueueSize = loadBuffer.getSize();
		if(loadQueueSize != tableModel.getRowCount()) {
			tableModel.setRowCount(loadQueueSize);
			for(int i = 0; i < loadQueueSize; i++)
			{
				tableModel.setValueAt("LOAD" + i, i, 0);
				label.setText("Load Queue (" + loadQueueSize + " LD instructions)");
			}
		}
		for(int i = 0; i < loadQueueSize; i++)
		{
			LoadBuffer.EntryData entry = loadBuffer.entries[i];
			if(entry == null)
			{
				tableModel.setValueAt("No", i, 1);
				for(int j = 2; j <= 3; j++) {
					tableModel.setValueAt("", i, j);
				}
				continue;
			}
			tableModel.setValueAt("Yes", i, 1);
			tableModel.setValueAt(entry.baseAddress, i, 2);
			tableModel.setValueAt(entry.offset, i, 3);
		}
	}

	void addLSQueue(int num, boolean busy, int address, float value) {
		/*if(busy == true) {
			tableModel.setValueAt("Yes", num, 1);
		}
		else {
			tableModel.setValueAt("No", num, 1);
		}
		
		tableModel.setValueAt(String.valueOf(address), num, 2);
		tableModel.setValueAt(String.valueOf(value), num, 3);*/
	}
}
