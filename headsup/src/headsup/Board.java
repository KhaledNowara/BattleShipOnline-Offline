package headsup;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTable;
import javax.swing.JToggleButton;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.plaf.metal.MetalButtonUI;
import javax.swing.text.DefaultCaret;

import java.awt.Color;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Button;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JList;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JLayeredPane;
import java.awt.GridBagLayout;

public class Board extends JFrame {

	protected HashMap<String,JToggleButton > btns = new HashMap <String,JToggleButton> ();
	protected ArrayList <JToggleButton> btnsEnemy = new ArrayList <JToggleButton> ();
	protected JPanel contentPane;
	protected JPanel panel;
	protected JPanel panelSelf;
	protected JTextArea textArea;
	private int radarCount = 0 ;
	protected JButton MainMenu;
	JLayeredPane layeredPane_1;
	JLabel black;
	JLabel lblNewLabel_3;
	JButton Close;
	JLabel Turn;
	JLabel NotTurn;
	JLabel time;
	
	public Board(ArrayList<String> list) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1920, 1080);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(153, 102, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
	
		
		layeredPane_1 = new JLayeredPane();
		layeredPane_1.setBounds(0, 0, 1920, 1080);
		layeredPane_1.setLayout(null);
		contentPane.add(layeredPane_1);
		addElements(layeredPane_1);
		addButtons(layeredPane_1,list);
		
	
		panel = new JPanel();
		layeredPane_1.setLayer(panel, 1);
		panel.setBounds(173, 287, 577, 521);
		layeredPane_1.add(panel);
		panel.setOpaque(false);
		panel.setLayout(new GridLayout(10, 10, 0, 0));
		setEnemyGrid(panel);
		
		panelSelf = new JPanel();
		layeredPane_1.setLayer(panelSelf, 1);
		panelSelf.setBounds(779, 287, 577, 521);
		layeredPane_1.add(panelSelf);
		panelSelf.setOpaque(false);
		panelSelf.setLayout(new GridLayout(10, 10, 0, 0));
		setPlayerGrid(panelSelf,list);
		
		
		JScrollPane scrollPane = new JScrollPane();
		
		layeredPane_1.setLayer(scrollPane, 1);
		scrollPane.setBounds(1393, 345, 406, 399);
		layeredPane_1.add(scrollPane);
		textArea= new JTextArea();
		textArea.setFont(new Font("Tahoma", Font.CENTER_BASELINE, 20));
		scrollPane.setViewportView(textArea);
		
		Turn = new JLabel("Your Turn");
		Turn.setForeground(Color.YELLOW);
		Turn.setFont(new Font("Snap ITC", Font.PLAIN, 40));
		layeredPane_1.setLayer(Turn, 1);
		Turn.setBounds(376, 865, 243, 49);
		
		
		NotTurn = new JLabel("Opponent's turn");
		layeredPane_1.setLayer(NotTurn, 1);
		
		time = new JLabel("");
		time.setFont(new Font("Snap ITC", Font.PLAIN, 30));
		time.setForeground(Color.YELLOW);
		layeredPane_1.setLayer(time, 1);
		time.setBounds(746, 864, 56, 58);
		layeredPane_1.add(time);
		NotTurn.setForeground(Color.YELLOW);
		NotTurn.setFont(new Font("Snap ITC", Font.PLAIN, 40));
		NotTurn.setBounds(897, 866, 392, 49);
		
		DefaultCaret caret = (DefaultCaret)textArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		
	}
	
	private void setEnemyGrid (JPanel panel) {
		char loopy = 'A';
		for (int i = 0; i <10; i++) {
			for (int j =0; j<10;j++) {
				JToggleButton button = new JToggleButton("");
				button.setName((char)loopy + "" + (j));
				btnsEnemy.add(button);
				panel.add(button);}
				
			loopy ++;}
	}
	private void setPlayerGrid (JPanel panelSelf,ArrayList <String> shipsLocation) {
		char loopy = 'A';
		for (int i = 0; i <10; i++) {
			for (int j =0; j<10;j++) {
				JToggleButton button = new JToggleButton("");
				button.setName((char)loopy + "" + (j));
				btns.put(button.getName(),button);
				panelSelf.add(button);
				button.setEnabled(false);
				if (shipsLocation.contains(button.getName())) {
					
					button.setDisabledIcon(new ImageIcon(Board.class.getResource("/headsup/dot.png")));
					button.setIcon(new ImageIcon(Board.class.getResource("/headsup/dot.png")));
					}
			
		}
			loopy ++;}
	
	}
	public void addElements (JLayeredPane layeredPane) {
		JLabel BackGround = new JLabel("");
		layeredPane.setLayer(BackGround, 0);
		BackGround.setBounds(0, 0, 1920, 1080);
		BackGround.setIcon(new ImageIcon(Board.class.getResource("/headsup/BackGround.jpg")));
		layeredPane.add(BackGround);
		
		JLabel logo = new JLabel("");
		logo.setBounds(349, 27, 940, 312);
		layeredPane.setLayer(logo, 1);
		logo.setIcon(new ImageIcon(SetShips.class.getResource("/headsup/NewLogo.png")));
		layeredPane.add(logo);
		
		JLabel lblAAA = new JLabel("0  1  2  3  4  5  6  7  8  9");
		lblAAA.setBounds(173, 244, 577, 43);
		layeredPane.setLayer(lblAAA, 1);
		layeredPane.add(lblAAA);
		lblAAA.setForeground(Color.WHITE);
		lblAAA.setFont(new Font("Snap ITC", Font.BOLD, 33));
		
		JLabel lblA = new JLabel("A");
		layeredPane.setLayer(lblA, 1);
		lblA.setBounds(129, 287, 32, 52);
		layeredPane.add(lblA);
		lblA.setForeground(Color.WHITE);
		lblA.setFont(new Font("Snap ITC", Font.BOLD, 33));
		
		JLabel lblB = new JLabel("B");
		layeredPane.setLayer(lblB, 1);
		lblB.setBounds(129, 339, 32, 52);
		layeredPane.add(lblB);
		lblB.setForeground(Color.WHITE);
		lblB.setFont(new Font("Snap ITC", Font.BOLD, 33));
		
		JLabel lblC = new JLabel("C");
		layeredPane.setLayer(lblC, 1);
		lblC.setBounds(129, 391, 32, 52);
		layeredPane.add(lblC);
		lblC.setForeground(Color.WHITE);
		lblC.setFont(new Font("Snap ITC", Font.BOLD, 33));
		
		JLabel lblD = new JLabel("D");
		layeredPane.setLayer(lblD, 1);
		lblD.setBounds(129, 443, 32, 52);
		layeredPane.add(lblD);
		lblD.setForeground(Color.WHITE);
		lblD.setFont(new Font("Snap ITC", Font.BOLD, 33));
		
		JLabel lblE = new JLabel("E");
		layeredPane.setLayer(lblE, 1);
		lblE.setBounds(129, 495, 32, 52);
		layeredPane.add(lblE);
		lblE.setForeground(Color.WHITE);
		lblE.setFont(new Font("Snap ITC", Font.BOLD, 33));
		
		JLabel lblF = new JLabel("F");
		layeredPane.setLayer(lblF, 1);
		lblF.setBounds(129, 547, 32, 52);
		layeredPane.add(lblF);
		lblF.setForeground(Color.WHITE);
		lblF.setFont(new Font("Snap ITC", Font.BOLD, 33));
		
		JLabel lblG = new JLabel("G");
		layeredPane.setLayer(lblG, 1);
		lblG.setBounds(129, 599, 32, 52);
		layeredPane.add(lblG);
		lblG.setForeground(Color.WHITE);
		lblG.setFont(new Font("Snap ITC", Font.BOLD, 33));
		
		JLabel lblH = new JLabel("H");
		layeredPane.setLayer(lblH, 1);
		lblH.setBounds(129, 651, 32, 52);
		layeredPane.add(lblH);
		lblH.setForeground(Color.WHITE);
		lblH.setFont(new Font("Snap ITC", Font.BOLD, 33));
		
		JLabel lblI = new JLabel("I");
		layeredPane.setLayer(lblI, 1);
		lblI.setBounds(129, 703, 32, 52);
		layeredPane.add(lblI);
		lblI.setForeground(Color.WHITE);
		lblI.setFont(new Font("Snap ITC", Font.BOLD, 33));
		
		JLabel lblJ = new JLabel("J");
		layeredPane_1.setLayer(lblJ, 1);
		layeredPane.setLayer(lblB, 1);
		lblJ.setBounds(129, 755, 32, 52);
		layeredPane.add(lblJ);
		lblJ.setForeground(Color.WHITE);
		lblJ.setFont(new Font("Snap ITC", Font.BOLD, 33));
		

		JLabel lblCommentaryBox = new JLabel("Commentary Box");
		layeredPane.setLayer(lblCommentaryBox, 1);
		lblCommentaryBox.setBounds(1383, 287, 310, 40);
		layeredPane.add(lblCommentaryBox);
		lblCommentaryBox.setForeground(Color.WHITE);
		lblCommentaryBox.setFont(new Font("Snap ITC", Font.BOLD, 30));
		
		black = new JLabel("");
		black.setIcon(new ImageIcon(SetShips.class.getResource("/headsup/BackGround - Copy (2).jpg")));
		layeredPane.setLayer(black, 2);
		black.setBounds(0, 0, 1908, 1031);
		
		
		lblNewLabel_3 = new JLabel("Waiting for Opponent ...");
		lblNewLabel_3.setForeground(Color.YELLOW);
		lblNewLabel_3.setFont(new Font("Snap ITC", Font.BOLD, 76));
		layeredPane.setLayer(lblNewLabel_3, 4);
		lblNewLabel_3.setBounds(463, 390, 1133, 137);
		}
	
	
	public void addButtons (JLayeredPane layeredPane,ArrayList <String> list) {
		JButton Hint = new JButton("Hint");
		layeredPane.setLayer(Hint, 1);
		Hint.setBounds(1562, 774, 237, 41);
		layeredPane.add(Hint);
		Hint.setForeground(Color.WHITE);
		Hint.setBackground(Color.GREEN);
		Hint.setFont(new Font("Snap ITC", Font.PLAIN, 25));
		Hint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (radarCount<3) {
				while (true) {
				
				int i = list.size();
				int location = (int)(Math.random()*i);
				if (list.get(location)!= "/" || list.contains(list.get(location))) {
				System.out.println (list);
				System.out.println(list.get(location));
				textArea.append("The radar says " +  list.get(location) + " is a hot zone \n");
				radarCount += radarCount---radarCount;}break;}}
				else {
					textArea.append("The radar is broken :'( \n");
				}
				
			}
		});
		
		MainMenu = new JButton("Main Menu");
		layeredPane.setLayer(MainMenu, 1);
		MainMenu.setBounds(1562, 824, 237, 41);
		layeredPane.add(MainMenu);
		MainMenu.setForeground(Color.WHITE);
		MainMenu.setBackground(Color.ORANGE);
		MainMenu.setFont(new Font("Snap ITC", Font.PLAIN, 25));
		
		Close = new JButton("Close");
		layeredPane.setLayer(Close, 1);
		Close.setBounds(1562, 874, 237, 41);
		Close.setForeground(Color.WHITE);
		Close.setBackground(Color.RED);
		Close.setFont(new Font("Snap ITC", Font.PLAIN, 25));
		layeredPane.add(Close);
		
		
	
		
	}
}
