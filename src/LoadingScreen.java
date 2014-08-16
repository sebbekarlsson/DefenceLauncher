import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class LoadingScreen{


	
	JFrame frame = new JFrame();
	public int WIDTH = 640;
	public int HEIGHT = WIDTH / 16 * 9;
	public JPanel panel = new JPanel();
	public JTextArea textarea = new JTextArea();
	public JScrollPane scroll = new JScrollPane(textarea);
	JButton closeButton = new JButton("Close");
	
	public LoadingScreen(){
		frame.setSize(WIDTH,HEIGHT);
		
		textarea.setEditable(false);
		textarea.setPreferredSize(new Dimension(WIDTH - 120, HEIGHT - 120));
		
		panel.add(scroll);
		panel.add(closeButton);
		
		frame.add(panel);
		
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		
		closeButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				
			}});
	}
	
	public void writeText(String text){
		textarea.append(text+"\n");
		frame.repaint();
		frame.toFront();
	}

}
