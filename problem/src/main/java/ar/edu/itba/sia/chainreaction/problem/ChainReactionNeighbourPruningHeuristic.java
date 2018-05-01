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
	public double getValue(ChainReactionState c) {
		if (c.getLeft() == 1) {
			return 0;
		}
		boolean[][] checked = new boolean[c.getRows()][c.getCols()];
		for (int i = 0; i < c.getRows(); i+=inc) {
			for (int j = 0; j < c.getCols(); j+=inc) {
				if (checked[i][j]) {
					continue;
				}
				Pair<Integer, Integer> pair = new Pair<>(i, j);
				if (c.isZeroZeroSquare(pair) || c.isOccupied(pair.getKey(), pair.getValue())){
					continue;
				}
				List<Pair<Integer, Integer>> list = c.getPairNeighbours(pair);
				if (list.isEmpty()) {
					return Double.POSITIVE_INFINITY;
				} else {
					list.forEach(p -> {
						checked[p.getKey()][p.getValue()] = true;
					});
				}
			}
		}
		List<Pair<Integer, Integer>> neighbours = c.getNeighbours();
		int open = neighbours.size();
		return c.getLeft() - 1.0 / open;
	}
}
