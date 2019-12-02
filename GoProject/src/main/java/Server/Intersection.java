package Server;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Intersection {

    public void putToken(Player owner) {
        List<Intersection> neighbors = getNotEmptyNeighbors();
        setOwner(owner);
        new StoneChain(this, owner);
        if (!neighbors.isEmpty()) {

            for (Intersection intersection : neighbors
            ) {
                this.stoneChain.merge(intersection.getStoneChain());
                stoneChain.removeLiberti(this);

            }
            for (Intersection emptyIntersection: getEmptyNeighbors()
            ) {
                stoneChain.addLiberti(emptyIntersection);
            }
        }
    }

	public void setOwner(Player owner) {
		this.owner = owner;
	}

	private final int x, y;
    private StoneChain stoneChain;
    private Player owner = null;
    private Board board;

    // regular constructor
    public Intersection(int x, int y, StoneChain stoneChain, Board board) {
        this.x = x;
        this.y = y;
        this.stoneChain = stoneChain;
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

		int xTable[] = {-1, 0, 1, 0};
		int yTable[] = {0, -1, 0, 1};
		int newX;
		int newY;
		System.out.println(emptyNeighbors.toString());
		for (int i = 0; i < xTable.length; i++) {
			newX = this.x + xTable[i];
			newY = this.y + yTable[i];

			if (board.isIn(newX, newY)) {
				Intersection sharedWallIntersection = board.getIntersection(newX, newY);

				if (sharedWallIntersection.isEmpty()) {

					emptyNeighbors.add(sharedWallIntersection);

				}
			}
		}
		return emptyNeighbors;
	}
	


    public List<Intersection> getNotEmptyNeighbors() {
        List<Intersection> notEmptyNeighbors = new ArrayList<Intersection>();

        int xTable[] = {-1, 0, 1, 0};
        int yTable[] = {0, -1, 0, 1};
        int newX;
        int newY;
		System.out.println(notEmptyNeighbors.toString());
        for (int i = 0; i < xTable.length; i++) {
            newX = this.x + xTable[i];
            newY = this.y + yTable[i];

            if (board.isIn(newX, newY)) {
                Intersection sharedWallIntersection = board.getIntersection(newX, newY);

                if (!sharedWallIntersection.isEmpty()) {

                    notEmptyNeighbors.add(sharedWallIntersection);

                }
            }
        }
        return notEmptyNeighbors;
    }


}
