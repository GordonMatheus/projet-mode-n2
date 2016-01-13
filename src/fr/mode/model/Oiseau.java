package fr.mode.model;

import fr.mode.constantes.Constantes;

public class Oiseau extends Corps{
	
	public Oiseau(){
		super();
		this.dimensions[0]= Constantes.DIAMETRE ;
		this.dimensions[1]= Constantes.DIAMETRE ;
		
		this.poids= 100 ;
	}
}
