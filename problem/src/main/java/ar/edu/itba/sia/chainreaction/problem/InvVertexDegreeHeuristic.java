package ar.edu.itba.sia.chainreaction.problem;

import ar.com.itba.sia.Heuristic;

public class InvVertexDegreeHeuristic implements Heuristic<ChainReactionState> {
    @Override
    public double getValue(ChainReactionState chainReactionState) {
        return chainReactionState.getNeighbours().size();
    }
}
