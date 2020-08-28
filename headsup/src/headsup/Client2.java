package headsup;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Client2 {
	static Socket sock;
	
	public static void main (String [] args) {
		try {
			sock = new Socket("127.0.0.1", 5000);
			ObjectOutputStream out = new ObjectOutputStream ( sock.getOutputStream());
			ObjectInputStream in = new ObjectInputStream ( sock.getInputStream());
			out.writeObject("~testuser2");
			out.flush();
			System.out.println("Sent");
			String message;
			
			
			Reader reader = new Reader(in);
			Writer  writer = new Writer(out);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	

}}
