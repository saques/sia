package ar.edu.itba.sia.chainreaction.engine;

import ar.com.itba.sia.Heuristic;
import ar.com.itba.sia.Problem;
import ar.com.itba.sia.Rule;
import ar.edu.itba.sia.chainreaction.engine.frontier.Frontier;
import ar.edu.itba.sia.chainreaction.engine.frontier.NonEmptyFrontierException;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class Engine<E> {
    public static <E> Engine<E> build(Frontier<E> frontier) throws InstantiationException, IllegalAccessException {
        if(!frontier.isEmpty())
            throw new NonEmptyFrontierException();
        return new Engine<>(frontier);
    }

    private Frontier<E> frontier;
    private long expandedNodes, visitedNodes;
    private Engine(Frontier<E> frontier) throws InstantiationException, IllegalAccessException{
        this.frontier = frontier;
        this.expandedNodes = 0;
        this.visitedNodes = 0;
    }

    public Node<E> solution(Node<E> start, Problem<E> problem, Heuristic<E> heuristic, int depth){

        frontier.add(start);

        while (!frontier.isEmpty()){
            Node<E> node = frontier.getPrioritary();
            visitedNodes++;
            if(problem.isResolved(node.getCurrent()))
                return node;

            List<Rule<E>> rules = problem.getRules(node.getCurrent());
            expandedNodes += rules.size();
            rules.stream()
                    .map(x-> {
                        E state = x.applyToState(node.getCurrent());
                        return new Node<>(state, node, x,x.getCost()+node.getCost(), heuristic.getValue(state));
                    })
                    .filter(x -> x.getDepth() <= depth)
                    .forEach(frontier::add);

        }
        return null;
    }

    public Node<E> solution(Node<E> start, Problem<E> problem, Heuristic<E> heuristic){
        return solution(start, problem, heuristic, Integer.MAX_VALUE);
    }

    public Node<E> solution(Node<E> start, Problem<E> problem) {
        return solution(start, problem, (x) -> 0, Integer.MAX_VALUE);
    }

    public Node<E> solution(Node<E> start, Problem<E> problem, int depth) {
        return solution(start, problem, (x) -> 0, depth);
    }

    public Node<E> iddfs(Node<E> start, Problem<E> problem, int maxDepth){
        frontier.clear();
        Node<E> ans = null;
        for(int i=1; i < maxDepth; i++){
            ans = solution(start,problem,i);
            if(ans != null)
                break;
            frontier.clear();
        }
        return ans;
    }

    public long getExpandedNodes() {
        return expandedNodes;
    }

    public long getVisitedNodes() {
        return visitedNodes;
    }

    public int getFrontierNodes() {
        return frontier.size();
    }
}
