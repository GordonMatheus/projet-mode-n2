package fr.mode.view;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import fr.mode.model.AngryBirdsModel;
import fr.mode.constantes.*;

@SuppressWarnings("serial")
public class AngryBirdsView extends JPanel implements Observer {
	
	/*
	 * CONSTRUCTEUR DE LA CLASSE
	 */
	
	// *********************************************************************
	// Constructeur permettant d'initialiser la frame
	
	public AngryBirdsView (AngryBirdsModel m) {
	
		this.m = m;
		m.addObserver(this);
	}
	
	/*
	 * METHODES DE LA CLASSE
	 */
	
	// *********************************************************************
	// La methode qui sera appelee a chaque repaint pour mettre a jour
	// notre fenetre
	
	public void paintComponent(Graphics g) {

		// *********************************************************************
		// On définit une rectangle blanc en fond
		
		g.setColor(new Color(255, 255, 255));
		g.fillRect(0, 0, getSize().width, getSize().height);
		
		// *********************************************************************
		// On dessine notre objet
		
		g.setColor(new Color(255, 0, 0));
		g.fillOval((int)AngryBirdsModel.PlayerPos[0], (int)AngryBirdsModel.PlayerPos[1], 40, 40);
		
		g.setColor(new Color(0, 0, 0));
		g.drawOval((int)AngryBirdsModel.PlayerPos[0], (int) AngryBirdsModel.PlayerPos[1], 40, 40);		
	
		// *********************************************************************
		// Dessin du bec
		
		int [] x = { (int)AngryBirdsModel.PlayerPos[0]+40 , (int)AngryBirdsModel.PlayerPos[0]+40 , (int)AngryBirdsModel.PlayerPos[0]+50 };
		int [] y = { (int)AngryBirdsModel.PlayerPos[1]+13 , (int)AngryBirdsModel.PlayerPos[1]+27 , (int)AngryBirdsModel.PlayerPos[1]+20 };
		
		g.setColor(new Color(0, 0, 0));
		g.fillPolygon(x, y, 3);
	
		// *********************************************************************
		// Dessin des ennemis
		
		g.setColor(new Color(0, 0, 255));
		g.fillOval((int)(Constantes.BORD_DROIT*0.9), 40, 20, 20);
		g.fillRect((int)(Constantes.BORD_DROIT*0.82), 600, 30, 10);
		g.fillRect((int)(Constantes.BORD_DROIT*0.930), 440, 10, 30);
		g.fillOval((int)(Constantes.BORD_DROIT*0.817), 230, 25, 25);
		g.fillRect((int)(Constantes.BORD_DROIT*0.932), 510, 20, 40);
		g.fillOval((int)(Constantes.BORD_DROIT*0.721), 730, 15, 15);
		
	}
	
	/*
	 * METHODES IMPLEMENTEES DE L'INTERFACE
	 */
	
	// *********************************************************************
	// Implementation des methodes de l'interface
	
	public void update(Observable arg0, Object arg1) {
		repaint();
	}
	
	/*
	 * VARIABLES GLOBALES DE LA CLASSE
	 */

	protected AngryBirdsModel m;
}
