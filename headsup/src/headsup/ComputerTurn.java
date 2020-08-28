package headsup;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;

public class ComputerTurn implements Runnable{
	public HashMap<String,JToggleButton > ButtonIndex;
	private shipGuess brains = new shipGuess();
	private String testHit;
	private String hit;
	int conStrikes;
	public ComputerTurn(HashMap<String,JToggleButton> map) {

	ButtonIndex = map;
	
	}

	@Override
	public void run() {
	
		ArrayList <String> previousStrikes = new ArrayList<String>();
		
		while (true) {
			boolean flag = true;
			synchronized (this){
			if (!StartGame.playerTurn) {
				// make a guess
				
				
				while (!brains.possible.isEmpty() ){
						flag = true;
					testHit = brains.possible.get(0);
					brains.possible.remove(0);
					if (!previousStrikes.contains(testHit) ) {
						hit = testHit;
						previousStrikes.add(hit);
						flag = false;
						break;}}
				if (flag) {
				while (true){
					switch (conStrikes) {
					case 5: brains.Carrier = 1;break;
					case 4:brains.Battleship = 1;break;
					case 3 :brains.Spot3 += brains.Spot3---brains.Spot3;break;
					
					}
				 testHit = (char) (int) (Math.random()*10 +65) + ""+ ((int)(Math.random() *10 ));
				 if (testHit.charAt(0)<'K' && testHit.charAt(0)>'@' && testHit.charAt(1)<':' && testHit.charAt(1)>'/') {
						
				if (!previousStrikes.contains(testHit) ) {
					hit = testHit;
					previousStrikes.add(hit);
					conStrikes =0;
					break;
				}}}}
				
				JToggleButton button = ButtonIndex.get(hit);
				StartGame.board.textArea.append ("The enemy striked " + button.getName () + "\n");
				if (StartGame.playerShips.contains(hit)) {
					StartGame.playerShips.remove(hit);
					 if (conStrikes == 0)
					brains.setHit(hit);
					brains.think(conStrikes, hit);
					conStrikes +=conStrikes---conStrikes;
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
					if (JOptionPane.showConfirmDialog(null, "You lost \n Do you want to play again", "Game Over", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, new ImageIcon (Board.class.getResource("/headsup/loss.png")))== 0) {
						StartGame.board.dispose();
						StartGame.main (null);}
					else if(JOptionPane.showConfirmDialog(null, " Do you want to exit", "Game Over", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, new ImageIcon (Board.class.getResource("/headsup/close.png")))== 0)
					System.exit(0);
					else {
						StartGame.board.dispose();
						StartGame.main (null);}
					}
					

				StartGame.playerTurn = true;
			}}
		}
	}

}
