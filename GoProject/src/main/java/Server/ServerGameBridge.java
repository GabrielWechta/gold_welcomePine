package Server;

import GameMaster.Game;

import java.io.IOException;

public class ServerGameBridge {
    Game game = null;
    ServerConnector connector = null;

    ServerGameBridge(ServerConnector connector) {
        this.connector = connector;
        connector.setServerBridge(this);
    }

    void initiateGame(int dimention) {
        if (game == null) {
            this.game = new Game(dimention);
        }
    }

    synchronized public void execute(String command) {
        switch (command.charAt(0))
        { case 'i':
            int boardSize =Integer.parseInt(command.substring(1));
            initiateGame(boardSize);
            try {
                connector.connectSecondPlayer(boardSize);
            } catch (IOException e) {
                e.printStackTrace();
            }
            break;


        }
    }


    public void sendGiveUp(char player) {

    }


    public void sendPass(char player) {

    }


    public void sendQuit(char player) {

    }


    public void sendTurn(int x, int y) {

    }


    public void sendException(char player, Exception exception) {

    }


    public void sendFieldState(int[][] field) {

    }
}
