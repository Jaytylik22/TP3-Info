package vueGraphique;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import hierarchieVehicules.CS100;
import hierarchieVehicules.CS300;
import hierarchieVehicules.GreyHound102D3;
import hierarchieVehicules.GreyHoundG4500;
import hierarchieVehicules.TgvAtlantique;
import hierarchieVehicules.TgvDuplex;
/**
 * @author Jayty
 *
 */
public class PanneauVehicule extends JPanel {

	private JComboBox b1;
	private JLabel l1;
	private JLabel l2;
	
	
	public PanneauVehicule() {
		
		//Array contenant les véchicules
		final String [] tabNomTypeVehicule =
	         {"CS100", "CS300", "GreyHound102D3","GreyHoundG4500", "Tgv Atlantique", "tgv Duplex"};

		
		//Création de la combobox et des labels
		b1 = new JComboBox(tabNomTypeVehicule);
		l1 = new JLabel();
		l2 = new JLabel();
		
		//Logique pour afficher les labels en fonction du véhicule choisi
		int id = (int) b1.getSelectedItem();
		
		if(id == 0) {
			l1.setText("Type de carburant : Kerosene");
			l2.setText("Nombre de places : 110");
		}
		else if(id == 1){
			l1.setText("Type de carburant : Kerosene");
			l2.setText("Nombre de places : 130");
		}
		else if(id == 2){
			l1.setText("Type de carburant : Essence");
			l2.setText("Nombre de places : 46");
		}
		else if(id == 3){
			l1.setText("Type de carburant : Essence");
			l2.setText("Nombre de places : 55");
		}
		else if(id == 4){
			l1.setText("Type de carburant : Electrique");
			l2.setText("Nombre de places : 485");
		}
		else if(id == 5){
			l1.setText("Type de carburant : Electrique");
			l2.setText("Nombre de places : 455");
		}
		
	}
}
