package ar.edu.itba.sia.chainreaction.problem;

import ar.com.itba.sia.Heuristic;
import javafx.util.Pair;

import java.util.List;

public class ChainReactionDirectionalDeadCheckHeuristic implements Heuristic<ChainReactionState> {

    @Override
    public double getValue(ChainReactionState c) {
        if (c.getLeft() == 1) {
            return 0;
        }
        boolean[][] checked = new boolean[c.getRows()][c.getCols()];
        int x = c.getRow(), y = c.getCol();

        for(int yp=0; yp<c.getCols(); yp++){
            if(doCheck(x,y,x,yp,checked,c))
                return Double.POSITIVE_INFINITY;
        }

        for(int xp=0; xp<c.getRows(); xp++){
            if(doCheck(x,y,xp,y,checked,c))
                return Double.POSITIVE_INFINITY;
        }

        return c.getLeft() - 1.0 / c.getNeighbours().size();
    }

    private static boolean doCheck(int x, int y, int xp, int yp, boolean[][] checked, ChainReactionState c){
        Pair<Integer, Integer> pair = new Pair<>(xp, yp);
        if(checked[xp][yp] || (yp == y && xp == x) || omit(pair, c)) {
            return false;
        }
        List<Pair<Integer, Integer>> list = c.getPairNeighbours(pair);
        if(list.isEmpty()) {
            return true;
        }
        list.forEach(p -> {
            checked[p.getKey()][p.getValue()] = true;
        });
        return false;
    }

    private static boolean omit(Pair<Integer, Integer> pair, ChainReactionState chainReactionState){
        return chainReactionState.isZeroZeroSquare(pair) ||
            chainReactionState.isOccupied(pair.getKey(), pair.getValue());
    }
}
