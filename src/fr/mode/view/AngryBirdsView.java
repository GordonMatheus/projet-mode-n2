package fr.mode.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import fr.mode.constantes.Constantes;
import fr.mode.model.AngryBirdsModel;
import fr.mode.model.Obstacle;
import fr.mode.model.ObstacleRect;
import fr.mode.model.ObstacleRond;

@SuppressWarnings("serial")
public class AngryBirdsView extends JPanel implements Observer {

	/*
	 * VARIABLES GLOBALES DE LA CLASSE
	 */

	/**
	 * Le modele associe a la vue
	 */
	protected AngryBirdsModel m;

	/*
	 * CONSTRUCTEUR DE LA CLASSE
	 */

	// *********************************************************************
	// Constructeur permettant d'initialiser la frame

	/**
	 * Construit une AngryBirdsView grace a un AngryBirdsModel
	 * @param m Un AngryBirdsModel
	 */
	public AngryBirdsView (AngryBirdsModel m) {

		this.m = m;
		m.addObserver(this);
		AngryBirdsModel.listeObstacles.add(new ObstacleRect( 1200, 200, 80, 50));
		AngryBirdsModel.listeObstacles.add(new ObstacleRect(600, 800, 80, 50));
		AngryBirdsModel.listeObstacles.add(new ObstacleRect(1400, 100, 40, 50));
		AngryBirdsModel.listeObstacles.add(new ObstacleRond(900, 400, 150));
	}

	/*
	 * METHODES DE LA CLASSE
	 */

	// *********************************************************************
	// La methode qui sera appelee a chaque repaint pour mettre a jour
	// notre fenetre

	/**
	 * Paint la scene dans le JPanel
	 */
	public void paintComponent(Graphics g) {

		// *********************************************************************
		// Affichage du fond et du lance-pierre
		int cpt = 0;
		
		g.setColor(new Color(255, 255, 255));
		g.drawImage(new ImageIcon("ressources/AgBirdBACK1.jpg").getImage(),0 ,0 , Constantes.BORD_DROIT,Constantes.SOL, this );
		g.drawImage(new ImageIcon("ressources/lancePierre.png").getImage(),0 , 1000 ,120,220, this );
		g.setColor(new Color(0, 0, 5));
		g.fillRect(0, 950, Constantes.BORD_DROIT, 60);  // A CHANGER !!
		
		
		
		
		
		//

		// *********************************************************************
		// On dessine la trajectoire

		g.setColor(new Color(0,0,0));
		int size = AngryBirdsModel.trajectoryX.size();
		for(int i =0;i<size;i++){
			g.fillRect(AngryBirdsModel.trajectoryX.get(i)+15, AngryBirdsModel.trajectoryY.get(i)+15, 5,5);

		}

		// *********************************************************************
		// On dessine notre objet

		/*g.setColor(m.getColorBird());

		g.fillOval((int)AngryBirdsModel.PlayerPos[0], (int)AngryBirdsModel.PlayerPos[1], Constantes.DIAMETRE/2,  Constantes.DIAMETRE/2);
		g.setColor(new Color(0, 0, 0));
		g.drawOval((int)AngryBirdsModel.PlayerPos[0], (int) AngryBirdsModel.PlayerPos[1],  Constantes.DIAMETRE/2,  Constantes.DIAMETRE/2);

		// *********************************************************************
		// Dessin du bec

		int [] x = { (int)AngryBirdsModel.PlayerPos[0]+  Constantes.DIAMETRE/2 , (int)AngryBirdsModel.PlayerPos[0]+  Constantes.DIAMETRE/2 , (int)AngryBirdsModel.PlayerPos[0]+Constantes.DIAMETRE-20 };
		int [] y = { (int)AngryBirdsModel.PlayerPos[1]+8 , (int)AngryBirdsModel.PlayerPos[1]+22 , (int)AngryBirdsModel.PlayerPos[1]+Constantes.DIAMETRE/4};

		g.setColor(new Color(0, 0, 0));
		g.fillPolygon(x, y, 3);
		 */
		Image img = null;
		Image img2 = null;
		try{
			img = ImageIO.read(new File("ressources/oiseau_vener.png"));
			img2 = ImageIO.read(new File("ressources/oiseau_vener_collision.png"));
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		List<Double> angles = m.getListeAngles(m.getX(), m.getY());
		g.drawImage(img, (int) AngryBirdsModel.PlayerPos[0], (int) AngryBirdsModel.PlayerPos[1], this);
		
		/*Graphics2D test = (Graphics2D)g;
		test.rotate(angles.get(cpt));
		*/
		
		
		// *********************************************************************
		// Dessin des obstacles

		g.setColor(new Color(21, 96, 189));


		for (Obstacle o : AngryBirdsModel.listeObstacles){
			if(o instanceof ObstacleRond)
				g.fillOval((int)(o.getObstaclePosX()+ o.getDimensionsHeight()/2),(int)(o.getObstaclePosY()+o.getDimensionsLenght()/2), o.getDimensionsHeight(), o.getDimensionsLenght());
			else
				g.fillRect((int)o.getObstaclePosX(),(int)o.getObstaclePosY(), o.getDimensionsHeight(), o.getDimensionsLenght());

		}
		cpt++;
	}

	/*
	 * METHODES IMPLEMENTEES DE L'INTERFACE
	 */

	// *********************************************************************
	// Implementation des methodes de l'interface

	/**
	 * 	Met a jour les objets observes
	 */
	public void update(Observable arg0, Object arg1) {
		repaint();
	}
}
