package Server;

import Exceptions.*;
import GameMaster.*;

import java.io.IOException;
import java.util.ArrayList;

public class ServerGameBridge {

    ArrayList<PlayerConnection> observers;
    ArrayList<PlayerConnection> players;


    ServerGameBridge(ArrayList<PlayerConnection> players, ArrayList<PlayerConnection> observers) throws IOException {
        this.observers = observers;
        this.players = players;
    }

    PlayerConnection getOpponent(PlayerConnection playerConnection) {
        if (playerConnection.equals(players.get(0)))
        {
            return players.get(1);
        }
        else{
            return players.get(0);
        }
    }

    void addObserver(PlayerConnection observer) {
        observers.add(observer);
    }

    void initiateGame(int dimention) {
        new Game(dimention, this);
    }

    synchronized public void execute(String command, PlayerConnection playerConnection) {

        String[] args;

        switch (command.charAt(0)) {

            case 't':
                args = command.split("t");
                tryToPlay(Integer.parseInt(args[1]), Integer.parseInt(args[2]), playerConnection);
                break;
            case 'q':
                playerConnection.sendQuit();
                break;
            case 'g':
                playerConnection.sendGiveUp();
                break;
            case 'p':
                playerConnection.sendPass();
                pass(playerConnection);
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

    void pass(PlayerConnection playerConnection) {

        try {
            getPlayer(playerConnection).pass();

        } catch (NotYourTurnExeption notYourTurnExeption) {
            playerConnection.sendNotTurn();
        }
    }

    void tryToPlay(int x, int y, PlayerConnection playerConnection) {


        try {
            play(x, y, playerConnection);
            getOpponent(playerConnection).makeTurn();
        } catch (OutOfBoardsBoundsException e) {
            playerConnection.sendNotInBounds();
        } catch (KoExeption koExeption) {
            playerConnection.sendKo();
        } catch (SuicidalTurnExeption suicidalTurnExeption) {
            playerConnection.sendSuicide();
        } catch (StoneAlreadyThereException e) {
            playerConnection.sendOccupied();
        } catch (NotYourTurnExeption notYourTurnExeption) {
            playerConnection.sendNotTurn();
        }

    }

    public void sendFieldState(int[][] field) {

        for (PlayerConnection observerConnection:observers
             ) {
            observerConnection.sendFieldState(field);
        }
        for (PlayerConnection playerConnection:players
        ) {
            playerConnection.sendFieldState(field);
        }

    }

    public void endGame(int scoreW, int scoreB) {
        boolean BlackIsWinner = scoreW < scoreB ;

            players.get(0).endGame(BlackIsWinner,scoreB);
        players.get(0).endGame(!BlackIsWinner,scoreW);

    }

    public RealPlayer getPlayer(PlayerConnection connection) {
        if (connection.equals(players.get(1))) {
            return (RealPlayer) PlayerW.getPlayer();
        } else if (connection.equals( players.get(0))) {
            return (RealPlayer) PlayerB.getPlayer();
        } else {
            return (RealPlayer) FictionPlayer.getPlayer();
        }
    }

    public void play(int x, int y, PlayerConnection playerConnection) throws SuicidalTurnExeption, NotYourTurnExeption, KoExeption, OutOfBoardsBoundsException, StoneAlreadyThereException {
        getPlayer(playerConnection).playStone(x, y);
        sendFieldState(((RealPlayer)PlayerW.getPlayer()).getFieldState());
    }
}
