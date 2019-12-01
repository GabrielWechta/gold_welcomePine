package Server;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Intersection {

	private final int x, y;
	private StoneChain stoneChain;
	private Player owner = null;
	private Board board;

	// regular constructor
	public Intersection(int x, int y, StoneChain stoneChain, Board board) {
		this.x = x;
		this.y = y;
		this.stoneChain = stoneChain;
		this.board = board;
	}

	public Intersection(int x, int y, Board board) {
		super();
		this.x = x;
		this.y = y;
		this.board = board;
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
	
	public void setOwner(Player owner) {
		this.owner = owner;
	}

	public Player getOwner() {
		return owner;
	}

	public boolean isEmpty() {
		if (this.owner == null)
			return true;
		else
			return false;
	}

	public List<Intersection> getEmptyNeighbors() {
		List<Intersection> emptyNeighbors = new ArrayList<Intersection>();

		int xTable[] = { -1, 0, 1, 0 };
		int yTable[] = { 0, -1, 0, 1 };

		for (int i = 0; i < xTable.length; i++) {
			int newX = this.x + xTable[i];
			int newY = this.y + yTable[i];

			if (board.isIn(newX, newY)) {
				Intersection sharedWallIntersection = board.getIntersection(newX, newY);
				if (sharedWallIntersection.isEmpty())
					emptyNeighbors.add(sharedWallIntersection);
			}
		}
		return emptyNeighbors;
	}
	
	public List<Intersection> getNotEmptyNeighbors() {
		List<Intersection> notEmptyNeighbors = new ArrayList<Intersection>();

		int xTable[] = { -1, 0, 1, 0 };
		int yTable[] = { 0, -1, 0, 1 };

		for (int i = 0; i < xTable.length; i++) {
			int newX = this.x + xTable[i];
			int newY = this.y + yTable[i];

			if (board.isIn(newX, newY)) {
				Intersection sharedWallIntersection = board.getIntersection(newX, newY);
				if (sharedWallIntersection.isEmpty() == false)
					notEmptyNeighbors.add(sharedWallIntersection);
			}
		}
		return notEmptyNeighbors;
	}

	public void putToken(Player owner) {
		setOwner(owner);
		List<Intersection> neighbors = getNotEmptyNeighbors();
		
		for (Intersection intersection : neighbors) {
		}
		
		new StoneChain(this, owner);
		if (!neighbors.isEmpty()) {

			for (Intersection intersection : neighbors) {
				this.stoneChain.merge(intersection.getStoneChain());
			}
		}
	}


}
