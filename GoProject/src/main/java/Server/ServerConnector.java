package Server;

import GameMaster.Player;
import GameMaster.RealPlayer;

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

        try (ServerSocket listener = new ServerSocket(58901)) {
            System.out.println("Go Server is Running...");
            pool = Executors.newFixedThreadPool(200);
            black = this.new Connection(listener.accept(), 'b');
            black.setup();
            black.send("ib");
            white = this.new Connection(listener.accept(), 'w');
            black.processCommands();


        }
    }

    public Connection getWhitePlayerConnection() {
        return white;
    }

    public Connection getBlackPlayerConnection() {
        return black;
    }

    public void connectSecondPlayer(int boardSize) throws IOException {
        white.setup();
        white.send("iw" + boardSize);
        pool.execute(black);
        pool.execute(white);
    }

    public void send(String command) {
        white.send(command);
        black.send(command);
    }

    public class Connection implements Runnable {
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
            System.out.println("Sent to" + color + ":" + command);
            output.println(command);
        }

        private void setup() throws IOException {
            input = new Scanner(socket.getInputStream());
            output = new PrintWriter(socket.getOutputStream(), true);
        }

        private void processCommands() {
            boolean nextLine;
            while (true) {
                nextLine = false;
                try {
                    nextLine = input.hasNextLine();
                } catch (Exception e) {

                }

                if (nextLine) {
                    String command = input.nextLine();
                    System.out.println("Got from" + color + ":" + command);
                    bridge.execute(command, (RealPlayer) Player.getPlayer(color));
                    if (command.startsWith("q")) {
                        return;
                    }
                }


            }
        }
    }


}


