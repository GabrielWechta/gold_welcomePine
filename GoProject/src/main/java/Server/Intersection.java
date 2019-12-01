package Server;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Intersection {

    private final int x, y;
    private StoneChain stoneChain;
    private Player owner = null;
    private Board board;

    //regular constructor
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
        Set<Intersection> neighbors = getNotEmptyNeighbors();
        Intersection currentNeighbors;

        	new StoneChain(this,owner);
		if (!neighbors.isEmpty()) {

			for (Intersection intersection : neighbors
			) {
this.stoneChain.merge(intersection.getStoneChain());
			}
		}
    }


    public Intersection getEmptyNeighbors() {
        List<Intersection> emptyNeighbors = new ArrayList<Intersection>();

        int xTable[] = {-1, 0, 1, 0};
        int yTable[] = {0, -1, 0, 1};

        for (int i = 0; i < xTable.length; i++) {
            int newX = this.x + xTable[i];
            int newY = this.y + xTable[i];

            if (board.isIn(newX, newY)) {

            }
        }
        return null;
    }


}
