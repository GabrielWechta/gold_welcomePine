package GameMaster;

import java.util.HashSet;
import java.util.Set;

public class ScoreHandler {
	private int scoreB = 0;
	private int scoreW = 0;
	private Game game;

	public ScoreHandler(Game game) {
		this.game = game;
	}

	/**
	 * main class method used to calculate territory points after finishing the game
	 */
	public void calculateTerritoryScore() {
		Set<Intersection> island = new HashSet<Intersection>();

		for (int y = game.getBoardSize() - 1; y >= 0; y--) {
			for (int x = 0; x < game.getBoardSize(); x++) {
				if (game.getIntersection(x, y).isEmpty()) {
					island = createIsland(game.getIntersection(x, y));
					checkIslandBorder(island);
				}
			}
		}
	}

	/** creates and fulfills islands with '9' */
	public Set<Intersection> createIsland(Intersection intersection) {
		Player calculatedPlayer = FictionPlayer.getPlayer();
		calculatedPlayer.setGame(game);
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

	public void addScoreW(int stonesKilled)
	{
		scoreW=+ stonesKilled;

	}
	public void addScoreB(int stonesKilled)
	{
		scoreB=+ stonesKilled;

	}
}
