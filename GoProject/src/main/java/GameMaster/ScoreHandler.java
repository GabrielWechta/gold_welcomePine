package GameMaster;

import java.util.HashSet;
import java.util.Set;

import Server.Board;
import Server.Intersection;
import Server.Player;

public class ScoreHandler {
	private int scoreB = 0;
	private int scoreW = 0;
	private Board board;

	public ScoreHandler(Board board) {
		this.board = board;
	}

	/**
	 * main class method used to calculate territory points after finishing the game
	 */
	public void calculateTerritoryScore() {
		Set<Intersection> island = new HashSet<Intersection>();

		for (int y = board.getBoardSize() - 1; y >= 0; y--) {
			for (int x = 0; x < board.getBoardSize(); x++) {
				if (board.getIntersection(x, y).isEmpty()) {
					island = createIsland(board.getIntersection(x, y));
					checkIslandBorder(island);
				}
			}
		}
	}

	/** creates and fulfills islands with '9' */
	public Set<Intersection> createIsland(Intersection intersection) {
		Player calculatedPlayer = new Player(9);

		Set<Intersection> newIsland = new HashSet<Intersection>();
		Set<Intersection> changeIsland = new HashSet<Intersection>();
		Set<Intersection> oldIsland = new HashSet<Intersection>();

		newIsland.add(intersection);

		while (newIsland.equals(oldIsland) == false) {
			oldIsland.addAll(newIsland);
			for (Intersection section : newIsland) {
				changeIsland.addAll(section.getEmptyNeighbors());
			}
			newIsland.addAll(changeIsland);
		}

		for (Intersection section : newIsland) {
			section.setOwner(calculatedPlayer);
		}

		return newIsland;

	}

	/** checks if islands has neighbors in only one color */
	public void checkIslandBorder(Set<Intersection> island) {
		int bBorder = 0, wBorder = 0;
		for (Intersection intersection : island) {
			Set<Intersection> neigbors = new HashSet<Intersection>();
			neigbors.addAll(intersection.getNotEmptyNeighbors());
			for (Intersection neighbor : neigbors) {
				if (neighbor.getOwner().getColor() == 1)
					bBorder++;
				if (neighbor.getOwner().getColor() == 2)
					wBorder++;
			}
		}
		if (bBorder > 0 && wBorder == 0) {
			System.out.println("B:" + String.valueOf(island.size())); // for debugging
			this.scoreB += island.size();
		} else if (bBorder == 0 && wBorder > 0) {
			System.out.println("W:" + String.valueOf(island.size())); // for debugging
			scoreW += island.size();
		} else
			System.out.print("mutual");
	}

	public int getScoreB() {
		return scoreB;
	}

	public int getScoreW() {
		return scoreW;
	}

	public Board getBoard() {
		return board;
	}
}
