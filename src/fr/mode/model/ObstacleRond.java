package fr.mode.model;

import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import fr.mode.constantes.Constantes;

/**
 * La classe ObstacleRond repr�sente un obstacle de forme circulaire
 */
public class ObstacleRond extends Obstacle {

	/** Constructeur de l'objet ObstacleRond, 
	 * 	un Obstacle de forme ronde.
	 * 
	 * @param double Coordonn�e sur l'axe x 	
	 * @param double Coordonn�e sur l'axe y
	 * @param int Diam�tre du rond
	 */
	public ObstacleRond(double pos0, double pos1, int diametre) {
		this.dimensions[0] = diametre;
		this.dimensions[1] = diametre;
		this.ObstaclePos[0] = pos0;
		this.ObstaclePos[1] = pos1;
	}

	/** 
	 * Calcule la distance entre l'obstacle rond et l'oiseau pour 
	 * d�terminer s'ils entrent en collision � partir de la distance
	 * entre les deux et le diam�tre de l'oiseau et de l'obstacle.
	 * 
	 * @return boolean Vrai si la distance entre l'obstacle et l'oiseau est inf�rieure ou �gale � 0, faux sinon.
	 */
	boolean collision() {
		return ((Math.sqrt(Math.pow(AngryBirdsModel.PlayerPos[0]
				- ObstaclePos[0], 2.0)
				+ Math.pow(AngryBirdsModel.PlayerPos[1] - ObstaclePos[1], 2.0))) <= (Constantes.DIAMETRE + dimensions[0]));

	}
}
