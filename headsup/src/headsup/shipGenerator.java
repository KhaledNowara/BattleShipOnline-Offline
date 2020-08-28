package headsup;

import java.util.ArrayList;

public class shipGenerator {
	static ArrayList <String> shipsLocation = new ArrayList <String> ();
	static int [] shipSizes = {5,4,3,3,2};
	
	private static boolean toss () {
		 if ((int)( Math.random()*10+1)>5)
			 return true;
		 else {
			 return false;
		 }}
	public static ArrayList<String> generateShip(){ 
		ArrayList <String> shipsLocation = new ArrayList <String> ();
		shipsLocation.add("/");
		for (int i : shipSizes) {
			String [] currentShip = new String[i];
			boolean flag =false;
			while (!flag) {
			flag = true ;
			if (toss()) {
				char l = (char) (int) (Math.random()*10 +65);
				int n = (int)(Math.random() *(10-i) );
				currentShip[0] = l+""+n;
				for(int j = 1;j<i;j++) {
					currentShip[j] = l+""+(n+j);
					}
			}
			else {
				char l = (char) (int) (Math.random()*(10-i) +65);
				int n = (int)(Math.random() *(10) );
				currentShip[0] = l+""+n;
				for(int j = 1;j<i;j++) {
					currentShip[j] = (char)(l+j)+""+n;
					}
			}
			for (String s:currentShip) {
				if (shipsLocation.contains(s)) {
					flag = false;
					break;
				}}
			if (flag) {
				for (String s:currentShip) {
					shipsLocation.add(s);
				}
			}
			}
			shipsLocation.add("/");}
		return shipsLocation;}
	
		
	
	public static void main (String []  args) {
		shipGenerator test = new shipGenerator();
	}

}
