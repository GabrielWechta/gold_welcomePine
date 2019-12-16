package recalling.recallingSwing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GoPanel extends JPanel {

	Square[][] board;
	boolean whiteToMove;
	int dimension;
	ClientServerBridge clientServerBridge;

	GoPanel(int dimension, ClientServerBridge clientServerBridge) {
		board = new Square[dimension][dimension];
		whiteToMove = false;
		this.dimension = dimension;
		this.clientServerBridge = clientServerBridge;
		initBoard(dimension);
	}

	private void initBoard(int dimension) {
		super.setLayout(new GridLayout(dimension, dimension));
		for (int row = 0; row < dimension; row++) {
			for (int col = 0; col < dimension; col++) {
				board[row][col] = new Square(row, col);
				super.add(board[row][col]);
			}
		}
		repaint();
	}

	private void refreshBoard() {
		int[][] codedBoard = clientServerBridge.getCodedBoard();
		for (int row = 0; row < dimension; row++) {
			for (int col = 0; col < dimension; col++) {
				switch (codedBoard[row][col]) {
					case 0: {
						this.board[row][col].stone = Stone.NONE;
					}
						break;
					case 1: {
						this.board[row][col].stone = Stone.BLACK;
					}
						break;
					case 2: {
						this.board[row][col].stone = Stone.WHITE;
					}
						break;
					default: {
						System.out.println("error in case");
					}
						break;
				}
			}
		}
		repaint();
	}

	private class Square extends JPanel {

		Stone stone;
		final int row;
		final int col;

		Square(int r, int c) {
			stone = Stone.NONE;
			row = r;
			col = c;
			super.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent me) {
					if (stone != Stone.NONE)
						return;
					clientServerBridge.sendCoordinates(row, col);
					if (clientServerBridge.receive() == true) {
						refreshBoard();
					}
					stone = whiteToMove ? Stone.WHITE : Stone.BLACK;
					whiteToMove = !whiteToMove;
					repaint();
				}
			});
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			int w = super.getWidth();
			int h = super.getHeight();
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect(0, 0, w, h);
			g.setColor(Color.BLACK);
			if (row == 0 || row == board.length - 1 || col == 0 || col == board.length - 1) {
				if (col == 0) {
					g.drawLine(w / 2, h / 2, w, h / 2);
					if (row == 0)
						g.drawLine(w / 2, h / 2, w / 2, h);
					else if (row == board.length - 1)
						g.drawLine(w / 2, h / 2, w / 2, 0);
					else
						g.drawLine(w / 2, 0, w / 2, h);
				} else if (col == board.length - 1) {
					g.drawLine(0, h / 2, w / 2, h / 2);
					if (row == 0)
						g.drawLine(w / 2, h / 2, w / 2, h);
					else if (row == board.length - 1)
						g.drawLine(w / 2, h / 2, w / 2, 0);
					else
						g.drawLine(w / 2, 0, w / 2, h);
				} else if (row == 0) {
					g.drawLine(0, h / 2, w, h / 2);
					g.drawLine(w / 2, h / 2, w / 2, h);
				} else {
					g.drawLine(0, h / 2, w, h / 2);
					g.drawLine(w / 2, h / 2, w / 2, 0);
				}
			} else {
				g.drawLine(0, h / 2, w, h / 2);
				g.drawLine(w / 2, 0, w / 2, h);
			}
			stone.paint(g, w);
		}
	}
}
