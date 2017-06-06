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
import logic.components.InstructionMemory;
import logic.components.InstructionMemory.FPUInstruction;
import logic.components.InstructionMemory.Instruction;
import logic.components.InstructionMemory.LoadStoreInstruction;
import logic.components.Operation;

public class InstructionQueuePanel extends JPanel{
	private JLabel label;
	private JTable table;
	private DefaultTableModel tableModel;
	private JScrollPane scroller;
	protected InstructionMemory instructionMemory;
	
	InstructionQueuePanel(){
		label = new JLabel("÷∏¡Ó¡–±Ì", SwingConstants.CENTER);
		label.setFont(new Font(Font.DIALOG, Font.BOLD, 16));
		
		String[] colName = {"Address", "OpCode", "Desti", "Sourcej", "Sourcek"};
		tableModel = new DefaultTableModel(null, colName);
		table = new JTable(tableModel);
		table.setRowHeight(25);
		scroller = new JScrollPane(table);
        scroller.setPreferredSize(new Dimension(300, 200));
        
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(label);
        add(scroller);
	}
	
	public void updateFromLogic() {
		int memorySize = instructionMemory.getSize();
		if(memorySize != tableModel.getRowCount()) {
			tableModel.setRowCount(memorySize);
			for(int i = 0; i < memorySize; i++)
			{
				tableModel.setValueAt(i, i, 0);
				label.setText("Instruction Queue (" + memorySize + " instructions)");
			}
		}
		for(int i = 0; i < memorySize; i++)
		{
			Instruction instruction = instructionMemory.getInstruction(i);
			if(instruction == null)
			{
				tableModel.setValueAt("NOP", i, 1);
				for(int j = 2; j <= 4; j++) {
					tableModel.setValueAt("", i, j);
				}
				continue;
			}
			tableModel.setValueAt("" + Operation.OperationAbbr(instruction.operation), i, 1);
			if(instruction.operation != Operation.LOAD &&
					instruction.operation != Operation.STORE) {
				FPUInstruction fpInst = (FPUInstruction)instruction;
				tableModel.setValueAt("F" + fpInst.destination, i, 2);
				tableModel.setValueAt("F" + fpInst.source1, i, 3);
				tableModel.setValueAt("F" + fpInst.source2, i, 4);
			}
			else {
				LoadStoreInstruction lsInst = (LoadStoreInstruction)instruction;
				tableModel.setValueAt("F" + lsInst.dataRegister, i, 2);
				tableModel.setValueAt("" + lsInst.offset, i, 3);
				tableModel.setValueAt("R" + lsInst.baseRegister, i, 4);
			}
			//tableModel.setValueAt(instructionMemory.getData(i), i, 1);
		}
	}
	
	public void writeToLogic() {
		
	}
	
	public void bindInstructionMemory(InstructionMemory instructionMemory) {
		this.instructionMemory = instructionMemory;
		updateFromLogic();
	}
	
	void addInstruction(String name, String Desti, String Sourcej, String Sourcek) {
		tableModel.addRow(new String[]{name, Desti, Sourcej, Sourcek});
	}
}
