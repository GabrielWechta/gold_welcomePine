package Server;

import GameMaster.Player;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ServerConnector {
    private ServerGameBridge bridge;
    private Connection black;
    private Connection white;
    private ExecutorService pool;

    void setServerBridge(ServerGameBridge bridge) {
        this.bridge = bridge;
    }

    void initiateConnection() throws IOException {

        try (var listener = new ServerSocket(58901)) {
            System.out.println("Go Server is Running...");
            pool = Executors.newFixedThreadPool(200);
            black = this.new Connection(listener.accept(), 'b');
            black.setup();
            black.send("ib");
            white = this.new Connection(listener.accept(), 'w');
            black.processCommands();


        }
    }

    Connection getWhitePlayerConnection() {
        return white;
    }

    Connection getBlackPlayerConnection() {
        return black;
    }

    public void connectSecondPlayer(int boardSize) throws IOException {
        white.setup();
        white.send("iw" + boardSize);
        pool.execute(black);
        pool.execute(white);
    }

    class Connection implements Runnable {
        char color;
        Socket socket;
        Scanner input;
        PrintWriter output;

        public Connection(Socket socket, char color) {
            this.socket = socket;
            this.color = color;
        }

        @Override
        public void run() {
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

        }

        void send(String command) {
            System.out.println("Sent to"+color+":" + command);
            output.println(command);
        }

        private void setup() throws IOException {
            input = new Scanner(socket.getInputStream());
            output = new PrintWriter(socket.getOutputStream(), true);
        }

        private void processCommands() {
            boolean nextLine;
            while (true) {
                try {
                    nextLine = input.hasNextLine();
                    if (nextLine) {
                        var command = input.nextLine();
                        System.out.println("Got from"+color+":" + command);
                        if (command.startsWith("q")) {
                            return;
                        } else
                            bridge.execute(command, color);
                    }
                } catch (Exception e) {

                }

            }
        }
    }


}


