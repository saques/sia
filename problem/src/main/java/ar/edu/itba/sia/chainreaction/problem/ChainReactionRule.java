package ar.edu.itba.sia.chainreaction.problem;

import ar.com.itba.sia.Rule;


public class ChainReactionRule implements Rule<ChainReactionState> {
	private final int nextRow, nextCol;

	ChainReactionRule(final int nextRow, final int nextCol) {
		this.nextRow = nextRow;
		this.nextCol = nextCol;
	}

	@Override
	public double getCost() {
		return 1;
	}

	@Override
	public void setCost(double v) {

	}

	@Override
	public ChainReactionState applyToState(ChainReactionState chainReactionState) {
		return new ChainReactionState(chainReactionState, nextRow, nextCol);
	}

	@Override
	public boolean equals(Object other) {
		if (other.getClass() != ChainReactionRule.class) {
			return false;
		}
		ChainReactionRule or = (ChainReactionRule) other;
		return or.nextRow != nextRow || or.nextCol != nextCol;
	}

	@Override
	public int hashCode() {
		return nextRow ^ nextCol;
	}
}
