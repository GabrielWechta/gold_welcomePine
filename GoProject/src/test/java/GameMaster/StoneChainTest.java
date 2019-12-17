package GameMaster;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import Exceptions.KoExeption;
import Exceptions.OutOfBoardsBoundsException;
import Exceptions.StoneAlreadyThereException;
import Exceptions.SuicidalTurnExeption;


public class StoneChainTest {
    Board board;
    Player f;
    Player s;

    @Before
    public void setUp() {
        board = new Board(6);

        board.showBoard();

        f = new Player(1, board);
        s = new Player(2, board);


    }


    @Test
    public void getOwner() {
    }

    @Test
    public void killTest() throws SuicidalTurnExeption, KoExeption, OutOfBoardsBoundsException, StoneAlreadyThereException {
        f.playStone(0,0);
        s.playStone(1,0);
        s.playStone(0,1);
        board.showBoard();
        assertTrue(board.getIntersection(0,0).getOwner()==null);



    }
    @Test(expected = KoExeption.class)
    public void koTest() throws SuicidalTurnExeption, KoExeption, OutOfBoardsBoundsException, StoneAlreadyThereException {

        f.playStone(1,0);
        f.playStone(0,1);
        f.playStone(1,2);
        s.playStone(3,1);
        s.playStone(2,0);
        s.playStone(2,2);
        board.showBoard();
        s.playStone(1,1);
        board.showBoard();
        f.playStone(2,1);

        board.showBoard();
        s.playStone(1,1);
        assertTrue(s.wasInKo());
        board.showBoard();
        f.playStone(2,1);
    }

    @Test
    public void testingMerge() {
    	
    }
}