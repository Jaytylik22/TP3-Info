package nouveauDossier;

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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


public class VueGraphique extends JFrame implements Runnable,ActionListener {
	
	
	private JFrame self;
	private Dimension tailleEcran;
	private Configuration config;
	JPanel panel_left=new JPanel();
	JPanel panel_right=new JPanel();
	JButton btnsouss = new JButton("ajouter");
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new VueGraphique());
	}

	@Override
	//demare le lancement de l'interface graphique 
	public void run() {
		// TODO Auto-generated method stub
		initComposants();
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
		
		
		
		//placemnet des panneaus
		self.add(panel_left, BorderLayout.LINE_START);
		
		self.add(panel_right, BorderLayout.LINE_END);
		
		panel_left.add(btnsouss,Component.BOTTOM_ALIGNMENT);
		
		panel_left.setVisible(true);
		
	}
	
	private void PanneauVehicule() {
		
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
