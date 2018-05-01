package ar.edu.itba.sia.chainreaction.engine.frontier;

import ar.edu.itba.sia.chainreaction.engine.Node;

import java.util.Deque;
import java.util.LinkedList;

public class QueueFrontier<E> implements Frontier<E> {

    private Deque<Node<E>> q = new LinkedList<>();

    @Override
    public Node<E> getPrioritary() {
        return q.removeLast();
    }

    @Override
    public void add(Node<E> n) {
        q.addFirst(n);
    }

    @Override
    public Node<E> observePrioritary() {
        return q.getLast();
    }

    @Override
    public int size() {
        return q.size();
    }

    @Override
    public void clear() {
        q.clear();
    }
}
