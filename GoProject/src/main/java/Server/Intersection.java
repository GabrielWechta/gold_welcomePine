package Server;

public class Intersection {

	private final int x, y;
	private StoneChain stoneChain;
	private Player owner;
	
	public Intersection(int x, int y, StoneChain stoneChain) {
		super();
		this.x = x;
		this.y = y;
		this.stoneChain = stoneChain;
	}

	public StoneChain getStoneChain() {
		return stoneChain;
	}

	public void setStoneChain(StoneChain stoneChain) {
		this.stoneChain = stoneChain;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Player getOwner() {
		return owner;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
	}
	
	
	
}
