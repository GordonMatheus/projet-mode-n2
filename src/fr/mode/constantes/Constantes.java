package fr.mode.constantes;

import java.awt.Toolkit;

/**
 * <b>La classe Constantes contient les constantes utiles au projet</b>
 *
 */
public class Constantes {

	/**
	 * Le sol de la fen�tre
	 */
	public static int SOL = Toolkit.getDefaultToolkit().getScreenSize().height;
	
	/**
	 * Le plafond de la fen�tre
	 */
	public static int PLAFOND = 0;
	
	/**
	 * Le bord gauche de la fen�tre
	 */
	public static int BORD_GAUCHE = 0;
	
	/**
	 * Le bord droit de la fen�tre
	 */
	public static int BORD_DROIT = Toolkit.getDefaultToolkit().getScreenSize().width;
	
	/**
	 *  La constante du diam�tre de l'oiseau
	 */
	public final static int DIAMETRE = 60;	
}
