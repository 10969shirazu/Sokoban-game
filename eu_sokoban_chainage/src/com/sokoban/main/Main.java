package com.sokoban.main;


import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import com.sokoban.interfaces.BienvenuePane;

public class Main {
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel( new NimbusLookAndFeel() );
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		new BienvenuePane();
	}
}
