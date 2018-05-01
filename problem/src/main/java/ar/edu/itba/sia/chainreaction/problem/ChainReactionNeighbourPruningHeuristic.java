package ar.edu.itba.sia.chainreaction.problem;

import ar.com.itba.sia.Heuristic;
import javafx.util.Pair;

import java.util.List;

public class ChainReactionNeighbourPruningHeuristic implements Heuristic<ChainReactionState> {

	private int inc;

	public ChainReactionNeighbourPruningHeuristic(){
		this(1);
	}

	public ChainReactionNeighbourPruningHeuristic(int inc){
		this.inc = inc;
	}

	@Override
	public double getValue(ChainReactionState chainReactionState) {
		if (chainReactionState.getLeft() == 1) {
			return 0;
		}
		boolean[][] checked = new boolean[chainReactionState.getRows()][chainReactionState.getCols()];
		for (int i = 0; i < chainReactionState.getRows(); i+=inc) {
			for (int j = 0; j < chainReactionState.getCols(); j+=inc) {
				if (checked[i][j]) {
					continue;
				}
				Pair<Integer, Integer> pair = new Pair<>(i, j);
				if (chainReactionState.isZeroZeroSquare(pair) || chainReactionState.isOccupied(pair.getKey(), pair.getValue())){
					continue;
				}
				List<Pair<Integer, Integer>> list = chainReactionState.getPairNeighbours(pair);
				if (list.isEmpty()) {
					return Double.POSITIVE_INFINITY;
				} else {
					list.forEach(p -> {
						checked[p.getKey()][p.getValue()] = true;
					});
				}
			}
		}
		//System.out.println("["+ chainReactionState.getLeft() + "]");
		List<Pair<Integer, Integer>> neighbours = chainReactionState.getNeighbours();
		int sum = neighbours.size();
//		for (Pair<Integer, Integer> p: neighbours) {
//			sum += chainReactionState.getPairNeighbours(p).size() - 1;
//		}
 		//double min = chainReactionState.getNeighbours().size();
		return chainReactionState.getLeft() - 1.0 / sum;
	}
}
