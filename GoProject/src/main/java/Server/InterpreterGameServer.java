package Server;

import GameMaster.Game;

public class InterpreterGameServer implements Interpreter {
    Game game;
    ServerConnector connector;

    InterpreterGameServer(Game game, ServerConnector connector) {
        this.game = game;
        this.connector = connector;
    }


    @Override
   synchronized public void execute(String command ) {

    }

    @Override
    public void send(char color, int[][] fieldState) {

    }

    @Override
    public void send(char color, int putX, int putY) {

    }
}
