package fr.mode.model;

import java.awt.Rectangle;

import fr.mode.constantes.Constantes;

/**
 * <b> La classe ObstacleRond représente un Obstacle circulaire </b>
 *
 */
public class ObstacleRond extends Obstacle{

	/**
	 * Construit un ObstacleRond par défaut
	 */
	public ObstacleRond(){
		super();
		this.setEstRond(true);
	}

	/** Constructeur de l'objet ObstacleRond,
	 * 	un Obstacle de forme ronde.
	 *
	 * @param double Coordonnee sur l'axe x
	 * @param double Coordonnee sur l'axe y
	 * @param int Diametre du rond
	 */
	public ObstacleRond(double pos0, double pos1, int diametre) {
		this.setDimensionsX(diametre);
		this.setDimensionsY(diametre);
		this.setCorpsPosX(pos0);
		this.setCorpsPosY(pos1);
		this.setCorpsSpeedX(0);
		this.setCorpsSpeedY(0);
	}

	/**
	 * Calcule la distance entre l'obstacle rond et l'oiseau pour
	 * determiner s'ils entrent en collision a partir de la distance
	 * entre les deux et le diametre de l'oiseau et de l'obstacle.
	 *
	 * @return s'il y a collision entre l'oiseau et un obstacle.
	 */
	boolean collision() {
		return ( new Rectangle((int)getCorpsPosX()+getDimensionsHeight()/2,(int)getCorpsPosY()+getDimensionsHeight()/2, getDimensionsHeight(), getDimensionsLenght()).intersects(new Rectangle(((int)AngryBirdsModel.oiseau.getCorpsPosX())-(Constantes.DIAMETRE/2),((int)AngryBirdsModel.oiseau.getCorpsPosY())-(Constantes.DIAMETRE/2)+10,Constantes.DIAMETRE,Constantes.DIAMETRE)));
	}
}
