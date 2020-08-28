package BBS;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.*;

public class ServerBSS {
 	static Map <String,Client > Clients = new HashMap<String ,	Client > ();
 	static String s = "<<";
	static ArrayList <Client> waitingClients = new  ArrayList <Client> ();
	public ArrayList <String> grid = new ArrayList <String> ();
	private ServerSocket serverSocket;
	private Socket socket; 
	private String list;
	public static void main (String [] args ) {
		ServerBSS Server = new ServerBSS();
		while (true) {
		try {
			
			
			Server.socket = Server.serverSocket.accept();
			System.out.println ("ClientAdded");
			new Client (Server,Server.socket);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}}
	}
	public void sendWaitingList () throws IOException  {
		String list = "!!!";
		for (Client c  : waitingClients) {
			list += c.userName + "\\" ;  
		}
		for (Client c : waitingClients) {
			synchronized (c) {
				c.out.writeObject (list);
				System.out.println ("sending to " + c.userName);
				c.out.flush ();
			}}
		}
		
	
	public ServerBSS () {
		gridmaker();
		try {
			serverSocket = new ServerSocket (5000);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Waiting for clients");
	} 
	public void removeClient (Client c) {
		synchronized (this) {
			Clients.remove(c);
		} 
	}
	
	public void send (Client c, String message) {
		ObjectOutputStream output = Clients.get(c).out;
		try {
			output.writeObject(message);
			output.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public String read (ObjectInputStream in) throws ClassNotFoundException, IOException {
			
		String read = (String) in.readObject();
		
		return read; }
	private void gridmaker () {
		char index = 'A' ; 
		for (int i = 0; i < 10 ; i++ ) {
			for (int j = 0; j <10; j++) {
				grid.add('#' +"" +  index +  "" + j);
				
			}
			index ++; 
		}
	}
	}
		
		
	
	
	
