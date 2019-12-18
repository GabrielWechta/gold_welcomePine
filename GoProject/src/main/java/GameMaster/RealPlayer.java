package GameMaster;

import Exceptions.*;
import Server.ServerConnector;
import Server.ServerGameBridge;

public interface RealPlayer extends Player {




     boolean wasInKo();

    void setWasInKo(boolean koState);

    RealPlayer getOponent();

     void addScore(int points);

     ServerConnector.Connection getConnection(ServerConnector connector);

    void pass() throws NotYourTurnExeption;
    int getScore();
    void playStone(int x, int y) throws OutOfBoardsBoundsException, KoExeption, SuicidalTurnExeption, StoneAlreadyThereException, NotYourTurnExeption;
}
