package ar.edu.itba.sia.chainreaction.problem;

import ar.com.itba.sia.Heuristic;

/**
 * Created by alejosaques on 29/4/18.
 */
public class RemainingVertexDegreeHeuristic implements Heuristic<ChainReactionState> {
  @Override
  public double getValue(ChainReactionState chainReactionState) {
    double neighs = chainReactionState.getNeighbours().size();
    double rate = chainReactionState.fillRate();
    return neighs > 0 ? (1/neighs)*rate : Double.POSITIVE_INFINITY;
  }
}
