package BoardTests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import Server.Board;
import Server.Player;

public class BoardTest {

	@Test
	public void TestBasicInsertion() {
	Board board = new Board(6);
		
	Player playerB = new Player(1, board);
	
	playerB.playStone(0, 0);
	playerB.playStone(1, 0);
	playerB.playStone(2, 0);
	playerB.playStone(3, 3);
	
	//board.showBoard();
	
	assertEquals(1,board.getIntersection(0, 0).getOwner().getColor());
	}
	
	@Test
	public void TestChainMerging() {
	Board board = new Board(6);
		
	Player playerB = new Player(1, board);
	
	playerB.playStone(0, 0);
	System.out.println(board.getIntersection(0, 0).getStoneChain());
	playerB.playStone(1, 0);
	System.out.println(board.getIntersection(1, 0).getStoneChain());
	playerB.playStone(0, 1);	
	System.out.println(board.getIntersection(0, 1).getStoneChain());
	playerB.playStone(2, 0);
	System.out.println(board.getIntersection(2, 0).getStoneChain());

//	playerB.playStone(1, 0);
//	playerB.playStone(2, 0);
//	playerB.playStone(3, 3);
	
	board.showBoard();
	
	assertEquals(1,board.getIntersection(0, 0).getOwner().getColor());
	}
}
