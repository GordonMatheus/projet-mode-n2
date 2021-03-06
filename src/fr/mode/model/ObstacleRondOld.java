package fr.mode.model;

import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import fr.mode.constantes.Constantes;

/**
 * <b>La classe ObstacleRond represente un obstacle de forme circulaire</b>
 */
public class ObstacleRondOld extends ObstacleOld {

	/** Constructeur de l'objet ObstacleRond,
	 * 	un Obstacle de forme ronde.
	 *
	 * @param double Coordonnee sur l'axe x
	 * @param double Coordonnee sur l'axe y
	 * @param int Diametre du rond
	 */
	public ObstacleRondOld(double pos0, double pos1, int diametre) {
		this.dimensions[0] = diametre;
		this.dimensions[1] = diametre;
		this.ObstaclePos[0] = pos0;
		this.ObstaclePos[1] = pos1;
		coordA[0]=pos0;
		coordA[1]=pos1;
	}

	/**
	 * Calcule la distance entre l'obstacle rond et l'oiseau pour
	 * determiner s'ils entrent en collision a partir de la distance
	 * entre les deux et le diametre de l'oiseau et de l'obstacle.
	 *
	 * @return s'il y a collision entre l'oiseau et un obstacle.
	 */
	boolean collision() {
		return ( new Rectangle((int)getObstaclePosX()+getDimensionsHeight()/2,(int)getObstaclePosY()+getDimensionsHeight()/2, getDimensionsHeight(), getDimensionsLenght()).intersects(new Rectangle(((int)AngryBirdsModel.oiseau.corpsPos[0])-(Constantes.DIAMETRE/2),((int)AngryBirdsModel.oiseau.corpsPos[1])-(Constantes.DIAMETRE/2)+10,Constantes.DIAMETRE,Constantes.DIAMETRE)));
	}
}
