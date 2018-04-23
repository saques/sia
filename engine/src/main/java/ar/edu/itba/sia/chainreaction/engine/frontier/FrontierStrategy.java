package ar.edu.itba.sia.chainreaction.engine.frontier;

import ar.edu.itba.sia.chainreaction.engine.Node;

import java.util.NoSuchElementException;

public interface FrontierStrategy<E> {

    Node<E> getPrioritary();

    void add(Node<E> n);

    Node<E> observePrioritary();

    int size();

}
