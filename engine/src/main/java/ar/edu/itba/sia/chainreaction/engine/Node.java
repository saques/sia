package ar.edu.itba.sia.chainreaction.engine;

import ar.com.itba.sia.Rule;

import java.util.Objects;

public class Node<E> {

    private E current;

    private Node<E> parent;

    private Rule<E> rule;

    private double cost;
    private double heuristicCost;

    private int depth;

    private long millis;

    public Node(E current, Node<E> parent, Rule<E> rule, double cost, double heuristicCost){
        this.current = current;
        this.parent = parent;
        this.rule = rule;
        this.cost = cost;
        this.heuristicCost = heuristicCost;
        this.millis = System.currentTimeMillis();
        this.depth = parent != null ? parent.depth + 1 : 0;
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
        return parent.toString() + "\n--> " + rule + " -->\n\n" + current;
    }

    public double getCost() {
        return cost;
    }
    public double getHeuristicCost() {return heuristicCost;}

    public int getDepth(){
        return depth;
    }

    public long getMillis(){
        return millis;
    }

    public long elapsed(Node<E> n){
        return Math.abs(millis -n.millis);
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