package headsup;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.Icon;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListModel;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import java.awt.Font;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.AbstractListModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JScrollPane;
import java.awt.Toolkit;

public class SetShips extends JFrame {

	private JPanel contentPane;
	HashMap<String,JToggleButton > ButtonIndex = new HashMap <String,JToggleButton> ();
	ArrayList<String> shipsLocation = new ArrayList<String>(); 
	private final ButtonGroup buttonGroup = new ButtonGroup();
	shipList shipList = new shipList ();
	private int shipSize = 0;
	private int listIndex = 5;
	private boolean horizontal = true ; 
	static SetShips frame; 
	JButton btnPlay;
	JButton Back;
	JButton Exit;
	JButton OExit;
	JLayeredPane layeredPane;
	boolean Online = false;
	JLabel black;
	JLabel lblNewLabel_3;
	JLabel background;
	private JLabel lblNewLabel;
	JLabel timee;
	JLabel txt;

	


	
	public SetShips() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(SetShips.class.getResource("/headsup/Logo.png")));
	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1920,1080 );
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 1920, 1080);
		layeredPane.setLayout(null);
	
	
		JPanel panel = new JPanel();
		layeredPane.setLayer(panel, 1);
		panel.setBounds(491, 281, 591, 488);
		layeredPane.add(panel);
		panel.setOpaque(false);
		panel.setLayout(new GridLayout(10, 10, 0, 0));
		
		initiateShips();
		setGrid (panel);
		addElements (layeredPane);
		
		black = new JLabel("");
		black.setIcon(new ImageIcon(SetShips.class.getResource("/headsup/BackGround - Copy (2).jpg")));
		layeredPane.setLayer(black, 2);
		black.setBounds(0, 0, 1908, 1031);
		
		
		
		timee = new JLabel("");
		timee.setForeground(Color.ORANGE);
		timee.setFont(new Font("Snap ITC", Font.PLAIN, 40));
		layeredPane.setLayer(timee, 1);
		
		txt = new JLabel("Waitinigi for opponent ...");
		txt.setForeground(Color.YELLOW);
		layeredPane.setLayer(txt, 3);
		txt.setFont(new Font("Snap ITC", Font.PLAIN, 60));
		txt.setBounds(423, 350, 970, 89);
	
		timee.setBounds(359, 281, 96, 69);
		
		
		
		
		
		
		
	}
	private boolean valid (String location, boolean horizontal,int shipsize,ArrayList <String> shipsLocation) {
		
		if (horizontal) {
			for (int i =0 ;i<shipsize;i++) {
				if (shipsLocation.contains(location.charAt(0) + "" + (char)(location.charAt(1) + i)))return false;
			}
			return((char)('9'-shipsize+1)-location.charAt(1) >=0);  
		}
		else{
			for (int i =0 ;i<shipsize;i++) {
				if(shipsLocation.contains((char)(location.charAt(0) + i) + "" + (location.charAt(1))))return false;}
		 
			return(((char)('J'- shipsize+1)-location.charAt(0)) >=0)  ;}
		
	}
	public void reset () {
		shipsLocation = new ArrayList<String>();
		shipsLocation.add("/");
		shipList.empty();
		shipList.add("Carrier");
		shipList.add("Battleship");
		shipList.add("Cruiser");
		shipList.add("Submarine");
		shipList.add("Destroyer");
		repaint();
		char loopy = 'A';
		Icon icon = new ImageIcon();
		for (int i = 0; i <10; i++) {
			
			for (int j = 0 ; j<10;j++) {
				String index = loopy + "" + (j);
				JToggleButton current = ButtonIndex.get(index);
				current.setSelected(false);
				current.setEnabled(true);
				current.setIcon(icon);
				
				
				
			}
			loopy ++;}
	}
	private void setGrid (JPanel panel) {
		char loopy = 'A';
		for (int i = 0; i <10; i++) {
			
			for (int j = 0 ; j<10;j++) {
			JToggleButton button = new JToggleButton("");
			button.setOpaque(false);
			String index = loopy + "" + (j);
			button.setName(index);;
			ButtonIndex.put(index , button);
			panel.add(button);
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					button.setSelected(false);
					boolean valid = true;
					if (shipSize ==0) valid = false; 
					
					char firstIndex = button.getName().charAt(0);
					char secondIndex = button.getName().charAt(1);
					valid = valid (firstIndex + "" + secondIndex,horizontal,shipSize,shipsLocation);
					
					if (valid) {
					for (int i = 0; i <shipSize;i++) {
						shipsLocation.add(firstIndex + "" + secondIndex);
						JToggleButton current = ButtonIndex.get(firstIndex + "" +secondIndex);
						current.setSelected(true);
						current.setIcon(new ImageIcon(Board.class.getResource("/headsup/dot.png")));
						current.setDisabledIcon(new ImageIcon(Board.class.getResource("/headsup/dot.png")));
						current.setEnabled(false);
						if (horizontal)secondIndex ++;
						else {firstIndex++;}
						shipsLocation.add("/");}
					
					shipList.remove(listIndex);
					listIndex = 5;
					shipSize = 0;
					repaint();}
					
					
					
				
					
					
					
				}
			});}
			loopy +=1 ;}
	}
	private void addElements (JLayeredPane layeredPane) {

		btnPlay = new JButton("Play");
		layeredPane.setLayer(btnPlay, 1);
		btnPlay.setBounds(1453, 288, 228, 39);
		layeredPane.add(btnPlay);
		btnPlay.setBackground(Color.GREEN);
		btnPlay.setFont(new Font("Snap ITC", Font.BOLD, 23));
		background = new JLabel("");
		background.setBounds(0, 0, 1920, 1080);
		background.setIcon(new ImageIcon(Board.class.getResource("/headsup/BackGround.jpg")));
		layeredPane.add(background);
		contentPane.setLayout(null);
		
		
		contentPane.add(layeredPane);
		
		JButton btnPlay_1 = new JButton("Random");
		layeredPane.setLayer(btnPlay_1, 1);
		btnPlay_1.setFont(new Font("Snap ITC", Font.BOLD, 23));
		btnPlay_1.setBackground(Color.ORANGE);
		btnPlay_1.setBounds(1453, 388, 228, 39);
		layeredPane.add(btnPlay_1);	
		btnPlay_1.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent arg0) {
				random ();
				
			}
			
		});
		Exit = new JButton("Exit");
		Exit.setBackground(Color.RED);
		
		Exit.setFont(new Font("Snap ITC", Font.BOLD, 23));
		layeredPane.setLayer(Exit, 1);
		Exit.setBounds(1453, 488, 228, 39);
		layeredPane.add(Exit);
		
		OExit = new JButton("Exit");
		OExit.setFont(new Font("Snap ITC", Font.BOLD, 23));
		layeredPane.setLayer(OExit, 2);
		OExit.setBackground(Color.RED);
		OExit.setBounds(1453, 540, 228, 39);
		
		
		JLabel lblNewLabel_1 = new JLabel("Help");
		lblNewLabel_1.setForeground(Color.ORANGE);
		lblNewLabel_1.setFont(new Font("Snap ITC", Font.PLAIN, 30));
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(null, "You should arrange your ships on the grid in prepration for the battle \n" + 
			            "The lieutenant says \n " + "The Carrier needs 5 spots on the grid \n" + 
						"The Battleship needs 4 spots onn the grid \n" 
						+ "The Submarine and the Cruiser need 3 spots on the grid \n" 
						+ " The Destroyer needs 2 spots on the grid \n" + 
						"Choose the formation wisely, May the force be with you", "Crew advice ", JOptionPane.INFORMATION_MESSAGE, new ImageIcon (Board.class.getResource("/headsup/madam.png")));		}
		});
		layeredPane.setLayer(lblNewLabel_1, 1);
		lblNewLabel_1.setIcon(new ImageIcon(SetShips.class.getResource("/headsup/help.png")));
		lblNewLabel_1.setBounds(27, 13, 131, 80);
		layeredPane.add(lblNewLabel_1);
		
		
		JButton reset = new JButton("Reset");
		layeredPane.setLayer(reset, 1);
		reset.setBounds(1453, 338, 228, 39);
		layeredPane.add(reset);
		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reset();			}
		});
		reset.setFont(new Font("Snap ITC", Font.BOLD, 25));
		reset.setBackground(Color.YELLOW);
		
		Back = new JButton("Back");
		layeredPane.setLayer(Back, 1);
		Back.setFont(new Font("Snap ITC", Font.BOLD, 23));
		Back.setBackground(new Color(255, 102, 102));
		Back.setBounds(1453, 438, 228, 39);
		layeredPane.add(Back);
		
		lblNewLabel = new JLabel("");
		layeredPane.setLayer(lblNewLabel, 1);
		lblNewLabel.setIcon(new ImageIcon(SetShips.class.getResource("/headsup/NewLogo.png")));
		lblNewLabel.setBounds(349, 27, 940, 312);
		layeredPane.add(lblNewLabel);
		
		JRadioButton Horizontal = new JRadioButton("Horizontal");
		layeredPane.setLayer(Horizontal, 1);
		Horizontal.setBounds(1205, 616, 248, 30);
		layeredPane.add(Horizontal);
		Horizontal.setBackground(new Color(0, 0, 51));
		Horizontal.setFont(new Font("Snap ITC", Font.PLAIN, 30));
		Horizontal.setForeground(Color.WHITE);
		Horizontal.setSelected(true);
		Horizontal.setOpaque(false);
		Horizontal.addActionListener(new ActionListener () {
			public void actionPerformed (ActionEvent arg) {
				horizontal = true;
				
			}
		});
		buttonGroup.add(Horizontal);
		
		JRadioButton Vertical = new JRadioButton("Vertical");
		layeredPane.setLayer(Vertical, 1);
		Vertical.setBounds(1205, 659, 203, 25);
		layeredPane.add(Vertical);
		Vertical.setOpaque(false);
		Vertical.setBackground(new Color(0, 0, 51));
		Vertical.setFont(new Font("Snap ITC", Font.PLAIN, 30));
		Vertical.setForeground(Color.WHITE);
		Vertical.addActionListener(new ActionListener () {
			public void actionPerformed (ActionEvent arg) {
				horizontal = false;
				
			}
		});
		
		buttonGroup.add(Vertical);
		JList list = new JList(shipList);
		layeredPane.setLayer(list, 1);
		list.setBounds(1205, 281, 203, 298);
		layeredPane.add(list);
		
		
	 	list.setForeground(Color.WHITE);
		list.setBackground(new Color(0, 0, 51));
		list.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		list.setSelectedIndex(0);
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				if (shipList.getElementAt(list.getSelectedIndex()).equals("Carrier"))
						shipSize = 5 ;
				else if (shipList.getElementAt(list.getSelectedIndex()).equals("Battleship"))
						shipSize = 4 ; 
				else if (shipList.getElementAt(list.getSelectedIndex()).equals("Cruiser")||shipList.getElementAt(list.getSelectedIndex()).equals("Submarine"))
						shipSize = 3;
				else if (shipList.getElementAt(list.getSelectedIndex()).equals("Destroyer"))
						shipSize = 2;
			listIndex = list.getSelectedIndex();
			
			}
		});
		
		list.setFont(new Font("Snap ITC", Font.PLAIN, 23));
		
		
		
	}
	private void initiateShips () {
		shipsLocation.add("/");
		shipList.add("Carrier");
		shipList.add("Battleship");
		shipList.add("Cruiser");
		shipList.add("Submarine");
		shipList.add("Destroyer");
	}
	public void random () {
		reset();
		shipList.empty();
		shipsLocation = shipGenerator.generateShip();
		for (String location : shipsLocation) {
			if (!location.equals("/")) { 
			
			ButtonIndex.get(location).setDisabledIcon(new ImageIcon(Board.class.getResource("/headsup/dot.png")));
			ButtonIndex.get(location).setIcon(new ImageIcon(Board.class.getResource("/headsup/dot.png")));	
	}}}
	
	}

