package fr.mode.model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import fr.mode.constantes.Constantes;
import fr.mode.view.AngryBirdsView;

/**
 * <b>La classe AngryBirdsModel repr�sente le moteur physique de l'application</b>
 *
 */
public class AngryBirdsModel extends Observable {

	/*
	 * VARIABLES GLOBALES
	 */

	/**
	 * La vue associee au modele
	 */
	public static AngryBirdsView vue;

	/**
	 * Le compteur du nombre de vols realises
	 */
	public int cpt = 0;

	/**
	 *  Tableau de 2 cases ( = x,y ) pour la position du joueur
	 */
	public static double PlayerPos[] = new double[2];

	/**
	 *  Tableau de 2 cases ( = x,y ) pour la vitesse du joueur
	 */
	public static double PlayerSpeed[] = new double[2];

	/**
	 * Liste des positions en X de l'oiseau
	 */
	public static List<Integer> trajectoryX = new ArrayList<Integer>();

	/**
	 * Liste des positions en Y de l'oiseau
	 */
	public static List<Integer> trajectoryY = new ArrayList<Integer>();

	/**
	 *  Liste des obstacles pr�sents
	 */
	public static ArrayList<Obstacle> listeObstacles = new ArrayList<Obstacle>();

	/**
	 *  Le Timer de l'animation
	 */
	public static Timer timer;

	/**
	 * La couleur de l'oiseau
	 */

	static Color coloBird;

	 /*
	 * CONSTRUCTEUR DE LA CLASSE
	 */

	// *********************************************************************
	// Constructeur de la classe, permet d'initialiser la fen�tre
	// et de lancer la trame

	/**
	 * Construit un AngryBirdsModel
	 *
	 */
	public AngryBirdsModel() {

		coloBird = Color.yellow;

		// *********************************************************************
		// Initialiser la position de d�part

		PlayerPos[0] = 10;
		PlayerPos[1] = 400;

		// **********************************************************************
		// Initialiser la vitesse de d�part

		PlayerSpeed[0] = 8;
		PlayerSpeed[1] = -5;

		// **********************************************************************
		// Initialiser le timer
		timer = new Timer();
		timer.schedule(new FrameTask(), 0, 16);
	}

	/*
	 * CLASSE INTERNE
	 */

	// *********************************************************************
	// Classe interne h�ritant de TimerTask pour red�finir la m�thode run
	// et utiliser le Timer

	/**
	 * La classe interne FrameTask represente la tache repetee par le timer
	 *
	 */
	class FrameTask extends TimerTask {

		/**
		 * Detecte s'il faut lancer la methode trame(),
		 * gere les tours de boucle et determine
		 * une position de depart et des forces aleatoires
		 */
		public void run() {

			Random r = new Random();

			if (poursuiteAnim()) {
				trame();

			} else {
				if (cpt < 10) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					cpt++;
					coloBird = Color.yellow;

					// timer.schedule(this, 200);
					PlayerPos[0] = 5 + r.nextInt(15);
					PlayerPos[1] = 350 + r.nextInt(350);
					//PlayerSpeed[0] = 5+ r.nextInt(10);
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

			trajectoryX.add((int) PlayerPos[0]);
			trajectoryY.add((int) PlayerPos[1]);
		}
	}

	/*
	 * METHODES DE CLASSE
	 */

	// *********************************************************************
	// M�thode g�rant toute la physique appliqu�e � nos objets dessin�s par le
	// panel

	/**
	 * Applique les forces et enregistre la trajectoire de l'oiseau
	 */
	public static void trame() {

		PlayerSpeed[1] += 0.1;

		PlayerPos[0] += PlayerSpeed[0];
		PlayerPos[1] += PlayerSpeed[1];
	}

	// *********************************************************************
	// M�thode permettant de savoir si les conditions d'arr�t de l'animation
	// en cours sont respect�es � chaque frame ou non

	/**
	 * Verifie si l'oiseau est toujours dans l'ecran et s'il ne touche pas un obstacle
	 * (change sa couleur dans le cas contraire)
	 *
	 * @return si l'animation doit se poursuivre ou non
	 */
	public  boolean poursuiteAnim() {
		// Encore � faire - ce return n'est que provisoire

		for (Obstacle i : listeObstacles) {
			if (i.collision()){
				System.out.println("obstacle");
				coloBird = Color.red;
				setChanged();
				notifyObservers();
				return false;}
		}
		return (PlayerPos[0] + Constantes.DIAMETRE) > Constantes.BORD_GAUCHE
				&& (PlayerPos[0] + Constantes.DIAMETRE) < Constantes.BORD_DROIT
				&& (PlayerPos[1] + Constantes.DIAMETRE) > Constantes.PLAFOND
				&& (PlayerPos[1] + Constantes.DIAMETRE) < Constantes.SOL;
	}

	/**
	 * Retourne la couleur de l'oiseau
	 *
	 * @return la couleur de l'oiseau
	 */
	public Color getColorBird(){
		return coloBird;
	}
}