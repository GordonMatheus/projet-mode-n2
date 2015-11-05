package fr.mode.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import fr.mode.constantes.Constantes;
import fr.mode.view.AngryBirdsView;

public class AngryBirdsModel extends Observable {
	/*
	 * CONSTRUCTEUR DE LA CLASSE
	 */
	
	// *********************************************************************
	// Constructeur de la classe, permet d'initialiser la fenêtre
	// et de lancer la trame
	
	public AngryBirdsModel() {
		
		// *********************************************************************
		// Initialiser la position de départ
		
		PlayerPos[0] = 10;
		PlayerPos[1] = 400;
		
		// **********************************************************************
		// Initialiser la vitesse de départ
		
		PlayerSpeed[0] = 5;
		PlayerSpeed[1] = -5;
			
		// **********************************************************************
		// Initialiser la physique à "true"
		
		g = true;
				
		// **********************************************************************
		// Initialiser le timer
		timer = new Timer();
		timer.schedule(new FrameTask(), 0, 18);
	}
	
	/*
	 * CLASSE INTERNE
	 */
	
	// *********************************************************************
	// Classe interne héritant de TimerTask pour redéfinir la méthode run
	// et utiliser le Timer
	
	class FrameTask extends TimerTask {
		
		public void run () {
			
			Random r = new Random();
			
			if (poursuiteAnim()) {
				trame();
				cpt++;
			} else {
				if (cpt < 10) {
					//timer.schedule(this, 200);
					PlayerPos[0] = 5 + r.nextInt(15);
					PlayerPos[1] = 50 + r.nextInt(650);
					PlayerSpeed[0] = 5 + r.nextInt(15);
					PlayerSpeed[1] = -1 * r.nextInt(8);
					
					trajectoryX.clear();
					trajectoryY.clear();
					
					setChanged();
					notifyObservers();
				} else {
					timer.cancel();
				}
			}
			setChanged();
			notifyObservers();
			
			trajectoryX.add((int)PlayerPos[0]);
			trajectoryY.add((int)PlayerPos[1]);
		}
	}

	/*
	 * METHODES DE CLASSE
	 */
	
	// *********************************************************************
	// Méthode gérant toute la physique appliquée à nos objets dessinés par le panel
	
	public static void trame() {
		if (g)
			PlayerSpeed[1]+=0.1;
				
		PlayerPos[0]+=PlayerSpeed[0];
		PlayerPos[1]+=PlayerSpeed[1];

		System.out.println("PlayerPos : " + PlayerPos[0] + ":" + PlayerPos[1]);
		System.out.println("PlayerSpeed : " + PlayerSpeed[0] + ":" + PlayerSpeed[1]);
	}
	
	// *********************************************************************
	// Méthode permettant de savoir si les conditions d'arrêt de l'animation
	// en cours sont respectées à chaque frame ou non
	
	public static boolean poursuiteAnim () {
		// Encore à faire - ce return n'est que provisoire
		
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
	// Panel gérant notre affichage
		
	public static int FrameRate = 12;
	public static int FinalFrameRate = 0;
	
	// *********************************************************************
	// Compteur du nombre de vols réalisés
	
	public int cpt = 0;

	// *********************************************************************
	// Tableau de 2 cases ( = x,y ) pour la position du joueur
	
	public static double PlayerPos[] = new double[2];
	
	// *********************************************************************
	// Tableau de 2 cases ( = x,y ) pour la vitesse du joueur
	
	public static double PlayerSpeed[] = new double[2];
	
	// *********************************************************************
	// Tableau des positions
	
	public static List<Integer> trajectoryX = new ArrayList<Integer>();
	public static List<Integer> trajectoryY = new ArrayList<Integer>();
	
	// *********************************************************************
	// Liste des obstacles présents 
	
	public static ArrayList<Obstacle> listeObstacles = new ArrayList<Obstacle>();

	
	// *********************************************************************
	// Booléen pour vérifier si notre objet a encore besoin d'être
	// soustrait aux lois de la physique
	
	public static boolean g = true;
	
	// *********************************************************************
	// Le Timer de l'animation
	
	public static Timer timer;

}