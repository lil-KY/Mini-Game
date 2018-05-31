import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Menu extends JPanel {
	
	
	private JButton newGameButton;
	private JButton loadGameButton;
	private JButton exitButton;
	private MyButtonListener listenForButton;
	public Menu() {
		newGameButton = new JButton("Start Game");
		newGameButton.addActionListener(listenForButton);
		add(newGameButton);
		
		loadGameButton = new JButton("Load Game");
		loadGameButton.addActionListener(listenForButton);
		add(loadGameButton);
		
		
		exitButton = new JButton("Exit");
		exitButton.addActionListener(listenForButton);
		add(exitButton);
	}
	
	
	
	 private class MyButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == newGameButton) {
				
				new KGame();
			}
			if(e.getSource() == loadGameButton) {
				
			}
			if(e.getSource() == exitButton) {
				System.exit(0);
			}
		}
	}
}