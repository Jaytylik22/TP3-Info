/**
 * 
 */
package hierarchieVehicules;

/**
 * @authorJerome
 *
 */
public abstract class AbstractVehicule implements InterfaceVehicules{

	public int typeCarburant;
	
	//Constructeur par copie d'attribue
	public AbstractVehicule(int type) {
		typeCarburant = type;
	}
	
	//Accesseur
	public int getTypeCarburant() {
		return typeCarburant;
	}
	
	//Mutateur
	public void setTypeCarburant(int nouveauTypeCarburant) {
		
		typeCarburant = nouveauTypeCarburant;
	}
}
