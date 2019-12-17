package Server;

import java.io.IOException;

public class Server {
	public static void main(String[] args) throws IOException {
		ServerConnector connection = new ServerConnector();
		new ServerGameBridge(connection);

	}
}
