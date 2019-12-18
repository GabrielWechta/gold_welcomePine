package GameMaster;

import Exceptions.*;

public interface RealPlayer extends Player {


    boolean wasInKo();

    void setWasInKo(boolean koState);

    RealPlayer getOpponent();

    void addScore(int points);

    void pass() throws NotYourTurnExeption;

    int getScore();

    int[][] getFieldState();

    void playStone(int x, int y) throws OutOfBoardsBoundsException, KoExeption, SuicidalTurnExeption, StoneAlreadyThereException, NotYourTurnExeption;
}
