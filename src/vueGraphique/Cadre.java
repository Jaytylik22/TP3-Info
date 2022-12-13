package vueGraphique;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import enginCartes.Configuration;


public class Cadre extends JFrame implements Runnable,ActionListener {
	
	
	//Création de l'option menu
	JMenu menu = new JMenu("menu");
	//Création des items du menu
	JOptionPane optionPane = new JOptionPane("test");
	
	private JFrame self;
	private Dimension tailleEcran;
	private Configuration config;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Cadre());
	}

	@Override
	//demare le lancement de l'interface graphique 
	public void run() {
		// TODO Auto-generated method stub
		initComposants();
		//optenir les dimention de l'écran 
		tailleEcran=(Toolkit.getDefaultToolkit().getScreenSize());
		double hauteur = tailleEcran.getHeight();
		double laurgeur =tailleEcran.getWidth();
		
	}
	
	
	
	private void initComposants() {
		self =this;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		addWindowListener(new FenetreEcouteur());
		
		PanneauPrincipal();
		
		
		
		self.setVisible(true);
	}
	
	
	private void PanneauPrincipal() {
		
		
	PanneauProgression panneau_haut= new PanneauProgression();
        PanneauDessin panneau_centre= new PanneauDessin();
        PanneauPrincipal panneau_gauche= new PanneauPrincipal();
        PanneauPrincipal panneau_droite= new PanneauPrincipal();
		
		//placement des panneaus ne fonctionne pas 
		/*self.add(panel_left, BorderLayout.LINE_START);
		
		self.add(panel_right, BorderLayout.LINE_END);
		
		panel_left.add(btnsouss,Component.BOTTOM_ALIGNMENT);
		
		panel_left.setVisible(true);*/
		
	}

	
	private class FenetreEcouteur implements WindowListener{
		JPanel panneauContenu = (JPanel) getContentPane();
		@Override
		public void windowOpened(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
		

			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				//confirmation de l'utilisateur pour quitter le programe 
				 int reponse = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment quiter","Selectioner une option",JOptionPane.YES_NO_CANCEL_OPTION);
				    if(reponse == JOptionPane.YES_OPTION)
				    {
				        dispose();
				    }
				 else {
				     setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				 }
			}
	
			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
	
			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
	
			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
	
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
	
			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
