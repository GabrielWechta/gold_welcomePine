package Client;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class GoPanelTest {

	@Test
	public void testingGoPanel(){
		GoPanel gp = new GoPanel(new GuiFacade('b', 9, null));
		gp.refreshBoard();
	}
}
