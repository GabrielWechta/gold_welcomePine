package GameMaster;

import Exceptions.*;
import Server.ServerConnector;

import java.util.HashSet;
import java.util.Set;

public class PlayerN implements Player {

    private final char color = 'n';
    private static Player thisPlayer = null;
    private Game game;

    static Player getPlayer() {
        if (thisPlayer == null) {
            thisPlayer = new PlayerN();
        }
        return thisPlayer;
    }

    private PlayerN() {
    }

    public void setGame(Game game) {
        this.game = game;
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
    public boolean isEqual(Player player){
        return player == this;
    }

}
