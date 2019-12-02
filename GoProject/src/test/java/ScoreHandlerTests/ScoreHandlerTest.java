package ScoreHandlerTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Test;

import GameMaster.ScoreHandler;
import Server.Board;
import Server.Intersection;
import Server.Player;

public class ScoreHandlerTest {

	@Test
	public void createIslandTest() {
		Board board = new Board(9);
		ScoreHandler scoreHandler = new ScoreHandler(board);

		Player playerB = new Player(1, board);

		playerB.playStone(2, 0);
		playerB.playStone(2, 1);
		playerB.playStone(1, 1);
		playerB.playStone(0, 2);
//		playerB.playStone(0, 3);

		Set<Intersection> island = scoreHandler.createIsland(board.getIntersection(0, 8));
		scoreHandler.checkIslandBorder(island);

		assertEquals(74, scoreHandler.getScoreB());
		assertEquals(0, scoreHandler.getScoreW());

	}

	@Test
	public void createIslandTest2() {
		Board board = new Board(9);
		ScoreHandler scoreHandler = new ScoreHandler(board);

		Player playerW = new Player(2, board);

		playerW.playStone(3, 3);
		playerW.playStone(2, 4);
		playerW.playStone(3, 5);
		playerW.playStone(4, 4);

		Set<Intersection> island = scoreHandler.createIsland(board.getIntersection(3, 4));
		scoreHandler.checkIslandBorder(island);

		board.showBoard();
		assertEquals(1, scoreHandler.getScoreW());

	}

	@Test
	public void createIslandTest3() {
		Board board = new Board(9);
		ScoreHandler scoreHandler = new ScoreHandler(board);

		Player playerB = new Player(1, board);
		Player playerW = new Player(2, board);

		playerW.playStone(3, 3);
		playerW.playStone(2, 4);
		playerW.playStone(3, 5);
		playerW.playStone(4, 4);

		playerB.playStone(2, 0);
		playerB.playStone(2, 1);
		playerB.playStone(1, 1);
		playerB.playStone(0, 2);

		Set<Intersection> island = scoreHandler.createIsland(board.getIntersection(3, 4));
		scoreHandler.checkIslandBorder(island);

		board.showBoard();

		Set<Intersection> island2 = scoreHandler.createIsland(board.getIntersection(0, 0));
		scoreHandler.checkIslandBorder(island2);

		board.showBoard();

		Set<Intersection> island3 = scoreHandler.createIsland(board.getIntersection(5, 0));
		scoreHandler.checkIslandBorder(island3);

		System.out.println();
		board.showBoard();

		assertEquals(1, scoreHandler.getScoreW());
		assertEquals(3, scoreHandler.getScoreB());

	}
	
	@Test
	public void calculateTerritoryTest() {
		Board board = new Board(9);
		ScoreHandler scoreHandler = new ScoreHandler(board);

		Player playerB = new Player(1, board);
		Player playerW = new Player(2, board);

		playerW.playStone(3, 3);
		playerW.playStone(2, 4);
		playerW.playStone(3, 5);
		playerW.playStone(4, 4);

		playerB.playStone(2, 0);
		playerB.playStone(2, 1);
		playerB.playStone(1, 1);
		playerB.playStone(0, 2);
		
		scoreHandler.calculateTerritoryScore();

		System.out.println();
		board.showBoard();

		assertEquals(1, scoreHandler.getScoreW());
		assertEquals(3, scoreHandler.getScoreB());

	}
}
