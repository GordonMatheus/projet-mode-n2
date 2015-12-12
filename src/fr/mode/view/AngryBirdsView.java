package fr.mode.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import fr.mode.constantes.Constantes;
import fr.mode.model.AngryBirdsModel;
import fr.mode.model.Obstacle;
import fr.mode.model.ObstacleRect;
import fr.mode.model.ObstacleRond;

@SuppressWarnings("serial") 
public class AngryBirdsView extends JPanel implements Observer , MouseListener , MouseInputListener{

	/*
	 * VARIABLES GLOBALES DE LA CLASSE
	 */

	/**
	 * Le modele associe a la vue
	 */
	protected AngryBirdsModel m;

	
	boolean press = false;
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
		g.fillRect(0 ,0 , Constantes.BORD_DROIT,Constantes.SOL);
		/*Image img_Fond = null;
		try {
			img_Fond = ImageIO.read(new File("ressources/Fond.jpg"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		Image img_LancePierre = null;
		try {
			img_LancePierre = ImageIO.read(new File("ressources/lancePierre.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		g.drawImage(img_Fond,0 ,0 , Constantes.BORD_DROIT,Constantes.SOL, this );
		g.drawImage(img_LancePierre,0 , 1000 ,120,220, this );*/
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
		String chemin_image;

		if(AngryBirdsModel.etat == 0)
			chemin_image = "ressources/oiseau_vener.png";
		else {
			chemin_image = "ressources/oiseau_vener_collision.png";
		}

		try{
			img = ImageIO.read(new File(chemin_image));
		}catch(Exception e){
			e.printStackTrace();
		}

		List<Double> angles = m.getListeAngles(m.getX(), m.getY());

		Graphics2D test = (Graphics2D) this.getGraphics();

		test.translate( (int) (AngryBirdsModel.PlayerPos[0]+img.getWidth(null)/2) , (int) (AngryBirdsModel.PlayerPos[1]+img.getHeight(null)/2));
		test.rotate(Math.toDegrees(angles.get(cpt)));
		test.drawImage(img, 0, 0, this);
		test.rotate(Math.toDegrees(angles.get(-cpt)));
		test.translate( -(int) (AngryBirdsModel.PlayerPos[0]+img.getWidth(null)/2) , -(int) (AngryBirdsModel.PlayerPos[1]+img.getHeight(null)/2));

		cpt++;

		// *********************************************************************
		// Dessin des obstacles

		g.setColor(new Color(21, 96, 189));


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

	/**
	 * 	Met a jour les objets observes
	 */
	public void update(Observable arg0, Object arg1) {
		repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		press = true;
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		m.setPlayerPos(new double []{arg0.getX(),arg0.getY()});
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
