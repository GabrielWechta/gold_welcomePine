package Server;

import GameMaster.Player;
import GameMaster.RealPlayer;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class RealPlayerConnection implements PlayerConnection,Runnable {
    ServerGameBridge bridge;
    Socket socket;
    Scanner input;
    PrintWriter output;

    public void setBridge(ServerGameBridge bridge) {
        this.bridge = bridge;
    }

    @Override
    public void sendQuit() {
        send("q");
    }

    @Override
    public void sendGiveUp() {
        send("g");
    }

    @Override
    public void sendPass() {
        send("p");
    }

    @Override
    public void sendNotTurn() {
        send("n");
    }

    @Override
    public void sendNotInBounds() {
        send("b");
    }

    @Override
    public void sendKo() {
        send("k");
    }

    @Override
    public void sendSuicide() {
        send("s");
    }

    @Override
    public void sendOccupied() {
        send("o");
    }

    public RealPlayerConnection(Socket socket) {
        this.socket = socket;
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
        System.out.println("Sent:" + command);
        output.println(command);
    }

    void setup() throws IOException {
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
                System.out.println("Got:" + ":" + command);
                bridge.execute(command, this);
                if (command.startsWith("q")) {
                    return;
                }
            }


        }
    }

    public void sendFieldState(int[][] field) {
        int size = field.length;
        StringBuilder com = new StringBuilder();
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++) {
                com.append('t');
                com.append(i);
                com.append(':');
                com.append(j);
                com.append(':');
                com.append(field[i][j]);
            }
        String command = com.toString();
        send(command);
    }

    @Override
    public void endGame(boolean blackIsWinner, int scoreB) {
        char color = blackIsWinner ? 'b' : 'w';
        send("e" + color + scoreB);
    }

    public String getLine() {
        boolean nextLine;
        while (true) {
            nextLine = false;
            try {
                nextLine = input.hasNextLine();
            } catch (Exception e) {

            }

            if (nextLine) {
                String command = input.nextLine();
                System.out.println("Got:" + ":" + command);
                return command;
            }
        }


    }
}



