//package Server;
//
//import org.junit.Test;
//
//import GameMaster.Board;
//
//public class ServerBotTest {
//	
//	@Test
//	public void testingIfBotMakesMoves() {
//		Board board = new Board(9);
//		ServerBot bot = new ServerBot(board);
//		
//		board.showBoard();
//		bot.tryPlayUntillValid();
//		board.showBoard();
//		
//		bot.tryPlayUntillValid();
//		bot.tryPlayUntillValid();
//		bot.tryPlayUntillValid();
//		bot.tryPlayUntillValid();
//		bot.tryPlayUntillValid();
//		bot.tryPlayUntillValid();
//		bot.tryPlayUntillValid();
//		board.showBoard();
//	}
//	
//	@Test
//	public void testingIfBotPasses() {
//		Board board = new Board(9);
//		ServerBot bot = new ServerBot(board);
//		
//		bot.pass();
//	}
//}
