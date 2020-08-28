package headsup;

import java.util.TimerTask;

import javax.swing.JLabel;

public class timer60 extends TimerTask{
	int i ;
	String j = "" ;
	JLabel lable;
	boolean frame = false;
	boolean function = true;
	public timer60(JLabel l , int i) {
		lable = l;
		this.i = i;
	}

	@Override
	public void run() {
		j = i + "";
		if (i == 90) frame = true;
		lable.setText(j);
		lable.repaint();
		i --;
		if (i == 0 && function) { 
			if (frame) {
			StartGame.time90out ();
			cancel();}
			else {
				StartGame.time60out ();
				cancel();
				}
			}
		
		
	}

}
