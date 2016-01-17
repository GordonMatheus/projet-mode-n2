package fr.mode.model;

import java.awt.Rectangle;

import fr.mode.constantes.Constantes;

/**
 * <b>La classe abstraite Corps represente un corps, qu'il soit obstacle ou
 * oiseau.</b>
 * <p>
 * Un Corps est caracterise par les informations suivantes :
 * <ul>
 * <li>Sa position</li>
 * <li>Ses dimensions</li>
 * </ul>
 * </p>
 *
 */

public abstract class Corps {
	/*--- Variables de la classe ---*/

	/**
	 * Entier permettant la répartition proportionelle d'énergie en cas de collision.
	 */
	public int poids=10;

	/**
	 * Tableau de 2 cases ( = x,y ) pour la position du corps.
	 */
	public double corpsPos[] = new double[2];

	/**
	 * Tableau de 2 cases ( = x,y ) pour la vitesse du corps.
	 */
	public double corpsSpeed[] = new double[2];

	/**
	 * Tableau de 2 cases ( = x,y ) pour les dimensions du corps.
	 */
	public int dimensions[] = new int[2];

	/**
	 * Booléen indiquant si un corps est en mouvement ou non,
	 * évitant les calculs de déplacement inutiles.
	 */
	public boolean mobile;

	/*--- Méthodes de la classe ---*/

	void mouvement() {
		if (this.getMobile()) {
			corpsSpeed[1] += 0.1;

			corpsPos[0] += corpsSpeed[0];
			corpsPos[1] += corpsSpeed[1];
		}
	}

	boolean testCollision(Corps autreCorps) {
		return ( new Rectangle((int)this.getCorpsPosX(),(int)this.getCorpsPosY(), this.getDimensionsHeight(), this.getDimensionsLenght()).intersects(new Rectangle(((int)autreCorps.getCorpsPosX())-(Constantes.DIAMETRE/2),((int)autreCorps.getCorpsPosY())-(Constantes.DIAMETRE/2)+10,Constantes.DIAMETRE,Constantes.DIAMETRE)));
	}

	void appliquerCollision(Corps autreCorps){
		if(this instanceof Obstacle)
			((Obstacle)this).vie-= this.getCorpsSpeedX()+this.getCorpsSpeedY()+autreCorps.getCorpsSpeedX()+autreCorps.getCorpsSpeedY();
		if(this instanceof Obstacle)
			((Obstacle)this).vie-= this.getCorpsSpeedX()+this.getCorpsSpeedY()+autreCorps.getCorpsSpeedX()+autreCorps.getCorpsSpeedY();
		if (!this.getMobile())
			this.setMobile(true);
		if (!autreCorps.getMobile())
			autreCorps.setMobile(true);

		int speedX = (int)((this.getCorpsSpeedX()+ autreCorps.getCorpsSpeedX())/2-0.5);
		int speedY = (int)((this.getCorpsSpeedY()+ autreCorps.getCorpsSpeedY())/2-0.5);
		this.setCorpsSpeedX(speedX * (this.getPoids()*100)/this.getPoids()+autreCorps.getPoids());
		this.setCorpsSpeedY(speedY * (this.getPoids()*100)/this.getPoids()+autreCorps.getPoids());
		autreCorps.setCorpsSpeedX(-speedX * (autreCorps.getPoids()*100)/this.getPoids()+autreCorps.getPoids());
		autreCorps.setCorpsSpeedY(-speedY * (autreCorps.getPoids()*100)/this.getPoids()+autreCorps.getPoids());

	}

	public double [] getCorpsPos() {
		return corpsPos;
	}

	/**
	 * Retourne la position du corps sur l'axe x (axe horizontal).
	 *
	 * @return double Coordonnee horizontale du corps.
	 */
	public double getCorpsPosX() {
		return corpsPos[0];
	}

	/**
	 * Retourne la position du corps sur l'axe y (axe vertical).
	 *
	 * @return double Coordonnee verticale du corps.
	 */
	public double getCorpsPosY() {
		return corpsPos[1];
	}

	/**
	 * Retourne la largeur du corps.
	 *
	 * @return int Largeur du corps (axe horizontal).
	 */
	public int getDimensionsHeight() {
		return dimensions[0];
	}

	public double [] getCorpsSpeed() {
		return corpsSpeed;
	}

	/**
	 * Retourne la vitesse du corps sur l'axe x (axe horizontal).
	 *
	 * @return double Vitesse horizontale du corps.
	 */
	public double getCorpsSpeedX() {
		return corpsSpeed[0];
	}

	/**
	 * Retourne la vitesse du corps sur l'axe y (axe vertical).
	 *
	 * @return double Vitesse verticale du corps.
	 */
	public double getCorpsSpeedY() {
		return corpsSpeed[1];
	}

	/**
	 * Retourne la largeur de du corps.
	 *
	 * @return int Largeur du corps (axe horizontal).
	 */
	public int getDimensionsLenght() {
		return dimensions[1];
	}

	public boolean getMobile() {
		return mobile;
	}

	public int getPoids() {
		return poids;
	}



	public void setCorpsPos(double [] pos) {
		corpsPos[0] = pos[0];
		corpsPos[1] = pos[1];
	}

	public void setCorpsPosX(double pos) {
		corpsPos[0] = pos;
	}

	public void setCorpsPosY(double pos) {
		corpsPos[1] = pos;
	}

	public void setCorpsSpeed(double [] speed) {
		corpsSpeed[0] = speed[0];
		corpsSpeed[1] = speed[1];
	}

	public void setCorpsSpeedX(double speed) {
		corpsSpeed[0] = speed;
	}

	public void setCorpsSpeedY(double speed) {
		corpsSpeed[1] = speed;
	}

	public void setDimensionsX(int dim) {
		dimensions[0] = dim;
	}

	public void setDimensionsY(int dim) {
		dimensions[1] = dim;
	}

	public void setMobile(boolean mobilite) {
		mobile = mobilite;
	}

	public void setPoids(int p) {
		poids = p;
	}
}
