package fr.mode.main;

import javax.swing.JFrame;

import fr.mode.constantes.Constantes;
import fr.mode.model.*;
import fr.mode.view.*;

public class Launch extends JFrame {

	public Launch(AngryBirdsView v) {
		
		this.setTitle("Oiseaux �nerv�s");
		this.setVisible(true);

		setBounds(0, 0, Constantes.BORD_DROIT, Constantes.SOL);
		System.out.println(Constantes.BORD_DROIT);
		System.out.println(Constantes.SOL);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.getContentPane().add(v);
		this.pack();
	}
	
	public static void main(String[] args) {

		AngryBirdsModel m = new AngryBirdsModel();
		AngryBirdsView v = new AngryBirdsView(m);

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Launch(v);
			}
		});
	}
}
