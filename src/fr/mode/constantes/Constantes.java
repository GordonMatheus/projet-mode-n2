package fr.mode.constantes;

import java.awt.Toolkit;

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
