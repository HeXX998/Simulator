package gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class ParameterPanel extends JPanel{
	private JLabel clock;
	private JLabel instructionNum;
	private JLabel pcPointer;
	
	ParameterPanel() {
		setLayout(new FlowLayout());
		
		ImageIcon clockIcon = new ImageIcon("icon/clock.png");
		ImageIcon codeIcon = new ImageIcon("icon/code.png");
		ImageIcon pcIcon = new ImageIcon("icon/cursor.png");
		JLabel clockIconLabel = new JLabel(clockIcon);
		JLabel codeIconLabel = new JLabel(codeIcon);
		JLabel pcIconLabel = new JLabel(pcIcon);
		
		JLabel clockLabel = new JLabel("Clock: ");
		JLabel codeLabel = new JLabel("Ö¸ÁîÌõÊý: ");
		JLabel pcLabel = new JLabel("PC: ");
		clock = new JLabel("0");
		instructionNum = new JLabel("0");
		pcPointer = new JLabel("0");
		
		add(clockIconLabel);
		add(clockLabel);
		add(clock);
		add(Box.createHorizontalStrut(60));
		
		add(codeIconLabel);
		add(codeLabel);
		add(instructionNum);
		add(Box.createHorizontalStrut(60));
		
		add(pcIconLabel);
		add(pcLabel);
		add(pcPointer);
		add(Box.createHorizontalStrut(60));
	}
	
	void setParameter(int clockValue, int instructionValue, int pcValue) {
		clock.setText(String.valueOf(clockValue));
		instructionNum.setText(String.valueOf(instructionValue));
		pcPointer.setText(String.valueOf(pcValue));
	}
}
