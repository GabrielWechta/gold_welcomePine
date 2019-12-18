package Server;

import GameMaster.RealPlayer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    static ServerSocket listener;

    public static void main(String[] args) throws IOException {

        listener = new ServerSocket(58901);
        ArrayList<PlayerConnection> players = new ArrayList<PlayerConnection>();
        ArrayList<PlayerConnection> observers = new ArrayList<PlayerConnection>();
        System.out.println("Go Server is Running...");
        ExecutorService pool = Executors.newFixedThreadPool(200);
        RealPlayerConnection firstPlayer = new RealPlayerConnection(listener.accept());
        firstPlayer.setup();
        firstPlayer.send("i");
        String command = firstPlayer.getLine();
        String[] arg = command.substring(1).split(":");
        int size = Integer.parseInt(arg[0]);
        char color = arg[1].charAt(0);
        int bots = Integer.parseInt(arg[2]);

        if (bots == 2) {
            players.add(new ServerBot(size,true));
            players.add(new ServerBot(size,false));
        } else if (bots == 1) {
            switch (color) {
                case 'p':
                    observers.add(firstPlayer);
                    players.add(new ServerBot(size,true));
                    players.add(connectPlayer(size, 'w'));
                    pool.execute((Runnable) players.get(1));
                    break;
                case 'w':
                    players.add(new ServerBot(size,false));
                    players.add(firstPlayer);
                    pool.execute((Runnable) players.get(1));
                    break;
                case 'b':
                    players.add(firstPlayer);
                    players.add(new ServerBot(size,false));
                    pool.execute((Runnable) players.get(0));
                    break;
            }

        } else {
            switch (color) {
                case 'p':
                    observers.add(firstPlayer);
                    players.add(connectPlayer(size, 'b'));
                    players.add(connectPlayer(size, 'w'));
                    break;
                case 'w':
                    players.add(connectPlayer(size, 'b'));
                    players.add(firstPlayer);
                    break;
                case 'b':
                    players.add(firstPlayer);
                    players.add(connectPlayer(size, 'w'));
                    break;
            }
            pool.execute((Runnable)players.get(0));
            pool.execute((Runnable)players.get(1));
        }
        System.out.println("Connections established...");
        ServerGameBridge bridge = new ServerGameBridge(players, observers);

        bridge.initiateGame(size);

        players.get(1).setBridge(bridge);

        players.get(0).setBridge(bridge);


        System.out.println("Recieving input...");

        while (true) {
            try {
                bridge.addObserver(connectPlayer(size, 's'));
            } catch (Exception e) {
            }
        }
    }


    static RealPlayerConnection connectPlayer(int size, char color) throws IOException {
        RealPlayerConnection newPlayer = new RealPlayerConnection(listener.accept());
        newPlayer.setup();
        newPlayer.send("i" + ":" + color + ":" + size);
        return newPlayer;
    }
}
