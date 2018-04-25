package ar.edu.itba.sia.chainreaction.engine.frontier;

import ar.edu.itba.sia.chainreaction.engine.Node;

import java.util.NoSuchElementException;

public interface Frontier<E> {

    Node<E> getPrioritary();

    void add(Node<E> n);

    Node<E> observePrioritary();

    int size();

    default boolean isEmpty(){
        return size() == 0;
    }

}
