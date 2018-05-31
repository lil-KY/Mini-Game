import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;


public class GameBlocks {
	
	
	
	public int blockXPos = 800;
	public int blockYPos = (int) (Math.random() * 600);
	public int yDir = -1 + (int) (Math.random() * 3);
	public static int blockXSpeed = 7;
	public static int blockYSpeed = (int)(Math.random()* 10);
	private Timer blockTimer = new Timer(50, new MyBlockTimeListener());
	
	
	public void draw(Graphics g) {
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(Color.RED);
		g2d.fillRect(blockXPos, blockYPos, 40, 40);
		blockTimer.start();
		
	}
	
	private class MyBlockTimeListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			
			blockXPos -= blockXSpeed;
			blockYPos += blockYSpeed * yDir;
			
		}
	}
	
	
	
}