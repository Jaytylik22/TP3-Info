/**
 * 
 */
package hierarchieVehicules;

/**
 * @author Jerome
 *
 */
public class TgvDuplex extends AbstractTrain{
	
	public int nbPassager = 455;

	public TgvDuplex(int type) {
		super(type);
		// TODO Auto-generated constructor stub
	}
	
	public String toString() {
		return "Tgv Duplex";
		
	}

	@Override
	public int getNbPassagersMax() {
		// TODO Auto-generated method stub
		return nbPassager;
	}

}
