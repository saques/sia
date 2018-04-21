package ar.edu.itba.sia.chainreaction;

import ar.com.itba.sia.Problem;
import ar.edu.itba.sia.chainreaction.problem.ProblemFactory;

import java.io.File;
import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        File problemFile = new File("./test_problems/problem1.txt");
        try {
            Problem problem = ProblemFactory.createChainReactionProblem(problemFile);
            problem.getInitialState();

        } catch (IOException e) {
            System.out.println("There was a read error.");
        }
    }
}
