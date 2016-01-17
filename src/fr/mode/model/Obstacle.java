package fr.mode.model;

/**
 * <b>La classe Obstacle represente un obstacle</b>
 * <p> Un Obstacle peut être rond ou rectangulaire, et dispose de 100 points de vie <p>
 *
 */
public abstract class Obstacle extends Corps{

	/**
	 * Booléen déterminant si l'Obstacle est rond
	 */
	public boolean estRond;

	/**
	 * La vie de l'Obstacle
	 */
	public int vie= 100;

	/**
	 * Construit un Obstacle
	 */
	public Obstacle(){
		super();
	}

	/**
	 * Méthode permettant de définir une détection de collision
	 * @return
	 */
	abstract boolean collision();

	/**
	 * Retourne si l'Obstacle est rond
	 * @return si l'Obstacle est rond
	 */
	public boolean getEstRond() {
		return estRond;
	}

	/**
	 * Modifie l'état (rond ou rectangulaire)de l'Obstacle
	 * @param rond
	 */
	public void setEstRond(boolean rond) {
		estRond = rond;
	}
}
