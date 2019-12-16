package Client;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class GuiFacade {
    char color;
    int boardSize;
    int[][] board;
    private ClientServerBridge bridge;

    public GuiFacade(char color,ClientServerBridge bridge) {
        this.color = color;
        this.bridge = bridge;
        System.out.println("Welcome to go game by Ivan Feofilaktov and Gabriel Wechta. Pleasy type how much lines you want: ");
        Scanner in = new Scanner(System.in);
        boardSize = in.nextInt();
        System.out.println("You are starting: " + boardSize + "x" + boardSize + " go game");
        System.out.println("Goodluck");
        board = new int[boardSize][boardSize];
        bridge.initializeGame(boardSize);
    }

    public GuiFacade(char color, int boardSize,ClientServerBridge bridge) {
        this.boardSize = boardSize;
        this.color = color;
        System.out.println("Welcome to go game by Ivan Feofilaktov and Gabriel Wechta.");
        System.out.println("You are starting: " + boardSize + "x" + boardSize + " go game");
        System.out.println("Goodluck");
        board = new int[boardSize][boardSize];
    }

    void initialize() {
        JFrame operatingFrame = new JFrame("Operating Frame");
        operatingFrame.add(new OperatingPanel(this));
        operatingFrame.setSize(300, 425);
        operatingFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        operatingFrame.setLocationRelativeTo(null);
        operatingFrame.setVisible(true);

        JFrame gameFrame = new JFrame();
        gameFrame.setLayout(new BorderLayout());
        gameFrame.add(new GoPanel(this), BorderLayout.CENTER);
        gameFrame.setSize(600, 625);
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setResizable(false);
        gameFrame.setVisible(true);
    }




    public int[][] getCodedBoard() {
        return board;
    }

    public void sendTurn(int row, int col) {
        bridge.sendTurn(row,col);
    }

    public char getPlayerColor() {
        return color;
    }

    public void pass() {
        bridge.pass();
    }

    public void giveUp() {
        bridge.giveUp();
    }

    public int getBoardSize() {
        return boardSize;
    }
}

