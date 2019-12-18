package GameMaster;

import Exceptions.*;

public class FictionPlayer implements RealPlayer {

    private final char color = 'n';
    private static Player thisPlayer = null;

    public static Player getPlayer() {
        if (thisPlayer == null) {
            thisPlayer = new FictionPlayer();
        }
        return thisPlayer;
    }

    private FictionPlayer() {
    }

    public void setGame(Game game) {
    }

    public char getColor() {
        return color;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int getNumber() {
        return 0;
    }

    @Override
    public boolean isEqual(Player player) {
        return player == this;
    }


    @Override
    public boolean wasInKo() {
        return false;
    }

    @Override
    public void setWasInKo(boolean koState) {

    }

    @Override
    public RealPlayer getOpponent() {
        return this;
    }

    @Override
    public void addScore(int points) {
    }

    @Override
    public void pass() throws NotYourTurnExeption {

    }
    @Override
    public int[][] getFieldState() {
        return new int[1][1];
    }

    @Override
    public int getScore() {
        return 0;
    }

    @Override
    public void playStone(int x, int y) throws OutOfBoardsBoundsException, KoExeption, SuicidalTurnExeption, StoneAlreadyThereException, NotYourTurnExeption {

    }
}
