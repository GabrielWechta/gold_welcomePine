package Server;

import Exceptions.KoExeption;
import Exceptions.OutOfBoardsBoundsException;
import Exceptions.StoneAlreadyThereException;
import Exceptions.SuicidalTurnExeption;
import GameMaster.Board;
import GameMaster.Intersection;
import GameMaster.Player;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IntersectionTest {

    Board board;
    Player f;
    Player s;


    @BeforeEach
    void setUp() {
        board = new Board(6);

        board.showBoard();

        f = new Player(1, board);
        s = new Player(2, board);


    }


    @Test
    void getX() {
        try {
            f.playStone(1,1);
        } catch (OutOfBoardsBoundsException e) {
            e.printStackTrace();
        } catch (KoExeption koExeption) {
            koExeption.printStackTrace();
        } catch (SuicidalTurnExeption suicidalTurnExeption) {
            suicidalTurnExeption.printStackTrace();
        } catch (StoneAlreadyThereException e) {
            e.printStackTrace();
        }
        assertEquals(board.getIntersection(1,1).getX() , 1);

    }

    @Test
    void getY() {
        try {
            f.playStone(1,1);
        } catch (OutOfBoardsBoundsException e) {
            e.printStackTrace();
        } catch (KoExeption koExeption) {
            koExeption.printStackTrace();
        } catch (SuicidalTurnExeption suicidalTurnExeption) {
            suicidalTurnExeption.printStackTrace();
        } catch (StoneAlreadyThereException e) {
            e.printStackTrace();
        }
        assertEquals(board.getIntersection(1,1).getY() , 1);
    }

    @Test
    void getOwner() {
        try {
            f.playStone(1,1);
            s.playStone(2,2);
            s.playStone(3,3);
            f.playStone(4,4);
        } catch (OutOfBoardsBoundsException e) {
            e.printStackTrace();
        } catch (KoExeption koExeption) {
            koExeption.printStackTrace();
        } catch (SuicidalTurnExeption suicidalTurnExeption) {
            suicidalTurnExeption.printStackTrace();
        } catch (StoneAlreadyThereException e) {
            e.printStackTrace();
        }

        assertEquals(board.getIntersection(1,1).getOwner(),f);
        assertEquals(board.getIntersection(2,2).getOwner(),s);
        assertEquals(board.getIntersection(3,3).getOwner(),s);
        assertEquals(board.getIntersection(4,4).getOwner(),f);
    }

    @Test
    void putToken() {

        try {
            board.getIntersection(1,1).putToken(f);
        } catch (StoneAlreadyThereException e) {
            e.printStackTrace();
        } catch (KoExeption koExeption) {
            koExeption.printStackTrace();
        } catch (SuicidalTurnExeption suicidalTurnExeption) {
            suicidalTurnExeption.printStackTrace();
        }
        assertEquals(board.getIntersection(1,1).getOwner(),f);
    }

    @org.junit.jupiter.api.Test
    void getEmptyNeighbors() {
        try {
            f.playStone(1,1);
            f.playStone(1,2);
            f.playStone(1,3);
        } catch (OutOfBoardsBoundsException e) {
            e.printStackTrace();
        } catch (KoExeption koExeption) {
            koExeption.printStackTrace();
        } catch (SuicidalTurnExeption suicidalTurnExeption) {
            suicidalTurnExeption.printStackTrace();
        } catch (StoneAlreadyThereException e) {
            e.printStackTrace();
        }

        List<Intersection> emptySpaces = board.getIntersection(1,2).getEmptyNeighbors();
        assertTrue(emptySpaces.contains(board.getIntersection(2,2)));
        assertTrue(emptySpaces.contains(board.getIntersection(0,2)));
    }
    @Test
    void getNotEmptyNeighbors() {
        try {
            f.playStone(0,0);
            f.playStone(1,0);
        } catch (OutOfBoardsBoundsException e) {
            e.printStackTrace();
        } catch (KoExeption koExeption) {
            koExeption.printStackTrace();
        } catch (SuicidalTurnExeption suicidalTurnExeption) {
            suicidalTurnExeption.printStackTrace();
        } catch (StoneAlreadyThereException e) {
            e.printStackTrace();
        }


        List<Intersection> emptySpaces = board.getIntersection(0,0).getNotEmptyNeighbors();
        assertTrue(emptySpaces.contains(board.getIntersection(1,0)));

    }
}