//package GameMaster;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertTrue;
//
//import java.util.Set;
//
//import Exceptions.KoExeption;
//import Exceptions.NotYourTurnExeption;
//import Exceptions.OutOfBoardsBoundsException;
//import Exceptions.StoneAlreadyThereException;
//import Exceptions.SuicidalTurnExeption;
//import GameMaster.Board;
//import GameMaster.Intersection;
//import GameMaster.Player;
//import org.junit.Test;
//
//import GameMaster.ScoreHandler;
//public class ScoreHandlerTest {
//
//	@Test
//	public void createIslandTest() {
//		Game game = new Game(9);
//		ScoreHandler scoreHandler = new ScoreHandler(game);
//
//		PlayerB playerB = new PlayerB(game.getBoard());
//
//		try {
//			playerB.playStoneForTest(2, 0);
//			playerB.playStoneForTest(2, 1);
//			playerB.playStoneForTest(1, 1);
//			playerB.playStoneForTest(0, 2);
//		} catch (OutOfBoardsBoundsException e) {
//			e.printStackTrace();
//		} catch (KoExeption koExeption) {
//			koExeption.printStackTrace();
//		} catch (SuicidalTurnExeption suicidalTurnExeption) {
//			suicidalTurnExeption.printStackTrace();
//		} catch (StoneAlreadyThereException e) {
//			e.printStackTrace();
//		} catch (NotYourTurnExeption e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
////		playerB.playStoneForTest(0, 3);
//
//		Set<Intersection> island = scoreHandler.createIsland(game.getBoard().getIntersection(0, 8));
//		scoreHandler.checkIslandBorder(island);
//
//		assertEquals(74, scoreHandler.getScoreB());
//		assertEquals(0, scoreHandler.getScoreW());
//
//	}
//
//	@Test
//	public void createIslandTest2() {
//		Game game = new Game(9);
//		ScoreHandler scoreHandler = new ScoreHandler(game);
//
//		PlayerB playerB = new PlayerB(game.getBoard());
//
//		try {
//			playerW.playStoneForTest(3, 3);
//			playerW.playStoneForTest(2, 4);
//			playerW.playStoneForTest(3, 5);
//			playerW.playStoneForTest(4, 4);
//		} catch (OutOfBoardsBoundsException e) {
//			e.printStackTrace();
//		} catch (KoExeption koExeption) {
//			koExeption.printStackTrace();
//		} catch (SuicidalTurnExeption suicidalTurnExeption) {
//			suicidalTurnExeption.printStackTrace();
//		} catch (StoneAlreadyThereException e) {
//			e.printStackTrace();
//		}
//
//
//		Set<Intersection> island = scoreHandler.createIsland(board.getIntersection(3, 4));
//		scoreHandler.checkIslandBorder(island);
//
//		board.showBoard();
//		assertEquals(1, scoreHandler.getScoreW());
//
//	}
//
//	@Test
//	public void createIslandTest3() {
//		Board board = new Board(9);
//		ScoreHandler scoreHandler = new ScoreHandler(board);
//
//		Player playerB = new Player(1, board);
//		Player playerW = new Player(2, board);
//
//		try {
//			playerW.playStoneForTest(3, 3);
//			playerW.playStoneForTest(2, 4);
//			playerW.playStoneForTest(3, 5);
//			playerW.playStoneForTest(4, 4);
//
//			playerB.playStoneForTest(2, 0);
//			playerB.playStoneForTest(2, 1);
//			playerB.playStoneForTest(1, 1);
//			playerB.playStoneForTest(0, 2);
//		} catch (OutOfBoardsBoundsException e) {
//			e.printStackTrace();
//		} catch (KoExeption koExeption) {
//			koExeption.printStackTrace();
//		} catch (SuicidalTurnExeption suicidalTurnExeption) {
//			suicidalTurnExeption.printStackTrace();
//		} catch (StoneAlreadyThereException e) {
//			e.printStackTrace();
//		}
//
//
//		Set<Intersection> island = scoreHandler.createIsland(board.getIntersection(3, 4));
//		scoreHandler.checkIslandBorder(island);
//
//		board.showBoard();
//
//		Set<Intersection> island2 = scoreHandler.createIsland(board.getIntersection(0, 0));
//		scoreHandler.checkIslandBorder(island2);
//
//		board.showBoard();
//
//		Set<Intersection> island3 = scoreHandler.createIsland(board.getIntersection(5, 0));
//		scoreHandler.checkIslandBorder(island3);
//
//		System.out.println();
//		board.showBoard();
//
//		assertEquals(1, scoreHandler.getScoreW());
//		assertEquals(3, scoreHandler.getScoreB());
//
//	}
//	
//	@Test
//	public void calculateTerritoryTest() {
//		Board board = new Board(9);
//		ScoreHandler scoreHandler = new ScoreHandler(board);
//
//		Player playerB = new Player(1, board);
//		Player playerW = new Player(2, board);
//
//		try {
//			playerW.playStoneForTest(3, 3);
//			playerW.playStoneForTest(2, 4);
//			playerW.playStoneForTest(3, 5);
//			playerW.playStoneForTest(4, 4);
//
//			playerB.playStoneForTest(2, 0);
//			playerB.playStoneForTest(2, 1);
//			playerB.playStoneForTest(1, 1);
//			playerB.playStoneForTest(0, 2);
//		} catch (OutOfBoardsBoundsException e) {
//			e.printStackTrace();
//		} catch (KoExeption koExeption) {
//			koExeption.printStackTrace();
//		} catch (SuicidalTurnExeption suicidalTurnExeption) {
//			suicidalTurnExeption.printStackTrace();
//		} catch (StoneAlreadyThereException e) {
//			e.printStackTrace();
//		}
//
//		
//		scoreHandler.calculateTerritoryScore();
//
//		System.out.println();
//		board.showBoard();
//
//		assertEquals(1, scoreHandler.getScoreW());
//		assertEquals(3, scoreHandler.getScoreB());
//
//	}
//}
