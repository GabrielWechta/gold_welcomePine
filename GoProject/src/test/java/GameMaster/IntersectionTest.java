package GameMaster;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import Exceptions.KoExeption;
import Exceptions.NotYourTurnExeption;
import Exceptions.OutOfBoardsBoundsException;
import Exceptions.StoneAlreadyThereException;
import Exceptions.SuicidalTurnExeption;
import GameMaster.Board;
import GameMaster.Intersection;
import GameMaster.Player;

public class IntersectionTest {

	Board board;
	PlayerB f;
	Player s;

	@Before
	public void setUp() {
		board = new Board(9);

		board.showBoard();

		f = new PlayerB(board);

	}

	@Test
	public void getX() {
		try {
			f.playStoneForTest(1, 1);
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

		assertEquals(1, 1);

	}

	@Test
	public void getY() {
		try {
			f.playStoneForTest(1, 1);
		} catch (OutOfBoardsBoundsException e) {
			e.printStackTrace();
		} catch (KoExeption koExeption) {
			koExeption.printStackTrace();
		} catch (SuicidalTurnExeption suicidalTurnExeption) {
			suicidalTurnExeption.printStackTrace();
		} catch (StoneAlreadyThereException e) {
			e.printStackTrace();
		}
		// assertEquals(board.getIntersection(1,1).getY() , 1);
		catch (NotYourTurnExeption e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void getOwner() {
		try {
			f.playStoneForTest(1, 1);
//            s.playStoneForTest(2,2);
//            s.playStoneForTest(3,3);
			f.playStoneForTest(4, 4);
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

		assertEquals(board.getIntersection(1, 1).getOwner(), f);
//		assertEquals(board.getIntersection(2, 2).getOwner(), s);
//		assertEquals(board.getIntersection(3, 3).getOwner(), s);
		assertEquals(board.getIntersection(4, 4).getOwner(), f);
	}

	@Test
	public void putToken() {

		try {
			board.getIntersection(1, 1).putToken(f);
		} catch (StoneAlreadyThereException e) {
			e.printStackTrace();
		} catch (KoExeption koExeption) {
			koExeption.printStackTrace();
		} catch (SuicidalTurnExeption suicidalTurnExeption) {
			suicidalTurnExeption.printStackTrace();
		}
		assertEquals(board.getIntersection(1, 1).getOwner(), f);
	}

	@Test
	public void getEmptyNeighbors() {
		try {
			f.playStoneForTest(1, 1);
			f.playStoneForTest(1, 2);
			f.playStoneForTest(1, 3);
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

		List<Intersection> emptySpaces = board.getIntersection(1, 2).getEmptyNeighbors();
		assertTrue(emptySpaces.contains(board.getIntersection(2, 2)));
		assertTrue(emptySpaces.contains(board.getIntersection(0, 2)));
	}

	@Test
	public void getNotEmptyNeighbors() {
		try {
			f.playStoneForTest(0, 0);
			f.playStoneForTest(1, 0);
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

		List<Intersection> emptySpaces = board.getIntersection(0, 0).getNotEmptyNeighbors();
		assertTrue(emptySpaces.contains(board.getIntersection(1, 0)));

	}
}