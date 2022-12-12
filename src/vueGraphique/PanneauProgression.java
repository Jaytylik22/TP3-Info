/**
 * 
 */
package vueGraphique;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

/**
 * @author Jayty
 *
 */
public class PanneauProgression extends JPanel {
	private JProgressBar b1;
	
	public PanneauProgression() {
 
        // create a panel
        JPanel p = new JPanel();
 
        // create a progressbar
        b1 = new JProgressBar();
	}
	
	public void setProgressBar(int value) {
		
		b1.setValue(value);
	}
	
}
