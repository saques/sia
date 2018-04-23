package ar.edu.itba.sia.chainreaction.engine.frontier;

import ar.edu.itba.sia.chainreaction.engine.Node;

import java.util.Deque;
import java.util.LinkedList;

public class StackFrontierStrategy<E> implements FrontierStrategy<E>{

    private Deque<Node<E>> s = new LinkedList<>();

    @Override
    public Node<E> getPrioritary() {
        return s.getFirst();
    }

    @Override
    public void add(Node<E> n) {
        s.addFirst(n);
    }

    @Override
    public Node<E> observePrioritary() {
        return s.peekFirst();
    }

    @Override
    public int size() {
        return s.size();
    }
}
