package Client;

import javax.swing.*;

import jdk.internal.org.objectweb.asm.Label;

import java.awt.*;
import java.util.Scanner;

public class GuiFacade {
	private char color;
	private int boardSize;
	private int[][] board;
	private ClientServerBridge bridge;
	private GoPanel goPanel;

	public GuiFacade(char color, ClientServerBridge bridge) {
		this.color = color;
		this.bridge = bridge;
		System.out.println(
				"Welcome to go game by Ivan Feofilaktov and Gabriel Wechta. Pleasy type how much lines you want: ");
		Scanner in = new Scanner(System.in);
		boardSize = in.nextInt();
		System.out.println("You are starting: " + boardSize + "x" + boardSize + " go game");
		System.out.println("Goodluck");
		board = new int[boardSize][boardSize];
		bridge.initializeGame(boardSize);
	}

	public GuiFacade(char color, int boardSize, ClientServerBridge bridge) {
		this.boardSize = boardSize;
		this.bridge = bridge;
		this.color = color;
		System.out.println("Welcome to go game by Ivan Feofilaktov and Gabriel Wechta.");
		System.out.println("You are starting: " + boardSize + "x" + boardSize + " go game");
		System.out.println("Goodluck");
		board = new int[boardSize][boardSize];
	}
	
	public GuiFacade() {
		
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
		goPanel = new GoPanel(this);
		gameFrame.add(goPanel, BorderLayout.CENTER);
		gameFrame.setSize(600, 625);
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.setResizable(false);
		gameFrame.setVisible(true);
	}

	public int[][] getCodedBoard() {
		return board;
	}

	public void sendTurn(int row, int col) {
		bridge.sendTurn(row, col);
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

	public void updateField(int x, int y, int owner) {
		board[x][y] = owner;
	}

	public void updateGui() {
		goPanel.refreshBoard();
	}

	public void displayPass() {
		JFrame passFrame = new JFrame();
		passFrame.setLayout(new BorderLayout());

		JPanel passPanel = new JPanel();
		JLabel passLabel = new JLabel();
		passLabel.setText(
				"Your oponend has proposed pass. If you agree to end game. Please press 'pass' on Operating Frame.");
		passPanel.add(passLabel);

		passFrame.add(passPanel, BorderLayout.CENTER);
		passFrame.setSize(1000, 200);
		passFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		passFrame.setVisible(true);
	}

	public void displayQuit() {
		JFrame quitFrame = new JFrame();
		quitFrame.setLayout(new BorderLayout());

		JPanel quitPanel = new JPanel();
		JLabel quitLabel = new JLabel();
		quitLabel.setText(
				"Your oponend has quit. Well done! Most likely he is now crying in the corner.");
		quitPanel.add(quitLabel);

		quitFrame.add(quitPanel, BorderLayout.CENTER);
		quitFrame.setSize(600, 200);
		quitFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		quitFrame.setVisible(true);
	}

	public void claimTerritory() {
		// todo
	}

	void sendClaims(int[][] isClaimed) {
		// todo
		// change and use this to send claims to server 0 -not claimed; 1 - claimed; 2 -
		// claimed to be neutral;
		bridge.sendClaims(isClaimed);
	}

	public void contGame() {
		// todo
	}

	public void displayEndGame(boolean b) {
		JFrame endGameFrame = new JFrame();
		endGameFrame.setLayout(new BorderLayout());

		JPanel endGamePanel = new JPanel();
		JLabel endGameLabel = new JLabel();
		if(b == true) {
			endGameLabel.setText(
				"YOU WON!");
		}
		else {
			endGameLabel.setText("YOU DIED (LOST)");
		}
		endGamePanel.add(endGameLabel);

		endGameFrame.add(endGamePanel, BorderLayout.CENTER);
		endGameFrame.setSize(600, 200);
		endGameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		endGameFrame.setVisible(true);
	}

	public void displayEndGame(char color) {
		displayEndGame(this.color == color);
	}
}
