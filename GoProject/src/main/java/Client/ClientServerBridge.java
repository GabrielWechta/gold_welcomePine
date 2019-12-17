package Client;

public class ClientServerBridge {
    private ClientConnector connection;
    private GuiFacade gui;

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
        connection.send("t" + row + "t" + col);
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
        //todo
        //add quit handler to window
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
            case 't':
                String[] args = command.split("t");
                String[] subargs;
                for (int i = 1; i < args.length; i++) {
                    subargs = args[i].split(":");
                    gui.updateField(Integer.parseInt(subargs[0]), Integer.parseInt(subargs[1]), Integer.parseInt(subargs[2]));
                }
                gui.updateGui();
                break;
            case 'p':
                gui.displayPass();
                break;
            case 'q':
                gui.displayQuit();
                break;
            case 'g':
                gui.displayEndGame(true);
                break;
            case 'e':
                gui.claimTerritory();
                break;
            case 'r':
                if (command.charAt(1) == 0) {
                    gui.contGame();
                } else {
                   gui.displayEndGame(command.charAt(2));
                }

                break;

        }
    }

    public void initializeGame(int boardSize) {
        connection.send("i" + boardSize);
    }

    public void sendClaims(int[][] isClaimed) {

        int size = isClaimed.length;
        StringBuilder com = new StringBuilder();
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++) {
                com.append('c');
                com.append(i);
                com.append(':');
                com.append(j);
                com.append(':');
                com.append(isClaimed[i][j]);
            }
        String command = com.toString();
        connection.send(command);

    }
}
