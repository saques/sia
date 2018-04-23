package ar.edu.itba.sia.chainreaction.engine.frontier;

import ar.edu.itba.sia.chainreaction.engine.Node;

import java.util.Comparator;
import java.util.PriorityQueue;

public class PQFrontierStrategy<E> implements FrontierStrategy<E> {



    private PriorityQueue<Node<E>> pq;


    public PQFrontierStrategy(Comparator<Node<E>> comparator){
        pq = new PriorityQueue<>(comparator);
    }


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