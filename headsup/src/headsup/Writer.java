package headsup;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class Writer  extends Thread{
	ObjectOutputStream out;
	String message; 
	Scanner sc = new Scanner (System.in);
	
	public Writer (ObjectOutputStream out) {
		this.out = out ; 
		start();
		}
	public void run () { 
		while (true) {
		String newMessage = sc.next (); 
		try {
			out.writeObject (newMessage);
			out.flush();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println ("Network error");

			stop();
		}
		
		}}
	
	
}
