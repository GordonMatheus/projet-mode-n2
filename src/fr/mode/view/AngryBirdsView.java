package fr.mode.view;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import fr.mode.model.AngryBirdsModel;
import fr.mode.model.Obstacle;
import fr.mode.model.ObstacleRect;
import fr.mode.model.ObstacleRond;
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
		// On dessine la trajectoire
		// *********************************************************************
		
		g.setColor(new Color(0,0,0));
		int size = AngryBirdsModel.trajectoryX.size();
		for(int i =0;i<size;i++){
			g.fillRect(AngryBirdsModel.trajectoryX.get(i)+15, AngryBirdsModel.trajectoryY.get(i)+15, 5,5);
			
		}
		
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
		// Dessin des obstacles
		
		g.setColor(new Color(0, 0, 255));
		AngryBirdsModel.listeObstacles.add(new ObstacleRond( 1600, 400, 80));
		AngryBirdsModel.listeObstacles.add(new ObstacleRond(1200, 100, 80));
		AngryBirdsModel.listeObstacles.add(new ObstacleRect(200, 100, 40,60));
		AngryBirdsModel.listeObstacles.add(new ObstacleRect(1020, 800, 100,20));
	
		for (Obstacle o : AngryBirdsModel.listeObstacles){
			if(o instanceof ObstacleRond)
				g.fillOval((int)(o.getObstaclePosX()+ o.getDimensionsHeight()/2),(int)(o.getObstaclePosY()+o.getDimensionsLenght()/2), o.getDimensionsHeight(), o.getDimensionsLenght());
			else
				g.fillRect((int)o.getObstaclePosX(),(int)o.getObstaclePosY(), o.getDimensionsHeight(), o.getDimensionsLenght());

		}
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
