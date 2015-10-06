package test;

import javax.swing.JFrame;

public class Frame extends JFrame {
public int w =12;
panel p = new panel();

public Frame(String s){
	this.setTitle(s);
	this.setVisible(true);
	this.setSize(1200, 700);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setContentPane(p);
	trame();
}


private void trame() {
	double t;
	while(ThreadTimer.milli <3000){
	int buffer = ThreadTimer.milli;
	t =ThreadTimer.milli;
	t=t/3000;
	//System.out.println(t);
	p.pm1[0] = p.p1[0]+(p.p2[0]-p.p1[0])/(1/t);
	p.pm1[1] = p.p1[1]+(p.p2[1]-p.p1[1])/(1/t);
	
	p.pm2[0] = p.p2[0]+(p.p3[0]-p.p2[0])/(1/t);
	p.pm2[1] = p.p2[1]+(p.p3[1]-p.p2[1])/(1/t);
	
	p.pm3[0] = p.pm1[0]+(p.pm2[0]-p.pm1[0])/(1/t);
	p.pm3[1] = p.pm1[1]+(p.pm2[1]-p.pm1[1])/(1/t);

	p.repaint();
	if(w >( ThreadTimer.milli-buffer)){
		try {
			Thread.sleep(w-(ThreadTimer.milli-buffer));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
//	System.out.println(p.pm1[0]+":"+p.pm1[1]);
}


}
