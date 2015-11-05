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
	// Constructeur de la classe, permet d'initialiser la fen�tre
	// et de lancer la trame
	
	public AngryBirdsModel() {
		
		// *********************************************************************
		// Initialiser la position de d�part
		
		PlayerPos[0] = 10;
		PlayerPos[1] = 400;
		
		// **********************************************************************
		// Initialiser la vitesse de d�part
		
		PlayerSpeed[0] = 5;
		PlayerSpeed[1] = -5;
			
		// **********************************************************************
		// Initialiser la physique � "true"
		
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
	// Classe interne h�ritant de TimerTask pour red�finir la m�thode run
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
	// M�thode g�rant toute la physique appliqu�e � nos objets dessin�s par le panel
	
	public static void trame() {
		if (g)
			PlayerSpeed[1]+=0.1;
				
		PlayerPos[0]+=PlayerSpeed[0];
		PlayerPos[1]+=PlayerSpeed[1];

		System.out.println("PlayerPos : " + PlayerPos[0] + ":" + PlayerPos[1]);
		System.out.println("PlayerSpeed : " + PlayerSpeed[0] + ":" + PlayerSpeed[1]);
	}
	
	// *********************************************************************
	// M�thode permettant de savoir si les conditions d'arr�t de l'animation
	// en cours sont respect�es � chaque frame ou non
	
	public static boolean poursuiteAnim () {
		// Encore � faire - ce return n'est que provisoire
		
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
	// Compteur du nombre de vols r�alis�s
	
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
	// Liste des obstacles pr�sents 
	
	public static ArrayList<Obstacle> listeObstacles = new ArrayList<Obstacle>();

	
	// *********************************************************************
	// Bool�en pour v�rifier si notre objet a encore besoin d'�tre
	// soustrait aux lois de la physique
	
	public static boolean g = true;
	
	// *********************************************************************
	// Le Timer de l'animation
	
	public static Timer timer;

}