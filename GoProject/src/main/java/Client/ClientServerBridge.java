package Client;

public class ClientServerBridge {
    ClientConnector connection;
    GuiFacade gui;

    ClientServerBridge(ClientConnector connection) {
        this.connection = connection;
        connection.setBridge(this);
        connection.initializeConnection();
    }

    private void initialize(char color, int boardSize) {
        gui = new GuiFacade(color, boardSize, this);
        gui.initialize();
    }

    private void initialize(char color) {
        gui = new GuiFacade(color, this);
        gui.initialize();
    }

    public void sendTurn(int row, int col) {
        connection.send("t"+row+"t"+col);
    }


    public void pass() {
        // sending 'pass' command to server
        // it has to be handled by class Mediator
        connection.send("p");

    }

    public void giveUp() {
        // sending 'giveUp' command to server
        // it has to be handled by class Mediator
        connection.send("g");

    }

    public void quit() {
        // sending 'giveUp' command to server
        // it has to be handled by class Mediator
        connection.send("q");

    }


    public void execute(String command) {
        switch (command.charAt(0)) {
            case 'i':
                char color = command.charAt(1);


                if (color == 'w') {
                    initialize(color, Integer.parseInt(command.substring(2)));
                } else {
                    initialize(color);
                }


                break;


        }
    }

    public void initializeGame(int boardSize) {
        connection.send("i" + boardSize);
    }
}
