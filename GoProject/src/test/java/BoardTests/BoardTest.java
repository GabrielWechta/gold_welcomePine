package BoardTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import Server.Board;
import Server.Player;
import Server.StoneChain;

public class BoardTest {

	@Test
	public void TestBoardBasics() {
		Board board = new Board(9);

		assertEquals(null, board.getIntersection(0, 0).getOwner());
		assertEquals(null, board.getIntersection(8, 8).getOwner());

		assertEquals(true, board.isIn(8, 8));
		assertEquals(true, board.isIn(3, 8));
		assertEquals(false, board.isIn(-1, 8));
		assertEquals(false, board.isIn(9, 8));
		assertEquals(false, board.isIn(0, 10));

	}

	@Test
	public void TestBasicInsertion() {
		Board board = new Board(6);

		Player playerB = new Player(1, board);

		playerB.playStone(0, 0);
		playerB.playStone(1, 0);
		playerB.playStone(2, 0);
		playerB.playStone(3, 3);

		// board.showBoard();

		assertEquals(1, board.getIntersection(0, 0).getOwner().getColor());
	}

	@Test
	public void TestChainMerging() {
		Board board = new Board(6);

		Player playerB = new Player(1, board);

		playerB.playStone(0, 0);
		playerB.playStone(1, 0);
		playerB.playStone(0, 1);
		playerB.playStone(2, 0);
		
		StoneChain temp = board.getIntersection(0, 0).getStoneChain();
		assertEquals(temp, board.getIntersection(0, 0).getStoneChain());

		playerB.playStone(2, 2);
		
		StoneChain temp2 = board.getIntersection(2, 2).getStoneChain();
		assertEquals(temp2, board.getIntersection(2, 2).getStoneChain());
		
		playerB.playStone(2, 1);
		
		assertEquals(board.getIntersection(0, 0).getStoneChain(), board.getIntersection(2, 2).getStoneChain());
		
		//board.showBoard();
	}
}