package fr.mode.model;

import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

import fr.mode.constantes.Constantes;
import fr.mode.view.AngryBirdsView;

public class AngryBirdsModel extends Observable {
	/*
	 * CONSTRUCTEUR DE LA CLASSE
	 */
	
	// *********************************************************************
	// Constructeur de la classe, permet d'initialiser la fen�tre
	// et de lancer la trame
	
	public AngryBirdsModel() {
		
		// *********************************************************************
		// Initialiser la position de d�part
		
		PlayerPos[0] = 10;
		PlayerPos[1] = 400;
		
		// **********************************************************************
		// Initialiser la vitesse de d�part
		
		PlayerSpeed[0] = 13;
		PlayerSpeed[1] = -5;
			
		// **********************************************************************
		// Initialiser la physique � "true"
		
		g = true;
				
		// **********************************************************************
		// Initialiser le timer
		timer = new Timer();
		timer.schedule(new FrameTask(), 0, 24);
	}
	
	/*
	 * CLASSE INTERNE
	 */
	
	// *********************************************************************
	// Classe interne h�ritant de TimerTask pour red�finir la m�thode run
	// et utiliser le Timer
	
	class FrameTask extends TimerTask {
		
		public void run () {
			
			if (poursuiteAnim()) {
				trame();
			} else {
				timer.cancel();
			}
			setChanged();
			notifyObservers();
		}
	}

	/*
	 * METHODES DE CLASSE
	 */
	
	// *********************************************************************
	// M�thode g�rant toute la physique appliqu�e � nos objets dessin�s par le panel
	
	public static void trame() {
			
			// V�rifications que l'oiseau est dans la sc�ne
			if ((PlayerPos[0]+Constantes.DIAMETRE) > Constantes.BORD_GAUCHE 
					&& (PlayerPos[0]+Constantes.DIAMETRE) < Constantes.BORD_DROIT 
					&& (PlayerPos[1]+Constantes.DIAMETRE) > Constantes.PLAFOND 
					&& (PlayerPos[1]+Constantes.DIAMETRE) < Constantes.SOL) {
			
				if (PlayerPos[1]+Constantes.DIAMETRE >= Constantes.SOL) {
					g = false;
				}
				
				if (g)
					PlayerSpeed[1]+=0.1;
				
				PlayerPos[0]+=PlayerSpeed[0];
				PlayerPos[1]+=PlayerSpeed[1];
							
			} else {
				
				PlayerSpeed[0] = 0;
				PlayerSpeed[1] = 0;
			}
			System.out.println("PlayerPos : " + PlayerPos[0] + ":" + PlayerPos[1]);
			System.out.println("PlayerSpeed : " + PlayerSpeed[0] + ":" + PlayerSpeed[1]);
	}
	
	// *********************************************************************
	// M�thode permettant de savoir si les conditions d'arr�t de l'animation
	// en cours sont respect�es � chaque frame ou non
	
	public static boolean poursuiteAnim () {
		
		return (PlayerPos[0]+Constantes.DIAMETRE) > Constantes.BORD_GAUCHE 
		&& (PlayerPos[0]+Constantes.DIAMETRE) < Constantes.BORD_DROIT 
		&& (PlayerPos[1]+Constantes.DIAMETRE) > Constantes.PLAFOND 
		&& (PlayerPos[1]+Constantes.DIAMETRE) < Constantes.SOL;
	}
	
	/*
	 * VARIABLES GLOBALES
	 */

	// *********************************************************************
	// Une vue
	
	public static AngryBirdsView vue;
	
	// *********************************************************************
	// Panel g�rant notre affichage
		
	public static int FrameRate = 12;
	public static int FinalFrameRate = 0;

	// *********************************************************************
	// Tableau de 2 cases ( = x,y ) pour la position du joueur
	
	public static double PlayerPos[] = new double[2];
	
	// *********************************************************************
	// Tableau de 2 cases ( = x,y ) pour la vitesse du joueur
	
	public static double PlayerSpeed[] = new double[2];
	
	// *********************************************************************
	// Bool�en pour v�rifier si notre objet a encore besoin d'�tre
	// soustrait aux lois de la physique
	
	public static boolean g = true;
	
	// *********************************************************************
	// Le Timer de l'animation
	
	public static Timer timer;

}