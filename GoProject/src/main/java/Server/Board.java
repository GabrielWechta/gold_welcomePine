package Server;

import java.awt.Point;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import Exceptions.IllegalRockColorException;
import Exceptions.OutOfBoardsBoundsException;
import Exceptions.StoneAlreadyThereException;

public class Board {

	private Intersection[][] intersections;
	private int boardSize;

	public Board(int size) {
		this.boardSize = size;
		this.intersections = new Intersection[boardSize][boardSize];
	}

	/**
	 * @return method shows gameBoard where (0,0) is the most South-East point on
	 *         the board.
	 */
	public void showGameBoardTable() {
		for (int y = boardSize - 1; y >= 0; y--) {
			for (int x = 0; x < boardSize; x++) {
				System.out.print(intersections[x][y].getOwner().getColor() + " ");
			}
			System.out.println();
		}
	}

	public boolean isIn(int x, int y) {
		if (x >= 0 && x < boardSize && y >= 0 && y < boardSize)
			return true;
		else
			return false;
	}

}
