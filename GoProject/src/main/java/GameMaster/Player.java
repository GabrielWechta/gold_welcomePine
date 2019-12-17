package GameMaster;

import Exceptions.KoExeption;
import Exceptions.OutOfBoardsBoundsException;
import Exceptions.StoneAlreadyThereException;
import Exceptions.SuicidalTurnExeption;

import java.util.HashSet;
import java.util.Set;

public class Player {
    private int color = 1;
    private Board board;
    private boolean wasInKoLastTurn = false;
    private Set<Intersection> claims = new HashSet<>();
    private Set<Intersection> seki = new HashSet<>();

    public Player(int color, Board board) {
        this.color = color;
        this.board = board;
    }

    public Player(int color) {
        this.color = color;
    }

    public void playStone(int x, int y) throws OutOfBoardsBoundsException, KoExeption, SuicidalTurnExeption, StoneAlreadyThereException {
        board.playStone(x, y, this);
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public boolean wasInKo() {
        return wasInKoLastTurn;
    }

    void setWasInKo(boolean koState) {
        wasInKoLastTurn = koState;
    }

    void claim(int x, int y, int claim) {
        if (claim == 1) {
            claims.add(board.getIntersection(x, y));
        } else {
            seki.add(board.getIntersection(x, y));
        }
    }

    void clearClaims() {
        claims.clear();
        seki.clear();;
    }

    Set<Intersection> getClaims() {
        return claims;
    }
    Set<Intersection> getSeki() {
        return seki;
    }


}
