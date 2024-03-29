package ar.edu.itba.sia.chainreaction.problem;

import ar.com.itba.sia.Heuristic;
import javafx.util.Pair;

import java.util.List;

/**
 * Created by alejosaques on 1/5/18.
 */
public class ChainReactionLeftVsOpenHeuristic implements Heuristic<ChainReactionState> {

  @Override
  public double getValue(ChainReactionState c) {
    List<Pair<Integer, Integer>> neighbours = c.getNeighbours();
    int open = neighbours.size();
    if(open == 0 && c.getLeft() != 0)
      return Double.POSITIVE_INFINITY;
    return c.getLeft() - 1.0 / open;
  }

}
