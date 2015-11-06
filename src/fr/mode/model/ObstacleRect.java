package fr.mode.model;

import fr.mode.constantes.Constantes;

/**
 * <b>La classe ObstacleRect</b>
 * <p>
 * Elle représente un obstacle de forme rectangulaire, et permet
 * de calculer les conditions de sa collision avec l'oiseau. 
 * </p>
 */
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
		//   Calcule si l'oiseau est en dessous de l'obstacle ou non, afin d'appliquer le théorème de Thales avec la longueur ou la largeur de l'obstacle.
		if(AngryBirdsModel.PlayerPos[0]<this.ObstaclePos[0] && AngryBirdsModel.PlayerPos[0]>(this.ObstaclePos[0]+dimensions[1]) )
			//   Calcule si la distance entre l'oiseau et l'obstacle, soustait à la moitié de du diamètre de l'oiseau, est supérieure ou égale la distance entre le centre de l'obstacle et l'une de ses extrémités.
			if((Math.sqrt(Math.pow(AngryBirdsModel.PlayerPos[0]- ObstaclePos[0] ,2.0)+ Math.pow(AngryBirdsModel.PlayerPos[1]- ObstaclePos[1] ,2.0)))-(Constantes.DIAMETRE/2)
					>= (AngryBirdsModel.PlayerPos[0]- ObstaclePos[0])* (Math.sqrt(Math.pow(AngryBirdsModel.PlayerPos[0]- ObstaclePos[0] ,2.0)+ Math.pow(AngryBirdsModel.PlayerPos[1]- ObstaclePos[1] ,2.0)))/ (dimensions[0]/2))
				return true;
		else
			//   Calcule si la distance entre l'oiseau et l'obstacle, soustait à la moitié de du diamètre de l'oiseau, est supérieure ou égale la distance entre le centre de l'obstacle et l'une de ses extrémités.  
			if((Math.sqrt(Math.pow(AngryBirdsModel.PlayerPos[0]- ObstaclePos[0] ,2.0)+ Math.pow(AngryBirdsModel.PlayerPos[1]- ObstaclePos[1] ,2.0)))-(Constantes.DIAMETRE/2)
					>= (AngryBirdsModel.PlayerPos[1]- ObstaclePos[1])* (Math.sqrt(Math.pow(AngryBirdsModel.PlayerPos[0]- ObstaclePos[0] ,2.0)+ Math.pow(AngryBirdsModel.PlayerPos[1]- ObstaclePos[1] ,2.0)))/ (dimensions[1]/2))
				return true;		
		return false;
	}

}
