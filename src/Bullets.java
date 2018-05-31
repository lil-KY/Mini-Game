import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Bullets {
	
	public int bulletXLoc = KGame.xLoc;
	public int bulletYLoc = KGame.yLoc;
	private Timer bulletTimer = new Timer(50, new MoveBulletListener());
	
	public void draw(Graphics g) {
		
		
		g.setColor(Color.WHITE);
		g.fillOval(bulletXLoc+ 30, bulletYLoc+ 10, 10, 10);
		//g.translate(KGame.mouseX, KGame.mouseY);
		bulletTimer.start();
	}
	
	
	private class MoveBulletListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			bulletXLoc += 5;
			
		}
	}
}