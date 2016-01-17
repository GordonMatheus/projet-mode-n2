package fr.mode.model;

import fr.mode.constantes.Constantes;

public class Oiseau extends Corps{

	public Oiseau(){
		super();
		this.setDimensionsX(Constantes.DIAMETRE);
		this.setDimensionsY(Constantes.DIAMETRE);

		this.setPoids(100);
	}
}
