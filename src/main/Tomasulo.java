package main;

import gui.MainGui;

public class Tomasulo {
	private MainGui mainGui;
	
	public Tomasulo() {
		mainGui = new MainGui(this);
	}
	
	public void reset() {
		mainGui.close();
		mainGui = new MainGui(this);
	}
	
	public static void main(String[] args) {
		Tomasulo tomasulo = new Tomasulo();
	}

}
