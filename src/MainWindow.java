import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;


import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;



public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;

	public static int WIDTH = 640;
	public static int HEIGHT = WIDTH / 16 * 9;

	public static JPanel panel = new JPanel();
	public static JEditorPane textarea = new JEditorPane();
	public static JScrollPane scroll = new JScrollPane(textarea);
	public static JTextField usernameField = new JTextField();
	public static JPasswordField passwordField = new JPasswordField();
	public static JButton startButton = new JButton("Login");



	public MainWindow(){
		try{
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		}catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		textarea.setPreferredSize(new Dimension(WIDTH - 64, HEIGHT - 120));
		textarea.setContentType("text/html");
		textarea.setEditable(false);

		usernameField.setPreferredSize(new Dimension(120,32));
		passwordField.setPreferredSize(new Dimension(120,32));
		panel.add(textarea);

		this.setSize(WIDTH,HEIGHT);

		panel.add(scroll);
		panel.add(new JLabel("Username:"));
		panel.add(usernameField);
		panel.add(new JLabel("Password:"));
		panel.add(passwordField);
		panel.add(startButton);
		this.add(panel);

		this.setVisible(true);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(3);
		LoadingScreen screen = new LoadingScreen();


		new Thread(new Runnable(){

			@Override
			public void run() {
				screen.writeText("Downloading changelog...");
				FileDownloader.downloadFile("http://www.sebka.nu/files/towerdefence/changelog.txt","changelog.txt");
				screen.writeText("Done.");
				screen.writeText("Updating changelog area...");
				MainWindow.textarea.setText(FileHand.getContent("changelog.txt"));
				MainWindow.getFrames()[0].toFront();
				screen.writeText("Done.");
				
				screen.writeText("Removing old update...");
				File file = new File("td.jar");
				file.delete();
				screen.writeText("Done.");
				
				screen.writeText("Downloading update...");
				FileDownloader.downloadFile("http://www.sebka.nu/files/towerdefence/game.jar", "td.jar");
				screen.writeText("Done.");
				
			}}).run();
		



		startButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				screen.writeText("Starting game...");


				try {



					/// LAUNCH PROGRAM ////
					Process proc;
					proc = Runtime.getRuntime().exec("java -jar td.jar");
					proc.waitFor();

					System.exit(0);




				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}});

	}

	public static void main(String[] args){

		new MainWindow();

	}
}
