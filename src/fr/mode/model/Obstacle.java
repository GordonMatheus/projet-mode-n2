package fr.mode.model;

public abstract class Obstacle extends Corps{

	public boolean estRond;

	public int vie= 100;

	public Obstacle(){
		super();
	}

	abstract boolean collision();

	public boolean getEstRond() {
		return estRond;
	}

	public void setEstRond(boolean rond) {
		estRond = rond;
	}
}
