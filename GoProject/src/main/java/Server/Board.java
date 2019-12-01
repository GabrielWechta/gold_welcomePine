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
		initBoard();
	}
	
	public void initBoard() {
		for(int i = 0; i < boardSize; i++) {
			for(int j = 0; j < boardSize; j++) {
				intersections[i][j] = new Intersection(i,j);
			}
		}
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

	public Intersection getIntersection(int x, int y) {
		return intersections[x][y];
	}

	public void playStone(int x, int y, Player owner) {
		if (isIn(x, y))
			intersections[x][y].setOwner(owner);
		else
			System.out.println("OutOfBands");
	}

	public void showBoard() {
		for (int i = boardSize - 1; i >= 0; i--) {
			for (int j = 0; j < boardSize; j++) {
				Player owner = intersections[j][i].getOwner();
				if(owner != null) System.out.print(owner.getColor());
				else System.out.print("0");
			}
			System.out.println();
		}
	}
}
