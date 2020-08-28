package headsup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;

public class OponentsTurn implements Runnable{
	public HashMap<String,JToggleButton > ButtonIndex;
	int BigCheck = 0;
	int SmallCheck = 0;
	String hit;
	int conStrikes;
	Client client;
	int playAgain = -1;
	Board board; 
	boolean flag = true;
	timer60 action ;
	Timer timer;

	public OponentsTurn(HashMap<String,JToggleButton> map , Board board,Client c) {
		ButtonIndex = map;
		this.board = board;
		client = c;
	}

	@Override
	public void run() {
	
		
		timer = new Timer();
		action = new timer60(StartGame.board.time,30) ;
		timer.schedule(action,0,1000);
		while (true) {
			synchronized (this){
			if (!StartGame.playerTurn) {
				
				if (flag) {
				timer.cancel();
				timer = new Timer();
				action.cancel();
				action = new timer60(StartGame.board.time,30) ;
				action.function = false;
				timer.schedule(action,0,1000);
				StartGame.board.layeredPane_1.add(StartGame.board.NotTurn);
				StartGame.board.layeredPane_1.remove(StartGame.board.Turn);
				StartGame.board.repaint();
				flag = false;
				}
				if (BigCheck-SmallCheck == 1) {
					SmallCheck += SmallCheck --- SmallCheck;
					JToggleButton button = ButtonIndex.get(hit);
					StartGame.board.textArea.append ("The enemy striked " + button.getName () + "\n");
					if (StartGame.playerShips.contains(hit)) {
						StartGame.playerShips.remove(hit);
					button.setIcon(new ImageIcon(Board.class.getResource("/headsup/hit.png")));
					button.setDisabledIcon(new ImageIcon(Board.class.getResource("/headsup/hit.png")));
					StartGame.board.textArea.append (button.getName () + " is a hit \n");
					int Count = 0; 
					for (int i =0 ; i < StartGame.playerShips.size();i++) {
						if (StartGame.playerShips.get(i).equals("/"))Count +=Count---Count;
						else Count = 0;
						if (Count == 2) {
							StartGame.board.textArea.append (" Captin we lost a ship! \n");
							if (StartGame.MusicOn)StartGame.boom();
							StartGame.playerShips.remove(i);
							}
						}
					}
					else {
						button.setIcon(new ImageIcon(Board.class.getResource("/headsup/miss.png")));
						button.setDisabledIcon(new ImageIcon(Board.class.getResource("/headsup/miss.png")));
						StartGame.board.textArea.append (button.getName () + " is a miss \n");
					}
				boolean done = true; 
				for (String s: StartGame.playerShips) {
					if (!s.equals("/")) {
						done = false;
						break;
					}
				}
				if (done) {
					JOptionPane.showMessageDialog(null, "You lost \n Do you want to play again", "Game Over", JOptionPane.YES_OPTION, new ImageIcon (Board.class.getResource("/headsup/loss.png")));
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
								board.dispose();}
						
							
								StartGame.playerTurn = true;
								StartGame.board.layeredPane_1.add(StartGame.board.Turn);
								StartGame.board.layeredPane_1.remove(StartGame.board.NotTurn);
								timer.cancel();
								timer = new Timer();
								action.cancel();
								action = new timer60(StartGame.board.time,30) ;
								timer.schedule(action,0,1000);
								StartGame.board.repaint();
								flag = true;
						
				}
					
				
					

				
				
				}}
		}
	}

}
