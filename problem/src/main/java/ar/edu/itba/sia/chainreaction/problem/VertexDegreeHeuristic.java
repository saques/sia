package ar.edu.itba.sia.chainreaction.problem;

import ar.com.itba.sia.Heuristic;

public class VertexDegreeHeuristic implements Heuristic<ChainReactionState> {
    @Override
    public double getValue(ChainReactionState chainReactionState) {
        double neighs = chainReactionState.getNeighbours().size();
        return neighs > 0 ? 1/neighs : Double.POSITIVE_INFINITY;
    }
}
