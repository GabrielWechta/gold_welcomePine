package GameMaster;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import Exceptions.KoExeption;
import Exceptions.NotYourTurnExeption;
import Exceptions.OutOfBoardsBoundsException;
import Exceptions.StoneAlreadyThereException;
import Exceptions.SuicidalTurnExeption;
import GameMaster.Board;
import GameMaster.Player;
import GameMaster.StoneChain;
import org.junit.Test;

public class BoardTest {

	@Test
	public void TestBoardBasicBasics() {
		Board board = new Board(9);
		assertEquals(9, board.getBoardSize());

		int[][] fieldState = board.getFieldState();

		assertEquals(0, fieldState[0][0]);

	}

	@Test
	public void TestBoardBasics() {
		Board board = new Board(9);
		assertEquals(true, board.isIn(8, 8));
		assertEquals(true, board.isIn(3, 8));
		assertEquals(false, board.isIn(-1, 8));
		assertEquals(false, board.isIn(9, 8));
		assertEquals(false, board.isIn(0, 10));

	}

	@Test
	public void TestBoardShowing() {
		Board board = new Board(9);
		board.showBoard();

		int[][] fieldState = board.getFieldState();

		assertEquals(0, fieldState[0][0]);

	}
	

	@Test
	public void TestBasicInsertion() {
		Board board = new Board(6);

		PlayerB playerB = new PlayerB(board);
		

		try {
			playerB.playStoneForTest(0, 0);
			playerB.playStoneForTest(1, 0);
			playerB.playStoneForTest(2, 0);
			playerB.playStoneForTest(3, 3);
		} catch (OutOfBoardsBoundsException e) {
			e.printStackTrace();
		} catch (KoExeption koExeption) {
			koExeption.printStackTrace();
		} catch (SuicidalTurnExeption suicidalTurnExeption) {
			suicidalTurnExeption.printStackTrace();
		} catch (StoneAlreadyThereException e) {
			e.printStackTrace();
		} catch (NotYourTurnExeption e) {
			e.printStackTrace();
		}

		// board.showBoard();

		assertEquals('b', board.getIntersection(0, 0).getOwner().getColor());
	}

	@Test
	public void TestChainMerging() {
		Board board = new Board(6);

		PlayerB playerB = new PlayerB(board);

		try {
			playerB.playStoneForTest(0, 1);
			playerB.playStoneForTest(0, 0);
			playerB.playStoneForTest(1, 0);
			playerB.playStoneForTest(2, 0);
		} catch (OutOfBoardsBoundsException e) {

		} catch (KoExeption koExeption) {
			koExeption.printStackTrace();
		} catch (SuicidalTurnExeption suicidalTurnExeption) {
			suicidalTurnExeption.printStackTrace();
		} catch (StoneAlreadyThereException e) {
			e.printStackTrace();
		} catch (NotYourTurnExeption e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		StoneChain temp = board.getIntersection(0, 0).getStoneChain();
		assertEquals(temp, board.getIntersection(0, 0).getStoneChain());

		try {
			playerB.playStoneForTest(2, 2);
		} catch (OutOfBoardsBoundsException e) {
			e.printStackTrace();
		} catch (KoExeption koExeption) {
			koExeption.printStackTrace();
		} catch (SuicidalTurnExeption suicidalTurnExeption) {
			suicidalTurnExeption.printStackTrace();
		} catch (StoneAlreadyThereException e) {
			e.printStackTrace();
		} catch (NotYourTurnExeption e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		StoneChain temp2 = board.getIntersection(2, 2).getStoneChain();
		assertEquals(temp2, board.getIntersection(2, 2).getStoneChain());

		try {
			playerB.playStoneForTest(2, 1);
		} catch (OutOfBoardsBoundsException e) {
			e.printStackTrace();
		} catch (KoExeption koExeption) {
			koExeption.printStackTrace();
		} catch (SuicidalTurnExeption suicidalTurnExeption) {
			suicidalTurnExeption.printStackTrace();
		} catch (StoneAlreadyThereException e) {
			e.printStackTrace();
		} catch (NotYourTurnExeption e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertEquals(board.getIntersection(0, 0).getStoneChain(), board.getIntersection(2, 2).getStoneChain());

		// board.showBoard();
	}
}
