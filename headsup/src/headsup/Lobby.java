package headsup;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.JLayeredPane;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import java.awt.Toolkit;

public class Lobby extends JFrame {

	private JPanel contentPane;
	JButton MainMenu ;
	playersList playersList = new playersList ();
	JList list;
	Client client ;
	JScrollPane scrollPane;
	String name;
	String enemy;
	private JLabel lblNewLabel;
	JLabel waiting;
	JLayeredPane layeredPane;
	private JScrollPane scrollPane_1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Lobby frame = new Lobby();
					frame.setVisible(true);
					

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Lobby() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Lobby.class.getResource("/headsup/NewLogo.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1920, 1080);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 0, 0));
		
	
		
		layeredPane = new JLayeredPane();
		contentPane.add(layeredPane);
		
		lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(Lobby.class.getResource("/headsup/BackGround.jpg")));
		lblNewLabel.setBounds(0, 0, 1920, 1080);
		layeredPane.add(lblNewLabel);
		MainMenu = new JButton("Main Menu");
		
	
		layeredPane.setLayer(MainMenu, 1);
		MainMenu.setBounds(881, 515, 266, 41);
		layeredPane.add(MainMenu);
		MainMenu.setForeground(Color.WHITE);
		MainMenu.setBackground(Color.ORANGE);
		MainMenu.setFont(new Font("Snap ITC", Font.PLAIN, 25));
		
		JButton Close = new JButton("Close");
		layeredPane.setLayer(Close, 1);
		Close.setBounds(881, 567, 266, 41);
		Close.setForeground(Color.WHITE);
		Close.setBackground(Color.RED);
		Close.setFont(new Font("Snap ITC", Font.PLAIN, 25));
		layeredPane.add(Close);
		Close.addActionListener(new ActionListener() {
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

		JLabel logo = new JLabel("");
		logo.setBounds(349, 27, 940, 312);
		layeredPane.setLayer(logo, 1);
		logo.setIcon(new ImageIcon(SetShips.class.getResource("/headsup/NewLogo.png")));
		layeredPane.add(logo);
		
		scrollPane = new JScrollPane();
		layeredPane.setLayer(scrollPane, 1);
		scrollPane.setBounds(479, 387, 319, 362);
		layeredPane.add(scrollPane);
		
		
		list = new JList(playersList);

	 	list.setForeground(Color.WHITE);
		list.setBackground(new Color(0, 0, 51));
		list.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		list.setSelectedIndex(0);
		list.setFont(new Font("Snap ITC", Font.PLAIN, 23));
		scrollPane.setViewportView(list);
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
			enemy = (String) playersList.getElementAt(list.getSelectedIndex());
	
		
				
			}
		});
		
		
		
		
	
		
		JLabel lblNewLabel_1 = new JLabel("Available Players");
		lblNewLabel_1.setForeground(Color.YELLOW);
		lblNewLabel_1.setFont(new Font("Snap ITC", Font.BOLD, 27));
		layeredPane.setLayer(lblNewLabel_1, 1);
		lblNewLabel_1.setBounds(479, 309, 317, 65);
		layeredPane.add(lblNewLabel_1);
		
		JButton MainMenu_1 = new JButton("Request game ");
		MainMenu_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("doing");
				if (enemy != null) {
				try {
					client.out.writeObject('@' + enemy);
					client.out.flush();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}
				else JOptionPane.showMessageDialog(null, "please select a player","Error",JOptionPane.INFORMATION_MESSAGE );
			}
			
		});

		layeredPane.setLayer(MainMenu_1, 1);
		MainMenu_1.setForeground(Color.WHITE);
		MainMenu_1.setFont(new Font("Snap ITC", Font.PLAIN, 25));
		MainMenu_1.setBackground(Color.GREEN);
		MainMenu_1.setBounds(881, 461, 266, 41);
		layeredPane.add(MainMenu_1);
		
		waiting = new JLabel("Waiting for player to respond ...");
		waiting.setForeground(Color.YELLOW);
		waiting.setFont(new Font("Snap ITC", Font.PLAIN, 20));
		layeredPane.setLayer(waiting, 1);
		waiting.setBounds(832, 369, 421, 34);
		
	}
	public void lister (String message) {
		
		playersList.reset();
		message = message.substring(3);
		for (int i = 0; i < message.length(); i++) {
			String userName = "";
			while (message.charAt (i) !='\\') {
				userName += message.charAt(i);
				i += i---i;
			}
			
			if (!userName.equals(name)) playersList.add(userName);
			
		}
		list = new JList(playersList);
		listIntialzer (list);
		scrollPane.setViewportView(list);
		repaint();
		
		
	}
	public void listIntialzer (final JList list) {
		

	 	list.setForeground(Color.WHITE);
		list.setBackground(new Color(0, 0, 51));
		list.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		list.setSelectedIndex(0);
		list.setFont(new Font("Snap ITC", Font.PLAIN, 23));
		scrollPane.setViewportView(list);
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
			System.out.println("fff");
			enemy = (String) playersList.getElementAt(list.getSelectedIndex());
			System.out.println(enemy);
		
				
			}
		});
	}
}
