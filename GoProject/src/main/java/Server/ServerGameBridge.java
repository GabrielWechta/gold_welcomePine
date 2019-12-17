package Server;

import Exceptions.*;
import GameMaster.Game;
import GameMaster.Player;

import java.io.IOException;

public class ServerGameBridge {
    private Game game = null;
    private ServerConnector connector;


    ServerGameBridge(ServerConnector connector) throws IOException {
        this.connector = connector;
        connector.setServerBridge(this);
        connector.initiateConnection();
    }

    void initiateGame(int dimention) {
        if (game == null) {
            this.game = new Game(dimention, this);
        }
    }

    synchronized public void execute(String command, char color) {
        ServerConnector.Connection otherPlayer;
        String[] args;
        if (color == 'b')
            otherPlayer = connector.getWhitePlayerConnection();
        else
            otherPlayer = connector.getBlackPlayerConnection();
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
                tryToPlay(Integer.parseInt(args[1]), Integer.parseInt(args[2]), game.getPlayer(color));
                break;
            case 'q':
                otherPlayer.send("q");
                break;
            case 'g':
                otherPlayer.send("g");
                break;
            case 'p':
                otherPlayer.send("p");
                game.pass();
                break;
            case 'c':
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
            game.makeMoveIfVaild(x, y, player);
            sendFieldState();
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

    public void sendFieldState() {
        int[][] field = game.getFieldState();
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

    public void endGame() {
        connector.send("e");
    }
}
