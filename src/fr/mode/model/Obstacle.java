package fr.mode.model;

public abstract class Obstacle {
	protected double ObstaclePos[]=new double[2];
	protected int dimensions[]= new int[2];
	
	/** Retourne la position  de l'obstacle sur l'axe x (axe horizontal).
	 * 
	 * @return double Coordonn�e horizontale de l'obstacle.
	 */
	public double getObstaclePosX() {
		return ObstaclePos[0];
	}
	
	/** Retourne la position  de l'obstacle sur l'axe y (axe vertical).
	 * 
	 * @return double Coordonn�e verticale de l'obstacle.
	 */
	public double getObstaclePosY() {
		return ObstaclePos[1];
	}

	/** Retourne la largeur de l'obstacle.
	 * 
	 * @return int Largeur de l'obstacle (axe horizontal).
	 */
	public int getDimensionsHeight() {
		return dimensions[0];
	}
	
	/** Retourne la longueur de l'obstacle.
	 * 
	 * @return int Longueur de l'obstacle (axe horizontal).
	 */
	public int getDimensionsLenght() {
		return dimensions[1];
	}

	/** 
	 * Calcule la distance entre l'obstacle et l'oiseau 
	 * pour d�terminer s'ils entrent en collision.
	 * 
	 * @return boolean Vrai si la distance entre l'obstacle et l'oiseau est inf�rieure ou �gale � 0, faux sinon.
	 */
	abstract boolean collision (); 
}
