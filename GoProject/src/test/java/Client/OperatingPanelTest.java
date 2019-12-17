package Client;

import org.junit.Test;

public class OperatingPanelTest {
	@Test
	public void testingOperatingPanel() {
		OperatingPanel op = new OperatingPanel(new GuiFacade('b', 9, null));
	}
}
