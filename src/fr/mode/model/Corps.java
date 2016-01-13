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
	public static int poids=10;
	
	/**
	 * Tableau de 2 cases ( = x,y ) pour la position du corps.
	 */
	public static double corpsPos[] = new double[2];

	/**
	 * Tableau de 2 cases ( = x,y ) pour la vitesse du corps.
	 */
	public static double corpsSpeed[] = new double[2];

	/**
	 * Tableau de 2 cases ( = x,y ) pour les dimensions du corps.
	 */
	public static int dimensions[] = new int[2];

	/**
	 * Booléen indiquant si un corps est en mouvement ou non,
	 * évitant les calculs de déplacement inutiles.
	 */
	public static boolean mobile;
	
	/*--- Méthodes de la classe ---*/

	void mouvement() {
		if (this.mobile) {
			corpsSpeed[1] += 0.1;

			corpsPos[0] += corpsSpeed[0];
			corpsPos[1] += corpsSpeed[1];
		}
	}

	boolean testCollision(Corps autreCorps) {
		return ( new Rectangle((int)this.getCorpsPosX(),(int)this.getCorpsPosY(), this.getDimensionsHeight(), this.getDimensionsLenght()).intersects(new Rectangle(((int)autreCorps.corpsPos[0])-(Constantes.DIAMETRE/2),((int)autreCorps.corpsPos[1])-(Constantes.DIAMETRE/2)+10,Constantes.DIAMETRE,Constantes.DIAMETRE)));
	}
	
	void collision(Corps autreCorps){
		if(this instanceof Obstacle)
			((Obstacle)this).vie-= this.corpsSpeed[0]+this.corpsSpeed[1]+autreCorps.corpsSpeed[0]+autreCorps.corpsSpeed[1];
		if(this instanceof Obstacle)
			((Obstacle)this).vie-= this.corpsSpeed[0]+this.corpsSpeed[1]+autreCorps.corpsSpeed[0]+autreCorps.corpsSpeed[1];
		if (!this.mobile)
			this.mobile=true;
		if (!autreCorps.mobile)
			autreCorps.mobile=true;
		
		int speedX = (int)((this.corpsSpeed[0]+ autreCorps.corpsSpeed[0])/2-0.5);
		int speedY = (int)((this.corpsSpeed[1]+ autreCorps.corpsSpeed[1])/2-0.5);
		this.corpsSpeed[0]= speedX * (this.poids*100)/this.poids+autreCorps.poids ;
		this.corpsSpeed[0]= speedY * (this.poids*100)/this.poids+autreCorps.poids;
		autreCorps.corpsSpeed[0]= -speedX * (autreCorps.poids*100)/this.poids+autreCorps.poids;
		autreCorps.corpsSpeed[1]= -speedY * (autreCorps.poids*100)/this.poids+autreCorps.poids;
	
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
	 * Retourne la largeur de du corps.
	 * 
	 * @return int Largeur du corps (axe horizontal).
	 */
	public int getDimensionsLenght() {
		return dimensions[1];
	}
}
