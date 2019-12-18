package GameMaster;

public interface Player {
    char getColor();
    static Player getPlayer(char color)
    { 
        
        Player player = EmptyPlayer.getPlayer();
        switch (color)
        {
            case 'b':
                player = PlayerB.getPlayer();
                break;
            case 'w':
                player= PlayerW.getPlayer();
                break;
            case 'n':
                player= FictionPlayer.getPlayer();
                break;
        }
        return player;
    }

    boolean isEmpty();

    void setGame(Game game);

    boolean isEqual(Player player);

    int getNumber();


}
