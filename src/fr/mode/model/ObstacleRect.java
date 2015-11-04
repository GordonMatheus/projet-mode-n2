package fr.mode.model;

import fr.mode.constantes.Constantes;

public class ObstacleRect extends Obstacle {
	private int height;
	private int lenght;
	
	public ObstacleRect(double pos0, double pos1, int height, int lenght) {
		this.height = height ;
		this.lenght = lenght ;
		this.ObstaclePos[0]= pos0;
		this.ObstaclePos[1]= pos1;
	}

	@Override
	boolean collision() {
		if(AngryBirdsModel.PlayerPos[0]<this.ObstaclePos[0] && AngryBirdsModel.PlayerPos[0]>(this.ObstaclePos[0]+lenght) )
			if((Math.sqrt(Math.pow(AngryBirdsModel.PlayerPos[0]- ObstaclePos[0] ,2.0)+ Math.pow(AngryBirdsModel.PlayerPos[1]- ObstaclePos[1] ,2.0)))-Constantes.DIAMETRE
					>= (AngryBirdsModel.PlayerPos[0]- ObstaclePos[0])* (Math.sqrt(Math.pow(AngryBirdsModel.PlayerPos[0]- ObstaclePos[0] ,2.0)+ Math.pow(AngryBirdsModel.PlayerPos[1]
							- ObstaclePos[1] ,2.0)))/ (height/2))
				return true;
		else	
			if((Math.sqrt(Math.pow(AngryBirdsModel.PlayerPos[0]- ObstaclePos[0] ,2.0)+ Math.pow(AngryBirdsModel.PlayerPos[1]- ObstaclePos[1] ,2.0)))-Constantes.DIAMETRE
					>= (AngryBirdsModel.PlayerPos[1]- ObstaclePos[1])* (Math.sqrt(Math.pow(AngryBirdsModel.PlayerPos[0]- ObstaclePos[0] ,2.0)+ Math.pow(AngryBirdsModel.PlayerPos[1]- ObstaclePos[1] ,2.0)))/ (lenght/2))
				return true;		
		return false;
	}

}
