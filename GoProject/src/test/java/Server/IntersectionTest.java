package Server;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IntersectionTest {

    Board board;
    Player f;
    Player s;


    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        board = new Board(6);

        board.showBoard();

        f = new Player(1, board);
        s = new Player(2, board);


    }

    @org.junit.jupiter.api.Test
    void getStoneChain() {
    }

    @org.junit.jupiter.api.Test
    void setStoneChain() {
    }

    @org.junit.jupiter.api.Test
    void getX() {
        f.playStone(1,1);
        assertEquals(board.getIntersection(1,1).getX() , 1);

    }

    @org.junit.jupiter.api.Test
    void getY() {
        f.playStone(1,1);
        assertEquals(board.getIntersection(1,1).getY() , 1);
    }

    @org.junit.jupiter.api.Test
    void getOwner() {
        f.playStone(1,1);
        s.playStone(2,2);
        s.playStone(3,3);
        f.playStone(4,4);
        assertEquals(board.getIntersection(1,1).getOwner(),f);
        assertEquals(board.getIntersection(2,2).getOwner(),s);
        assertEquals(board.getIntersection(3,3).getOwner(),s);
        assertEquals(board.getIntersection(4,4).getOwner(),f);
    }

    @org.junit.jupiter.api.Test
    void putToken() {

        board.getIntersection(1,1).putToken(f);
        assertEquals(board.getIntersection(1,1).getOwner(),f);
    }

    @org.junit.jupiter.api.Test
    void getEmptyNeighbors() {
        f.playStone(1,1);
        f.playStone(1,2);
        f.playStone(1,3);
        List<Intersection> emptySpaces = board.getIntersection(1,2).getEmptyNeighbors();
        assertTrue(emptySpaces.contains(board.getIntersection(2,2)));
        assertTrue(emptySpaces.contains(board.getIntersection(0,2)));
    }
    @org.junit.jupiter.api.Test
    void getNotEmptyNeighbors() {
        f.playStone(0,0);
        f.playStone(1,0);

        List<Intersection> emptySpaces = board.getIntersection(0,0).getNotEmptyNeighbors();
        assertTrue(emptySpaces.contains(board.getIntersection(1,0)));

    }
}