package GameMaster;

import Exceptions.KoExeption;
import Exceptions.OutOfBoardsBoundsException;
import Exceptions.StoneAlreadyThereException;
import Exceptions.SuicidalTurnExeption;

public class Player {
	private int color = 1;
	private Board board;
	private boolean wasInKoLastTurn = false;

	public Player(int color, Board board) {
		this.color = color;
		this.board = board;
	}

	public Player(int color) {
		this.color = color;
	}

	public void playStone(int x, int y) throws OutOfBoardsBoundsException, KoExeption, SuicidalTurnExeption, StoneAlreadyThereException {
		board.playStone(x, y, this);
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public boolean wasInKo()
	{
		return wasInKoLastTurn;
	}
	void setWasInKo(boolean koState)
	{
		wasInKoLastTurn =koState;
	}

}
