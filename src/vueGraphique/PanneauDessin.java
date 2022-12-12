package vueGraphique;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import enginCartes.Carte;
import enginCartes.Configuration;
import enginCartes.Lien;
import enginCartes.MoteurCartes;
import listeChainee.ListeDChainee;
import problemeVilles.PopulationVilles;
import problemeVilles.Ville;

/**
 * Panneau qui dessine les villes et les liens entre le villes.
 * 
 * @author Pierre Bélisle
 * @version H18
 * 
 */
public class PanneauDessin extends JPanel {
	
	/*
	 * Stratégie : Le panneau de dessin est refait à chaque fois qu'il y a un 
	 * changement à une constante de la simulation.
	 */
	
	// Degrés d'une ellipse pleine.
	private static final int PLEIN = 360;

	// La taille de départ des cercles qui représentent les villes (en pixels).
	private static final int RAYON_X = 5;
	private static final int RAYON_Y = 5;
	
	
	// Les villes.  
	private PopulationVilles popVilles;
	
	// Les constantes de la simulation
	private Configuration config;
	
	// L'engin de recherche doit 
	private Carte carte;

	private boolean firstTime = true;
	
	private static MoteurCartes enginCartes;
	
	
	/**
	 * Les trois références doivent être instanciées avant l'appel.
	 * 	
	 * @param popVilles
	 * @param config
	 * @param tailleDessin
	 */
	public PanneauDessin(PopulationVilles popVilles,
	   		                                Configuration config,
		                                 	Dimension tailleDessin) {
		
		this.config  = config;
		
		this.popVilles = popVilles;
		
		initComposants(tailleDessin);

	}

	/*
	 * Initialise la taille et la bordure.
	 */
	private void initComposants(Dimension tailleDessin) {
		
		enginCartes = new enginCartes.MoteurCartes(popVilles, config);
		
		// On veut que la position des villes soient dans l'écran (on ne peut pas 
		// le faire avant que le panneau existe). 
		config.setMaxX(tailleDessin.width);
		config.setMaxY(tailleDessin.height);
		
		UtilitaireSwing.setDimension(this, tailleDessin);
		
		setBorder(BorderFactory.createBevelBorder(2));

		
	}

	/**
	 * Exécuté chaque rafraîchissement d'écran.  On ne dessine pas les liens si la
	 * carte est à null.
	 */
	public void paintComponent(Graphics g) {
		
		Graphics2D g2 = (Graphics2D) g;
		
		super.paintComponent(g);
			
		Dimension taille = getPreferredSize();
		
		TestPanneauDessin.go(enginCartes, config);
		carte = enginCartes.getMeilleurSolution();
		
		dessinerVilles(g2);
		dessinerLiens(g2);
		
	}


	/**
	 * Mutateur de la population qui doit être utilisé si une des constantes
	 * qui recrée l'engin est modifiée.
	 * 
	 * @param popVilles
	 */
	public void setPopulation(PopulationVilles popVilles) {
					
		this.popVilles = popVilles;
		
		validate();
		repaint();
		
	}
	
	/*
	 * Mutateur de carte pour obtenir la meilleure solution.
	 */
	public void setCarte(Carte carte) {
		
		this.carte = carte;
		repaint();
	}
		
	/**
	 * On obtient les liens de la solution pour les dessiner.
	 * 
	 * @param g2
	 */
	public void dessinerLiens(Graphics2D g2) {

		/*
		 * Stratégie : On parcourt tous les liens et on fait dessiner les villes et le lien
		 * entre elles par des SP.
		 * 
		 */
		//carte.evalueScore(config, true);

		int i = 0;		
		
		//carte.evalueScore(config, true);

		ListeDChainee listeLiens = carte.getListeLiens();

		// Part du prochain noeud.
		listeLiens.deplacerDebut();

		g2.setColor(Color.BLACK);

		// Passe au travers de toutes la liste des liens.
		do{
			
			// Obtient une référence sur le lien.
			Lien lien = (Lien)listeLiens.getElement();

			Ville  villeCourante = lien.getSrc();

			// Oui, obtient la destination.
			Ville destination = lien.getDest(villeCourante);

			int echelle = config.getEchelle();	

			// Les villes ont une taille différente selon le nombre de fois qu'elles sont 
			// connectés. 			
			int tailleVilleCourante = carte.getNbDeCetteVille(villeCourante) ;
			int tailleDestination = carte.getNbDeCetteVille(destination) ; 

			dessinerUneVille(villeCourante, g2, tailleVilleCourante);				
			dessinerUneVille(destination, g2, tailleDestination);				

			// Le lien entre les deux.
			g2.drawLine((int)(villeCourante.getPosX()* echelle), 
					(int)(villeCourante.getPosY()* echelle),
					(int)(destination.getPosX()  * echelle),
					(int)(destination.getPosY() *echelle));

			listeLiens.deplacerAIndex(i++);

		}while(listeLiens.deplacerSuivant());


	}
		
	/**
	 * Dessine toutes les villes de la population.
	 * 
	 * @param g2
	 */

		private void dessinerVilles(Graphics2D g2) {
			
			for(int index = 0; index < popVilles.getNbVille(); index++) {

				Ville v = popVilles.getVille(index);
				dessinerUneVille(v, g2, 1);

			}
	
	}
		
		
	/*
	 * Dessine une ville de la taille reçue dans l'objet graphique..
	 */
	private void dessinerUneVille(Ville v, Graphics2D g2, int taille) {
		
		/*
		 * Stratégie : La taille des villes à l'écran est calculée selon le nombre de villes 
		 * qui sont reliées.  Pour des raisons d'effet visuel, nous limitons la taille 
		 * au double du rayon en x (on présume un cercle).
		 * 
		 * On utilise les données initiales pour tenir compte de l'échelle.
		 */
		final int MAX = 2 * RAYON_X;
		
	
		int echelle = config.getEchelle();
		
		//On contrôle la taille maximale visuelle des villes.
		if(taille > MAX) {
			taille = MAX;
		}

		// Permet de positionner selon l'échelle. 
		int echelleX = (int) (v.getPosX() * echelle) - (RAYON_X * taille) / 2;
		int echelleY = (int) (v.getPosY() * echelle) - (RAYON_Y * taille) / 2;

		// Le numéro de la ville index).
		g2.drawString(String.valueOf(v.getIndex() + 1), 
				echelleX - (RAYON_X * taille)/2, echelleY - (RAYON_Y * taille)/2);
		
		g2.setColor(Color.RED);
		
		g2.fillRoundRect(echelleX, echelleY, 
				RAYON_X * taille, RAYON_Y * taille, PLEIN, PLEIN);	
			
		}
}
