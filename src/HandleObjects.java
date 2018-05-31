import java.awt.Graphics2D;
import java.util.ArrayList;


public class HandleObjects {
	
	private GameBlocks tempBlock = new GameBlocks();
	public ArrayList<GameBlocks> blockList = new ArrayList<GameBlocks>();
	
	public void addBlock(GameBlocks block) {
		blockList.add(block);
	}
	
	public void removeBlock(GameBlocks block) {
		//block = null;
		blockList.remove(block);
	}
	
	public void clearBlock() {
		blockList.clear();
	}
	
	public void render(Graphics2D g) {
		for(int i = 0; i < blockList.size(); i++) {
			tempBlock = blockList.get(i);
			tempBlock.draw(g);
		}
	}
	
	public void hitBlock() {
		GameBlocks.blockXSpeed = 0;
		GameBlocks.blockYSpeed = 0;
			
		
	}
	
	public int getSize() {
		return blockList.size();
	}
	
	public GameBlocks getIndex(int i) {
		return blockList.get(i);
	}
	
	public void increaseSpeed() {
		GameBlocks.blockXSpeed += 1;
		
		
	}
	
}