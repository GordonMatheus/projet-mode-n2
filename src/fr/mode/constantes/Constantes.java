package fr.mode.constantes;

import java.awt.Toolkit;

/**
 * <b>La classe Constantes</b>
 * <p>
 * Elle contient toutes les constantes utilisées dans le
 * programme, mise à la visibilitée maximale.
 * Il y a deux utilisations des constantes dans cette classe :
 * <ul>
 * <li>Les limites d'affichage de l'écran, et donc les limites horizontales et verticales.</li>
 * <li>Le diamètre de l'oiseau lancé.</li>
 * </ul>
 * </p>
 */
public class Constantes {

	// *********************************************************************
		// Constantes pour les bords de la scène
		// L'attribut "final" n'est pas spécifié ici puisque j'ai besoin
		// d'initialiser mes variables après avoir déclaré la taille de ma
		// fenêtre
		
		public static int SOL = Toolkit.getDefaultToolkit().getScreenSize().height;
		public static int PLAFOND = 0;
		public static int BORD_GAUCHE = 0;
		public static int BORD_DROIT = Toolkit.getDefaultToolkit().getScreenSize().width;
		
		// *********************************************************************
		// Constante du diamètre de l'oiseau
		
		public final static int DIAMETRE = 60;
}
