package Server;

import java.util.Random;

import Exceptions.KoExeption;
import Exceptions.OutOfBoardsBoundsException;
import Exceptions.StoneAlreadyThereException;
import Exceptions.SuicidalTurnExeption;
import GameMaster.Board;
import GameMaster.Player;

//przepraszam jeżeli źle zapamiętałem jak miały nazywać się te metody
public class ServerBot {

	private Board board;
	private Player botPlayerWhite; // nie moglem wymyslec innego rozwiazania. Jak W bocie będzie zagnieżdzony
									// Player to bedzie można go używac jak Playera przy użycie getters and setters
	private Random random;

	public ServerBot(Board board) {
		this.board = board;
		this.botPlayerWhite = new Player(2);
		this.random = new Random();
	}

	public void tryPlayUntillValid() {
		boolean validPlay = false;

		while (validPlay == false) {
			int x = random.nextInt() % board.getBoardSize();
			int y = random.nextInt() % board.getBoardSize();
			try {
				board.playStone(x, y, botPlayerWhite);
				validPlay = true;
			} catch (StoneAlreadyThereException e) {
				System.out.println("Bot thinks");
			} catch (OutOfBoardsBoundsException e) {
				System.out.println("Bot thinks");
			} catch (KoExeption e) {
				System.out.println("Bot thinks");
			} catch (SuicidalTurnExeption e) {
				System.out.println("Bot thinks");
			}
		}
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public void pass() {
		System.out.println("Bot passes");
		// connection.pass();
	}

}
