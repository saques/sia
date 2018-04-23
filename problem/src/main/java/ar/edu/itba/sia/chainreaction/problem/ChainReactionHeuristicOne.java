package ar.edu.itba.sia.chainreaction.problem;

import ar.com.itba.sia.Heuristic;

public class ChainReactionHeuristicOne implements Heuristic<ChainReactionState> {
    @Override
    public double getValue(ChainReactionState chainReactionState) {
        return 1/chainReactionState.getNeighbours().size();
    }
}
