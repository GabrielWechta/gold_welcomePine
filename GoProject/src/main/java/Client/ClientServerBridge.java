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

    private void initialize() {
        gui = new GuiFacade(this);
        gui.initialize();
    }

    public void sendTurn(int row, int col) {
        connection.send("t" + row + "t" + col);
    }


    public void pass() {
        connection.send("p");

    }

    public void giveUp() {
        connection.send("g");

    }

    public void quit() {
        connection.send("q");
    }


    public void execute(String command) {
        String[] args;
        String[] subargs;
        switch (command.charAt(0)) {
            case 'i':
                if (command.equals("i")) {
                    initialize();
                } else {
                    args = command.split(":");
                    initialize(args[1].charAt(0), Integer.parseInt(args[2]));
                }
                break;
            case 't':
                args = command.split("t");
                for (
                        int i = 1;
                        i < args.length; i++) {
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
                gui.displayEndGame(command.charAt(1));
                break;
            case 'r':
                gui.displayEndGame(command.charAt(2));
                break;

        }

    }

    public void initializeGame(int boardSize, char color, int bots) {
        connection.send("i" + boardSize + ":" + color + ":" + bots);
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
