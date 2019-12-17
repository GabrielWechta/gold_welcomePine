package GameMaster;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import Exceptions.KoExeption;
import Exceptions.NotYourTurnExeption;
import Exceptions.OutOfBoardsBoundsException;
import Exceptions.StoneAlreadyThereException;
import Exceptions.SuicidalTurnExeption;
import Server.ServerGameBridge;

public class GameTest {

	@Test
	public void testingConstructor() {
		Player playerB = new Player(1);
		Player playerW = new Player(2);
		Game game = new Game(playerB, playerW, new Board(9));
		
		assertEquals(playerB, game.getBlackPlayer());
		assertEquals(playerW, game.getWhitePlayer());

	}
	
	@Test
	public void testingSecondConstructor() {
		ServerGameBridge bridge = null;
		Game game = new Game(9, bridge);
	}
	
	@Test(expected = NotYourTurnExeption.class)
	public void testingMethods() throws OutOfBoardsBoundsException, StoneAlreadyThereException, NotYourTurnExeption, KoExeption, SuicidalTurnExeption {
		Player playerB = new Player(1);
		Player playerW = new Player(2);
		Game game = new Game(playerB, playerW, null);
		
		assertEquals(false, game.ifReadyStart());
		
		game.start();
		game.pass();
		
		game.makeMoveIfVaild(2, 3, playerW);

	}
	
	@Test
	public void testingMethodsPart2() {
		Player playerB = new Player(1);
		Player playerW = new Player(2);
		Game game = new Game(playerB, playerW, new Board(9));
		
		game.claimsNotContradict();
		game.claimsAreDone();
		game.finalScore('b');
		game.addClaim('w', 1, 1, 0);
		game.getFieldState();
		game.getPlayer('b');

	}
	
	
}
