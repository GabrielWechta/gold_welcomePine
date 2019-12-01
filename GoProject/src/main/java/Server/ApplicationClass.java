package Server;

public class ApplicationClass {

	public static void main(String[] args) {
		Board board = new Board(6);
		
		board.showBoard();
		
		Player playerB = new Player(1, board);
		Player playerW = new Player(2, board);
		
		playerB.playStone(1, 1);
		
		System.out.println();
		
		board.showBoard();
		
	}

}
