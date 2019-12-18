package GameMaster;

import java.util.HashSet;
import java.util.Set;

public class StoneChain {

    private Set<Intersection> stones;
    private Set<Intersection> liberties;
    private Player owner;

    public StoneChain(Intersection intersection, Player player) {
        intersection.setStoneChain(this);
        this.stones = new HashSet<Intersection>();
        stones.add(intersection);
        owner = player;
        liberties = new HashSet<Intersection>();
        liberties.addAll(intersection.getEmptyNeighbors());
    }

    public Player getOwner() {
        return owner;
    }

    public void merge(StoneChain chain) {
        if (chain.getOwner() == owner) {
            for (Intersection intersection :
                    chain.getStones()) {
                intersection.setStoneChain(this);
            }
            stones.addAll(chain.getStones());
            liberties.addAll(chain.getLiberties());
        }
    }

    public Set<Intersection> getStones() {
        return stones;
    }

    public Set<Intersection> getLiberties() {
        return liberties;
    }

    public void addLiberti(Intersection intersection) {
        liberties.add(intersection);
    }

    public void removeLiberti(Intersection intersection) {
        liberties.remove(intersection);
    }

    public int getLibertiesNumber() {
        return liberties.size();
    }

    public int getStoneNumber() {
        return stones.size();
    }

    public void tryToKill(RealPlayer killer) {
        if (getLibertiesNumber() == 0) {
            if (getStoneNumber() == 1) {
                killer.setWasInKo(true);
            }
            die();

        }
    }

    private void die() {
        for (Intersection intersection : stones
        ) {
            intersection.die();
        }

    }
}
