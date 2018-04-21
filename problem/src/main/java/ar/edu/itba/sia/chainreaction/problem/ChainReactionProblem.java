package ar.edu.itba.sia.chainreaction.problem;

import ar.com.itba.sia.Problem;
import ar.com.itba.sia.Rule;
import javafx.util.Pair;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ChainReactionProblem implements Problem<ChainReactionState> {
	private final ChainReactionState initialState;

	ChainReactionProblem(int[][][] board, int forms, int colors, int totalSquares, int rows, int cols) {
		this.initialState = new ChainReactionState(board, forms, colors, totalSquares,0, 0, rows, cols);
	}

	@Override
	public ChainReactionState getInitialState() {
		return initialState;
	}

	@NotNull
	@Override
	public List<Rule<ChainReactionState>> getRules(ChainReactionState chainReactionState) {
		Set<Rule<ChainReactionState>> rules = new HashSet<>();
		Pair<Integer, Integer> p;
		if ((p = chainReactionState.canGoUp()) != null) {
			rules.add(new ChainReactionRule(p.getKey(), p.getValue()));
		}
		if ((p = chainReactionState.canGoDown()) != null) {
			rules.add(new ChainReactionRule(p.getKey(), p.getValue()));
		}
		if ((p = chainReactionState.canGoLeft()) != null) {
			rules.add(new ChainReactionRule(p.getKey(), p.getValue()));
		}
		if ((p = chainReactionState.canGoRight()) != null) {
			rules.add(new ChainReactionRule(p.getKey(), p.getValue()));
		}
		return new ArrayList<>(rules);
	}

	@Override
	public boolean isResolved(ChainReactionState chainReactionState) {
		return chainReactionState.isFinal();
	}
}
