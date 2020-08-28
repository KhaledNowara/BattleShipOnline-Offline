package headsup;

import java.util.ArrayList;

public class shipGuess {
	String hit;
	public ArrayList <String> possible= new ArrayList <String>();
	public int Carrier = 0;
	public int 	Battleship = 0;
	public int Spot3 = 0;
	
	public shipGuess() {
		
	}
	public void setHit (String hit) {
		this.hit = hit;
		
	}
	public void think (int conStrikes, String hit){
		if (conStrikes==0) {
			String hit1 = (char) (hit.charAt(0) + 1) + "" +hit.charAt(1);
			String hit2 = (char) (hit.charAt(0) - 1) + "" +hit.charAt(1);
			String hit3 = hit.charAt(0)+ "" +(char) (hit.charAt(1)+1);
			String hit4 = hit.charAt(0)+ "" +(char) (hit.charAt(1)-1);
			if (hit1.charAt(0)<'K' && hit1.charAt(0)>'@' && hit1.charAt(1)<':' && hit.charAt(1)>'/')
				this.possible.add(hit1);
			if (hit2.charAt(0)<'K' && hit2.charAt(0)>'@' && hit2.charAt(1)<':' && hit2.charAt(1)>'/')
				this.possible.add(hit2);
			if (hit3.charAt(0)<'K' && hit3.charAt(0)>'@' && hit3.charAt(1)<':' && hit3.charAt(1)>'/')
				this.possible.add(hit3);
			if (hit4.charAt(0)<'K' && hit4.charAt(0)>'@' && hit4.charAt(1)<':' && hit4.charAt(1)>'/')
				this.possible.add(hit4);
		}
		else if (conStrikes==1) {
			int num1 = hit.charAt(0) - this.hit.charAt(0);
			int num2 = hit.charAt(1) - this.hit.charAt(1);
			String hit1 = hit;
			String hit2 = hit;
			
			switch (num1) {
			case 1 : hit1 = (char) (hit.charAt(0) - 2) + "" +hit.charAt(1);
					 hit2 = (char) (hit.charAt(0) + 1) + "" +hit.charAt(1);
					 break;
			case (-1): hit1 = (char) (hit.charAt(0) - 1) + "" +hit.charAt(1);
					 hit2 = (char) (hit.charAt(0) + 2) + "" +hit.charAt(1);
					 break;
			case 0: break;		 
			}
			switch (num2) {
			case 1 :  hit1 = hit.charAt(0)+ "" +(char) (hit.charAt(1)-2);	
					  hit2 = hit.charAt(0)+ "" +(char) (hit.charAt(1)+1);
					  	break;
			case (-1) : hit1 = hit.charAt(0)+ "" +(char) (hit.charAt(1)-1);
					    hit2 = hit.charAt(0)+ "" +(char) (hit.charAt(1)+2); 
					   
					   break;
			case 0: break;		 
			}
			if (hit1.charAt(0)<'K' && hit1.charAt(0)>'@' && hit1.charAt(1)<':' && hit1.charAt(1)>'/')
				this.possible.add(hit1);
			if (hit2.charAt(0)<'K' && hit2.charAt(0)>'@' && hit2.charAt(1)<':' && hit2.charAt(1)>'/')
				this.possible.add(hit2);
			
		}
		else if (conStrikes==2) {
			if (Carrier + Battleship + Spot3 < 4) {
			int num1 = hit.charAt(0) - this.hit.charAt(0);
			int num2 = hit.charAt(1) - this.hit.charAt(1);
			String hit1 = hit;
			String hit2 = hit;
			switch (num1) {
			case 2 : hit1 = (char) (hit.charAt(0) - 3) + "" +hit.charAt(1);
					 hit2 = (char) (hit.charAt(0) + 1) + "" +hit.charAt(1);
					 break;
			case (-2): hit1 = (char) (hit.charAt(0) - 1) + "" +hit.charAt(1);
					 hit2 = (char) (hit.charAt(0) + 3) + "" +hit.charAt(1);
					 break;
			case 0: break;		 
			}
			switch (num2) {
			case 2 :  hit1 = hit.charAt(0)+ "" +(char) (hit.charAt(1)-3);	
					  hit2 = hit.charAt(0)+ "" +(char) (hit.charAt(1)+1);
					  	break;
			case (-2) : hit1 = hit.charAt(0)+ "" +(char) (hit.charAt(1)-1);
					    hit2 = hit.charAt(0)+ "" +(char) (hit.charAt(1)+3); 
					   
					   break;
			case 0: break;		 
			}
			if (hit1.charAt(0)<'K' && hit1.charAt(0)>'@' && hit1.charAt(1)<':' && hit1.charAt(1)>'/')
				this.possible.add(hit1);
			if (hit2.charAt(0)<'K' && hit2.charAt(0)>'@' && hit2.charAt(1)<':' && hit2.charAt(1)>'/')
				this.possible.add(hit2);
			
			}
			else possible =new ArrayList<String>();
			}
		else if (conStrikes==3) {
			if (Carrier + Battleship < 2) {
			int num1 = hit.charAt(0) - this.hit.charAt(0);
			int num2 = hit.charAt(1) - this.hit.charAt(1);
			String hit1 = hit;
			String hit2 = hit;

			switch (num1) {
			case 3 : hit1 = (char) (hit.charAt(0) - 4) + "" +hit.charAt(1);
					 hit2 = (char) (hit.charAt(0) + 1) + "" +hit.charAt(1);
					 break;
			case (-3): hit1 = (char) (hit.charAt(0) - 1) + "" +hit.charAt(1);
					 hit2 = (char) (hit.charAt(0) + 4) + "" +hit.charAt(1);
					 break;
			case 0: break;		 
			}
			switch (num2) {
			case 3 :  hit1 = hit.charAt(0)+ "" +(char) (hit.charAt(1)-4);	
					  hit2 = hit.charAt(0)+ "" +(char) (hit.charAt(1)+1);
					  	break;
			case (-3) : hit1 = hit.charAt(0)+ "" +(char) (hit.charAt(1)-1);
					    hit2 = hit.charAt(0)+ "" +(char) (hit.charAt(1)+4); 
					   
					   break;
			case 0: break;		 
			}
			if (hit1.charAt(0)<'K' && hit1.charAt(0)>'@' && hit1.charAt(1)<':' && hit1.charAt(1)>'/')
				this.possible.add(hit1);
			if (hit2.charAt(0)<'K' && hit2.charAt(0)>'@' && hit2.charAt(1)<':' && hit2.charAt(1)>'/')
				this.possible.add(hit2);
			
			}
			else possible =new ArrayList<String>();}
		else if (conStrikes==4) {
			if (Carrier < 1) {
			int num1 = hit.charAt(0) - this.hit.charAt(0);
			int num2 = hit.charAt(1) - this.hit.charAt(1);
			String hit1 = hit;
			String hit2 = hit;
			switch (num1) {
			case 1 : hit1 = (char) (hit.charAt(0) - 5) + "" +hit.charAt(1);
					 hit2 = (char) (hit.charAt(0) + 1) + "" +hit.charAt(1);
					 break;
			case (-1): hit1 = (char) (hit.charAt(0) - 1) + "" +hit.charAt(1);
					 hit2 = (char) (hit.charAt(0) + 5) + "" +hit.charAt(1);
					 break;
			case 0: break;		 
			}
			switch (num2) {
			case 1 :  hit1 = hit.charAt(0)+ "" +(char) (hit.charAt(1)-5);	
					  hit2 = hit.charAt(0)+ "" +(char) (hit.charAt(1)+1);
					  	break;
			case (-1) : hit1 = hit.charAt(0)+ "" +(char) (hit.charAt(1)-1);
					    hit2 = hit.charAt(0)+ "" +(char) (hit.charAt(1)+5); 
					   
					   break;
			case 0: break;		 
			}
			if (hit1.charAt(0)<'K' && hit1.charAt(0)>'@' && hit1.charAt(1)<':' && hit1.charAt(1)>'/')
				this.possible.add(hit1);
			if (hit2.charAt(0)<'K' && hit2.charAt(0)>'@' && hit2.charAt(1)<':' && hit2.charAt(1)>'/')
				this.possible.add(hit2);
			
			}
			else possible =new ArrayList<String>();}
		
		
	}

}
