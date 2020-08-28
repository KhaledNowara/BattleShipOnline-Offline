package BBS;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Client extends Thread{
	private ServerBSS server;
	private ArrayList<String> grid ;
	Socket socket;
	ObjectInputStream in;
	ObjectOutputStream out;
	Client enemy;
	String userName;
	boolean turn;
	boolean inGame = false;
	private String message;
	private boolean NameSet = false;
	private boolean shipsSet = true; 
	private boolean GameReady = false;
	public Client  (ServerBSS server,Socket socket) {
		
		this.server = server;
		this.socket = socket; 
		this.grid = server.grid;
		try {
			in = new ObjectInputStream (socket.getInputStream());
			out = new ObjectOutputStream (socket.getOutputStream());
			out.writeObject(server.s);
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		start ();
		
	}
	
				
	public String read () throws ClassNotFoundException, IOException {
	 	while (true) {
		String s = (String) this.in.readObject();
		if (!s.equals(null)) {
			return s ;}
		}
	}	

	public void run () {
		while (true) {
			
			try {
				message = read();
				System.out.println(message);
			if (message.charAt(0) == '~') {
				userName = message.substring(1);
				System.out.println(userName);
				while (true) {
				if (server.Clients.containsKey(userName)) 
					userName = userName + "" +(int)Math.random()*1000;
					break;}
				
				server.Clients.put(userName, this);
				server.s += userName + "/";
				server.waitingClients.add(this); 
				server.sendWaitingList();
				NameSet = true;}
			else if (NameSet && message.startsWith ("@")) {
				message = message.substring(1);
				
				if (server.Clients.containsKey(message)) { 
				System.out.println("sending request");
				out.writeObject("&&");
				out.flush();
				enemy = server.Clients.get(message);
				enemy.enemy = this;
				enemy.out.writeObject("??" + userName);
				enemy.out.flush();
				}
				else {
					out.writeObject("!&"); //oponent unavailable
					out.flush();
				}}	
			else if (message.equals("ACCEPTED")) {
				enemy.out.writeObject ("SET_GAME_READY");
				enemy.out.flush();
				out.writeObject("SET_GAME_READY");
				out.flush();
				server.waitingClients.remove(this);
				server.waitingClients.remove(enemy);
				server.sendWaitingList();
				inGame = true;
				enemy.inGame = true;
				
			
			}else if(message.equals("REJECTED")) { 
	
				enemy.out.writeObject(message);
				enemy.out.flush();
				enemy = null;
				}
		
			else if  (message.startsWith("%%")) {
				enemy.out.writeObject ("!" + message);
				enemy.out.flush ();
				GameReady = true;
				if (enemy.GameReady == true) {
					if  ((int)Math.random()*10 %2 ==0) {
						turn= true;
						out.writeObject ("TURN");
						out.flush ();
						
						enemy.turn = false;
						enemy.out.writeObject ("!TURN");
						enemy.out.flush ();
					}
					else {
						turn= false;
						out.writeObject ("!TURN");
						out.flush ();
						enemy.turn = true;
						enemy.out.writeObject ("TURN");
						enemy.out.flush ();
					}
					out.writeObject ("PLAYGAME");
					enemy.out.writeObject("PLAYGAME");
					out.flush();
					enemy.out.flush();
				}
				
			}
			else if (message.startsWith ("!%%")) {
				out.writeObject(message);
				out.flush();
			}
			else if (message.equals("EXIT")) {
				server.Clients.remove (userName);
				server.s = server.s.replace(userName + "/" ,"");
				System.out.println(server.s);
				if (server.waitingClients.contains(this)) 
					server.waitingClients.remove(this);
				try {
					server.sendWaitingList();}
					catch (Exception e) {
						
					}
				if (inGame) {
					server.waitingClients.add(enemy);
					enemy.inGame = false ;
					try {
						enemy.out.writeObject("DISCONNECTED_ERROR");
						enemy.out.flush();
						enemy.enemy = null;
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					socket.close();
					stop();
			
	
			}}
			else if (message.equals("WSEND")) server.sendWaitingList();
			else if (message.equals("XX")) {
				inGame = false;
				enemy.inGame = false;
				server.waitingClients.add(this);
				server.waitingClients.add(enemy);
				enemy.out.writeObject("DISCONNECTED_ERROR");
				enemy.out.flush();
				server.sendWaitingList();
				enemy.enemy = null;
			}
			else if (message.startsWith("##")) {
				enemy.out.writeObject(message);
				enemy.out.flush();
				
			}
			}catch (Exception e) {
				
				e.printStackTrace();
				
				server.Clients.remove (userName);
				server.s = server.s.replace(userName  + "/","");
				System.out.println(server.s);
				if (server.waitingClients.contains(this)) {
					server.waitingClients.remove(this);
					
				} 
				if (inGame) {
					try {
						enemy.out.writeObject("DISCONNECTED_ERROR");
						enemy.out.flush();
						enemy.inGame= false;
						enemy.enemy = null;
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}try {
					socket.close();
					stop();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			}
	}
	

}