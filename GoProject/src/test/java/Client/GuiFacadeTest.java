package Client;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class GuiFacadeTest {
	
	@Test
	public void testingDispalyingPassPopUp() {
		GuiFacade guiFacade = new GuiFacade();
		guiFacade.displayPass();
		assertTrue(true);
	}
	@Test
	public void testingDispalyingQuitPopUp() {
		GuiFacade guiFacade = new GuiFacade();
		guiFacade.displayQuit();
	}
	@Test
	public void testingDispalyingEndGameWinPopUp() {
		GuiFacade guiFacade = new GuiFacade();
		guiFacade.displayEndGame(true);
	}
	@Test
	public void testingDispalyingEndGameLosePopUp() {
		GuiFacade guiFacade = new GuiFacade();
		guiFacade.displayEndGame(false);
	}
}