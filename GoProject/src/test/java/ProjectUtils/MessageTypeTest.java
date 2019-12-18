package ProjectUtils;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ProjectUtils.MessageType;

public class MessageTypeTest {
	@Test
	public void testingMessageTypeEnum() {
		MessageType mt = MessageType.GiveupMessage;
		assertEquals('g', mt.command);
	}
	
	@Test
	public void testingMessageTypeEnum2() {
		MessageType mt = MessageType.InitializeMessage;
		assertEquals('i', mt.command);
	}


}
