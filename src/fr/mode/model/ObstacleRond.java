package fr.mode.model;

import fr.mode.constantes.Constantes;

public class ObstacleRond extends Obstacle {
	
	public ObstacleRond(double pos0, double pos1, int diametre) {
		this.dimensions[0] = diametre ;
		this.dimensions[1] = diametre ;
		this.ObstaclePos[0]= pos0;
		this.ObstaclePos[1]= pos1;
	}

	@Override
	boolean collision() {
		return ( (Math.sqrt(Math.pow(AngryBirdsModel.PlayerPos[0]- ObstaclePos[0] ,2.0)+ Math.pow(AngryBirdsModel.PlayerPos[1]- ObstaclePos[1] ,2.0)))
				<= (Constantes.DIAMETRE + dimensions[0]));
	}
}
