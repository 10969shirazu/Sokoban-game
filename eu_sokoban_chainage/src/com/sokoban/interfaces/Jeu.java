package com.sokoban.interfaces;

import java.util.Stack;
import java.util.List;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.sokoban.helper.Custom;
import com.sokoban.modules.Cellule;
import com.sokoban.modules.Cellule.Cell;
import com.sokoban.modules.Direction;
import com.sokoban.modules.Matrice;
import java.awt.Point;

public class Jeu extends JPanel implements ActionListener{
	//-----------------------------------------------
	
	//-----------------------------------------------
	private static Matrice matrice;
	private int niveau;
	//-----------------------------------------------
	private JButton nextBtn, lastBtn;
	private JLabel Information, victoire;
	//-----------------------------------------------

	public Jeu(Matrice matrice) {
	    Jeu.matrice = matrice;
	   
	    setLayout(null);
	    setFocusable(true);
	    addMouseListener(new MouseListener() {
	        public void mouseReleased(MouseEvent e) {}

	        public void mousePressed(MouseEvent e) {}

	        public void mouseExited(MouseEvent e) {}

	        public void mouseEntered(MouseEvent e) {}

	        public void mouseClicked(MouseEvent e) {
	            setFocusable(true);

	            // Récupérer la cellule cliquée
	            int x = e.getX();
	            int y = e.getY();
	            
	            if(x > (getWidth() - matrice.getM())/2) {
	            	
	            	x=x/(getWidth()/matrice.getM());
	            	y=y/(getHeight( )/matrice.getN());
	            	
	            	System.out.println(x + "    "+y);
	            	
	            	
	            	Cellule celluleCible = matrice.getCellule(x, y);
	                
		            // Vérifier si la cellule cliquée est valide
		            if (celluleCible.getType() == Cell.mur) {
		                return;
		            }

		            // Trouver le chemin vers la cellule cliquée
		            List<Cellule> chemin = matrice.trouverChemin(celluleCible);

		            // Dessiner le chemin sur la grille
		            tracerChemin(chemin);
	            	
	            }
	            
	        }
	    });
	    getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
	    addKeyListener(new TAdapter());
	    nextBtn = Custom.newButton("Suivant", getBackground(), 0, 0, 100, 30);
	    lastBtn = Custom.newButton("Précédant", getBackground(), 0, 0, 100, 30);
	    Information = Custom.newLabel("Niveau : " + matrice.getNiveau(), null, getFont(), 0, 0, 100, 30);
	    victoire = Custom.newLabel("Hello", Color.red, new Font("Calibri Light", Font.BOLD, 22), 0, 0, 150, 50);
	    victoire.setVisible(false);
	    Custom.add(this, lastBtn, nextBtn, Information, victoire);
	    nextBtn.addActionListener(this);
	    lastBtn.addActionListener(this);
	    
	}
	


	
	
	private void tracerChemin(List<Cellule> chemin) {
	    Graphics g = getGraphics();
	    
	    g.setColor(Color.RED);
	    for (int i = 0; i < chemin.size() - 1; i++) {
	    	
	        Cellule cellule1 = chemin.get(i);
	        Cellule cellule2 = chemin.get(i + 1);
	        g.drawLine(cellule1.getX(), cellule1.getY(), cellule2.getX(), cellule2.getY());
	    }
	}


	
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g.create();
		int x, y, w, h;
		if(matrice != null) {
			w = getWidth()/matrice.getM(); h = getHeight()/matrice.getN();
			if(w < h) {
				x = (getWidth() - matrice.getM());
				y = (getHeight() - matrice.getM());
				for(Cellule c : matrice.getCellules())
					c.paint(g2, x, y, w, w);
			}
			
			else {
				x = (getWidth() - matrice.getM()*h )/2;
				y = (getHeight() - matrice.getM()*h)/2;
				for(Cellule c : matrice.getCellules())
					c.paint(g2, x, y, h, h);
			}
		}
		Information.setLocation(getWidth()/2-100, getHeight()-50);
		nextBtn.setLocation( getWidth()-130, getHeight()-50 );
		lastBtn.setLocation( 20, getHeight()-50 );
	}
	
	


	
	private boolean changeNiveau(int niveau)
	{
		if(! Niveau.aPlusNiveau(niveau)) return false;
		ArrayList<Cellule> cellules = Niveau.getCellules( niveau );
		matrice.setNiveau( niveau );
	
		matrice.setCellules( cellules );
		for(Cellule c : cellules)
			if(c.getType() == Cell.personne) matrice.setPersonne( cellules.indexOf( c ));
		
		return true;
	}
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		switch (((JButton) e.getSource()).getText()) 
		{
		case "Suivant":
			if(! Niveau.aPlusNiveau(matrice.getNiveau() + 1)) return;
			
			changeNiveau(matrice.getNiveau()+1);
			
			this.setFocusable(true);
						
			repaint();
			
			break;
			
		case "Précédant":
			if( matrice.getNiveau() == 0 ) return;
			changeNiveau(matrice.getNiveau()-1);
			this.setFocusable(true);
			repaint();
			
			
			break;
		}
		
		
		
	}
	
	
	

	
	
	private class TAdapter extends KeyAdapter {
	    private Stack<Action> history = new Stack<>();

	    @Override
	    public void keyPressed(KeyEvent e) {
	        int key = e.getKeyCode();
	        

	        switch (key) {
	            case KeyEvent.VK_LEFT:
	            	
	            	
	                matrice.deplacer(Direction.gauche); {
	                    history.push(new Action(Direction.droite));
	                    repaint();
	                }
	                break;
	            case KeyEvent.VK_RIGHT:
	                matrice.deplacer(Direction.droite); {
	                    history.push(new Action(Direction.gauche));
	                    repaint();
	                }
	                break;
	            case KeyEvent.VK_UP:
	            	
	               matrice.deplacer(Direction.haut); {
	                    history.push(new Action(Direction.bas));
	                    repaint();
	                }
	                break;
	            case KeyEvent.VK_DOWN:
	               matrice.deplacer(Direction.bas); {
	                    history.push(new Action(Direction.haut));
	                    repaint();
	                }
	                break;
	          
	            case KeyEvent.VK_ENTER:
	                victoire.setVisible(true);
	                victoire.setLocation(200, 100);
	                if (matrice.estComplete()) {
	                    victoire.setText("Bon travail !");
	                    changeNiveau(matrice.getNiveau()+1);
	                } else {
	                    victoire.setText("Oups !");
	                }
	                repaint();
	                victoire.setVisible(false);
	                break;
	            case KeyEvent.VK_Z:
	                if (e.isControlDown()) {
	                    if (!history.empty()) {
	                        Action lastAction = history.pop();
	                        matrice.deplacer(lastAction.direction);
	                        repaint();
	                    }
	                }
	                
	           
	            default:
	                break;
	        }
	    }
	}

	private class Action {
	    public Direction direction;

	    public Action(Direction direction) {
	        this.direction = direction;
	    }
	}

    // getteurs et setteurs
	public Matrice getMatrice() {return matrice;}
	public void setMatrice(Matrice matrice) {this.matrice = matrice;}

}