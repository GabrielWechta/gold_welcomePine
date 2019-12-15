package Server;

import GameMaster.Game;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.net.ServerSocket;
import java.util.concurrent.Executors;


public class ServerConnector {
    Interpreter interpreter;
    Connection black;
    Connection white;

    void setInterpreter(Interpreter interpreter) {

    }

    void initiateConnection() throws IOException {

        try (var listener = new ServerSocket(58901)) {
            System.out.println("Go Server is Running...");
            var pool = Executors.newFixedThreadPool(200);
            pool.execute(this.new Connection(listener.accept(), 'b'));
            pool.execute(this.new Connection(listener.accept(), 'w'));

        }
    }

    void sendToBlack(String command) {
        black.send(command);
    }

    void sendToWhite(String command) {
        white.send(command);
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

        }

        void send(String command) {
            output.println(command);
        }

        private void setup() throws IOException {
            input = new Scanner(socket.getInputStream());
            output = new PrintWriter(socket.getOutputStream(), true);

            if (color == 'b') {
                output.println("b");
            } else {
                output.println("w");
            }
        }

        private void processCommands() {
            while (input.hasNextLine()) {
                var command = input.nextLine();
                if (command.startsWith("q")) {
                    return;
                } else
                    interpreter.execute(command);
            }
        }
    }


}


