package ar.edu.itba.sia.chainreaction.problem;

import ar.com.itba.sia.Heuristic;
import javafx.util.Pair;

public class ChainReactionHeuristicNeighbourPruning implements Heuristic<ChainReactionState> {
	@Override
	public double getValue(ChainReactionState chainReactionState) {
		if (chainReactionState.getLeft() == 1) {
			return 0;
		}
		for (int i = 0; i < chainReactionState.getRows(); i++) {
			for (int j = 0; j < chainReactionState.getCols(); j++) {
				Pair<Integer, Integer> pair = new Pair<>(i, j);
				if (!chainReactionState.isZeroZeroSquare(pair) || chainReactionState.getOccupiedSquares().contains(pair)){
					continue;
				}
				if (!chainReactionState.checkPairNeighbours(pair)) {
					return Double.POSITIVE_INFINITY;
				}
			}
		}
		return chainReactionState.getLeft();
	}
}
