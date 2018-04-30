package ar.edu.itba.sia.chainreaction.engine;

import ar.com.itba.sia.Heuristic;
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

    private Engine(Frontier<E> frontier, Heuristic<E> heuristic) throws InstantiationException, IllegalAccessException{
        this.frontier = frontier;
    }

    public Node<E> solution(Node<E> start, Problem<E> problem, Heuristic<E> heuristic){
        frontier.add(start);
        int nodeVisitedCount = 0;
        while (!frontier.isEmpty()){
            Node<E> node = frontier.getPrioritary();
            System.out.println(nodeVisitedCount++);
            if(problem.isResolved(node.getCurrent()))
                return node;

            problem.getRules(node.getCurrent()).stream()
                    .map(x-> {
                        E state = x.applyToState(node.getCurrent());
                        return new Node<>(state, node, x,x.getCost()+node.getCost(), heuristic.getValue(state));
                    })
                    .forEach(frontier::add);

        }

        return null;

    }
    public Node<E> solution(Node<E> start, Problem<E> problem) {
        return solution(start, problem, (x) -> 0);
    }
}
