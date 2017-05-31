package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.Timer;
import javax.swing.JFileChooser;

import dialog.MemoryDialog;
import logic.Circuit;
import dialog.FloatDialog;
import dialog.IntegerDialog;
import dialog.InstructionDialog;

import main.Tomasulo;

public class MainGui {
	private JFrame mainFrame;
	private JMenuBar mainMenuBar;
	private JToolBar mainToolBar;
	private JPanel mainPanel;
	
	private FloatPanel floatPanel;
	private InstructionQueuePanel instructionQueuePanel;
	private IntegerPanel integerPanel;
	private LSQueuePanel loadQueuePanel;
	private LSQueuePanel storeQueuePanel;
	public MemoryPanel memoryPanel;
	private ParameterPanel parameterPanel;
	private ReservationStationPanel reservationStationPanel;
	private RuntimePanel runtimePanel;
	protected Circuit circuit;
	protected Timer timer;

	public MainGui (Circuit circuit){
		this.circuit = circuit;
		mainFrame = new JFrame("Tomasulo Simulator");
		mainFrame.setSize(1150, 775);
		mainFrame.setResizable(false);
		mainFrame.setLayout(new BorderLayout(20, 10));
		mainFrame.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        System.exit(0);
		    }
		});
		
		mainMenuBar = new JMenuBar();
		mainFrame.setJMenuBar(mainMenuBar);
		
		JMenu fileMenu = new JMenu("文件");
		JMenu instructionMenu = new JMenu("输入指令");
		JMenu valueMenu = new JMenu("赋值");
		JMenu runMenu = new JMenu("运行");
		JMenu modeMenu = new JMenu("模式设置");
		JMenu helpMenu = new JMenu("帮助");
		
		JMenuItem integerMenuItem = new JMenuItem("修改整型寄存器");
		JMenuItem floatMenuItem = new JMenuItem("修改浮点寄存器");
		JMenuItem memoryMenuItem = new JMenuItem("修改内存");
		memoryMenuItem.addActionListener(new MemoryListener(this));
		integerMenuItem.addActionListener(new IntegerListener(this));
		floatMenuItem.addActionListener(new FloatListener(this));
		
		valueMenu.add(integerMenuItem);
		valueMenu.add(floatMenuItem);
		valueMenu.addSeparator();
		valueMenu.add(memoryMenuItem);
		
		JMenuItem instructionMenuItem = new JMenuItem("添加指令");
		instructionMenuItem.addActionListener(new InstructionListener(this));
		
		instructionMenu.add(instructionMenuItem);
		
		//mainMenuBar.add(fileMenu);
		mainMenuBar.add(instructionMenu);
		mainMenuBar.add(valueMenu);
		//mainMenuBar.add(runMenu);
		//mainMenuBar.add(modeMenu);
		//mainMenuBar.add(helpMenu);
		
		mainToolBar = new JToolBar();
		ImageIcon openIcon = new ImageIcon("icon/external-link-symbol.png");
		ImageIcon nextIcon = new ImageIcon("icon/right-chevron.png");
		ImageIcon runIcon = new ImageIcon("icon/double-angle-pointing-to-right.png");
		ImageIcon stopIcon = new ImageIcon("icon/pause-symbol.png");
		ImageIcon resetIcon = new ImageIcon("icon/remove-symbol.png");
		JButton openButton = new JButton(openIcon);
		openButton.addActionListener(new OpenFileListener());
		JButton nextButton = new JButton(nextIcon);
		nextButton.addActionListener(new nextListener());
		JButton runButton = new JButton(runIcon);
		runButton.addActionListener(new runListener());
		JButton stopButton = new JButton(stopIcon);
		stopButton.addActionListener(new stopListener());
		JButton resetButton = new JButton(resetIcon);
		resetButton.addActionListener(new resetListener());
		mainToolBar.add(openButton);
		mainToolBar.addSeparator();
		mainToolBar.add(nextButton);
		mainToolBar.add(runButton);
		mainToolBar.addSeparator();
		mainToolBar.add(stopButton);
		mainToolBar.add(resetButton);
		
		
		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		
		JPanel abovePanel = new JPanel();
		abovePanel.setLayout(new BoxLayout(abovePanel, BoxLayout.X_AXIS));
		instructionQueuePanel = new InstructionQueuePanel();
		runtimePanel = new RuntimePanel();
		abovePanel.add(Box.createHorizontalStrut(40));
		abovePanel.add(instructionQueuePanel);
		abovePanel.add(Box.createHorizontalStrut(60));
		abovePanel.add(runtimePanel);
		abovePanel.add(Box.createHorizontalStrut(60));
		JPanel tempPanel1 = new JPanel();
		tempPanel1.setLayout(new BoxLayout(tempPanel1, BoxLayout.Y_AXIS));
		loadQueuePanel = new LSQueuePanel(true);
		storeQueuePanel = new LSQueuePanel(false);
		tempPanel1.add(loadQueuePanel);
		tempPanel1.add(storeQueuePanel);
		abovePanel.add(tempPanel1);
		abovePanel.add(Box.createHorizontalStrut(40));
		
		JPanel middlePanel = new JPanel();
		middlePanel.setLayout(new FlowLayout());
		memoryPanel = new MemoryPanel();
		middlePanel.add(Box.createHorizontalStrut(80));
		middlePanel.add(memoryPanel);
		middlePanel.add(Box.createHorizontalStrut(100));
		JPanel tempPanel2 = new JPanel();
		tempPanel2.setLayout(new BorderLayout());
		reservationStationPanel = new ReservationStationPanel();
		parameterPanel = new ParameterPanel();
		tempPanel2.add("Center", reservationStationPanel);
		tempPanel2.add("South", parameterPanel);
		middlePanel.add(tempPanel2);
		middlePanel.add(Box.createHorizontalStrut(80));
		
		JPanel belowPanel = new JPanel();
		belowPanel.setLayout(new BoxLayout(belowPanel, BoxLayout.Y_AXIS));
		floatPanel = new FloatPanel();
		integerPanel = new IntegerPanel();
		belowPanel.add(floatPanel);
		belowPanel.add(Box.createVerticalStrut(5));
		belowPanel.add(integerPanel);
		
		mainPanel.add(abovePanel);
		mainPanel.add(Box.createVerticalStrut(5));
		mainPanel.add(middlePanel);
		mainPanel.add(Box.createVerticalStrut(5));
		mainPanel.add(belowPanel);
		mainPanel.add(Box.createVerticalStrut(5));
		
		mainFrame.getContentPane().add("North", mainToolBar);
		mainFrame.getContentPane().add("Center", mainPanel);
		mainFrame.setVisible(true);
	}
	
	//关闭GUI
	public void close(){
		mainFrame.setVisible(false);
	}
	//Float Register Panel
	//修改浮点寄存器值，参数为序号、表达式、值，序号从0开始编号(其它类似)
	public void modifyFloatRegister(int num, String expr, float value) {
		floatPanel.modifyFloatRegister(num, expr, value);
	}
	//Instruction Queue Panel
	public void addInstruction(String name, String Desti, String Sourcej, String Sourcek) {
		instructionQueuePanel.addInstruction(name, Desti, Sourcej, Sourcek);
	}
	//Integer Register Panel
	public void modifyIntegerRegister(int num, int value) {
		integerPanel.modifyIntegerRegister(num, value);
	}
	//Load Queue Panel
	public void addLoadQueuePanel(int num, boolean busy, int address, float value) {
		loadQueuePanel.addLSQueue(num, busy, address, value);
	}
	//Store Queue Panel
	public void addStoreQueuePanel(int num, boolean busy, int address, float value) {
		storeQueuePanel.addLSQueue(num, busy, address, value);
	}
	//Memory Panel
	public void modifyMemory(int address, float value) {
		memoryPanel.modifyMemory(address, value);
	}
	//ParameterPanel
	//修改参数面板，参数面板包括clock time，指令条数，PC值三个参数
	public void setParameter(int clockValue, int instructionValue, int pcValue) {
		parameterPanel.setParameter(clockValue, instructionValue, pcValue);
	}
	//ReservationStationPanel
	//添加一条保留区信息
	public void addReservation(int num, int time, boolean busy, String op, float valueJ, float valueK, int queryJ, int queryK) {
		reservationStationPanel.addReservation(num, time, busy, op, valueJ, valueK, queryJ, queryK);
	}
	//移除一条保留区信息
	public void removeReservation(int num) {
		reservationStationPanel.removeReservation(num);
	}
	//RuntimePanel
	//添加一条空的指令运行状态，参数为指令名称
	public void addRuntime(String instruction) {
		runtimePanel.addRuntime(instruction);
	}
	//修改一条指令运行状态
	public void modifyRuntime(int num, String instruction, boolean status, String writeBack) {
		runtimePanel.modifyRuntime(num, instruction, status, writeBack);
	}
	
	private class MemoryListener implements ActionListener {
		private MainGui mg;
		MemoryListener(MainGui mg){
			this.mg = mg;
		}
		public void actionPerformed(ActionEvent e) {
			MemoryDialog memoryDialog = new MemoryDialog(mg);
		}
	}
	
	private class IntegerListener implements ActionListener {
		private MainGui mg;
		IntegerListener(MainGui mg){
			this.mg = mg;
		}
		public void actionPerformed(ActionEvent e) {
			IntegerDialog integerDialog = new IntegerDialog(mg);
		}
	}
	
	private class FloatListener implements ActionListener {
		private MainGui mg;
		FloatListener(MainGui mg){
			this.mg = mg;
		}
		public void actionPerformed(ActionEvent e) {
			FloatDialog floatDialog = new FloatDialog(mg);
		}
	}
	
	private class InstructionListener implements ActionListener {
		private MainGui mg;
		InstructionListener(MainGui mg){
			this.mg = mg;
		}
		public void actionPerformed(ActionEvent e) {
			InstructionDialog instructionDialog = new InstructionDialog(mg);
		}
	}
	
	private class OpenFileListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JFileChooser jFileChooser = new JFileChooser();
			int res = jFileChooser.showOpenDialog(mainFrame);
			if (res == jFileChooser.APPROVE_OPTION) {
				File a = jFileChooser.getSelectedFile();
				//这个文件应该是存贮多条指令的文件，这里获得了该文件，需要后台填写进行处理
				System.out.println(a.getAbsolutePath());
			}
		}
	}
	
	private class nextListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			circuit.tick();
			MainGui.this.memoryPanel.updateFromLogic();
		}
	}
	
	private class runListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			timer = new Timer(500, new nextListener());
			timer.start();
		}
	}
	
	private class stopListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			timer.stop();
			timer = null;
		}
	}
	
	private class resetListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			//tomasulo.reset();
		}
	}
	
	public void getMemoryDialog(int address, float value){
		// 需要后台填写 替代下方的输出测试代码  根据这里获得的address和value修改memory,数值未经过检查
		System.out.println("Memory");
		System.out.println(String.valueOf(address));
		System.out.println(String.valueOf(value));
	}
	
	public void getFloatDialog(int num, float value){
		// 需要后台填写  同上
		System.out.println("Dialog");
		System.out.println(String.valueOf(num));
		System.out.println(String.valueOf(value));
	}
	
	public void getIntegerDialog(int num, int value){
		// 需要后台填写  同上
		System.out.println("Integer");
		System.out.println(String.valueOf(num));
		System.out.println(String.valueOf(value));
	}
	
	public void getInstructionDialog(String ins) {
		// 需要后台填写  同上
		System.out.println("Instruction");
		System.out.println(ins);;
	}
	
	public JFrame getMainFrame() {
		return mainFrame;
	}
}
