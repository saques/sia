package ar.edu.itba.sia.chainreaction;

import ar.com.itba.sia.Heuristic;
import ar.com.itba.sia.Problem;
import ar.edu.itba.sia.chainreaction.engine.Engine;
import ar.edu.itba.sia.chainreaction.engine.Node;
import ar.edu.itba.sia.chainreaction.engine.frontier.PQFrontier;
import ar.edu.itba.sia.chainreaction.engine.frontier.QueueFrontier;
import ar.edu.itba.sia.chainreaction.problem.*;
import ar.edu.itba.sia.chainreaction.engine.frontier.Frontier;

import java.io.File;

public class App
{
    public static void main( String[] args ) throws Exception{
        File problemFile = new File("./test_problems/pjf7.txt");

        Problem problem = ProblemFactory.createChainReactionProblem(problemFile);
        Node<ChainReactionState> init = new Node<>((ChainReactionState)problem.getInitialState(),null, null, 0,0);
        Heuristic<ChainReactionState> h1 = new ChainReactionNeighbourPruningHeuristic();
        Heuristic<ChainReactionState> h2 = new ChainReactionDirectionalDeadCheckHeuristic();
        Heuristic<ChainReactionState> h3 = new ChainReactionLeftVsOpenHeuristic();
        Frontier<ChainReactionState> frontier;
        //frontier = PQFrontier.aStarFrontier(1000);
        //frontier = PQFrontier.dijkstraFrontier(10);
        frontier = PQFrontier.greedyFrontier(100);
        //frontier = new StackFrontier<>();
        //frontier = PQFrontier.dijkstraFrontier(10);
        //frontier = new StackFrontier<>();
        //frontier = new QueueFrontier<>();

        Engine<ChainReactionState> engine = Engine.build(frontier);


        Node<ChainReactionState> n = engine.solution(init, problem, h2);
        //Node<ChainReactionState> n = engine.iddfs(init,problem,9);

        if(n == null) {
            System.out.println("No solution");
        } else {
            System.out.println(n.toString());
            System.out.println("Elapsed: " + init.elapsed(n));
            System.out.println("Cost: " + n.getCost());
        }


    }
}
