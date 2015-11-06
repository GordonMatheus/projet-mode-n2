package fr.mode.model;

import fr.mode.constantes.Constantes;

public class ObstacleRond extends Obstacle {
	
	/** Constructeur de l'objet ObstacleRond, 
	 * 	un Obstacle de forme ronde.
	 * 
	 * @param double Coordonée sur l'axe x 	
	 * @param double Coordonée sur l'axe y
	 * @param int Diamètre du rond
	 */
	public ObstacleRond(double pos0, double pos1, int diametre) {
		this.dimensions[0] = diametre ;
		this.dimensions[1] = diametre ;
		this.ObstaclePos[0]= pos0;
		this.ObstaclePos[1]= pos1;
	}
	
	/** 
	 * Calcule la distance entre l'obstacle rond et l'oiseau pour 
	 * déterminer s'ils entrent en collision à partir de la distance
	 * entre les deux et le diamètre de l'oiseau et de l'obstacle.
	 * 
	 * @return boolean Vrai si la distance entre l'obstacle et l'oiseau est inférieure ou égale à 0, faux sinon.
	 */
	@Override
	boolean collision() {
		return ( (Math.sqrt(Math.pow(AngryBirdsModel.PlayerPos[0]- ObstaclePos[0] ,2.0)+ Math.pow(AngryBirdsModel.PlayerPos[1]- ObstaclePos[1] ,2.0)))
				<= (Constantes.DIAMETRE + dimensions[0]));
	}
}
