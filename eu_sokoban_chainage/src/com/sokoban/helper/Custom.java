package com.sokoban.helper;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.LayoutManager;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Custom {
	
	public static JFrame newFrame( int... dim ) {
		JFrame fr = new JFrame();
		fr.setSize( dim[0],dim[1] );
		fr.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		fr.setLocationRelativeTo( null );
		return fr;
	}
	
	public static JPanel newPanel( LayoutManager lm,Color c, int... dim ) {
		JPanel pn = new JPanel();
		pn.setLayout(lm);
		pn.setBounds( dim[0],dim[1],dim[2],dim[3] );
		pn.setBackground( c );
		return pn;
	}

	public static JLabel newLabel( String text, Color c, Font f, int... dim ) {
		JLabel lb = new JLabel(text);
		lb.setBounds( dim[0],dim[1],dim[2],dim[3] );
		lb.setForeground( c );
		lb.setFont( f );
		return lb;
	}
	
	public static JButton newButton( String text, Color c, int... dim ) {
		JButton btn = new JButton(text);
		btn.setBounds( dim[0],dim[1],dim[2],dim[3] );
		btn.setBackground(c);
		return btn;
	}
	
	
	public static JComboBox<String> newComboBox( String[] args, int... dim  ) {
		JComboBox<String> cb = new JComboBox<>();
		cb.setBounds( dim[0],dim[1],dim[2],dim[3] );
		for(String st:args)
			cb.addItem(st);
		return cb;
	}
	
	public static void add(Container ct, Component... cps) {
		for(Component cp:cps)
			ct.add(cp);
	}
		
}

