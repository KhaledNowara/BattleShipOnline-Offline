package headsup;

import java.io.IOException;
import java.io.ObjectInputStream;

public class Reader  extends Thread{
	ObjectInputStream in; 
	public Reader(ObjectInputStream in) {
		this.in = in ; 
		start();
		}
	public void run () { 
		while (true) {
		String message;
		try {
			message = (String) in.readObject ();
			
			}
	} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println ("Network error");
			stop();
		}
		
		}}
}
