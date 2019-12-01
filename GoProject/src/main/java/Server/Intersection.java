package Server;

import java.util.ArrayList;
import java.util.List;

public class Intersection {

	private final int x, y;
	private StoneChain stoneChain;
	private Player owner = null;
	private Board board;

	// regular constructor
	public Intersection(int x, int y, StoneChain stoneChain) {
		this.x = x;
		this.y = y;
		this.stoneChain = stoneChain;
	}

	public Intersection(int x, int y) {
		super();
		this.x = x;
		this.y = y;
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

	public boolean isEmpty() {
		if (this.owner == null)
			return false;
		else
			return true;
	}

	public List<Intersection> getEmptyNeighbors() {
		List<Intersection> emptyNeighbors = new ArrayList<Intersection>();

		int xTable[] = { -1, 0, 1, 0 };
		int yTable[] = { 0, -1, 0, 1 };

		for (int i = 0; i < xTable.length; i++) {
			int newX = this.x + xTable[i];
			int newY = this.y + xTable[i];

			if (board.isIn(newX, newY)) {
				Intersection sharedWallIntersection = board.getIntersection(newX, newY);
				if (sharedWallIntersection.isEmpty())
					emptyNeighbors.add(sharedWallIntersection);
			}
		}
		return emptyNeighbors;
	}

}
