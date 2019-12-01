package Server;

public class Player {
	private int color = 1;
	private Board board;

	public Player(int color, Board board) {
		this.color = color;
		this.board = board;
	}

	public Player(int color) {
		this.color = color;
	}

	public void playStone(int x, int y) {
		board.playStone(x, y, this);
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

}
