package headsup;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLayeredPane;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.Desktop;
import javax.swing.JToggleButton;
import java.awt.Toolkit;
import javax.swing.JScrollPane;

public class StartMenu extends JFrame {

	private JPanel contentPane;
	JButton Play;
	JButton PlayOnline;
	JButton GameRules;
	JButton Exit;
	JButton MusicOnOff ; 
	private JLabel lblNewLabel;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	
	public StartMenu() {
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(StartMenu.class.getResource("/headsup/NewLogo.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1920,1080 );
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 1920, 1080);
		contentPane.add(layeredPane);
		layeredPane.setLayout(null);
		
		JLabel BackGround = new JLabel("");
		BackGround.setBounds(0, 0, 1920, 1080);
		BackGround.setIcon(new ImageIcon(StartMenu.class.getResource("/headsup/BackGround.jpg")));
		layeredPane.add(BackGround);
		
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		layeredPane.setLayer(panel, 1);
		panel.setBounds(0, 0, 1920, 1080);
		layeredPane.add(panel);
		panel.setLayout(null);
		
		Play = new JButton("Play");
		Play.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		Play.setBackground(Color.GREEN);
		Play.setFont(new Font("Snap ITC", Font.BOLD, 30));
		Play.setBounds(750, 359, 300, 75);
		panel.add(Play);
		
		PlayOnline = new JButton("Play Online");
		PlayOnline.setBackground(Color.YELLOW);
		PlayOnline.setFont(new Font("Snap ITC", Font.BOLD, 30));
		PlayOnline.setBounds(750, 447, 300, 75);
		panel.add(PlayOnline);
		
		GameRules = new JButton("Game Rules");
		GameRules.setBackground(Color.ORANGE);
		GameRules.setFont(new Font("Snap ITC", Font.BOLD, 30));
		GameRules.setBounds(750, 535, 300, 75);
		panel.add(GameRules);
		GameRules.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  try {
	                    Desktop.getDesktop().browse(new URI("https://www.google.com/search?hl=fr&sxsrf=ALeKk03f4-0_FiE0HM0MRB2D8gbIOjgqrA%3A1597187736262&source=hp&ei=mCYzX5XhDJKblwS9_KagDA&q=How+to+use+google&oq=How+to+use+google&gs_lcp=CgZwc3ktYWIQAzIFCAAQywEyBQgAEMsBMgUIABDLATIFCAAQywEyBQgAEMsBMgUIABDLATIFCAAQywEyBQgAEMsBMgUIABDLATIFCAAQywE6BAgjECc6AggAOgIILjoFCC4QywE6BggjECcQEzoHCAAQChDLAToECAAQDVDRqAFY7vEBYOH2AWgEcAB4AIAB9wKIAcsWkgEIMy4xNy4wLjGYAQCgAQGqAQdnd3Mtd2l6&sclient=psy-ab&ved=0ahUKEwiVjaD9o5TrAhWSzYUKHT2-CcQQ4dUDCAc&uact=5"));
	                } catch (URISyntaxException | IOException ex) {
	                    //It looks like there's a problem
	                }
			}
		});
		
		Exit = new JButton("Exit");
		Exit.setBackground(Color.RED);
		Exit.setFont(new Font("Snap ITC", Font.BOLD, 30));
		Exit.setBounds(750, 711, 300, 75);
		panel.add(Exit);
		Exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			System.exit (0);
				
			}});
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(StartMenu.class.getResource("/headsup/NewLogo.png")));
		lblNewLabel.setBounds(431, 13, 938, 291);
		panel.add(lblNewLabel);
		
		MusicOnOff = new JButton("Sound On");
		MusicOnOff.setBounds(750, 623, 300, 75);
		MusicOnOff.setBackground(new Color(255, 102, 102));
		MusicOnOff.setFont(new Font("Snap ITC", Font.BOLD, 30));
		panel.add(MusicOnOff);
		

			
		
			}
}
