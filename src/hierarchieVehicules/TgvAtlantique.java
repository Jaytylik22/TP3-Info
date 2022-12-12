/**
 * 
 */
package hierarchieVehicules;

/**
 * @author Jayty
 *
 */
public class TgvAtlantique extends AbstractTrain {
	
	public int nbPassager = 485;

	public TgvAtlantique(int type) {
		super(type);
		// TODO Auto-generated constructor stub
	}
	
	public String toString() {
		return "Tgv Atlantique";
		
	}

	@Override
	public int getNbPassagersMax() {
		// TODO Auto-generated method stub
		return nbPassager;
	}

}
