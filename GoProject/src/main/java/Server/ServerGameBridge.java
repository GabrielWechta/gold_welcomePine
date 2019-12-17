package Server;

import Exceptions.*;
import GameMaster.Game;
import GameMaster.Player;

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
            this.game = new Game(dimention,this);
        }
    }

    synchronized public void execute(String command, char color) {

        switch (command.charAt(0)) {
            case 'i':
                int boardSize = Integer.parseInt(command.substring(1));
                initiateGame(boardSize);
                try {
                    connector.connectSecondPlayer(boardSize);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            case 't':
                String[] args = command.split("t");
                tryToPlay(Integer.parseInt(args[1]), Integer.parseInt(args[2]),game.getPlayer(color));
                break;
            case 'q':
                break;
            case 'g':
                break;
            case 'p':
                break;


        }
    }

    void tryToPlay(int x, int y, Player player) {
        ServerConnector.Connection connection;
        if (player.getColor() == 'b') {
            connection = connector.getBlackPlayerConnection();
        } else {
            connection = connector.getWhitePlayerConnection();
        }
        try {
           game.makeMoveIfVaild(x,y,player);
        } catch (OutOfBoardsBoundsException e) {
            connection.send("b");
        } catch (KoExeption koExeption) {
            connection.send("k");
        } catch (SuicidalTurnExeption suicidalTurnExeption) {
            connection.send("s");
        } catch (StoneAlreadyThereException e) {
            connection.send("o");
        }catch (NotYourTurnExeption notYourTurnExeption) {
            connection.send("n");
        }
    }

    public void sendGiveUp(char player) {

    }


    public void sendPass(char player) {

    }


    public void sendQuit(char player) {

    }


    public void sendException(char player, Exception exception) {

    }


    public void sendFieldState(int[][] field) {
        int size = field.length;
        var com = new  StringBuilder();
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++) {
                com.append('t');
                com.append(i);
                com.append(':');
                com.append(j);
                com.append(':');
                com.append(field[i][j]);
            }
        var command = com.toString();
        connector.getBlackPlayerConnection().send(command);
        connector.getWhitePlayerConnection().send(command);
    }
}
