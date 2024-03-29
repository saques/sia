package ar.edu.itba.sia.chainreaction.engine.frontier;

import ar.edu.itba.sia.chainreaction.engine.Node;
import java.util.Comparator;
import java.util.PriorityQueue;

public class PQFrontier<E> implements Frontier<E> {



    public static <E> PQFrontier<E> aStarFrontier(int initial){
        PQFrontier<E> ans = new PQFrontier<>();
        ans.pq = new PriorityQueue<>(initial, costAndHeuristicComparator());
        return ans;
    }

    public static <E> PQFrontier<E> dijkstraFrontier(int initial){
        PQFrontier<E> ans = new PQFrontier<>();
        ans.pq = new PriorityQueue<>(initial, costComparator());
        return ans;
    }

    public static <E> PQFrontier<E> greedyFrontier(int initial) {
        PQFrontier<E> ans = new PQFrontier<>();
        ans.pq = new PriorityQueue<>(initial, heuristicComparator());
        return ans;
    }

    private static <E> Comparator<Node<E>> heuristicComparator(){
        return Comparator.comparingDouble(Node::getHeuristicCost);
    }

    private static <E> Comparator<Node<E>> costAndHeuristicComparator(){
        return Comparator.comparingDouble(x -> x.getCost() + x.getHeuristicCost());
    }

    private static <E> Comparator<Node<E>> costComparator(){
        return Comparator.comparingDouble(Node::getCost);
    }

    private PriorityQueue<Node<E>> pq;

    private PQFrontier(){}


    @Override
    public Node<E> getPrioritary() {
        return pq.poll();
    }

    @Override
    public void add(Node<E> n) {
        if (n.getHeuristicCost() != Double.POSITIVE_INFINITY) {
            pq.add(n);
        }

    }

    @Override
    public Node<E> observePrioritary() {
        return pq.peek();
    }

    @Override
    public int size() {
        return pq.size();
    }

    @Override
    public void clear() {
        pq.clear();
    }
}
