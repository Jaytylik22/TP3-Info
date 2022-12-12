/**
 * 
 */
package hierarchieVehicules;

/**
 * @author Jayty
 *
 */
public class CS100 extends AbstractAvion{
	
	public int nbPassager = 110;

	public CS100(int type) {
		super(type);
		// TODO Auto-generated constructor stub
	}
	
	public String toString() {
		return "CS100";
		
	}

	@Override
	public int getNbPassagersMax() {
		// TODO Auto-generated method stub
		return nbPassager;
	}

}
