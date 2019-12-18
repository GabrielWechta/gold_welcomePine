package GameMaster;

import Exceptions.KoExeption;
import Exceptions.OutOfBoardsBoundsException;
import Exceptions.StoneAlreadyThereException;
import Exceptions.SuicidalTurnExeption;

import java.util.HashSet;
import java.util.Set;

public class EmptyPlayer implements Player {

    private final char color = 'b';
    private static Player thisPlayer = null;
    private Game game;

    @Override
    public boolean isEqual(Player player){
      return false;
    }
    static Player getPlayer() {
        if (thisPlayer == null) {
            thisPlayer = new EmptyPlayer();
        }
        return thisPlayer;
    }

    private EmptyPlayer() {
    }

    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    public int getNumber() {
        return 0;
    }



    public char getColor() {
        return color;
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

}
