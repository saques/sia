package ar.edu.itba.sia.chainreaction.engine;

public class Node<E> {

    private E current, parent;

    private double cost, heuristic;

    public Node(E current, E parent, double cost){
        this.current = current;
        this.parent = parent;
    }

    public E getCurrent() {
        return current;
    }

    public E getParent() {
        return parent;
    }

    public String toString(){
        if(parent == null){
            return current.toString();
        }
        return current + "<-" + parent.toString();
    }

    public double getCost() {
        return cost;
    }

    public double getHeuristic() {
        return heuristic;
    }

    public double estimatedTotalCost(){
        return cost + heuristic;
    }
}