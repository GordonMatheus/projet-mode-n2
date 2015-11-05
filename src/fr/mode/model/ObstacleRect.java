package fr.mode.model;

import fr.mode.constantes.Constantes;

public class ObstacleRect extends Obstacle {
	
	/** Constructeur de l'objet ObstacleRect, 
	 * 	un Obstacle de forme rectangulaire.
	 * 
	 * @param double Coordonée sur l'axe x 	
	 * @param double Coordonée sur l'axe y
	 * @param int Largeur de l'obstacle (axe horizontal)
	 * @param int Longueur de l'obstacle (axe vertical).
	 */
	public ObstacleRect(double pos0, double pos1, int height, int lenght) {
		this.dimensions[0] = height ;
		this.dimensions[1] = lenght ;
		this.ObstaclePos[0]= pos0;
		this.ObstaclePos[1]= pos1;
	}

	/** 
	 * Calcule la distance entre les extrémités de l'obstacle rectangulaire 
	 * et l'oiseau pour déterminer s'ils entrent en collision à partir de la 
	 * distance entre les deux et le diamètre de l'oiseau et de l'obstacle.
	 * 
	 * @return boolean Vrai si la distance entre le bord de l'obstacle et l'oiseau est inférieure ou égale à 0, faux sinon.
	 */
	@Override
	boolean collision() {
		if(AngryBirdsModel.PlayerPos[0]<this.ObstaclePos[0] && AngryBirdsModel.PlayerPos[0]>(this.ObstaclePos[0]+dimensions[1]) )
			if((Math.sqrt(Math.pow(AngryBirdsModel.PlayerPos[0]- ObstaclePos[0] ,2.0)+ Math.pow(AngryBirdsModel.PlayerPos[1]- ObstaclePos[1] ,2.0)))-Constantes.DIAMETRE
					>= (AngryBirdsModel.PlayerPos[0]- ObstaclePos[0])* (Math.sqrt(Math.pow(AngryBirdsModel.PlayerPos[0]- ObstaclePos[0] ,2.0)+ Math.pow(AngryBirdsModel.PlayerPos[1]
							- ObstaclePos[1] ,2.0)))/ (dimensions[0]/2))
				return true;
		else	
			if((Math.sqrt(Math.pow(AngryBirdsModel.PlayerPos[0]- ObstaclePos[0] ,2.0)+ Math.pow(AngryBirdsModel.PlayerPos[1]- ObstaclePos[1] ,2.0)))-Constantes.DIAMETRE
					>= (AngryBirdsModel.PlayerPos[1]- ObstaclePos[1])* (Math.sqrt(Math.pow(AngryBirdsModel.PlayerPos[0]- ObstaclePos[0] ,2.0)+ Math.pow(AngryBirdsModel.PlayerPos[1]- ObstaclePos[1] ,2.0)))/ (dimensions[1]/2))
				return true;		
		return false;
	}

}
