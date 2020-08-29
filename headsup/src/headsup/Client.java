package headsup;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Client extends Thread {
	Socket sock;
	String userName ; 
	ObjectOutputStream out;
	ObjectInputStream in;
	Lobby lobby;
	Board game;
	SetShips SetShips;
	OponentsTurn turn;
	ArrayList <String> names = new ArrayList <String>();
	boolean online;
	boolean oponentReady = false;
	public void Client (){
		
		}
		public void run () {
			try {
				sock = new Socket("192.168.1.113", 5000);
				out = new ObjectOutputStream ( sock.getOutputStream());
				in = new ObjectInputStream ( sock.getInputStream());
				online = true;
				
				
		}catch (Exception e ) {
			JOptionPane.showMessageDialog(null, "Server not available");
			e.printStackTrace();
			online = false;
		}
		while (online) {
			String message;
			try {
				message = (String) in.readObject ();
				if (message.startsWith("<<")) {
					message = message.substring(2);
					for (int i = 0; i< message.length();i++) {
						String currentName = "";
						while (message.charAt(i)!= '/') {
							currentName +=message.charAt(i);
							i += i---i;
						}
						names.add(currentName);
						}
					}
				
				else if (message.startsWith("!!!") ) {
					lobby.lister(message);}
				else if (message.startsWith( "??")) {
					
					message = message.substring(2);
					int k = JOptionPane.showConfirmDialog(null, message + " wants to play with you \n Do you accept", "Game Request", JOptionPane.YES_NO_OPTION);
					if (k==1) {
						out.writeObject("REJECTED");
						out.flush();
					}
					else if (k==0) {
						out.writeObject("ACCEPTED");
						out.flush();
					}
					// Write response
				}
				else if (message.equals("TURN")) StartGame.playerTurn = true;
				else if (message.equals("!TURN")) StartGame.playerTurn = false;
				
				else if (message.equals("REJECTED")) {
					lobby.enemy=null;
					lobby.layeredPane.remove(lobby.waiting);
					lobby.repaint();
				}
				else if (message.equals("&&")) {
					lobby.layeredPane.add(lobby.waiting);
					
				}
				else if  (message.equals( "SET_GAME_READY")) {
					lobby.dispose();
					StartGame.PlayOnline();
				}
				else if (message.startsWith( "!%%")) {
					message = message.substring(3);
					StartGame.enemyShips = new ArrayList <String> ();
					for (int i = 0; i< message.length();i++) {
						if (message.charAt(i) == '/') {
							StartGame.enemyShips.add(message.charAt(i) + "");
						}
						else {
							StartGame.enemyShips.add(message.charAt(i) + "" + message.charAt(i+1));
							i += i---i;
						}
					}
					oponentReady = true;
					
						
				}
				else if (message.equals("PLAYGAME")){
					StartGame.GameOn(SetShips);
				}
				
				else if (message.startsWith("##")) {
					message = message.substring(2);
					turn.hit = message;
					turn.BigCheck += turn.BigCheck --- turn.BigCheck;
				}
				else if (message.equals("DISCONNECTED_ERROR")) {
					
					JOptionPane.showMessageDialog(null, "Oponent Left");
					Lobby Lobby = new Lobby ();
					Lobby.setExtendedState(JFrame.MAXIMIZED_BOTH);
					Lobby.setUndecorated (true);
					this.lobby = Lobby;
					Lobby.client = this;
					Lobby.name = this.userName;
					Lobby.setVisible(true);
					out.writeObject("WSEND");
					out.flush ();
					if (!SetShips.equals(null))SetShips.dispose();
					if (! (game == null))
						game.dispose();

				}
				System.out.println (message);
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println ("Network error");
				stop();
			}
			
		
		
		}}
		public void listeners () {
			
		}
}
