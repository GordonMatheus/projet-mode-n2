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
 * <b>La classe AngryBirdsModel représente le moteur physique de l'application</b>
 *
 */
public class AngryBirdsModel extends Observable {
	
	/*
	 * VARIABLES GLOBALES
	 */

	/**
	 * La vue associée au modèle
	 */
	public static AngryBirdsView vue;

	/**
	 * Le compteur du nombre de vols réalisés
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
	 *  Liste des obstacles présents
	 */

	public static ArrayList<Obstacle> listeObstacles = new ArrayList<Obstacle>();

	/**
	 *  Le Timer de l'animation
	 */

	public static Timer timer;
	
	/**
	 * La couleur de l'oiseau
	 */
	
	public static Color coloBird;
	
	 /*
	 * CONSTRUCTEUR DE LA CLASSE
	 */
	
	/**
	 * Construit un AngryBirdsModel
	 * 
	 */
	public AngryBirdsModel() {

		// *********************************************************************
		// Initialiser la couleur de l'oiseau
		
		coloBird = Color.yellow;
		
		// *********************************************************************
		// Initialiser la position de départ

		PlayerPos[0] = 10;
		PlayerPos[1] = 400;

		// **********************************************************************
		// Initialiser la vitesse de départ

		PlayerSpeed[0] = 8;
		PlayerSpeed[1] = -5;

		// **********************************************************************
		// Initialiser le timer
		timer = new Timer();
		timer.schedule(new FrameTask(), 0, 18);
	}

	/*
	 * CLASSE INTERNE
	 */

	/**
	 * La classe interne FrameTask représente la tâche répétée par le timer
	 *
	 */
	class FrameTask extends TimerTask {

		/**
		 * Détecte s'il faut lancer la méthode trame(),
		 * gère les tours de boucle et détermine
		 * une position de départ et des forces aléatoires
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

			trajectoryX.add((int)PlayerPos[0]);
			trajectoryY.add((int)PlayerPos[1]);
		}
	}

	/*
	 * METHODES DE CLASSE
	 */

	/**
	 * Applique les forces et enregistre la trajectoire de l'oiseau
	 */
	public static void trame() {
		
		PlayerSpeed[1] += 0.1;
		
		PlayerPos[0] += PlayerSpeed[0];
		PlayerPos[1] += PlayerSpeed[1];
	}

	/**
	 * Vérifie si l'oiseau est toujours dans l'écran et s'il ne touche pas un obstacle
	 * (change sa couleur dans le cas contraire)
	 * 
	 * @return si l'animation doit se poursuivre ou non
	 */
	public  boolean poursuiteAnim() {

		for (Obstacle i : listeObstacles) {
			
			if (i.collision()){
				coloBird = Color.red;
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
	 * Retourne la couleur de l'oiseau
	 * 
	 * @return la couleur de l'oiseau 
	 */
	public Color getColorBird(){
		return coloBird;
	}
	
	/**
	 * Retourne une liste des positions en x de l'oiseau
	 * @return une liste des positions en x de l'oiseau
	 */
	public List<Integer> getTrajectX(){
		return this.trajectoryX;
	}
	
	/**
	 * Retourne une liste des positions en y de l'oiseau
	 * @return une liste des positions en y de l'oiseau
	 */
	public List<Integer> getTrajectY(){
		return this.trajectoryY;
	}
	
	/**
	 * Retourne une liste d'angles
	 * 
	 * @param X Une liste des positions en x de l'oiseau
	 * @param Y Une liste des positions en y de l'oiseau
	 * @return une liste d'angles
	 */
	public List<Double> getListeAngle(List<Integer> X, List<Integer> Y){
		
		List<Double> angles = new ArrayList<Double>();
		double dist1, dist2, dist3;
		double angle;
		
		for(int i = 0; i < X.size() - 1; i++){
			
			dist1 = distanceEntreDeuxPoints(X.get(i), Y.get(i), X.get(i + 1), Y.get(i + 1));
			dist2 = distanceEntreDeuxPoints(X.get(i), Y.get(i), X.get(i + 1), Y.get(i));
			angle = Math.acos(dist2/dist1);
			angles.add(angle);
		}
		
		return angles;
	}
	
	/**
	 * Calcule la distance entre deux points
	 * 
	 * @param x1 La coordonnée x du premier point
	 * @param y1 La coordonnée y du premier point
	 * @param x2 La coordonnée x du second point
	 * @param y2 La coordonnée y du second point
	 * 
	 * @return la distance entre les deux points
	 */
	public double distanceEntreDeuxPoints(int x1, int y1, int x2, int y2){
		return Math.pow(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2), 0.5); 
	}
}