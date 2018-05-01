package ar.edu.itba.sia.chainreaction.problem;

import ar.com.itba.sia.Heuristic;
import javafx.util.Pair;

import java.util.List;

public class ChainReactionDirectionalDeadCheckHeuristic implements Heuristic<ChainReactionState> {

    @Override
    public double getValue(ChainReactionState chainReactionState) {
        double neighs = chainReactionState.getNeighbours().size();
        if(neighs == 0 && chainReactionState.getLeft() != 0)
            return Double.POSITIVE_INFINITY;

        int x = chainReactionState.getRow(), y = chainReactionState.getCol();
        for(int yp=0; yp<chainReactionState.getCols(); yp++){
            Pair<Integer, Integer> pair = new Pair<>(x, yp);
            if(yp == y || omit(pair, chainReactionState)) {
                continue;
            }
            List<Pair<Integer, Integer>> list = chainReactionState.getPairNeighbours(pair);
            if(list.size() == 0) {
                return Double.POSITIVE_INFINITY;
            }
        }

        for(int xp=0; xp<chainReactionState.getRows(); xp++){
            Pair<Integer, Integer> pair = new Pair<>(xp, y);
            if(xp == x || omit(pair, chainReactionState)) {
                continue;
            }
            List<Pair<Integer, Integer>> list = chainReactionState.getPairNeighbours(pair);
            if(list.size() == 0) {
                return Double.POSITIVE_INFINITY;
            }
        }
        return chainReactionState.getLeft() - 1.0 / neighs;
    }

    private static boolean omit(Pair<Integer, Integer> pair, ChainReactionState chainReactionState){
        return chainReactionState.isZeroZeroSquare(pair) ||
            chainReactionState.isOccupied(pair.getKey(), pair.getValue());
    }
}
