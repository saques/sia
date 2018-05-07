package ar.edu.itba.sia.chainreaction;

import ar.com.itba.sia.Heuristic;
import ar.com.itba.sia.Problem;
import ar.edu.itba.sia.chainreaction.engine.Engine;
import ar.edu.itba.sia.chainreaction.engine.Node;
import ar.edu.itba.sia.chainreaction.engine.frontier.PQFrontier;
import ar.edu.itba.sia.chainreaction.engine.frontier.QueueFrontier;
import ar.edu.itba.sia.chainreaction.engine.frontier.StackFrontier;
import ar.edu.itba.sia.chainreaction.problem.ChainReactionNeighbourPruningHeuristic;
import ar.edu.itba.sia.chainreaction.graphics.ChainReactionGraphics;
import ar.edu.itba.sia.chainreaction.problem.*;
import ar.edu.itba.sia.chainreaction.engine.frontier.Frontier;
import ar.edu.itba.sia.grupo2.problem.SenkuBoard;
import ar.edu.itba.sia.grupo2.problem.SenkuProblem;
import ar.edu.itba.sia.grupo2.problem.heuristic.PegsDifficulty;

import java.io.File;
import java.io.IOException;
import java.util.stream.IntStream;

public class App
{
	public static Node<ChainReactionState> testFile(String path, int heuristic, String frontier) throws IOException, IllegalAccessException, InstantiationException{
		File problemFile = new File(path);

        Problem problem = ProblemFactory.createChainReactionProblem(problemFile);
        Node<ChainReactionState> init = new Node<>((ChainReactionState)problem.getInitialState(),null, null, 0,0);

        Heuristic<ChainReactionState> h;

        switch (heuristic){
			case 1:
				h = new ChainReactionNeighbourPruningHeuristic();
				break;
			case 2:
				h = new ChainReactionDirectionalDeadCheckHeuristic();
				break;
			case 3:
				h = new ChainReactionLeftVsOpenHeuristic();
				break;
			default:
				throw new IllegalArgumentException();
		}

        Frontier<ChainReactionState> f;

        switch (frontier){
			case "astar":
				f = PQFrontier.aStarFrontier(1000);
				break;
			case "stack":
				f = new StackFrontier<>();
				break;
			case "queue":
				f = new QueueFrontier<>();
				break;
			case "greedy":
				f = PQFrontier.greedyFrontier(100);
				break;
			case "dijkstra":
				f = PQFrontier.dijkstraFrontier(10);
				break;
			default:
				throw new IllegalArgumentException();
		}

		Engine<ChainReactionState> engine = Engine.build(f);
        Node<ChainReactionState> n = engine.solution(init, problem, h);

        printMetaData(n,init,engine);

		return n;
	}

	public static Node<ChainReactionState> testFileIddfs(String path, int depth) throws IOException, IllegalAccessException, InstantiationException{
		File problemFile = new File(path);

		Problem problem = ProblemFactory.createChainReactionProblem(problemFile);
		Node<ChainReactionState> init = new Node<>((ChainReactionState)problem.getInitialState(),null, null, 0,0);

		Engine<ChainReactionState> engine = Engine.build(new StackFrontier<>());
		Node<ChainReactionState> n = engine.iddfs(init,problem,depth);

		printMetaData(n,init,engine);

		return n;
	}

	public static Node<SenkuBoard> senku() throws Exception{

		Problem<SenkuBoard> problem = new SenkuProblem();
		Node<SenkuBoard> init = new Node<>(problem.getInitialState(),null, null, 0,0);
		Heuristic<SenkuBoard> heuristic = new PegsDifficulty();
		Frontier<SenkuBoard> frontier = PQFrontier.aStarFrontier(1000);



		Engine<SenkuBoard> engine = Engine.build(frontier);
		Node<SenkuBoard> n = engine.solution(init, problem, heuristic);

		printMetaData(n,init,engine);

		return n;
	}

	private static <E> void printMetaData(Node<E> n, Node<E> init, Engine<E> engine){
		if(n == null){
			System.out.println("No solution");
		} else {
			System.out.println("Elapsed: " + init.elapsed(n));
			System.out.println("Cost: " + n.getCost());
			System.out.println("Depth: " + n.getDepth());
			System.out.println("Expanded: " + engine.getExpandedNodes());
			System.out.println("Visited: " + engine.getVisitedNodes());
			System.out.println("Frontier: "+ engine.getFrontierNodes());
		}
    }

    public static void main( String[] args ) throws Exception{
		Node<SenkuBoard> n = senku();
		if(n != null)
			System.out.println(n);
	}
}
