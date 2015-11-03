package fr.mode.model;

public abstract class Obstacle {
	protected double ObstaclePos[]=new double[2];
	protected int dimensions[]= new int[2];
	
	public double getObstaclePosX() {
		return ObstaclePos[0];
	}
	public double getObstaclePosY() {
		return ObstaclePos[1];
	}

	public int getDimensionsHeight() {
		return dimensions[0];
	}
	public int getDimensionsLenght() {
		return dimensions[1];
	}

	abstract boolean collision (); 
	
}
