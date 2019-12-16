package Client;

public class ClientServerBridge {
	
	int[][] codedBoard;
	int playerColor;

	public ClientServerBridge(int dimension) {
		this.codedBoard = new int[dimension][dimension];
		sendGameBoardSize(dimension);
		this.playerColor = receivePlayerColor();
	}

	private int receivePlayerColor() {
		// this method is getting color send by Server
		int colorFromServer = 1; //mocking example
		return colorFromServer;
	}

	private void sendGameBoardSize(int dimension) {
		//send dimension to server
		//ClientServerBridge gets size from user input when client is open
		//then it sends it to server, maybe second Client's choice is simply irrelevant? 
	}

	public void sendCoordinates(int row, int col) {
		System.out.println(row + " : " + col);;
	}
	
	/** @category method for receiving every kind of message from server*/
	public boolean receive() {
		//codedBoard = received from server
		//example
		codedBoard[2][3] = 1;
		codedBoard[2][4] = 2;
		System.out.println("in receive");
		
		//here we will add games end handler
		//new window will pop up saying that games end is proposed and it will display proposed score, it will have buttons for agree and disagree
		return true;
	}

	public int[][] getCodedBoard() {
		return codedBoard;
	}

	public void pass() {
		// sending 'pass' command to server
		// it has to be handled by class Mediator
		System.out.println("passing");
		
	}

	public void giveUp() {
		// sending 'giveUp' command to server
		// it has to be handled by class Mediator
		System.out.println("giving up");
		
	}

	public int getPlayerColor() {
		return playerColor;
	}
	

}
