package Server;

public interface PlayerConnection {
    public void setBridge(ServerGameBridge bridge);

    void sendQuit();

    void sendGiveUp();

    void sendPass();

    void sendNotTurn();

    void sendNotInBounds();

    void sendKo();

    void sendSuicide();

    void sendOccupied();

    void sendFieldState(int[][] field);

    void endGame(boolean blackIsWinner, int scoreB);
}
