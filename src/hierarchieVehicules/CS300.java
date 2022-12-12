/**
 * 
 */
package hierarchieVehicules;

/**
 * @author Jerome
 *
 */
public class CS300 extends AbstractAvion {
	
	public int nbPassager = 130;

	public CS300(int type) {
		super(type);
		// TODO Auto-generated constructor stub
	}
	
	public String toString() {
		return "CS300";
		
	}

	@Override
	public int getNbPassagersMax() {
		// TODO Auto-generated method stub
		return nbPassager;
	}

}
