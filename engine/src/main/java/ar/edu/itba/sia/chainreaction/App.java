package ar.edu.itba.sia.chainreaction;

import ar.com.itba.sia.Problem;
import ar.edu.itba.sia.chainreaction.engine.Engine;
import ar.edu.itba.sia.chainreaction.engine.Node;
import ar.edu.itba.sia.chainreaction.engine.frontier.QueueFrontier;
import ar.edu.itba.sia.chainreaction.engine.frontier.StackFrontier;
import ar.edu.itba.sia.chainreaction.problem.ChainReactionState;
import ar.edu.itba.sia.chainreaction.problem.ProblemFactory;
import com.sun.net.httpserver.Filter;

import java.io.File;

public class App
{
    public static void main( String[] args ) throws Exception{

        Engine<ChainReactionState> engine = Engine.build(new StackFrontier<>());
        File problemFile = new File("./test_problems/problem1.txt");
        Problem problem = ProblemFactory.createChainReactionProblem(problemFile);

        Node<ChainReactionState> init = new Node<ChainReactionState>((ChainReactionState)problem.getInitialState(),null,0);

        Node<ChainReactionState> n = engine.solution(init, problem);

        if(n == null) {
            System.out.println("No solution");
        } else {
            System.out.println(n.toString());

            System.out.println("Elapsed: " + init.elapsed(n));
            System.out.println("Cost: " + n.getCost());
        }



    }
}
