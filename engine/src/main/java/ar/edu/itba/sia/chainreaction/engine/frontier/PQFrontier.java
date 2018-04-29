package ar.edu.itba.sia.chainreaction.engine.frontier;

import ar.com.itba.sia.Heuristic;
import ar.edu.itba.sia.chainreaction.engine.Node;

import java.util.Comparator;
import java.util.PriorityQueue;

public class PQFrontier<E> implements Frontier<E> {



    public static <E> PQFrontier<E> aStarFrontier(int initial, Heuristic<E> h){
        PQFrontier<E> ans = new PQFrontier<>();
        ans.pq = new PriorityQueue<>(initial, heuristicComparator(h));
        return ans;
    }

    public static <E> PQFrontier<E> dijkstraFrontier(int initial){
        PQFrontier<E> ans = new PQFrontier<>();
        ans.pq = new PriorityQueue<>(initial, costComparator());
        return ans;
    }

    private static <E> Comparator<Node<E>> heuristicComparator(Heuristic<E> h){
        return (x, y) -> Double.compare(h.getValue(x.getCurrent()), h.getValue(y.getCurrent()));
    }

    private static <E> Comparator<Node<E>> costComparator(){
        return (x, y) -> Double.compare(x.getCost(), y.getCost());
    }

    private PriorityQueue<Node<E>> pq;

    private PQFrontier(){}


    @Override
    public Node<E> getPrioritary() {
        return pq.poll();
    }

    @Override
    public void add(Node<E> n) {
        pq.add(n);
    }

    @Override
    public Node<E> observePrioritary() {
        return pq.peek();
    }

    @Override
    public int size() {
        return pq.size();
    }
}
