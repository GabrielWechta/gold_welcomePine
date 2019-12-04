package Server;

import java.util.HashSet;
import java.util.Set;

public class StoneChain {

    private Set<Intersection> stones;
    private Set<Intersection> liberties;
    private Player owner;


    public StoneChain(Set<Intersection> stones, Set<Intersection> liberties, Player owner) {
        this.stones = stones;
        this.liberties = liberties;
        this.owner = owner;
    }

    public StoneChain(Intersection intersection, Player owner) {
        intersection.setStoneChain(this);
        this.stones = new HashSet<Intersection>();
        stones.add(intersection);
        this.owner = owner;
        liberties = new HashSet<Intersection>();
        liberties.addAll(intersection.getEmptyNeighbors());
    }

    public Player getOwner() {
        return owner;
    }

    public void merge(StoneChain chain) {
        if (chain.getOwner() == this.owner) {
            for (Intersection intersection :
                    chain.getStones()) {
                intersection.setStoneChain(this);
            }
            this.stones.addAll(chain.getStones());
            this.liberties.addAll(chain.getLiberties());
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

    public void tryToKill(Player owner) {
        if (getLibertiesNumber() == 0) {
            if(getStoneNumber()==1)
            {
                owner.setWasInKo(true);
            }
            die();

        }
    }

    private void die() {
        for (Intersection intersection:stones
             ) {
            stones.remove(intersection);
            intersection.die();
        }

    }
}
