import GameMaster.Game;
import GameMaster.Board;
import GameMaster.Player;

public class ApplicationClass {

	public static void main(String[] args) {
		Player playerB = new Player(1);
		Player playerW = new Player(2);
		Board board = new Board(9);
		Game game = new Game(playerB, playerW, board);
		
		
		game.start();
		
	}

}
