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



  public class Regles extends JPanel
  {
    
     
     public void paintComponent (Graphics g){
         g.setColor(Color.BLACK);
         g.fillRect (0, 0, getWidth(), getHeight() );
         
         g.setColor(Color.WHITE);
         Font font = new Font ("Arial",Font.BOLD,30);
         g.setFont(font);
         g.drawString("Regles:", 50, 40);
         
         font = new Font("Arial",Font.PLAIN,20);
         g.setFont(font);
         g.drawString("la regle du jeu est tres simple: ", 60, 75);
         g.drawString("* le joueur doit bouger les boites dans le labyrinth en essayant de les mettre dans les zones ", 75, 100);
         g.drawString(" de stockage. ", 100, 125);
         g.drawString("* le joueur ne peut pas marcher a travers les boites et les murs. ", 75, 150);
         g.drawString("* le joueur ne doit pas pousser la boite au coin, sinon elle sera bloquÃ©e !", 75, 175);
         
         font = new Font ("Arial",Font.BOLD,30);
         g.setFont(font);
         g.drawString("Controle:", 50, 240);
         font = new Font("Arial",Font.PLAIN,20);
         g.setFont(font);
         
         g.drawString("Le joueur bouge les boites avec le clavier :", 75, 265);
         g.drawString("* pour bouger en haut il faut utiliser la fleche du haut.", 75, 290);
         g.drawString("* pour bouger en bas il faut utiliser la fleche du bas.", 75, 315);
         g.drawString("* pour bouger a gauche il faut utiliser la fleche de gauche.", 75, 340);
         g.drawString("* pour bouger a gauche il faut utiliser la fleche de gauche.", 75, 365);
         g.drawString("* pour revenir en arriere il faut utiliser ctrl+Z.", 75, 390);
         
         
         
     }

}