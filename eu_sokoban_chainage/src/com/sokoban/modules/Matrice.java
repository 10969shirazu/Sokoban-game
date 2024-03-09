package com.sokoban.modules;

import java.util.ArrayList;
import java.util.Collections;

import com.sokoban.modules.Cellule.Cell;
import java.util.List;
import java.util.Comparator;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.Queue;
import java.util.PriorityQueue;


public class Matrice 
{	
	private ArrayList<Cellule> cellules;
	private int m, n, niveau;
	private  int personne;
	
	public Matrice( ArrayList<Cellule> cellules, int m, int n, int niveau, int personne )
	{
		this.cellules = cellules; // tableau que contient toutes les cellules de jeu.
		this.m = m; // colonnes
		this.n = n; // lignes 
		this.niveau = niveau; // pour stocker le niveau
		this.personne = personne; // pour stocker l'indice de personne dans le tableau de cellules
	}

	
	public boolean deplacer(Direction d)
	{
		
		int pas = 0;  
		switch (d) 
		{
			
			case gauche: pas = -1; break; // passer l'indice a gauche par un -1
			case droite: pas = 1; break; // passer l'indice a droite par un +1
			case haut: pas = -m; break; // passer a le ligne suivant par decrementer par la hauteur de la cellule
			case bas: pas = m; break; // passer a le ligne suivant par ajouter la hauteur de la cellule
		}

		return deplacerChainnage( personne, pas );
	
	}
	
	public boolean deplacerChainnage( int indice, int pas )
	{
		
		Cellule c1 = cellules.get(indice + pas), c2 = cellules.get( indice );
		switch ( c1.getType() ) 
		{
		
		case vide :
			c1.setType(c2.getType()); // notre cellule courante est une personne, la cellule cible le devient aussi
			c2.setType(Cell.vide); // on l'a quitté
			personne += pas; //mise a jour des coordoonées 
			return true; // le déplacement est possible 
		case boite :
			if( !deplacerChainnage( indice+pas, pas ) ) return false; // on a trouvé un mur, on ne peut pas déplacer la boite
			c1.setType( c2.getType() );
			c2.setType( Cell.vide );
			return true;
		case mur :
			 return false; // la personne ne recule pas
			
			
	
		default :
			return false;
		}
	}

	
	

	// tester si toutes les cibles sont pleines 
	public boolean estComplete() 
	{
	    for (Cellule c : cellules) {
	        if (c.isEstCible() && c.getType() != Cell.boite) {
	            return false;
	        }
	    }
	    return true;
	}

	
	
	
	// Trouve le chemin le plus court entre la position courante de l'objet représenté par cette Matrice
	 // et la position cible spécifiée en utilisant l'algorithme de Dijkstra.
	
	public List<Cellule> trouverChemin(Cellule cible) {
	    // Initialisation des structures de données nécessaires pour l'algorithme de Dijkstra
	    List<Cellule> chemin = new ArrayList<>();
	    Set<Cellule> visitees = new HashSet<>();
	    Map<Cellule, Cellule> parents = new HashMap<>();
	    Queue<Cellule> aExplorer = new PriorityQueue<>(Comparator.comparingDouble(c -> c.getPosition().distance(cible.getPosition())));

	    // On commence à la position de la personne
	    Cellule courante = cellules.get(personne);
	    aExplorer.add(courante);
	    visitees.add(courante);

	    // Algorithme  pour trouver le chemin le plus court
	    while (!aExplorer.isEmpty()) {
	        courante = aExplorer.poll();
	        if (courante == cible) {
	            break;
	        }
	        // On explore les voisins dans toutes les directions possibles
	        for (Direction direction : Direction.values()) {
	            int pas = direction.getPas(m);
	            int nouvellePosition = personne + pas;
	            // On vérifie que la nouvelle position est valide
	            if (nouvellePosition < 0 || nouvellePosition >= cellules.size()) {
	                continue;
	            }
	            Cellule voisin = cellules.get(nouvellePosition);
	            // On vérifie que le voisin n'est pas un mur et qu'on ne l'a pas déjà visité
	            if (voisin.getType() == Cell.mur || visitees.contains(voisin)) {
	                continue;
	            }
	            // Calcul du coût pour aller à ce voisin et mise à jour des structures de données
	            // double distanceVoisin = voisin.getPosition().distance(cible.getPosition());
	          //   double cout = courante.getPosition().distance(voisin.getPosition()) + distanceVoisin;
	            aExplorer.add(voisin);
	            visitees.add(voisin);
	            parents.put(voisin, courante);
	        }
	    }

	    // Reconstruction du chemin à partir des parents
	    while (courante != null) {
	        chemin.add(courante);
	        courante = parents.get(courante);
	    }

	    // Inversion du chemin pour obtenir l'ordre correct
	    Collections.reverse(chemin);
	    return chemin;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {

	    return super.clone();
	}
	
	
	
	// getteurs et setteurs
	public Cellule getElement(int indice) { return cellules.get(indice); }
	public void setElement(int indice, Cellule c) { cellules.set(indice, c); }
	public ArrayList<Cellule> getCellules() { return cellules; }
	
	public int getNiveau() { return niveau; }
	public void setNiveau(int niveau) { this.niveau = niveau; }
	public void setCellules(ArrayList<Cellule> cellules) {this.cellules = cellules;}
	public int getM() {return m;}
	public void setM(int m) {this.m = m;}
	public int getN() {return n;}
	public void setN(int n) {this.n = n;}
	public int getPersonne() {return personne;}
	public void setPersonne(int personne) {this.personne = personne;}
	public Cellule getCellule(int x, int y) {
	   if (x < 0 || x >= m || y < 0 || y >= n) {
	        return null;
	    }
	    return cellules.get(x + y * m);
	}
}
