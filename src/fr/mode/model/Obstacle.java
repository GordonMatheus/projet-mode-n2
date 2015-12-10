package fr.mode.model;

/**
 * <b>La classe Obstacle represente un obstacle</b>
 * <p>
 * Un Obstacle est caracterise par les informations suivantes :
 * <ul>
 * <li>Sa position</li>
 * <li>Ses dimensions</li>
 * </ul>
 * </p>
 *
 */
public abstract class Obstacle {

	/**
	 * La position de l'obstacle
	 */
	protected double ObstaclePos[]=new double[2];

	/**
	 * Les dimensions de l'obstacle
	 */
	protected int dimensions[]= new int[2];

	/** Retourne la position  de l'obstacle sur l'axe x (axe horizontal).
	 *
	 * @return double Coordonnee horizontale de l'obstacle.
	 */
	public double getObstaclePosX() {
		return ObstaclePos[0];
	}

	/** Retourne la position  de l'obstacle sur l'axe y (axe vertical).
	 *
	 * @return double Coordonnee verticale de l'obstacle.
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

	/** Retourne la largeur de l'obstacle.
	 *
	 * @return int Largeur de l'obstacle (axe horizontal).
	 */
	public int getDimensionsLenght() {
		return dimensions[1];
	}

	/**
	 * Determine s'il y a collision entre un oiseau et un obstacle
	 *
	 * @return boolean Vrai si la distance entre l'obstacle et l'oiseau est inf�rieure ou �gale � 0, faux sinon.
	 */
	abstract boolean collision ();

}
