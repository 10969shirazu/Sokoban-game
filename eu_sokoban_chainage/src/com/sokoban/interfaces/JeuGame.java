package com.sokoban.interfaces;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.sokoban.helper.Custom;
import com.sokoban.modules.Matrice;

public class JeuGame{
	//-----------------------------------------------
	private final int WIDTH = 1000, HEIGHT = 600;
	//-----------------------------------------------
	private JPanel PaneauPro, PaneauJeu, PaneauReg;
	private JFrame form;
	//-----------------------------------------------
	
	public JeuGame() {
		Niveau.getFiles();
		form = Custom.newFrame( WIDTH, HEIGHT );
		PaneauJeu = new Jeu( new Matrice(Niveau.getCellules(0), Niveau.getM(0), Niveau.getM(0), 0, Niveau.getPersonne()));
		PaneauPro = new APropos();
		PaneauReg = new Regles();
		form.setMinimumSize(new Dimension(600, 500));
		form.setTitle("Jeu de Sokoban : Niveau "+((Jeu) PaneauJeu).getMatrice().getNiveau());
		form.add(createToolBar());
		form.setVisible(true);
	}
	
	
    private JTabbedPane createToolBar() {
    	JTabbedPane jtp = new JTabbedPane();
    	jtp.setFocusable(false);
    	jtp.add("Jouer Sokoban", PaneauJeu);
    	jtp.add("À propos", PaneauPro);
    	jtp.add("Regles", PaneauReg);
    	return jtp;
    }   
	
}