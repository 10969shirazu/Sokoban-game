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

       
public class APropos  extends JPanel{
	


	public void paintComponent (Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		g.setColor(Color.WHITE);
		Font font = new Font("Arial",Font.BOLD,30);
		g.setFont(font);
		g.drawString("Membre du groupe:", 50,40);
		
		font = new Font("Arial",Font.PLAIN,20);
		g.setFont(font);
		g.drawString("*Sheraz DAHOUMANE", 75, 75);
		g.drawString("*Melissa TAALBA", 75,100);
		g.drawString("*Yasmine EL-HAJJAJI", 75,125);
		g.drawString("*Thilleli MESSAOUDENE", 75,150);
		g.drawString("*Aissa HAMICHE	", 75,175);

		font = new Font("Arial",Font.BOLD,30);
		g.setFont(font);
		g.drawString("Description du projet:", 50,240); 
		
		font = new Font("Arial",Font.PLAIN,20);
		g.setFont(font);
		g.drawString(".Sokoban « garde d'entrepôt » est un jeu vidéo de réflexion inventé au Japon.", 75 ,275);
		g.drawString(".C'est un puzzle dans lequel vous contrôlez un rabbin pour transporter les marchandises", 75 ,300);
		g.drawString("dans l’entrepôt jusqu’au lieu de stockage indiqué avec le moins d’effort possible.", 90 ,325);
		g.drawString(".Une fois toutes les caisses rangées, vous gagnez !", 75 ,350);
		
		font = new Font("Arial",Font.BOLD,30);
		g.setFont(font);
		g.drawString("Histoire:", 50,400); 
		
		font = new Font("Arial",Font.PLAIN,20);
		g.setFont(font);
		g.drawString(".Le jeu original a été écrit par Hiroyuki Imabayashi et comportait 50 niveaux.", 75,430); 
		g.drawString(".Il remporte en 1980 un concours de jeu vidéo pour ordinateur.",75,455);
		g.drawString(".Aujourd'hui, il existe de multiples jeux dérivés de Sokoban, par exemple Boxworld, ",75,480);
		g.drawString("une variante fonctionnant sous Windows et incluant 100 niveaux.",90,505);
	}

}