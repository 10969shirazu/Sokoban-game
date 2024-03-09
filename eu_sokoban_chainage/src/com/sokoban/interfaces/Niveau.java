package com.sokoban.interfaces;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import com.sokoban.modules.Cellule;
import com.sokoban.modules.Cellule.Cell;

public class Niveau 
{
	public static ArrayList<File> files = new ArrayList<>();
	private static int personne;

	public static int getM(int niveau)
	{
		int m = 0;
		Scanner sc = null;
		try {
			sc = new Scanner(files.get(niveau));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		if(sc != null) 
			if(sc.hasNext()) {
				String line = sc.nextLine();
				m = Integer.parseInt(""+line.charAt(2));
			}
		
		return m;
	}
	
	
	
	public static char getName(int niveau)
	{
		char name = 'A';
		Scanner sc = null;
		try {
			sc = new Scanner(files.get(niveau));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		if(sc != null) 
			if(sc.hasNext()) 
			{
				String line = sc.nextLine();
				name = line.charAt(0);
			}
		return name;
	}
	
	public static ArrayList<Cellule> getCellules(int niveau)
	{
		ArrayList<Cellule> Cellules = new ArrayList<>();
		Scanner sc = null;
		try
		{
			sc = new Scanner(files.get(niveau));
		} catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		sc.nextLine(); //la premiere ligne contient le titre
		int l = 0;
		while( sc.hasNext() )
		{
			String line = sc.nextLine();
			if(line.length() == 0) break;
			else if( line.charAt(0)==' ') break;
			for(int i = 0; i < line.length(); i++) 
			{
				if(line.charAt(i) == '\n') break;
				Cell c = Cell.mur; boolean cible = false;
				switch(line.charAt(i)) {
					case '#': break;                                       
					case 'A': c = Cell.personne; cible = false; personne = Cellules.size(); //position du perso
					break;
					case 'a': c = Cell.personne; cible = true; break;
					case 'B': c = Cell.boite; cible = false; break;
					case 'b': c = Cell.boite; cible = true; break;
					case '@': c = Cell.vide; cible = true; break;
					case ' ': c = Cell.vide; cible = false; break;
					default : c = Cell.boite; cible = false; break;
				}
				Cellules.add(new Cellule( c, new Point(i, l), cible));
			}
			l++;
		}
		return Cellules;
	}
	
	public static void getFiles() 
	{
		for(final File f : (new File("./niveau")).listFiles()) 
			  files.add(f);
	}

	public static boolean aPlusNiveau(int niveau) 
	{
		if(niveau >= files.size()) return false;
		return true;
	}
	
	public static int getPersonne() {return personne;}
}
