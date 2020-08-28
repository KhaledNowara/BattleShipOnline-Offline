package headsup;
import java.util.ArrayList;
import java.util.Scanner;


public class GameStarter {
	static Scanner sc = new Scanner(System.in);
	public static String getGuess () {
		return sc.nextLine();
	} 
	
		
	
	public static void main (String [] args) {
		int numberOfHits = 0;
		String hit;
		System.out.println("Your goal is to sink three Battleships");
		System.out.println("MillenialFalcon, RogueShadow, DeathStar");
		System.out.println("The Battleships are located on a 7X7 grid with cells Starting form A1 to G7");
		System.out.println("Each turn you will enter a cell index where you think the BattleShips are located");
		System.out.println("Try to sink them all before the empire strikes back");
		System.out.println("First you will put your ships on the grid");
		WarZone player = new WarZone("player");
;		System.out.println("Start game");
			WarZone Tatooine = new WarZone();
			Tatooine.show();
			player.show();
			int i = 0;
			int conStrike = 0;
			shipGuess thi = new shipGuess();
			String testhit;
			while (true) {
				if (i%2==0) {
					i++;
					System.out.println("Enter your Guess");
					hit = getGuess();
					numberOfHits++;
					String check = Tatooine.checkWar(hit);
					System.out.println(check);
					if (Tatooine.size() == 0) {
						System.out.println("You won");
						break;}
				}
				else {
					i++;
					
					ArrayList <String> a = new ArrayList<String>();
					if (!thi.possible.isEmpty()) {
						while (true) {
						testhit = thi.possible.get(0);
						thi.possible.remove(0);
						if (!a.contains(testhit) ) {
							hit = testhit;
							a.add(hit);
							break;}}}
					else {
					while (true){
					 testhit = (char) (int) (Math.random()*7 +65) + Integer.toString((int)(Math.random() *7 +1));
					if (!a.contains(testhit) ) {
						hit = testhit;
						a.add(hit);
						conStrike =0;
						break;
					}}}
					System.out.println("The empire striked " + hit);
					String check = player.checkWar(hit);
					System.out.println(check);
					if (check.equals("Hit")) {
						if (conStrike == 0) 
							thi.setHit(hit);
						thi.think(conStrike, hit);
						conStrike++;
					}
					if (player.size() == 0) {
						System.out.println("You lost");
						break;}
				}
				
			}
	
			System.out.println ("you took " +  numberOfHits +  " hits");
		
			
		}
	
}

	
