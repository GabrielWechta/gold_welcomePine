package GameMaster;

import Exceptions.KoExeption;
import Exceptions.OutOfBoardsBoundsException;
import Exceptions.StoneAlreadyThereException;
import Exceptions.SuicidalTurnExeption;

public class Board {

    private Intersection[][] intersections;
    private int boardSize;

    public Board(int size) {
        this.boardSize = size;
        this.intersections = new Intersection[boardSize][boardSize];
        initBoard();
    }

    public void initBoard() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                intersections[i][j] = new Intersection(i, j, this);
            }
        }
    }

    /**
     * @return method shows gameBoard where (0,0) is the most South-East point on
     * the board.
     */
//    public void showGameBoardTable() {
//        for (int y = boardSize - 1; y >= 0; y--) {
//            for (int x = 0; x < boardSize; x++) {
//                System.out.print(intersections[x][y].getOwner().getColor() + " ");
//            }
//            System.out.println();
//        }
//    }

    public boolean isIn(int x, int y) {
        if (x >= 0 && x < boardSize && y >= 0 && y < boardSize)
            return true;
        else
            return false;
    }

    public Intersection getIntersection(int x, int y) {
        return intersections[x][y];
    }

    public void playStone(int x, int y, RealPlayer player) throws StoneAlreadyThereException, KoExeption, SuicidalTurnExeption, OutOfBoardsBoundsException {
        if (isIn(x, y)) {
            intersections[x][y].putToken(player);
        } else {
            throw new OutOfBoardsBoundsException();
        }
    }

    public void showBoard() {
        for (int i = boardSize - 1; i >= 0; i--) {
            for (int j = 0; j < boardSize; j++) {
                Player owner = intersections[j][i].getOwner();
                if (owner != null)
                    System.out.print(owner.getColor());
                else
                    System.out.print("0");
            }
            System.out.println();
        }
    }

    //added get
    public int getBoardSize() {
        return boardSize;
    }

    public int[][] getFieldState() {
        int[][] board = new int[boardSize][boardSize];
        for (int i = 0; i < boardSize; i++)
            for (int j = 0; j < boardSize; j++) {
                board[i][j] = intersections[i][j].getOwner().getNumber();
            }
        return board;
    }

}
