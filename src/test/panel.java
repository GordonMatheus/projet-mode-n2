package test;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

public class panel extends JPanel {

	int p1[] = new int[] { 250, 300 };
	int p2[] = new int[] { 350, 200 };
	int p3[] = new int[] { 520, 300 };
	double pm1[] = new double[2];
	double pm2[] = new double[2];
	double pm3[] = new double[2];
	double pm1Old[] = new double[2];
	double y[];
	double x[];
	boolean segment = true;
	ArrayList<Double> listX = new ArrayList<Double>();
	ArrayList<Double> listY = new ArrayList<Double>();

	public void paintComponent(Graphics g) {
		if (segment) {
			g.setColor(Color.white);
			g.fillRect(0, 0, getSize().width, getSize().height);
			g.setColor(Color.green);
			// g.drawPolyline((listX.toArray(x), (listY.toArray(y)),
			// listX.size());
			g.setColor(Color.black);
			g.fillRect(p1[0], p1[1], 6, 6);
			g.fillRect(p2[0], p2[1], 6, 6);
			g.fillRect(p3[0], p3[1], 6, 6);
			g.drawLine(p1[0] + 3, p1[1] + 3, p2[0] + 3, p2[1] + 3);
			g.drawLine(p2[0] + 3, p2[1] + 3, p3[0] + 3, p3[1] + 3);
			g.setColor(Color.red);
			g.fillRect((int) pm1[0], (int) pm1[1], 6, 6);
			g.fillRect((int) pm2[0], (int) pm2[1], 6, 6);
			g.drawLine((int) pm1[0] + 3, (int) pm1[1] + 3, (int) pm2[0] + 3,
					(int) pm2[1] + 3);
			g.setColor(Color.green);
			g.fillRect((int) pm3[0], (int) pm3[1], 6, 6);
			listX.add(pm3[0]);
			listY.add(pm3[1]);
		} else {
			g.setColor(Color.green);
			g.fillRect((int) pm3[0], (int) pm3[1], 6, 6);
		}
	}
}
