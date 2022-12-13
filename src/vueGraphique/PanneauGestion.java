package vueGraphique;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import enginCartes.Configuration;
import enginCartes.MoteurCartes;
import problemeVilles.PopulationVilles;

/**
 * Regroupe les boutons qui permettent de redémarrer la simulation avec des 
 * nouvelles villes ou de simplement refaire les clauls sur les mêmes villes.
 * 
 * @author Pierre Bélisle
 * @version copyright H2018
 *
 */
public class PanneauGestion extends JPanel{
	
	// Les deux boutons.
	private JButton btnNouvelleSimulation = new JButton("Nouvelle simulation");
	private JButton btnDemarrer = new JButton("Redémarrer");
	
	// Les données initiales reçues du constructeur.
	private Configuration config;
	
	// Le calcul doit être fait dans un processus séparé de l'EDT pour  le 
	// rafraîchissement d'écran.
	private Thread t = null;
	
	// Permet de refaire les calculs.
	private MoteurCartes enginCartes;	
	
	
	// Les références reçues au constructeur.
	private PopulationVilles popVilles;	
	
	// Doivent être mis à jour selon l'action choisie.
	private  PanneauProgression panProgression;	
	private  PanneauDessin panDessin;

	// Nécessaire pour la mise à l'échelle.
	private Dimension tailleDessin;

	/**
	 * Les panneaux reçus sont mis à jour et doivent être instanciés avant.
	 * 
	 * @param enginCartes L'engin de calcul des solutions.
	 * @param config Les données initiales.
	 * @param panProgression
	 * @param panDessin
	 * @param tailleGestion
	 */
	public PanneauGestion(MoteurCartes enginCartes, 
			                                  Configuration config, 
			                                  PanneauProgression panProgression,
			                                  PanneauDessin panDessin,
			                                  Dimension tailleGestion) {

		
		// Retient les références reçues.
		this.panDessin = panDessin;		
		this.panProgression = panProgression;
		this.enginCartes =enginCartes;
		this.config = config;
	
		// Obtient la dimension actuelle du panneau de dessin.
		tailleDessin = panDessin.getMaximumSize();
		
		
		initComposants(tailleGestion);
		resetPopulation();
	}

	/*
	 * On doit redémarrer le processus de calcul à chaque clic de bouton.
	 * 
	 * Si ce n'est pas la premièere fois, on doit tuer le processus avant d'en 
	 * partir un autre.
	 */
	private void go() {

		if(t !=null) {
			t.interrupt();			
		}
		
		// CréerArbre du tp2 démarrer dans un processus à part pour 
		// permettre la mise à jour de la barre de progression durant les 
		//calculs.
		t = new Thread(new CreeArbre());
		t.start();				  	

	}


	/*
	 * Dimension, dessiner une bordure et associe un écouteur aux boutons.
	 */
	private void initComposants(Dimension tailleGestion) {
		
		UtilitaireSwing.setDimension(this, tailleGestion);
		
		setBorder(BorderFactory.createBevelBorder(1));
		
		// Écouteur anonyme pour un nouvelle simulation.	Des nouvelles villes
		// seront utilisées.
		btnNouvelleSimulation.addActionListener(new ActionListener() {	

			@Override
			public void actionPerformed(ActionEvent e) {

				panDessin.setCarte(null);
				resetPopulation();

				go();
			}
			

			
		});
		
			
		/*
		 * Pour refaire les calculs avec les mêmes villes.
		 */
		btnDemarrer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				// Pour dessiner sans les liens avant de commencer.
				panDessin.setCarte(null);
				panDessin.setPopulation(popVilles);

				go();
			}
			
		});
		

		add(btnDemarrer);
		add(btnNouvelleSimulation);
		
	}
	
	
	
	public class CreeArbre implements Runnable{

		@Override
		public void run() {
			
			panProgression.setProgressBar(0);
	    	
			 //  Évalue les scores une première fois.
			enginCartes.evalueLesScores();
		    
		    // Maintenant, on procède à la boucle d'optimisation 
			// pour trouver la solution.       
			int nbIterations = config.getNbIterations();
			
		    for(int i=0;i<nbIterations;i++)
		    {

		    	// Itération d'optimisation: élargi, évalue et réduit.
		    	enginCartes.elargitLaPopulation();
		    	enginCartes.evalueLesScores();
		    	enginCartes.reduitLaPopulation();
		    		   
		    	// Calcule de la progression en réel.
		    	double v = (double)i / nbIterations * 100;		    	
		    	panProgression.setProgressBar((int)v);
		    			        
		    }
		    
		    // Montre que c'est complété dans la barre de progression.
	    	panProgression.setProgressBar(100);
	        
             // Montre les liens de la solution.
	    	panDessin.setCarte(enginCartes.getMeilleurSolution());
		    
		}
		
	}
	
	
	public void resetPopulation() {
		
		// Crée une nouvelle population de villes.
		popVilles = 
				new PopulationVilles(config.getNbVilles(), 
						config.getMaxX(), config.getMaxY());
		
		// On met à jour les références avec la nouvelle population.
		enginCartes.setPopulation(popVilles);
		panDessin.setPopulation(popVilles);
	
	}

	public void avisNouvelleSimulation() {

		btnNouvelleSimulation.doClick();
		
	}


	public void avisRedemarrer() {
				

		// On met à l'échelle que ce soit modifié ou non. 		
		mettreAEchelle(); 
			
		btnDemarrer.doClick();			
	}


	/*
	 * La stratégie utilisée a été de redimensionner le panneau de dessin en se 
	 * servant du facteur d'écelle des données initiales.  Comme le panneau est
	 *  dans un scrollPane, le tout s'adapte automatiquement.  J'aurais pu aussi 
	 *  utiliser affineTransform mais ça ne me tentait pas de l'expliquer pou
	 *  r le gain 
	 *  pédagogique que cela aurait pu avoir.
	 */
	private void mettreAEchelle() {

		int echelle = config.getEchelle();

		Dimension dim = new Dimension(tailleDessin);
		
		dim.width*=echelle;
		dim.height*=echelle;		
		
		UtilitaireSwing.setDimension(panDessin, dim);		
		
	}
}
