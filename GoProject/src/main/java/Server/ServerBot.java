package Server;

import java.util.Random;

import Exceptions.*;
import GameMaster.Board;
import GameMaster.Player;

public class ServerBot implements PlayerConnection {
    private ServerGameBridge bridge;
    private int size;
    private Random random;
    int turnCount  = 0;
    public ServerBot(int size,boolean first) {
        this.size = size;
        this.random = new Random();
        if(first)tryPlayUntillValid();
    }

    public void setBridge(ServerGameBridge bridge) {
        this.bridge = bridge;
    }

    @Override
    public void makeTurn() {
        tryPlayUntillValid();

    }

    @Override
    public void sendQuit() {
    }

    @Override
    public void sendGiveUp() {
    }

    @Override
    public void sendPass() {
        bridge.pass(this);
    }

    @Override
    public void sendNotTurn() {
    }

    @Override
    public void sendNotInBounds() {
    }

    @Override
    public void sendKo() {
    }

    @Override
    public void sendSuicide() {
    }

    @Override
    public void sendOccupied() {
    }

    @Override
    public void sendFieldState(int[][] field) {
    }

    @Override
    public void endGame(boolean blackIsWinner, int scoreB) {
    }

    public void tryPlayUntillValid()  {
        boolean validPlay = false;

        while (!validPlay) {
            int x = random.nextInt() % size;
            int y = random.nextInt() % size;
            try {
                bridge.play(x, y,this);
                validPlay = true;
                bridge.getOpponent(this).makeTurn();
            } catch (StoneAlreadyThereException e) {
                System.out.println("Bot is complaining about how owecrouded this fiel is");
            } catch (OutOfBoardsBoundsException e) {
                System.out.println("Bot is aiming");
            } catch (KoExeption e) {
                System.out.println("Bot tries to avoid loops");
            } catch (SuicidalTurnExeption e) {
                System.out.println("Bot don't want to kill himself");
            } catch (NotYourTurnExeption notYourTurnExeption) {
                System.out.println("Bot is waiting");

            }
        }
    }


}
