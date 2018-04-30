package ar.edu.itba.sia.chainreaction.problem;

import ar.com.itba.sia.Heuristic;
import javafx.util.Pair;

import java.util.List;

public class ChainReactionHeuristicNeighbourPruning implements Heuristic<ChainReactionState> {

	@Override
	public double getValue(ChainReactionState chainReactionState) {
		if (chainReactionState.getLeft() == 1) {
			return 0;
		}
		boolean[][] checked = new boolean[chainReactionState.getRows()][chainReactionState.getCols()];
		for (int i = 0; i < chainReactionState.getRows(); i++) {
			for (int j = 0; j < chainReactionState.getCols(); j++) {
				if (checked[i][j]) {
					continue;
				}
				Pair<Integer, Integer> pair = new Pair<>(i, j);
				if (chainReactionState.isZeroZeroSquare(pair) || chainReactionState.getOccupiedSquares()[pair.getKey()][pair.getValue()]){
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
		return chainReactionState.getNeighbours().size();
	}
}
