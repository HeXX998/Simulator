package dialog;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import gui.MainGui;

public class MemoryDialog {
	private JFrame memory;
	
	private JTextField address;
	private JTextField value;
	
	private MainGui mainGui;
	
	public MemoryDialog(MainGui mainGui) {
		this.mainGui = mainGui;
		
		memory = new JFrame("Memory Settings");
		memory.setSize(300, 200);
		memory.setLocationRelativeTo(mainGui.getMainFrame());
		memory.setResizable(false);
		memory.setLayout(new BoxLayout(memory.getContentPane(), BoxLayout.Y_AXIS));
		
		JLabel addressLabel = new JLabel("address");
		JLabel valueLabel = new JLabel("value  ");
		
		address = new JTextField("0", 8);
		value = new JTextField("0", 8);
		
		JPanel addressPanel = new JPanel();
		addressPanel.setLayout(new FlowLayout());
		addressPanel.add(addressLabel);
		addressPanel.add(Box.createHorizontalStrut(5));
		addressPanel.add(address);
		
		JPanel valuePanel = new JPanel();
		valuePanel.setLayout(new FlowLayout());
		valuePanel.add(valueLabel);
		valuePanel.add(Box.createHorizontalStrut(5));
		valuePanel.add(value);
		
		JButton confirmButton = new JButton("确认");
		confirmButton.addActionListener(new ConfirmListener());
		JButton cancelButton = new JButton("取消");
		cancelButton.addActionListener(new CancelListener());
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		buttonPanel.add(confirmButton);
		buttonPanel.add(Box.createHorizontalStrut(5));
		buttonPanel.add(cancelButton);
		
		memory.getContentPane().add(Box.createVerticalStrut(20));
		memory.getContentPane().add(addressPanel);
		memory.getContentPane().add(valuePanel);
		memory.getContentPane().add(Box.createVerticalStrut(5));
		memory.getContentPane().add(buttonPanel);
		
		memory.setVisible(true);
	}
	
	private class ConfirmListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			mainGui.getMemoryDialog(Integer.parseInt(address.getText()), Float.parseFloat(value.getText()));
			memory.setVisible(false);
		}
	}
	
	private class CancelListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			memory.setVisible(false);
		}
	}
}
