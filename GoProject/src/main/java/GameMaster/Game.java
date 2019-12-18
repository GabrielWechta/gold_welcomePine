package GameMaster;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import Exceptions.*;
import Server.ServerGameBridge;

public class Game {
    private RealPlayer playerB;
    private RealPlayer playerW;
    private Board board;
    private RealPlayer turn;
    private ServerGameBridge bridge;
    private boolean pass;
    private ScoreHandler scoreHandler;


    public Game(int size, ServerGameBridge bridge) {
        this.bridge = bridge;
        this.board = new Board(size);
        this.playerB = (RealPlayer) PlayerB.getPlayer();
        this.playerW = (RealPlayer) PlayerW.getPlayer();
        playerB.setGame(this);
        playerW.setGame(this);
        turn = playerB;
        this.scoreHandler = new ScoreHandler(this);

    }

    synchronized public boolean makeMoveIfVaild(int x, int y, RealPlayer player) throws OutOfBoardsBoundsException, KoExeption, SuicidalTurnExeption, StoneAlreadyThereException, NotYourTurnExeption {
        if (player.isEqual(turn)) {
            board.playStone(x, y, player);
            sendFieldState();
            pass = false;
            switchTurn();
            return true;
        } else {
            throw new NotYourTurnExeption();
        }
    }

    public void pass(Player player) throws NotYourTurnExeption {
        if (player.isEqual(turn)) {
            if (!pass) {
                pass = true;
                switchTurn();
            } else {
                scoreHandler.calculateTerritoryScore();
                bridge.endGame(scoreHandler.getScoreW(), scoreHandler.getScoreB());
            }

        } else {
            throw new NotYourTurnExeption();
        }
    }

    private void switchTurn() {
        turn = turn.getOponent();
    }

    public void sendFieldState() {
        bridge.sendFieldState(board.getFieldState());
    }

    public int finalScore(RealPlayer player) {
        return player.getScore();
    }

    public void addScoreB(int points) {
        scoreHandler.addScoreB(points);
    }

    public void addScoreW(int points) {
        scoreHandler.addScoreW(points);
    }

    public int getBoardSize() {
        return board.getBoardSize();
    }

    public Intersection getIntersection(int x, int y) {
        return board.getIntersection(x, y);
    }

    public int getScoreW() {
        return scoreHandler.getScoreW();
    }

    public int getScoreB() {
        return scoreHandler.getScoreB();
    }

    public boolean boardContains(int x, int y) {
        return board.isIn(x, y);
    }
}
