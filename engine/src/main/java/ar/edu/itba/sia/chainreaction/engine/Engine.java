package ar.edu.itba.sia.chainreaction.engine;

import ar.com.itba.sia.Problem;
import ar.com.itba.sia.Rule;
import ar.edu.itba.sia.chainreaction.engine.frontier.Frontier;
import ar.edu.itba.sia.chainreaction.engine.frontier.NonEmptyFrontierException;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class Engine<E> {

    public static <E> Engine<E> build(Frontier<E> frontier) throws InstantiationException, IllegalAccessException {
        if(!frontier.isEmpty())
            throw new NonEmptyFrontierException();
        return new Engine<>(frontier);
    }

    private Frontier<E> frontier;



    private Engine(Frontier<E> frontier) throws InstantiationException, IllegalAccessException{
        this.frontier = frontier;
    }

    public Node<E> solution(Node<E> start, Problem<E> problem){
        frontier.add(start);

        while (!frontier.isEmpty()){
            Node<E> node = frontier.getPrioritary();

            if(problem.isResolved(node.getCurrent()))
                return node;

            problem.getRules(node.getCurrent()).stream()
                    .map(x->new Node<>(x.applyToState(node.getCurrent()), node, x, x.getCost()+node.getCost()))
                    .forEach(frontier::add);

        }

        return null;

    }


}
