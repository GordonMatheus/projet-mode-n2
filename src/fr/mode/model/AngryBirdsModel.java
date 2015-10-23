package fr.mode.model;

import java.awt.Toolkit;
import java.util.Timer;
import java.util.TimerTask;

import fr.mode.constantes.Constantes;
import fr.mode.observer.Observable;
import fr.mode.observer.Observer;
import fr.mode.view.AngryBirdsView;

public class AngryBirdsModel implements Observable {
	/*
	 * CONSTRUCTEUR DE LA CLASSE
	 */
	
	// *********************************************************************
	// Constructeur de la classe, permet d'initialiser la fenêtre
	// et de lancer la trame
	
	public AngryBirdsModel(String title, int milliseconds) {
		
		vue = new AngryBirdsView(title);
		
		timer = new Timer();
		timer.schedule(new FrameTask(milliseconds), milliseconds);
	}
	
	// *********************************************************************
	// Classe internet héritant de TimerTask pour redéfinir la méthode run
	// et utiliser le Timer
	
	static class FrameTask extends TimerTask {
		
		int milli;
		
		public FrameTask (int milli) {
			this.milli = milli;
		}
		
		public void run () {
			
			while (poursuiteAnim()) {
				trame(milli);
				milli++;
			}
		}
	}

	/*
	 * METHODES DE CLASSE
	 */
	
	// *********************************************************************
	// Méthode gérant toute la physique appliquée à nos objets dessinés par le panel
	
	public static void trame (int milli) {
			
		// *********************************************************************
		// Initialiser la position de départ
		
		PlayerPos[0] = 10;
		PlayerPos[1] = 400;
		
		// **********************************************************************
		// Initialiser la vitesse de départ
		
		PlayerSpeed[0] = 0;
		PlayerSpeed[1] = 9;
			
			/* On initialise le buffer */
			int Buffer = milli;
		
			g = true;
			
			// Vérifications que l'oiseau est dans la scène
			if ((PlayerPos[0]+Constantes.DIAMETRE) > Constantes.BORD_GAUCHE 
					&& (PlayerPos[0]+Constantes.DIAMETRE) < Constantes.BORD_DROIT 
					&& (PlayerPos[1]+Constantes.DIAMETRE) > Constantes.PLAFOND 
					&& (PlayerPos[1]+Constantes.DIAMETRE) < Constantes.SOL) {
			
				if (PlayerPos[1]+Constantes.DIAMETRE >= Constantes.SOL) {
					/* gestion du rebond */
//					if (PlayerSpeed[1] > 1)
//						PlayerSpeed[1] = -(PlayerSpeed[1]*.9);
					/* gestion de la friction */
//					if (PlayerSpeed[0] > .01)
//						PlayerSpeed[0]*=.8;
//					else
//						PlayerSpeed[0] = 0;
//						PlayerSpeed[1] = 0;
//						
//					PlayerPos[1] = 400;
					g = false;
				}
				
				if (g)
					PlayerSpeed[1]+=0.1;
				
				PlayerPos[0]+=PlayerSpeed[0];
				PlayerPos[1]+=PlayerSpeed[1];
				
				vue.repaint();
				
				if (milli - Buffer < FrameRate) {
					timer.schedule(new FrameTask(milli), (long)FrameRate-(milli-Buffer));
				}
			
			} else {
				
				PlayerSpeed[0] = 0;
				PlayerSpeed[1] = 0;
			}
	}
	
	// *********************************************************************
	// Méthode permettant de savoir si les conditions d'arrêt de l'animation
	// en cours sont respectées à chaque frame ou non
	
	public static boolean poursuiteAnim () {
		
		return false;
	}
	
	// *********************************************************************
	// Implementation des methodes de l'interface
	
	public void addObserver(Observer obs) {
		
	}

	public void removeObserver() {
		
	}

	public void notifyObserver(String obs) {
		
	}
	
	/*
	 * VARIABLES GLOBALES
	 */

	// *********************************************************************
	// Une vue
	
	public static AngryBirdsView vue;
	
	// *********************************************************************
	// Panel gérant notre affichage
		
	public static int FrameRate = 12;
	public static int FinalFrameRate = 0;

	// *********************************************************************
	// Tableau de 2 cases ( = x,y ) pour la position du joueur
	
	public static double PlayerPos[] = new double[2];
	
	// *********************************************************************
	// Tableau de 2 cases ( = x,y ) pour la vitesse du joueur
	
	public static double PlayerSpeed[] = new double[2];
	
	// *********************************************************************
	// Booléen pour vérifier si notre objet a encore besoin d'être
	// soustrait aux lois de la physique
	
	public static boolean g = true;
	
	// *********************************************************************
	// Le Timer de l'animation
	
	public static Timer timer;

}