package ar.edu.itba.sia.chainreaction.problem;

import ar.com.itba.sia.Problem;
import ar.com.itba.sia.Rule;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
		return Stream.of(chainReactionState.canGoUp(), chainReactionState.canGoDown(), chainReactionState.canGoLeft(), chainReactionState.canGoRight())
				.filter(Objects::nonNull)
				.map(x -> new ChainReactionRule(x.getKey(), x.getValue()))
				.distinct()
				.collect(Collectors.toList());
	}

	@Override
	public boolean isResolved(ChainReactionState chainReactionState) {
		return chainReactionState.isFinal();
	}
}
