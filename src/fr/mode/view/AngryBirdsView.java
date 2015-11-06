package fr.mode.view;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;
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
	 * VARIABLES GLOBALES DE LA CLASSE
	 */

	/**
	 * Le modèle associé à la vue
	 */
	protected AngryBirdsModel m;
	
	/**
	 * Le compteur représentant une certaine position de l'oiseau
	 */
	private static int compteur = 0;
	
	/*
	 * CONSTRUCTEUR DE LA CLASSE
	 */
	
	// *********************************************************************
	// Constructeur permettant d'initialiser la frame
	/**
	 * Construit une AngryBirdsView grâce à un AngryBirdsModel
	 * @param m Un AngryBirdsModel
	 */
	public AngryBirdsView (AngryBirdsModel m) {
	
		this.m = m;
		m.addObserver(this);
		AngryBirdsModel.listeObstacles.add(new ObstacleRect( 800, 400, 80, 50));
		AngryBirdsModel.listeObstacles.add(new ObstacleRect(600, 100, 80, 50));
		AngryBirdsModel.listeObstacles.add(new ObstacleRect(200, 100, 40, 50));
		//AngryBirdsModel.listeObstacles.add(new ObstacleRond(1020, 800, 100));
	}
	
	/*
	 * METHODES DE LA CLASSE
	 */

	/**
	 * Paint la scène dans le JPanel
	 */
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
		
		g.setColor(m.getColorBird());
		
		g.fillOval((int)AngryBirdsModel.PlayerPos[0], (int)AngryBirdsModel.PlayerPos[1], Constantes.DIAMETRE/2,  Constantes.DIAMETRE/2);
		g.setColor(new Color(0, 0, 0));
		g.drawOval((int)AngryBirdsModel.PlayerPos[0], (int) AngryBirdsModel.PlayerPos[1],  Constantes.DIAMETRE/2,  Constantes.DIAMETRE/2);		
	
		// *********************************************************************
		// Dessin du bec
		
		List<Double> angles = m.getListeAngle(m.getTrajectX(), m.getTrajectY());
		System.out.println(angles);
		g.setColor(new Color(0, 0, 0));
		
		int [] x = new int[3];
		int [] y = new int[3];
		
		x[0] = (int) (AngryBirdsModel.PlayerPos[0] + 30 + 15 * Math.cos(angles.get(compteur) - 30));
		x[1] = (int) (AngryBirdsModel.PlayerPos[0] + 30 + ((2 * 15 * Math.cos(angles.get(compteur)))));
		x[2] = (int) (AngryBirdsModel.PlayerPos[0] + 30 + 15 * Math.cos(angles.get(compteur) + 30));
		y[0] = (int) (AngryBirdsModel.PlayerPos[1] + 30 + 15 * Math.sin(angles.get(compteur) - 30));
		y[1] = (int) (AngryBirdsModel.PlayerPos[1] + 30 +((2 * 15 * Math.sin(angles.get(compteur)))));
		y[2] = (int) (AngryBirdsModel.PlayerPos[1] + 30 + 15 * Math.sin(angles.get(compteur) + 30));
		g.fillPolygon(x, y, 3);
		
		compteur++;
		
		// *********************************************************************
		// Dessin des obstacles
		
		g.setColor(new Color(0, 0, 255));
	
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
	
	/**
	 * 	Met à jour les objets observés
	 */
	public void update(Observable arg0, Object arg1) {
		repaint();
	}
}
