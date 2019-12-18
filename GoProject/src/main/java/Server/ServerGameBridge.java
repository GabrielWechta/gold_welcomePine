package Server;

import Exceptions.*;
import GameMaster.*;

import java.io.IOException;

public class ServerGameBridge {
    RealPlayer playerW = (RealPlayer) PlayerW.getPlayer();
    RealPlayer playerB = (RealPlayer)PlayerB.getPlayer();
    private ServerConnector connector;


    ServerGameBridge(ServerConnector connector) throws IOException {
        this.connector = connector;
        connector.setServerBridge(this);
        connector.initiateConnection();
    }

    void initiateGame(int dimention) {
        new Game(dimention,this);
    }

    synchronized public void execute(String command,RealPlayer player) {

        String[] args;

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
                args = command.split("t");
                tryToPlay(Integer.parseInt(args[1]), Integer.parseInt(args[2]),player);
                break;
            case 'q':
                player.getOponent().getConnection(connector).send("q");
                break;
            case 'g':
                player.getOponent().getConnection(connector).send("g");
                break;
            case 'p':
                player.getOponent().getConnection(connector).send("p");
                pass(player);
                break;
         /*   case 'c':
                args = command.split("c");
                String[] subargs;

                for (int i = 1; i < args.length; i++) {
                    subargs = args[i].split(":");
                    game.addClaim(color, Integer.parseInt(subargs[1]), Integer.parseInt(subargs[2]),Integer.parseInt(subargs[3]));
                }
                if (game.claimsAreDone()) {
                    if (game.claimsNotContradict()) {
                       int  blackScore = game.finalScore('b');
                      int   whiteScore = game.finalScore('w');
                      char winner = blackScore>whiteScore ? 'b':'w';
                      connector.send("r"+"1"+winner);
                    } else {
                        connector.send("r"+"1");
                    }

                }
                break;
*/

        }
    }
    void pass(RealPlayer player)
    {
        ServerConnector.Connection connection = player.getConnection(connector);
        try {
            player.pass();

        } catch (NotYourTurnExeption notYourTurnExeption) {
            connection.send("n");
        }
    }

    void tryToPlay(int x, int y, RealPlayer player) {
        ServerConnector.Connection connection = player.getConnection(connector);

        try {
            player.playStone(x, y);
        } catch (OutOfBoardsBoundsException e) {
            connection.send("b");
        } catch (KoExeption koExeption) {
            connection.send("k");
        } catch (SuicidalTurnExeption suicidalTurnExeption) {
            connection.send("s");
        } catch (StoneAlreadyThereException e) {
            connection.send("o");
        } catch (NotYourTurnExeption notYourTurnExeption) {
            connection.send("n");
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
        connector.send(command);

    }

    public void endGame(int scoreW,int scoreB) {
        char winner = scoreW>=scoreB ? 'w':'b';
        connector.getBlackPlayerConnection().send("e"+winner+scoreB);
        connector.getWhitePlayerConnection().send("e"+winner+scoreW);
    }
}
