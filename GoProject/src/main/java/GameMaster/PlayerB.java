package GameMaster;

import Exceptions.*;

public class PlayerB implements RealPlayer {

    private final char color = 'b';
    private boolean wasInKoLastTurn = false;
    private static Player thisPlayer = null;
    private Game game;

    public static Player getPlayer() {
        if (thisPlayer == null) {
            thisPlayer = new PlayerB();
        }
        return thisPlayer;
    }

    private PlayerB() {
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public char getColor() {
        return color;
    }

    public boolean wasInKo() {
        return wasInKoLastTurn;
    }

    public void setWasInKo(boolean koState) {
        wasInKoLastTurn = koState;
    }

    public RealPlayer getOpponent() {
        return (RealPlayer) PlayerW.getPlayer();
    }

    public void addScore(int points) {
        game.addScoreB(points);
    }


    @Override
    public void pass() throws NotYourTurnExeption {
        game.pass(this);
    }
    @Override
    public boolean isEqual(Player player){
        return player == this;
    }

    @Override
    public int getScore() {
        return  game.getScoreB();
    }
    @Override
    public boolean isEmpty() {
        return false;
    }
    @Override
    public void playStone(int x, int y) throws OutOfBoardsBoundsException, KoExeption, SuicidalTurnExeption, StoneAlreadyThereException, NotYourTurnExeption{
        game.makeMoveIfVaild(x, y,this);
    }
    @Override
    public int getNumber() {
        return 1;
    }
    @Override
    public int[][] getFieldState() {
        return game.getFieldState();
    }
}
