package fr.mode.main;

import java.awt.Dimension;

import javax.swing.JFrame;

import fr.mode.constantes.Constantes;
import fr.mode.model.AngryBirdsModel;
import fr.mode.view.AngryBirdsView;

/**
 * <b>La classe Launch</b>
 * <p>
 * Classe cr�ant la fen�tre et contenant
 * le main du programme.
 * </p>
 */
public class Launch extends JFrame {

	/** Constructeur d'un Launch, soit un lancement du jeu. 
	 * 
	 */
	public Launch() {
		
		this.setTitle("Oiseaux �nerv�s");
		this.setPreferredSize(new Dimension(1750,1500));
		this.setVisible(true);

		setBounds(0, 0, Constantes.BORD_DROIT, Constantes.SOL);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		AngryBirdsModel m = new AngryBirdsModel();
		AngryBirdsView v = new AngryBirdsView(m);
		
		this.getContentPane().add(v);
		this.pack();
	}
	
	/** Main du jeu, le lan�ant.
	 * 
	 */
	public static void main(String[] args) {

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Launch();
			}
		});
	}
}
