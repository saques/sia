package ar.edu.itba.sia.chainreaction.engine;

import java.util.Objects;

public class Node<E> {

    private E current;

    private Node<E> parent;

    private double cost;
    private double heuristicCost;

    private long milis;

    public Node(E current, Node<E> parent, double cost, double heuristicCost){
        this.current = current;
        this.parent = parent;
        this.cost = cost;
        this.heuristicCost = heuristicCost;
        this.milis = System.currentTimeMillis();
    }

    public E getCurrent() {
        return current;
    }

    public Node<E> getParent() {
        return parent;
    }

    public String toString(){
        if(parent == null){
            return current.toString();
        }
        return current + "\n" + parent.toString();
    }

    public double getCost() {
        return cost;
    }
    public double getHeuristicCost() {return heuristicCost + cost;}

    public long getMilis(){
        return milis;
    }

    public long elapsed(Node<E> n){
        return Math.abs(milis-n.milis);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node<?> node = (Node<?>) o;
        return Double.compare(node.cost, cost) == 0 &&
                Objects.equals(current, node.current) &&
                Objects.equals(parent, node.parent);
    }

    @Override
    public int hashCode() {

        return Objects.hash(current, parent, cost);
    }

}