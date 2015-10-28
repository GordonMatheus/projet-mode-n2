package fr.mode.main;

import java.awt.Dimension;

import javax.swing.JFrame;

import fr.mode.constantes.Constantes;
import fr.mode.model.AngryBirdsModel;
import fr.mode.view.AngryBirdsView;

public class Launch extends JFrame {

	public Launch() {
		
		this.setTitle("Oiseaux énervés");
		this.setPreferredSize(new Dimension(1750,1500));
		this.setVisible(true);

		setBounds(0, 0, Constantes.BORD_DROIT, Constantes.SOL);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		AngryBirdsModel m = new AngryBirdsModel();
		AngryBirdsView v = new AngryBirdsView(m);
		
		this.getContentPane().add(v);
		this.pack();
	}
	
	public static void main(String[] args) {

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Launch();
			}
		});
	}
}
