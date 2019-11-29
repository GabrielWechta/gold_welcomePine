package go;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import Exceptions.IllegalRockColorException;
import Exceptions.OutOfBoardsBoundsException;
import Server.Board;

public class BoardTest {

	@Test
	public void testInsertingStones() {
		Board board = createExampleGameBoard();
		
		try {
			board.insertStoneOnPosition(1, 0, 1);
			board.insertStoneOnPosition(3, 3, 2);
		} catch (OutOfBoardsBoundsException e) {
			e.printStackTrace();
		} catch (IllegalRockColorException e) {
			e.printStackTrace();
		}
		
		assertEquals(1, board.getStonesTable()[1][0]);
		assertEquals(2, board.getStonesTable()[3][3]);
		
		int expectedX = 1, expectedY = 0, expectedColor = 2;
		
		try {
			board.insertStoneOnPosition(expectedX, expectedY, expectedColor);
		} catch (OutOfBoardsBoundsException e) {
			e.printStackTrace();
		} catch (IllegalRockColorException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testInsertingStonesOutOfBoardsBoundsException() throws OutOfBoardsBoundsException{
		Board board = createExampleGameBoard();
		
		try {
			board.insertStoneOnPosition(10, 0, 1);
		} catch (OutOfBoardsBoundsException e) {
			e.printStackTrace();
		} catch (IllegalRockColorException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testInsertingStonesIllegalRockColorException() throws IllegalRockColorException{
		Board board = createExampleGameBoard();
		
		try {
			board.insertStoneOnPosition(2, 0, -1);
		} catch (OutOfBoardsBoundsException e) {
			e.printStackTrace();
		} catch (IllegalRockColorException e) {
			e.printStackTrace();
		}
		
	}

	private Board createExampleGameBoard() {
		return new Board(9);
	}
}
