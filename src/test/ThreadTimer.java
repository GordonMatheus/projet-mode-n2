package test;

public class ThreadTimer extends Thread {

	public static int milli=0;
	public void run(){
		while(true){
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			milli++;
		}
	}

}
