package Client;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClientConnector {


    private Socket socket = null;
    private Scanner input;
    private PrintWriter output;
    private ClientServerBridge bridge;

    void setBridge(ClientServerBridge bridge) {
        this.bridge = bridge;
    }

    public ClientConnector(String serverAddress) {
        System.out.println("Waiting for serwer...");
        while (socket == null) {
            try {
                socket = new Socket(serverAddress, 58901);
            } catch (UnknownHostException e) {
            } catch (IOException e) {
            }

        }
        try {
            setup();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void initializeConnection() {
        ExecutorService pool = Executors.newFixedThreadPool(200);
        Runnable serverOutputProcessing = () -> {
            try {
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
            String command = input.nextLine();
            System.out.println("Got:" + command);
            bridge.execute(command);
        }
    }
}
