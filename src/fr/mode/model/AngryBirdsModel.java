package fr.mode.model;

import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;

import fr.mode.constantes.Constantes;
import fr.mode.view.AngryBirdsView;

/**
 * <b>La classe AngryBirdsModel repr�sente le moteur physique de
 * l'application</b>
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
	 * Compteur interne au modèle servant à calculer l'angle du bec en fonction du point de la trajectoire sur lequel l'oiseau est
	 */
	public int cpt_angle;

	/**
	 * Tableau de 2 cases ( = x,y ) pour la position du joueur
	 */
	public static double PlayerPos[] = new double[2];

	/**
	 * Tableau de 2 cases ( = x,y ) pour la vitesse du joueur
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
	 * Liste des obstacles pr�sents
	 */
	public static ArrayList<Obstacle> listeObstacles = new ArrayList<Obstacle>();

	/**
	 * Le Timer de l'animation
	 */
	public static Timer timer;

	/**
	 * Image de l'oiseau
	 */

	static Image img = null;

	/**
	 * Etat de l'oiseau
	 */

	/* 0 - Normal ; 1 - Collision */

	public static int etat;

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

		trame();
		try {
			etat = 0;
		} catch (Exception e) {
			e.printStackTrace();
		}

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

		cpt_angle = 0;
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
		 * Detecte s'il faut lancer la methode trame(), gere les tours de boucle
		 * et determine une position de depart et des forces aleatoires
		 */
		public void run() {

			Random r = new Random();

			if (Constantes.estLance) {

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
						etat = 0;

						PlayerPos[0] = 50;
						PlayerPos[1] = 400;

						PlayerSpeed[1] = -1 * r.nextInt(8);

						Constantes.estLance = false;

						trajectoryX.clear();
						trajectoryY.clear();

						setCptAngle(0);
						setChanged();
						notifyObservers();

					} else {
						timer.cancel();
					}
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
	// M�thode g�rant toute la physique appliqu�e � nos objets
	// dessin�s par le
	// panel

	/**
	 * Applique les forces et enregistre la trajectoire de l'oiseau
	 */
	public static void trame() {

		for (Obstacle o : AngryBirdsModel.listeObstacles) {
			o.mouvementObstacle();
		}

		PlayerSpeed[1] += 0.1;

		PlayerPos[0] += PlayerSpeed[0];
		PlayerPos[1] += PlayerSpeed[1];
	}

	// *********************************************************************
	// M�thode permettant de savoir si les conditions d'arr�t de l'animation
	// en cours sont respect�es � chaque frame ou non

	/**
	 * Verifie si l'oiseau est toujours dans l'ecran et s'il ne touche pas un
	 * obstacle (change sa couleur dans le cas contraire)
	 *
	 * @return si l'animation doit se poursuivre ou non
	 */
	public boolean poursuiteAnim() {

		for (Obstacle i : listeObstacles) {

			if (i.collision()) {

				try {
					etat = 1;
					img = ImageIO.read(new File(
							"ressources/oiseau_vener_collision.png"));
				} catch (Exception e) {
					e.printStackTrace();
				}

				setChanged();
				notifyObservers();

				return false;
			}
		}

		return (PlayerPos[0] + Constantes.DIAMETRE) > Constantes.BORD_GAUCHE
				&& (PlayerPos[0] + Constantes.DIAMETRE) < Constantes.BORD_DROIT
				&& (PlayerPos[1] + Constantes.DIAMETRE) > Constantes.PLAFOND
				&& (PlayerPos[1] + Constantes.DIAMETRE) < Constantes.SOL;
	}

	/**
	 * Retourne un tableau d'entier contenant la position (x,y) de l'oiseau
	 * @return un tableau d'entier contenant la position (x,y) de l'oiseau
	 */
	public static double[] getPlayerPos() {
		return PlayerPos;
	}

	/**
	 * Modifie la position de l'oiseau à la position donnée en paramètre
	 * @param playerPos
	 */
	public void setPlayerPos(double[] playerPos) {
		PlayerPos = playerPos;

		setChanged();
		notifyObservers();
	}

	/**
	 * Retourne un tableau d'entier contenant la vitesse (x,y) de l'oiseau
	 * @return un tableau d'entier contenant la vitesse (x,y) de l'oiseau
	 */
	public static double[] getPlayerSpeed() {
		return PlayerSpeed;
	}

	/**
	 * Modifie la vitesse de l'oiseau à la position donnée en paramètre
	 * @param playerSpeed
	 */
	public void setPlayerSpeed(double[] playerSpeed) {
		PlayerSpeed = playerSpeed;

		setChanged();
		notifyObservers();
	}

	/**
	 * Retourne une liste d'entier des positions x de l'oiseau
	 * @return une liste d'entier des positions x de l'oiseau
	 */
	public List<Integer> getX() {
		return this.trajectoryX;
	}

	/**
	 * Retourne une liste d'entier des positions y de l'oiseau
	 * @return une liste d'entier des positions y de l'oiseau
	 */
	public List<Integer> getY() {
		return this.trajectoryY;
	}

	/**
	 * Retourne l'angle donné entre le sol et la droite formée par la position actuelle de l'oiseau avec sa position suivante
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @return l'angle donné entre le sol et la droite formée par la position actuelle de l'oiseau avec sa position suivante
	 */
	public double getAngle(int x1, int y1, int x2, int y2){
		double angle = Math.atan2((y1 - y2), (x1 -x2));
		if(x1 < x2 && y1 > y2)
			return angle + Math.PI;
		else if(x1 < x2){
			angle += 135;
			return angle;
		}
		return y1 > y2 ? angle : -angle;
	}

	/**
	 * Retourne la distance entre deux points
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @return la distance entre deux points
	 */
	public double distanceEntreDeuxPoints(int x1, int y1, int x2, int y2) {
		return Math.pow((Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2)), 0.5);
	}

	/**
	 * Retourne la valeur de cpt_angle
	 * @return la valeur de cpt_angle
	 */
	public int getCptAngle() {
		return cpt_angle;
	}

	/**
	 * Incrémente la valeur cpt_angle
	 */
	public void cptAngleIncr() {
		cpt_angle++;
	}

	/**
	 * Modifie la variable cpt_angle a la valeur donnée en paramètre
	 * @param cpt_angle
	 */
	public void setCptAngle(int cpt_angle) {
		this.cpt_angle = cpt_angle;
	}
}