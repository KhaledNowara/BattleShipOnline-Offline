package headsup;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.applet.Applet;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Timer;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;

import com.sun.media.jfxmedia.Media;
import com.sun.media.jfxmedia.MediaPlayer;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class StartGame {
	static ArrayList <String> playerShips ;
	static ArrayList <String> enemyShips ;
	static Board board;
	static Lobby lobby;
	static SetShips SetShips;
	static boolean playerTurn = true;
	static OponentsTurn turn;
	static AudioStream AS;
	static AudioPlayer Ap =AudioPlayer.player;
	static private boolean done = false;
	static  boolean MusicOn = true;
	static private boolean start = true;
	static Client client;
	static Clip clip;
	static URL Menus = StartGame.class.getResource("Automation.wav");
	static URL GamePlay = StartGame.class.getResource("Machines.wav");
	static URL boom = StartGame.class.getResource("boom.wav");
	static ArrayList <String> previousStrikes; 
	

	public static void main (String[] args) {
		JFXPanel fxPanel = new JFXPanel();
		if (start) {
			try {
				AudioInputStream sound = AudioSystem.getAudioInputStream(Menus);
				clip = AudioSystem.getClip();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();}
			
			start = false;
		}
	
		StartMenu StartMenu = new StartMenu ();
		StartMenu.setExtendedState(JFrame.MAXIMIZED_BOTH);
		StartMenu.setUndecorated (true);
		StartMenu.setVisible(true);
		if (!done && MusicOn) {
		
			play (Menus);
			done = true;}		
		StartMenu.MusicOnOff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			if (MusicOn) {
				StartMenu.MusicOnOff.setText("Sound Off");
				MusicOn = false;
				clip.stop();
				
			}
			else {
				StartMenu.MusicOnOff.setText("Sound On");
				MusicOn = true;
			 	play (Menus);
			}
				
			}});
		
		StartMenu.Play.addActionListener (new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
			
				playVsComputer ();
				StartMenu.dispose();
				
			}
		});
		
	 	StartMenu.PlayOnline.addActionListener( new ActionListener () {
			public void actionPerformed (ActionEvent e) {
				client = new Client ();
				lobby = new Lobby ();
				client.lobby = lobby;
				lobby.client = client;
				client.start();
				client.userName = (String)JOptionPane.showInputDialog(null, "What is your name Captin ?",
		                "Enter your nick name", JOptionPane.QUESTION_MESSAGE,  new ImageIcon (Board.class.getResource("/headsup/madam.png")), null, null);
				if (client.userName == null || client.userName.replaceAll("\\s","").equals ("")) {
					client.stop();
					lobby = null;
					client = null;
				}
				
				else {	
					if (client.names.contains(client.userName) ){
						while (true) {
							client.userName =  (String)JOptionPane.showInputDialog(null,"Enter another name" ,
					                "This name is taken", JOptionPane.QUESTION_MESSAGE,  new ImageIcon (Board.class.getResource("/headsup/madam.png")), null, null);
							if (!client.names.contains(client.userName))break;
							if (client.userName == null || client.userName.replaceAll("\\s","").equals ("")) {
								client.stop();
								lobby = null;
								client = null;
							}
						}
					}
					lobby.name = client.userName;
				try {
					client.out.writeObject("~" + client.userName);
					client.out.flush();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Server not available");
					e1.printStackTrace();
				}
				if (client.online) {
					
					lobby.setVisible(true);
					StartMenu.dispose();
					JOptionPane.showConfirmDialog(null, "Alright " + client.userName + " You are online", "Online", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,  new ImageIcon (Board.class.getResource("/headsup/madam.png")));
					
					lobby.MainMenu.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							try {
								client.out.writeObject("EXIT");
								client.out.flush();
								client.sock.close();
								client.stop();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						
							main (null);
							lobby.dispose();
					
						}
					});
				}}}
		});
	
	
	
		
		
	}
	public static void playVsComputer () {
		SetShips = new SetShips (); 
		SetShips.setExtendedState(JFrame.MAXIMIZED_BOTH);
		SetShips.setUndecorated (true);
		SetShips.setVisible(true);
		SetShips.Exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		SetShips.Back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main (null);
				SetShips.dispose();
			}
		});
		SetShips.btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (SetShips.shipList.getSize()==0) {
				
					enemyShips = shipGenerator.generateShip();
					board = new Board (SetShips.shipsLocation);
					board.Close.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							System.exit(0);
						}
					});
					board.MainMenu.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						main (null);
						board.dispose();
					}});
					board.textArea.append("Game Started \n \n");
					board.setExtendedState(JFrame.MAXIMIZED_BOTH);
					board.setUndecorated (true);
					board.setVisible(true);
					SetShips.dispose();
					if (MusicOn) {
					
						play (GamePlay);
						done = false;}
						
				
					Thread computer = new Thread (new ComputerTurn (board.btns)) ;
					computer.start();
					playTurn (board.btnsEnemy,enemyShips);
					playerShips = SetShips.shipsLocation;
					
				
				
			}
		  else JOptionPane.showMessageDialog(null, "Please add all the ships ");
				}
		});
	}
	public static void PlayOnline () {
		SetShips = new SetShips (); 
		client.SetShips = SetShips;
		//SetShips.setExtendedState(JFrame.MAXIMIZED_BOTH);
		//SetShips.setUndecorated (true);
		timer60 time = new timer60(SetShips.timee,90);
		Timer timer = new Timer ();
		SetShips.setVisible(true);
		timer.schedule(time, 0,1000);
		SetShips.layeredPane.add(SetShips.timee);
		SetShips.layeredPane.add(SetShips.OExit);
		SetShips.OExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					client.out.writeObject("EXIT");
					client.out.flush();
					client.sock.close();
					client.stop();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.exit(0);
			}
		});
		SetShips.Exit.setText("Back to lobby");
		SetShips.Exit.setBackground(Color.MAGENTA);
		SetShips.Exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					client.out.writeObject("XX");
					client.out.flush();
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Lobby lobby = new Lobby ();
				client.lobby = lobby;
				lobby.client = client;
				lobby.name = client.userName;
				lobby.setVisible(true);
				SetShips.dispose();
			}
		});
		
		SetShips.Back.setText("Main Menu");
		SetShips.Back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					client.out.writeObject("EXIT");
					client.out.flush();
					client.sock.close();
					client.stop();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				main (null);
				SetShips.dispose();
			}
		});
		SetShips.layeredPane.add(SetShips.OExit);
		SetShips.OExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					client.out.writeObject("EXIT");
					client.out.flush();
					client.sock.close();
					client.stop();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				System.exit(0);
			}
		});
		
		SetShips.btnPlay.setText("Ready");
		SetShips.btnPlay.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				time.cancel();
				timer.cancel();
				if (SetShips.shipList.getSize()==0) {
					String loc = "%%";
					for (String i :SetShips.shipsLocation ) {
						loc += i;
					}
					try {
					client.out.writeObject(loc);
					client.out.flush();}
					catch (Exception e1) {}
					SetShips.layeredPane.add(SetShips.black);
					SetShips.layeredPane.add(SetShips.txt);}
					
				
				
			
		  else JOptionPane.showMessageDialog(null, "Please add all the ships ");
					}
		});
	}
	public static void checkEnemy (JToggleButton button, ArrayList<String> enemyShips) {
		board.textArea.append("You striked " + button.getName()+ "\n");
		if (enemyShips.contains(button.getName())) {
			enemyShips.remove(button.getName());
			
			board.textArea.append (button.getName () + " is a hit \n");
			button.setIcon(new ImageIcon(Board.class.getResource("/headsup/hit.png")));
			button.setDisabledIcon(new ImageIcon(Board.class.getResource("/headsup/hit.png")));
			button.setEnabled(false);
			int Count = 0; 
			for (int i =0 ; i < enemyShips.size();i++) {
				if (enemyShips.get(i).equals("/"))Count +=Count---Count;
				else Count = 0;
				if (Count == 2) {
					board.textArea.append (" We destroyed their ship \n");
					
					enemyShips.remove(i);
					i+=i---1;
					if (MusicOn)boom();
				}
			}
			
		}else {	
			button.setIcon(new ImageIcon(Board.class.getResource("/headsup/miss.png")));
			button.setDisabledIcon(new ImageIcon(Board.class.getResource("/headsup/miss.png")));
			board.textArea.append (button.getName () + " is a miss \n");}
		button.setEnabled(false);
		boolean done = true; 
		for (String s: enemyShips) {
			if (!s.equals("/")) {
				done = false;
				break;
			}
		}
		if (done) {
			if (JOptionPane.showConfirmDialog(null, "You won \n Do you want to play again", "Game Over", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, new ImageIcon (Board.class.getResource("/headsup/win.png")))== 0) {
				
				main (null);
				board.dispose();}
			else if(JOptionPane.showConfirmDialog(null, " Do you want to exit", "Game Over", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, new ImageIcon (Board.class.getResource("/headsup/close.png")))== 0)
			System.exit(0);
			else {
				
				main (null);
				board.dispose();}}
		}
	private static void playTurn (ArrayList <JToggleButton> btnsEnemy,ArrayList <String> enemy) {
		for (JToggleButton button : btnsEnemy ) {
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					synchronized (this) {
					if (playerTurn) {
					checkEnemy (button,enemy);
					button.setEnabled(false);
					playerTurn = false;}
					else {
						button.setSelected(false);
					}
					}
		
		}
	});}
	}
	public static void play (URL url) {
		try{
			clip.stop();
			AudioInputStream sound = AudioSystem.getAudioInputStream(url);	
			clip = AudioSystem.getClip();
			clip.open(sound);
			int loop = clip.LOOP_CONTINUOUSLY;
			clip.loop(loop);
			
			
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public static void boom (){
		Clip clip2;
		try{
			URL url = StartGame.class.getResource("boom.wav");
			AudioInputStream sound = AudioSystem.getAudioInputStream(url);	
			clip2 = AudioSystem.getClip();
			clip2.open(sound);
			 clip2.start();
			Thread.sleep(3000);
			
		}
		catch(Exception e){
			e.printStackTrace();
		}}
	
	public static void GameOn (SetShips SetShips) {
		board = new Board (SetShips.shipsLocation);
		client.game = board;
		
		board.Close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					client.out.writeObject("EXIT");
					client.out.flush();
					client.sock.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.exit(0);
			}
		});
		board.MainMenu.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			try {
				client.out.writeObject("EXIT");
				client.out.flush();
				client.sock.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			client.stop();
			main (null);
			board.dispose();
		}});
		board.textArea.append("Game Started \n \n");
		if (playerTurn) {
			StartGame.board.layeredPane_1.add(StartGame.board.Turn);
		}
		
		//board.setExtendedState(JFrame.MAXIMIZED_BOTH);
		//board.setUndecorated (true);
		board.setVisible(true);
		SetShips.dispose();
		if (MusicOn) {
		
			play (GamePlay);
			done = false;}
			
		turn =new OponentsTurn (board.btns,board,client);
		Thread Oponent = new Thread (turn) ;
		Oponent.start();
		client.turn = turn;
		playerShips = SetShips.shipsLocation;
		previousStrikes = new ArrayList<String>();
		playTurnOnline (board.btnsEnemy,enemyShips);
		
	}
	public static void playTurnOnline (ArrayList <JToggleButton> btnsEnemy,ArrayList <String> enemy) {
		for (JToggleButton button : btnsEnemy ) {
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					synchronized (this) {
					if (playerTurn) {
						String message = button.getName();
						previousStrikes.add(message);
						try {
							client.out.writeObject ("##" + message);
							client.out.flush();
							}
							catch (Exception e ) {}
					
					checkOnlineEnemy (button,enemy);
					button.setEnabled(false);
					playerTurn = false;}
					
					
					else {
						button.setSelected(false);
					}
					}
		
		}
	});}
	}
	public static void checkOnlineEnemy (JToggleButton button, ArrayList<String> enemyShips) {
		board.textArea.append("You striked " + button.getName()+ "\n");
		if (enemyShips.contains(button.getName())) {
			enemyShips.remove(button.getName());
			
			board.textArea.append (button.getName () + " is a hit \n");
			button.setIcon(new ImageIcon(Board.class.getResource("/headsup/hit.png")));
			button.setDisabledIcon(new ImageIcon(Board.class.getResource("/headsup/hit.png")));
			button.setEnabled(false);
			int Count = 0; 
			for (int i =0 ; i < enemyShips.size();i++) {
				if (enemyShips.get(i).equals("/"))Count +=Count---Count;
				else Count = 0;
				if (Count == 2) {
					board.textArea.append (" We destroyed their ship \n");
					
					enemyShips.remove(i);
					i+=i---1;
					if (MusicOn)boom();
				}
			}
			
		}else {	
			button.setIcon(new ImageIcon(Board.class.getResource("/headsup/miss.png")));
			button.setDisabledIcon(new ImageIcon(Board.class.getResource("/headsup/miss.png")));
			board.textArea.append (button.getName () + " is a miss \n");}
		button.setEnabled(false);
		boolean done = true; 
		for (String s: enemyShips) {
			if (!s.equals("/")) {
				done = false;
				break;
			}
		}
		if (done) {
			JOptionPane.showMessageDialog(null, "You won", "Game Over", JOptionPane.YES_OPTION, new ImageIcon (Board.class.getResource("/headsup/win.png")));
				
				try {
					client.out.writeObject("XX");
					client.out.flush();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
						Lobby lobby = new Lobby ();
						client.lobby = lobby;
						lobby.client = client;
						lobby.name = client.userName;
						lobby.setVisible(true);
						board.dispose();
				
					}}
				
					
				
		
		
	public static  void time90out () {
		SetShips.random();
		
			String loc = "%%";
			for (String i :SetShips.shipsLocation ) {
				loc += i;
			}
			try {
			client.out.writeObject(loc);
			client.out.flush();}
			catch (Exception e1) {}
			SetShips.layeredPane.add(SetShips.black);
			SetShips.layeredPane.add(SetShips.txt);
			
	}
	public static void time60out () {
		while (true){
			
			
			
		String testHit = (char) (int) (Math.random()*10 +65) + ""+ ((int)(Math.random() *10 ));
		 if (testHit.charAt(0)<'K' && testHit.charAt(0)>'@' && testHit.charAt(1)<':' && testHit.charAt(1)>'/') {
				
		if (!previousStrikes.contains(testHit) ) {
			JToggleButton button = null;
			for (JToggleButton b : board.btnsEnemy) {
				if (b.getName().equals(testHit)) {
					button = b;
					break;
				}
			}
			previousStrikes.add(testHit);
			try {
				client.out.writeObject ("##" + testHit);
				client.out.flush();
				}
				catch (Exception e ) {}
		
		checkOnlineEnemy (button,enemyShips);
		button.setEnabled(false);
		playerTurn = false;
			break;
		}}}
		
		
	}
	
}

