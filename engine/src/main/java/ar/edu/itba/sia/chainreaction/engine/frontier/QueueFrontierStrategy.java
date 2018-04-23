package ar.edu.itba.sia.chainreaction.engine.frontier;

import ar.edu.itba.sia.chainreaction.engine.Node;

import java.util.Deque;
import java.util.LinkedList;

public class QueueFrontierStrategy<E> implements FrontierStrategy<E> {

    private Deque<Node<E>> q = new LinkedList<>();

    @Override
    public Node<E> getPrioritary() {
        return q.getLast();
    }

    @Override
    public void add(Node<E> n) {
        q.addFirst(n);
    }

    @Override
    public Node<E> observePrioritary() {
        return q.peekLast();
    }

    @Override
    public int size() {
        return q.size();
    }
}
