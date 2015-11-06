package fr.mode.model;

import fr.mode.constantes.Constantes;

/**
 * <b>La classe ObstacleRect</b>
 * <p>
 * Elle repr�sente un obstacle de forme rectangulaire, et permet
 * de calculer les conditions de sa collision avec l'oiseau. 
 * </p>
 */
public class ObstacleRect extends Obstacle {
	
	/** Constructeur de l'objet ObstacleRect, 
	 * 	un Obstacle de forme rectangulaire.
	 * 
	 * @param double Coordon�e sur l'axe x 	
	 * @param double Coordon�e sur l'axe y
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
	 * Calcule la distance entre les extr�mit�s de l'obstacle rectangulaire 
	 * et l'oiseau pour d�terminer s'ils entrent en collision � partir de la 
	 * distance entre les deux et le diam�tre de l'oiseau et de l'obstacle.
	 * 
	 * @return boolean Vrai si la distance entre le bord de l'obstacle et l'oiseau est inf�rieure ou �gale � 0, faux sinon.
	 */
	@Override
	boolean collision() {
		//   Calcule si l'oiseau est en dessous de l'obstacle ou non, afin d'appliquer le th�or�me de Thales avec la longueur ou la largeur de l'obstacle.
		if(AngryBirdsModel.PlayerPos[0]<this.ObstaclePos[0] && AngryBirdsModel.PlayerPos[0]>(this.ObstaclePos[0]+dimensions[1]) )
			//   Calcule si la distance entre l'oiseau et l'obstacle, soustait � la moiti� de du diam�tre de l'oiseau, est sup�rieure ou �gale la distance entre le centre de l'obstacle et l'une de ses extr�mit�s.
			if((Math.sqrt(Math.pow(AngryBirdsModel.PlayerPos[0]- ObstaclePos[0] ,2.0)+ Math.pow(AngryBirdsModel.PlayerPos[1]- ObstaclePos[1] ,2.0)))-(Constantes.DIAMETRE/2)
					>= (AngryBirdsModel.PlayerPos[0]- ObstaclePos[0])* (Math.sqrt(Math.pow(AngryBirdsModel.PlayerPos[0]- ObstaclePos[0] ,2.0)+ Math.pow(AngryBirdsModel.PlayerPos[1]- ObstaclePos[1] ,2.0)))/ (dimensions[0]/2))
				return true;
		else
			//   Calcule si la distance entre l'oiseau et l'obstacle, soustait � la moiti� de du diam�tre de l'oiseau, est sup�rieure ou �gale la distance entre le centre de l'obstacle et l'une de ses extr�mit�s.  
			if((Math.sqrt(Math.pow(AngryBirdsModel.PlayerPos[0]- ObstaclePos[0] ,2.0)+ Math.pow(AngryBirdsModel.PlayerPos[1]- ObstaclePos[1] ,2.0)))-(Constantes.DIAMETRE/2)
					>= (AngryBirdsModel.PlayerPos[1]- ObstaclePos[1])* (Math.sqrt(Math.pow(AngryBirdsModel.PlayerPos[0]- ObstaclePos[0] ,2.0)+ Math.pow(AngryBirdsModel.PlayerPos[1]- ObstaclePos[1] ,2.0)))/ (dimensions[1]/2))
				return true;		
		return false;
	}

}
