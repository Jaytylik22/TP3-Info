/**
 * 
 */
package hierarchieVehicules;

/**
 * @author Jerome
 *
 */
public class FabriqueVehicule {

	// Les types de vehicules.  Servent comme indice de tableau
	// pour retrouver des facteurs d'ajustement futurs.
	public static final int CS100 = 0;
	public static final int CS300 = 1;	
	public static final int GREYHOUND102D3 = 2;
	public static final int GREYHOUNDG4500 = 3;
	public static final int TGVATLANTIQUE = 4;	
	public static final int TGVDUPLEX = 5;
	
	// Les noms de type de vehicules.
	public static final String [] tabNomTypeVehicule =
								         {"CS100", "CS300", "GreyHound102D3","GreyHoundG4500", "Tgv Atlantique", "tgv Duplex"};
	
	public static int getNbTypesVehicule() {
		return tabNomTypeVehicule.length;
	}
	
	//Méthode qui crée et retourne des véhicules selon un paramètre fourni.  
	public static InterfaceVehicules obtenirVehicule(int id) {
		
		if(id == 0) {
			CS100 vehicule = new CS100(1);
			return vehicule;
		}
		else if(id == 1){
			CS300 vehicule = new CS300(1);
			return vehicule;
		}
		else if(id == 2){
			GreyHound102D3 vehicule = new GreyHound102D3(0);
			return vehicule;
		}
		else if(id == 3){
			GreyHoundG4500 vehicule = new GreyHoundG4500(0);
			return vehicule;
		}
		else if(id == 4){
			TgvAtlantique vehicule = new TgvAtlantique(2);
			return vehicule;
		}
		else if(id == 5){
			TgvDuplex vehicule = new TgvDuplex(2);
			return vehicule;
		}
		else {
			return null;
		}
	}
}
