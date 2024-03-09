package com.sokoban.interfaces;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.sokoban.helper.Custom;

public class BienvenuePane
{
	public BienvenuePane() 
	{

		final JFrame form = Custom.newFrame( 1000, 550 );
		JPanel panneau = new JPanel(null){
		
			@Override
			public void paintComponent(Graphics g) 
			{
			    super.paintComponent(g);
			    try {
					g.drawImage(ImageIO.read(new File("./icones/background.jpg")), 0, 0, getWidth(), getHeight(), this);
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
			}

		};
		JButton jouerBtn = Custom.newButton("Jouer", new Color(0, 0, 160, 50), 100, 300, 140, 40);
		jouerBtn.setForeground(Color.white);
		JLabel titreLb =  Custom.newLabel("Sokoban Game ", Color.white, new Font("Calibri Light", Font.BOLD,24 ), 80, 100, 200, 40);
		JLabel DescLb = Custom.newLabel("C'est parti !", 
		Color.cyan, null, 120, 150, 320, 120);
		Custom.add(panneau, jouerBtn,  titreLb,  DescLb);

		jouerBtn.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				form.dispose();
				new JeuGame();
			}
		});
		
		
	
		form.add(panneau);
		form.setTitle("Jeu de Sokoban ");
		form.setVisible(true);
		
	}
}
