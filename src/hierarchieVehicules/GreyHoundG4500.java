/**
 * 
 */
package hierarchieVehicules;

/**
 * @author Jerome
 *
 */
public class GreyHoundG4500 extends AbstractAutobus {
	
	public int nbPassager = 55;

	public GreyHoundG4500(int type) {
		super(type);
		// TODO Auto-generated constructor stub
	}
	
	public String toString() {
		return "GreyHoundG4500";
		
	}

	@Override
	public int getNbPassagersMax() {
		// TODO Auto-generated method stub
		return nbPassager;
	}

}
