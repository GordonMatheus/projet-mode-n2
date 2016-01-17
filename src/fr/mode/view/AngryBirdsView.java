package fr.mode.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import fr.mode.constantes.Constantes;
import fr.mode.model.AngryBirdsModel;
import fr.mode.model.Obstacle;
import fr.mode.model.ObstacleRond;

@SuppressWarnings("serial")
public class AngryBirdsView extends JPanel implements Observer, MouseListener,
		MouseInputListener {

	/*
	 * VARIABLES GLOBALES DE LA CLASSE
	 */

	/**
	 * Tableau comprenant les coordonnées de l'origine de la catapulte
	 */
	double posOrigine[] = new double[2];

	/**
	 * Tableau comprenant les coordonnées de la catapulte
	 */
	double posCatapulte[] = new double[2];
	/**
	 * Le modele associe a la vue
	 */
	protected AngryBirdsModel m;

	/**
	 * Booléen pour vérifier si un clique de la souris a été pressé
	 */
	boolean press = false;

	/*
	 * CONSTRUCTEUR DE LA CLASSE
	 */

	// *********************************************************************
	// Constructeur permettant d'initialiser la frame

	/**
	 * Construit une AngryBirdsView grace a un AngryBirdsModel
	 *
	 * @param m
	 *            Un AngryBirdsModel
	 */
	public AngryBirdsView(AngryBirdsModel m) {

		this.m = m;
		m.addObserver(this);
		posCatapulte[0] = m.getPlayerPos()[0];
		posCatapulte[1] = m.getPlayerPos()[1];
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
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

		Graphics2D g2d = (Graphics2D) g;
		String chemin_image = "ressources/oiseau_vener.png";
		Image img = null;

		// fond
		Image img_fond = null;
		try {
			img_fond = ImageIO.read(new File("ressources/Fond.png"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		g2d.drawImage(img_fond, 0, 0, Constantes.BORD_DROIT, Constantes.SOL, null);

		//sol
		g.setColor(new Color(0, 51, 0));
		g.fillRect(0, Constantes.SOL-100, Constantes.BORD_DROIT, 200);

		Image img_LancePierre = null;
		try {
			img_LancePierre = ImageIO.read(new File(
					"ressources/lancePierre.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		AffineTransform rotation = new AffineTransform();
		rotation.translate((double) posCatapulte[0], (double) posCatapulte[1]);
		g2d.drawImage(img_LancePierre, rotation, null);

		if (!Constantes.estLance) {

			try {

				rotation = new AffineTransform();
				rotation.translate((double) AngryBirdsModel.oiseau.getCorpsPosX(),
						(double) AngryBirdsModel.oiseau.getCorpsPosY());
				img = ImageIO.read(new File(chemin_image));
				g2d.drawImage(img, rotation, null);
			} catch (IOException e) {
				e.printStackTrace();
			}
			g.setColor(Color.red);
			g.drawLine((int) posCatapulte[0] + 20, (int) posCatapulte[1] + 20,
					(int) m.getPlayerPos()[0] + 20,
					(int) m.getPlayerPos()[1] + 20);
			g.drawLine((int) posCatapulte[0] + 40, (int) posCatapulte[1] + 20,
					(int) m.getPlayerPos()[0] + 20,
					(int) m.getPlayerPos()[1] + 20);
		} else {

			// *********************************************************************
			// On dessine la trajectoire

			g.setColor(new Color(0, 0, 0));
			int size = m.getX().size();
			for (int i = 0; i < size; i++) {
				g.fillRect(m.getX().get(i) + 15,
						m.getY().get(i) + 15, 5, 5);

			}

			if (AngryBirdsModel.etat == 0)
				chemin_image = "ressources/oiseau_vener.png";
			else {
				chemin_image = "ressources/oiseau_vener_collision.png";
			}

			try {
				img = ImageIO.read(new File(chemin_image));
			} catch (Exception e) {
				e.printStackTrace();
			}

			double angle = m.getAngle(m.getX().get(m.getCptAngle()), m.getY()
					.get(m.getCptAngle()), m.getX().get(m.getCptAngle() + 1), m
					.getY().get(m.getCptAngle() + 1));

			rotation = new AffineTransform();
			rotation.translate((double) AngryBirdsModel.oiseau.getCorpsPosX(), (double) AngryBirdsModel.oiseau.getCorpsPosY());
			rotation.rotate(angle, img.getWidth(null) / 2,
					img.getHeight(null) / 2);
			g2d.drawImage(img, rotation, null);
		}

		if (m.getCptAngle() < m.getX().size() - 2)
			m.cptAngleIncr();

		// *********************************************************************
		// Dessin des obstacles

		g.setColor(new Color(21, 96, 189));

		for (Obstacle o : AngryBirdsModel.listeCorps) {
			if (o instanceof ObstacleRond)
				g.fillOval(
						(int) (o.getCorpsPosX() + o.getDimensionsHeight() / 2),
						(int) (o.getCorpsPosY() + o.getDimensionsLenght() / 2),
						o.getDimensionsHeight(), o.getDimensionsLenght());
			else
				g.fillRect((int) o.getCorpsPosX(),
						(int) o.getCorpsPosY(), o.getDimensionsHeight(),
						o.getDimensionsLenght());

		}
	}

	/*
	 * METHODES IMPLEMENTEES DE L'INTERFACE
	 */

	// *********************************************************************
	// Implementation des methodes de l'interface

	/**
	 * Met a jour les objets observes
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

	/**
	 * Action quand le clic gauche de la souris est pressé
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		posOrigine[0] = e.getX();
		posOrigine[1] = e.getY();
		if ((posOrigine[0] > AngryBirdsModel.oiseau.getCorpsPosX() && posOrigine[0] < AngryBirdsModel.oiseau.getCorpsPosX() + 50)
				&& (posOrigine[1] > AngryBirdsModel.oiseau.getCorpsPosY() && posOrigine[1] < AngryBirdsModel.oiseau.getCorpsPosY() + 50)) {
			press = true;
		}
	}

	/**
	 * Action quand le clic gauche de la souris est relâché
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		if (press) {
			Constantes.estLance = true;
			press = false;
		}
	}

	/**
	 * Action quand le clic gauche de la souris est maintenu et déplacé
	 */
	@Override
	public void mouseDragged(MouseEvent arg0) {
		if (press && arg0.getY() > 400 && arg0.getX() < 250) {
			m.setPlayerPos(new double[] { arg0.getX(), arg0.getY() });
			double varX = (posOrigine[0] - m.getPlayerPos()[0]) / 12;
			double varY = (posOrigine[1] - m.getPlayerPos()[1]) / 15;
			double[] tmp = new double[] { varX, varY };
			m.setPlayerSpeed(tmp);
		}

	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
}
