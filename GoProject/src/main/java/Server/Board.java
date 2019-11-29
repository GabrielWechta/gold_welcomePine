package Server;

import Exceptions.IllegalRockColorException;
import Exceptions.OutOfBoardsBoundsException;

public class Board {

	private int stonesTable[][]; // tablica na kamienie wart:= 0 - pusty, 1 -kamien czarny, 2 - kamien bialy
	private int groupsTable[][]; // tablica reprezentująca grupy kamieni
	private int boardSize;

	public Board(int size) {
		this.boardSize = size;
		this.stonesTable = new int[size][size];
		this.groupsTable = new int[size][size];
	}

	// biłem się z myślami czy dodawać Exceptions, czy tylko if'y, ale doszedlem do
	// wniosku ze jako kluczowym elementem tej gry jest jej plansza to bedzie lepiej
	// jak wkladanie w nia cos bedzie dobrze zabezpeiczone
	public void insertStoneOnPosition(int x, int y, int rockColor)
			throws OutOfBoardsBoundsException, IllegalRockColorException {
		if (x >= boardSize || x < 0 || y >= boardSize || y < 0)
			throw new OutOfBoardsBoundsException();
		else if (rockColor < 0 || rockColor > 2)
			throw new IllegalRockColorException();
		stonesTable[x][y] = rockColor;
	}

	/**
	 * @return method shows gameBoard where (0,0) is the most South-East point on
	 *         the board.
	 */
	public void showBoard() {
		for (int i = boardSize - 1; i >= 0; i--) {
			for (int j = 0; j < boardSize; j++) {
				System.out.print(stonesTable[j][i]);
			}
			System.out.println();
		}
	}

	public int[][] getStonesTable() {
		return stonesTable;
	}

	public void setStonesTable(int[][] stonesTable) {
		this.stonesTable = stonesTable;
	}

	public int[][] getGroupsTable() {
		return groupsTable;
	}

	public void setGroupsTable(int[][] groupsTable) {
		this.groupsTable = groupsTable;
	}

}
