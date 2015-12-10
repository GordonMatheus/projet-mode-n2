package fr.mode.model;

import java.awt.Rectangle;

import fr.mode.constantes.Constantes;

/**
 * <b>La classe ObstacleRect represente un obstacle
 * de forme rectangulaire</b>
 *
 */
public class ObstacleRect extends Obstacle {

	/** Constructeur de l'objet ObstacleRect,
	 * 	un Obstacle de forme rectangulaire.
	 *
	 * @param double Coordonnee sur l'axe x
	 * @param double Coordonnee sur l'axe y
	 * @param int Largeur de l'obstacle (axe horizontal)
	 * @param int Longueur de l'obstacle (axe vertical).
	 */
	public ObstacleRect(double pos0, double pos1, int height, int lenght) {
		this.dimensions[0] = height;
		this.dimensions[1] = lenght;
		this.ObstaclePos[0] = pos0;
		this.ObstaclePos[1] = pos1;
	}

	/**
	 * Verifie si deux rectangles se touchent
	 *
	 * @return s'il y a collision entre l'oiseau et un obstacle.
	 */
	boolean collision() {
		return ( new Rectangle((int)getObstaclePosX(),(int)getObstaclePosY(), getDimensionsHeight(), getDimensionsLenght()).intersects(new Rectangle(((int)AngryBirdsModel.PlayerPos[0])-(Constantes.DIAMETRE/2),((int)AngryBirdsModel.PlayerPos[1])-(Constantes.DIAMETRE/2)+10,Constantes.DIAMETRE,Constantes.DIAMETRE)));

	}
}
