package ar.edu.itba.sia.chainreaction.problem;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ChainReactionState {
	private final int totalSquares;
	private final int [][][] board;
	private final int forms, colors, row, col, rows, cols;
	private final HashSet<Pair<Integer, Integer>> occupiedSquares;
	private final List<Pair<Integer, Integer>> occupiedSquaresOrdered;

	/**
	 * @param board board[rows][cols][2]
	 *              Each square in the board informs the shape and color of the element.
	 *              Empty positions should inform the shape and value [0,0].
	 *              The board should be treated as final by the states and not be modified.
	 */
	ChainReactionState(final int[][][] board, final int forms, final int colors, final int totalSquares,
					   final int row, final int col, final int rows, final int cols) {
		this.board = board;
		this.occupiedSquares = new HashSet<>();
		this.occupiedSquaresOrdered = new ArrayList<>();
		this.forms = forms;
		this.colors = colors;
		this.totalSquares = totalSquares;
		this.row = row;
		this.col = col;
		this.rows = rows;
		this.cols = cols;
	}

	/**
	 *
	 * @param previousState
	 *
	 */
	ChainReactionState(ChainReactionState previousState, final int row, final int col) {
		this(previousState.board, previousState.forms, previousState.colors, previousState.totalSquares, row, col, previousState.rows, previousState.cols);
		this.occupiedSquares.addAll(previousState.occupiedSquares);
		Pair<Integer, Integer> p = new Pair<>(row, col);
		occupiedSquares.add(p);
		occupiedSquaresOrdered.add(p);
	}

	private boolean canGoThere(Pair<Integer, Integer> next) {
		return occupiedSquares.contains(next) &&
				(board[row][col][0] == board[next.getKey()][next.getValue()][0] ||
						board[row][col][1] == board[next.getKey()][next.getValue()][1]);
	}

	Pair<Integer, Integer> canGoUp() {
		int rowUp = row;
		do {
			rowUp = rowUp - 1 >= 0 ? rowUp - 1: rows - 1;
		} while (rowUp != row && board[rowUp][col][0] == 0);
		Pair<Integer, Integer> next = new Pair<>(rowUp, col);
		if (canGoThere(next)) {
			return next;
		} else {
			return null;
		}
	}

	Pair<Integer, Integer> canGoDown() {
		int rowDown = row;
		do {
			rowDown = rowDown + 1 < rows ? rowDown + 1: 0;
		} while (rowDown != row && board[rowDown][col][0] == 0);
		Pair<Integer, Integer> next = new Pair<>(rowDown, col);
		if (canGoThere(next)) {
			return next;
		} else {
			return null;
		}
	}

	Pair<Integer, Integer> canGoLeft() {
		int colLeft = col;
		do {
			colLeft = colLeft - 1 >= 0 ? colLeft - 1: cols - 1;
		} while (colLeft != col && board[row][colLeft][0] == 0);
		Pair<Integer, Integer> next = new Pair<>(row, colLeft);
		if (canGoThere(next)) {
			return next;
		} else {
			return null;
		}
	}

	Pair<Integer, Integer> canGoRight() {
		int colRight = col;
		do {
			colRight = colRight + 1 < cols ? colRight + 1: 0;
		} while (colRight != col && board[row][colRight][0] == 0);
		Pair<Integer, Integer> next = new Pair<>(row, colRight);
		if (canGoThere(next)) {
			return next;
		} else {
			return null;
		}
	}

	boolean isFinal() {
		return occupiedSquares.size() == totalSquares;
	}
}
