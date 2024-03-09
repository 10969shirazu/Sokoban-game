package com.sokoban.modules;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Cellule{

	public enum Cell { vide, mur, boite, personne; }
	//une classe pour stocker les information descriptive de chaque carré de jeu

	private Cell type; // pour memoriser le type de cellule
	private Point position; // pour stocker l'indices (ligne et column) de chaque cellule dans la matrice
	private boolean estCible; // pour tester si cette cellule est cible ou non
	
	public void paint(Graphics2D g2, int x, int y, int w, int h) {
		// fonction pour le tracage de cellule icone en utilisant un graphics donnée
		String icon = null; BufferedImage img = null;
		switch (type) {
		// initialiser le varaible icone pour chaque cell par rapport a son type
			case vide:
				if(estCible) { icon = "./icones/cible.png"; break; }
				else return;
			case mur: icon = "./icones/mur.png"; break;
			case boite:
				if(estCible) icon = "./icones/boitesurcible.png";
				else icon = "./icones/boite.png"; 
				break;
			case personne:
				if(estCible) icon = "./icones/personnesurcible.png";
				else icon = "./icones/personne.png"; 
				break;
		}
		// essayer de lire le fichier d'icones
		try { img = ImageIO.read(new File(icon)); } catch (IOException e) {System.out.println(e.getMessage());}
		// peindre l'icone
		g2.drawImage( img, x+position.x*w, y+position.y*h, w, h, null );
	}
	
		
	public Cellule(Cell type, Point position, boolean estCible) {
		super();
		this.type = type;
		this.position = position;
		this.estCible = estCible;
	}
	

	// getteurs et setteurs
	public Cell getType() { return type; }
	public void setType(Cell type) { this.type = type;}
	public Point getPosition() { return position; } 
	public void setPosition(Point position) { this.position = position; }
	public boolean isEstCible() {return estCible;}
	public void setEstCible(boolean estCible) { this.estCible = estCible; }
	public int getX() {
	    return position.x;
	}

	public int getY() {
	    return position.y;
	}

	
}