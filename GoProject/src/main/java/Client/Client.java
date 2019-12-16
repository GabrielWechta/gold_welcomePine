package recalling.recallingSwing;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

import javax.swing.*;

public class Client {
	public static void main(String[] args) {

		int gameBoardSize;
		System.out.println("Welcome to go game by Ivan Feofilaktov and Gabriel Wechta. Pleasy type how much lines you want: ");
		Scanner in = new Scanner(System.in);
		gameBoardSize = in.nextInt();
		System.out.println("You are starting: " + gameBoardSize + "x" + gameBoardSize + " go game");
		System.out.println("Goodluck");

		ClientServerBridge clientServerBridge = new ClientServerBridge(gameBoardSize);

		JFrame operatingFrame = new JFrame("Operating Frame");
		operatingFrame.add(new OperatingPanel(clientServerBridge));
		operatingFrame.setSize(300, 425);
		operatingFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		operatingFrame.setLocationRelativeTo(null);
		operatingFrame.setVisible(true);

		JFrame gameFrame = new JFrame();
		gameFrame.setLayout(new BorderLayout());
		gameFrame.add(new GoPanel(gameBoardSize, clientServerBridge), BorderLayout.CENTER);
		gameFrame.setSize(600, 625);
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.setResizable(false);
		gameFrame.setVisible(true);
	}
}