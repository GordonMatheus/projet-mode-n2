package fr.mode.model;

import java.awt.Rectangle;

import fr.mode.constantes.Constantes;

/**
 * <b> La classe ObstacleRect représente un Obstacle rectangulaire </b>
 */
public class ObstacleRect extends Obstacle {

	/**
	 * Contruit un ObstacleRect par défaut
	 */
	public ObstacleRect() {
		super();
		this.setEstRond(false);
	}

	/** Constructeur de l'objet ObstacleRect,
	 * 	un Obstacle de forme rectangulaire.
	 *
	 * @param double Coordonnee sur l'axe x
	 * @param double Coordonnee sur l'axe y
	 * @param int Largeur de l'obstacle (axe horizontal)
	 * @param int Longueur de l'obstacle (axe vertical).
	 */
	public ObstacleRect(double pos0, double pos1, int height, int length) {
		this.setDimensionsX(height);
		this.setDimensionsY(length);
		this.setCorpsPosX(pos0);
		this.setCorpsPosY(pos1);
		this.setCorpsSpeedX(0);
		this.setCorpsSpeedY(0);
	}

	/**
	 * Verifie si deux rectangles se touchent
	 *
	 * @return s'il y a collision entre l'oiseau et un obstacle.
	 */
	boolean collision() {
		return ( new Rectangle((int)getCorpsPosX(),(int)getCorpsPosY(), getDimensionsHeight(), getDimensionsLenght()).intersects(new Rectangle(((int)AngryBirdsModel.oiseau.getCorpsPosX())-(Constantes.DIAMETRE/2),((int)AngryBirdsModel.oiseau.getCorpsPosY())-(Constantes.DIAMETRE/2)+10,Constantes.DIAMETRE,Constantes.DIAMETRE)));

	}
}
