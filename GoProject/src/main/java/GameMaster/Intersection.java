package GameMaster;

import Exceptions.KoExeption;
import Exceptions.StoneAlreadyThereException;
import Exceptions.SuicidalTurnExeption;

import java.util.ArrayList;
import java.util.List;

public class Intersection {

    private final int x, y;
    private StoneChain stoneChain;
    private Player owner = EmptyPlayer.getPlayer();
    private Board board;

    public void putToken(RealPlayer player) throws StoneAlreadyThereException, KoExeption, SuicidalTurnExeption {
        if (!owner.isEmpty())
            throw new StoneAlreadyThereException();
        else if (isSuicidal(player)) {
            throw new SuicidalTurnExeption();
        } else {
            ((RealPlayer) player).setWasInKo(false);
            List<Intersection> neighbors = getNotEmptyNeighbors();
            setOwner(player);
            new StoneChain(this, player);
            if (!neighbors.isEmpty()) {

                for (Intersection intersection : neighbors
                ) {
                    intersection.removeLiberti(this);
                    if (!intersection.getOwner().isEqual(player)) {
                        intersection.tryToKill(player);
                    } else {
                        this.connect(intersection);
                    }
                }
                for (Intersection emptyIntersection : getEmptyNeighbors()
                ) {
                    addLiberti(emptyIntersection);
                }
            }
            if (getChainLibertiesCount() != 1) {
                player.setWasInKo(false);
            }
        }
    }

    private void connect(Intersection intersection) {
        stoneChain.merge(intersection.getStoneChain());
    }

    private void removeLiberti(Intersection intersection) {
        stoneChain.removeLiberti(intersection);
    }

    private void addLiberti(Intersection intersection) {
        stoneChain.addLiberti(intersection);
    }

    private void tryToKill(RealPlayer killer) {
        stoneChain.tryToKill(killer);
    }

    public Intersection(int x, int y, Board board) {
        this.x = x;
        this.y = y;
        this.board = board;
    }

    public StoneChain getStoneChain() {
        return stoneChain;
    }

    public void setStoneChain(StoneChain stoneChain) {
        this.stoneChain = stoneChain;
    }

    public Player getOwner() {
        return owner;
    }


    public void setOwner(Player player) {
        owner = player;
    }

    public boolean isEmpty() {
        return owner.isEmpty();
    }

    public List<Intersection> getEmptyNeighbors() {
        List<Intersection> emptyNeighbors = new ArrayList<Intersection>();

        int xTable[] = {-1, 0, 1, 0};
        int yTable[] = {0, -1, 0, 1};
        int newX;
        int newY;
        for (int i = 0; i < xTable.length; i++) {
            newX = this.x + xTable[i];
            newY = this.y + yTable[i];

            if (board.isIn(newX, newY)) {
                Intersection sharedWallIntersection = board.getIntersection(newX, newY);

                if (sharedWallIntersection.isEmpty()) {

                    emptyNeighbors.add(sharedWallIntersection);

                }
            }
        }
        return emptyNeighbors;
    }

    public List<Intersection> getNotEmptyNeighbors() {
        List<Intersection> notEmptyNeighbors = new ArrayList<Intersection>();

        int xTable[] = {-1, 0, 1, 0};
        int yTable[] = {0, -1, 0, 1};
        int newX;
        int newY;
        for (int i = 0; i < xTable.length; i++) {
            newX = this.x + xTable[i];
            newY = this.y + yTable[i];

            if (board.isIn(newX, newY)) {
                Intersection sharedWallIntersection = board.getIntersection(newX, newY);

                if (!sharedWallIntersection.isEmpty()) {

                    notEmptyNeighbors.add(sharedWallIntersection);

                }
            }
        }
        return notEmptyNeighbors;
    }

    int getChainLibertiesCount() {
        return stoneChain.getLibertiesNumber();
    }

    boolean isSuicidal(RealPlayer  player) throws KoExeption {
        boolean isKilling = false;
        boolean isSuicidal = getEmptyNeighbors().isEmpty();
        for (Intersection neighbor : getNotEmptyNeighbors()
        ) {
            if (neighbor.getChainLibertiesCount() == 1) {
                if (neighbor.getOwner().isEqual(player)) {
                } else {
                    isKilling = true;
                    if (neighbor.getChainStonesCount() == 1) {
                        if (player.wasInKo()) {
                            throw new KoExeption();
                        }
                    }

                }
            } else if (neighbor.getOwner().isEqual(player)) {
                isSuicidal = false;

            }

        }

        return (!isKilling) && (isSuicidal);
    }

    int getChainStonesCount() {
        return stoneChain.getStoneNumber();
    }

    public void die() {
        ((RealPlayer)owner).getOponent().addScore(1);
        setOwner(EmptyPlayer.getPlayer());
        for (Intersection intersection : getNotEmptyNeighbors()
        ) {
            intersection.addLiberti(this);
        }
        setStoneChain(null);
    }
}
