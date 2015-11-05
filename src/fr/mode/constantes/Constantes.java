package fr.mode.constantes;

import java.awt.Toolkit;

public class Constantes {

	// *********************************************************************
		// Constantes pour les bords de la sc�ne
		// L'attribut "final" n'est pas sp�cifi� ici puisque j'ai besoin
		// d'initialiser mes variables apr�s avoir d�clar� la taille de ma
		// fen�tre
		
		public static int SOL = Toolkit.getDefaultToolkit().getScreenSize().height;
		public static int PLAFOND = 0;
		public static int BORD_GAUCHE = 0;
		public static int BORD_DROIT = Toolkit.getDefaultToolkit().getScreenSize().width;
		
		// *********************************************************************
		// Constante du diam�tre de l'oiseau
		
		public final static int DIAMETRE = 60;
}
