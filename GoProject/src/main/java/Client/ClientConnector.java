package Client;

import java.io.IOException;
import java.util.Scanner;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.Executors;

public class ClientConnector {


    private Socket socket;
    private Scanner input;
    private PrintWriter output;
    ClientServerBridge bridge;

    void setBridge(ClientServerBridge bridge) {
        this.bridge = bridge;
    }

    public ClientConnector(String serverAddress) throws Exception {

        socket = new Socket(serverAddress, 58901);


    }

    void initializeConnection() {
        var pool = Executors.newFixedThreadPool(200);
        Runnable serverOutputProcessing = () -> {
            try {
                setup();
                processCommands();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                }
            }
        };
        pool.execute(serverOutputProcessing);
    }

    private void setup() throws IOException {
        input = new Scanner(socket.getInputStream());
        output = new PrintWriter(socket.getOutputStream(), true);
    }

    void send(String command) {
        System.out.println("Sent:" + command);
        output.println(command);
    }

    private void processCommands() {
        while (input.hasNextLine()) {
            var command = input.nextLine();
            System.out.println("Got:" + command);
            bridge.execute(command);
        }
    }
}
