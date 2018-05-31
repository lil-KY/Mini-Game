
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;



public class KGame extends JFrame {
	
	
	public static int xLoc = 0;
	public static int yLoc = 270;
	private int ySpeed = 0;
	private GamePanel gamePane;
	private Timer gameTime = new Timer(50, new GameTimeListener());
	//private GameBlocks gameBlock;
	private HandleObjects objectHandler;
	
	
	private GameBlocks tempObject;
	
	
	private boolean gotHit = false;
	private Timer spawnBlock = new Timer(1000, new SpawnBlockListener());
	
	private int playerScore = 0;
	private int highScore = 0;
	
	
	private Timer blockSpeedTimer = new Timer(1000, new MyBlockSpeedListener());
	
	
	private Bullets bullet;
	private boolean clicked = false;
	public static int mouseX;
	public static int mouseY;

	
	public KGame() {
		
		objectHandler = new HandleObjects();
		//gameBlock = new GameBlocks();
		//objectHandler.addBlock(gameBlock);
		
		gamePane = new GamePanel();
		gamePane.setFocusable(true);
		gamePane.setFocusTraversalKeysEnabled(false);
		gamePane.addKeyListener(new GameKeyListener());
		gamePane.addMouseListener(new MyMouseListener());
		
		add(gamePane);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(800,600);
		setResizable(false);
		setVisible(true);
		
		gameTime.start();
		spawnBlock.start();
		blockSpeedTimer.start();
	}

	private class GamePanel extends JPanel {
		public void paintComponent(Graphics g) {
			//super.paintComponent(g);
			
			Graphics2D g2d = (Graphics2D) g;
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
			g2d.setColor(Color.BLACK);
			g2d.fillRect(0, 0, getWidth(), getHeight());
			//setBackground(Color.BLACK);
			g2d.setColor(Color.WHITE);
			g2d.fillOval(xLoc, yLoc, 30, 30);
			
			
			
			
			g2d.setColor(Color.YELLOW);
			g2d.setFont(new Font("serif", Font.BOLD, 20));
			g2d.drawString("Score: " + playerScore, 670, 20);;
			//g2d.drawString("High Score: " + highScore, 650, 40);
			
			
			//gameBlock.draw(g2d);
			if(clicked) {
				bullet.draw(g2d);
			}
			
			
			
			objectHandler.render(g2d);
			
			
			if(gotHit) {
				g2d.setColor(Color.WHITE);
				g2d.setFont(new Font("serif", Font.BOLD, 50));
				g2d.drawString("Press Enter to Restart", 160, 300);
			}
			
			g2d.dispose();
			
			
		}
	}
	private class GameKeyListener implements KeyListener {

		@Override
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			
		
			
			if(key == KeyEvent.VK_UP) {
				ySpeed = -5;
			}
			if(key == KeyEvent.VK_DOWN) {
				ySpeed = 5;
			}
			
			if(key == KeyEvent.VK_ENTER) {
				
				gotHit = false;
				yLoc = 270;
				GameBlocks.blockXSpeed = 7;
				GameBlocks.blockYSpeed = ((int)(Math.random()* 10));
				
				playerScore = 0;
				highScore = Math.max(playerScore, highScore);
				//objectHandler.removeBlock(gameBlock);
				objectHandler.clearBlock();
				//gameBlock = new GameBlocks();
				//objectHandler.addBlock(gameBlock);
				gamePane.requestFocus();
			}
			
			if(key == KeyEvent.VK_ESCAPE) {
				System.exit(0);
			}
			
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			int key = e.getKeyCode();

			if(key == KeyEvent.VK_UP) {
				ySpeed = 0;
			}
			if(key == KeyEvent.VK_DOWN) {
				ySpeed = 0;
			}
			
		}

		@Override
		public void keyTyped(KeyEvent e) {
			
			
		}
		
	}
	
	private class GameTimeListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			
			for(int i = 0; i < objectHandler.getSize(); i++) {
				tempObject = objectHandler.getIndex(i);
				
				if(tempObject.blockXPos + 40 < 0) {
					playerScore += 1;
					objectHandler.removeBlock(tempObject);
					
				}
			}
			
			for(int i = 0; i < objectHandler.getSize(); i++) {
				tempObject = objectHandler.getIndex(i);
				if(new Rectangle(xLoc, yLoc, 30, 30).intersects(new Rectangle(tempObject.blockXPos - 5, tempObject.blockYPos, 40, 40))) {
					gotHit = true;
					ySpeed = 0;
					
					objectHandler.hitBlock();
					
					//blockSpeedTimer.stop();
				}
			}
			
			/*if(gameBlock.blockXPos + 40 == 0) {
				playerScore += 5;
			}
			if(new Rectangle(xLoc, yLoc, 30, 30).intersects(new Rectangle(gameBlock.blockXPos - 5, gameBlock.blockYPos, 40, 40))) {
				
				gotHit = true;
				ySpeed = 0;
				gameBlock.blockXSpeed = 0;
			}*/
			if(yLoc < 0) {
				ySpeed = 0;
				yLoc = 0;
			}
			if(yLoc > getHeight() - 60) {
				ySpeed = 0;
				yLoc = getHeight() - 60;
			}
			
			yLoc = yLoc + ySpeed;
			gamePane.repaint();
		}
		
		
	}
	
	private class SpawnBlockListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			objectHandler.addBlock(new GameBlocks());
		}
	}
	
	private class MyBlockSpeedListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			objectHandler.increaseSpeed();
		}
	}
	
	private class MyMouseListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			mouseX = e.getX();
			mouseY = e.getY();
			clicked = true;
			bullet = new Bullets();
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			//clicked = false;
			
		}
		
	}
}