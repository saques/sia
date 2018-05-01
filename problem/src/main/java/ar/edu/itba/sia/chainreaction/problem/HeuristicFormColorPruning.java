package ar.edu.itba.sia.chainreaction.problem;

import ar.com.itba.sia.Heuristic;
import javafx.util.Pair;

import java.util.List;
import java.util.SortedMap;

public class HeuristicFormColorPruning implements Heuristic<ChainReactionState> {
	@Override
	public double getValue(ChainReactionState chainReactionState) {
//		if (chainReactionState.getLeft() == 1) {
//			return 0;
//		}
//		SortedMap<Integer, Integer> colorMap
//		for (int i = 0; i < chainReactionState.getRows(); i++) {
//			for (int j = 0; j < chainReactionState.getCols(); j++) {
//				if (checked[i][j]) {
//					continue;
//				}
//				Pair<Integer, Integer> pair = new Pair<>(i, j);
//				if (chainReactionState.isZeroZeroSquare(pair) || chainReactionState.isOccupied(pair.getKey(), pair.getValue())){
//					continue;
//				}
//				List<Pair<Integer, Integer>> list = chainReactionState.getPairNeighbours(pair);
//				if (list.isEmpty()) {
//					return Double.POSITIVE_INFINITY;
//				} else {
//					list.forEach(p -> {
//						checked[p.getKey()][p.getValue()] = true;
//					});
//				}
//			}
//		}
//
//		//System.out.println("["+ chainReactionState.getLeft() + "]");
//		List<Pair<Integer, Integer>> neighbours = chainReactionState.getNeighbours();
//		int sum = neighbours.size();
//		for (Pair<Integer, Integer> p: neighbours) {
//			sum += chainReactionState.getPairNeighbours(p).size() - 1;
//		}
		//double min = chainReactionState.getNeighbours().size();
		return chainReactionState.getLeft() - 1.0 / 2;
	}
}
