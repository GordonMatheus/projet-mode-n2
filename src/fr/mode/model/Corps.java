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

	/**
	 * Applique les forces en fonction sur tous les obstacles mobiles
	 */
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

	/**
	 * Applique les dégâts causés par un Corps sur un autre objet Corps, mettant à jour ses points de vie ainsi que sa vitesse
	 * @param autreCorps
	 */
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

	/**
	 * Retourne le tableau de position de l'objet
	 * @return le tableau corpsPos
	 */
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

	/**
	 * Retourne le tableau des vitesses de l'objet
	 * @return le tableau corpsSpeed
	 */
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

	/**
	 * Retourne si l'objet est mobile ou non
	 * @return si l'objet est mobile ou non
	 */
	public boolean getMobile() {
		return mobile;
	}

	/**
	 * Retourne le poids de l'objet
	 * @return le poids de l'objet
	 */
	public int getPoids() {
		return poids;
	}


	/**
	 * Modifie la position horizontale et verticale de l'objet
	 * @param tabPos
	 */
	public void setCorpsPos(double [] tabPos) {
		corpsPos[0] = tabPos[0];
		corpsPos[1] = tabPos[1];
	}

	/**
	 * Modifie la position horizontale de l'objet
	 * @param posX
	 */
	public void setCorpsPosX(double posX) {
		corpsPos[0] = posX;
	}

	/**
	 * Modifie la position verticale de l'objet
	 * @param posY
	 */
	public void setCorpsPosY(double posY) {
		corpsPos[1] = posY;
	}

	/**
	 * Modifie la vitesse horizontale et verticale de l'objet
	 * @param tabSpeed
	 */
	public void setCorpsSpeed(double [] tabSpeed) {
		corpsSpeed[0] = tabSpeed[0];
		corpsSpeed[1] = tabSpeed[1];
	}

	/**
	 * Modifie la vitesse horizontale de l'objet
	 * @param speedX
	 */
	public void setCorpsSpeedX(double speedX) {
		corpsSpeed[0] = speedX;
	}

	/**
	 * Modifie la vitesse verticale de l'objet
	 * @param speedY
	 */
	public void setCorpsSpeedY(double speedY) {
		corpsSpeed[1] = speedY;
	}

	/**
	 * Modifie la longueur d'un objet
	 * @param width
	 */
	public void setDimensionsX(int width) {
		dimensions[0] = width;
	}

	/**
	 * Modifie la hauteur d'un objet
	 * @param height
	 */
	public void setDimensionsY(int height) {
		dimensions[1] = height;
	}

	/**
	 * Modifie la mobilité d'un objet
	 * @param mobilite
	 */
	public void setMobile(boolean mobilite) {
		mobile = mobilite;
	}

	/**
	 * Modifie le poids d'un objet
	 * @param p
	 */
	public void setPoids(int p) {
		poids = p;
	}
}
