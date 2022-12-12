package vueGraphique;


import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 * Classe utilitaire pour regrouper des instructions sur les
 * composants Swing qui risquent de se r�p�ter.
 * 
 * @author Pierre B�lisle
 * @version Copyright A2017
 */
public class UtilitaireSwing {

    /**
     * Permet de regrouper le code qui dimensionne un <b>composant<\b>
     * de Swing.
     * 
     * @param composant
     *        Le composant Swing � dimensionner
     * @param dimension
     *        La nouvelle dimension du composant.
     * 
     */
    public static void setDimension(JComponent component, Dimension dimension) {

        // Dans nos applications, il est pr�f�rable de dimensionner
        // les trois de fa�on identique.
        component.setMinimumSize(dimension);
        component.setMaximumSize(dimension);
        component.setPreferredSize(dimension);
        component.setSize(dimension);

    }


    /**
     * Permet de regrouper le code qui dimensionne <b>un conteneur</b>
     * de Swing.
     * 
     * @param composant
     *        Le composant Swing � dimensionner
     * @param dimension
     *        La nouvelle dimension du composant.
     * 
     */
    public static void setDimension(Container component, Dimension dimension) {

        // Dans nos applications, il est pr�f�rable de dimensionner
        // les trois de fa�on identique.
        component.setMinimumSize(dimension);
        component.setMaximumSize(dimension);
        component.setPreferredSize(dimension);
        component.setSize(dimension);
		
    }


    /*
     * Rafra�chit le composant.
     */
    public static void rafraichirCadre(JComponent component) {

        component.validate();
        component.repaint();
    }
}