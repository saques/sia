package ar.edu.itba.sia.chainreaction.problem;

import javafx.util.Pair;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ChainReactionState {
	private final int totalSquares;
	private final int [][][] board;
	private final int forms, colors, row, col, rows, cols;
	private final Set<Pair<Integer, Integer>> occupiedSquares;
	private final List<Pair<Integer, Integer>> occupiedSquaresOrdered;
	private final Set<Pair<Integer, Integer>> neighbours;

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
		Pair<Integer, Integer> p = new Pair<>(row, col);
		occupiedSquares.add(p);
		occupiedSquaresOrdered.add(p);
		Pair<Integer, Integer> pair = new Pair<>(row, col);
		this.neighbours = Stream.of(canGoDown(pair), canGoLeft(pair), canGoRight(pair), canGoUp(pair)).filter(Optional::isPresent)
								.map(Optional::get)
								.collect(Collectors.toSet());

	}

	/**
	 *
	 * @param previousState
	 *
	 */
	ChainReactionState(ChainReactionState previousState, final int row, final int col) {
		this(previousState.board, previousState.forms, previousState.colors, previousState.totalSquares, row, col, previousState.rows, previousState.cols);
		this.occupiedSquares.addAll(previousState.occupiedSquares);
		this.occupiedSquaresOrdered.addAll(previousState.occupiedSquaresOrdered);
	}

	private boolean canGoThere(int row, int col, Pair<Integer, Integer> next) {
		return !occupiedSquares.contains(next) &&
				(board[row][col][0] == board[next.getKey()][next.getValue()][0] ||
						board[row][col][1] == board[next.getKey()][next.getValue()][1]);
	}

	private Optional<Pair<Integer, Integer>> canGoUp(Pair<Integer, Integer> pair) {
		int row = pair.getKey();
		int col = pair.getValue();
		int rowUp = row;
		do {
			rowUp = rowUp - 1 >= 0 ? rowUp - 1: rows - 1;
		} while (rowUp != row && board[rowUp][col][0] == 0);
		Pair<Integer, Integer> next = new Pair<>(rowUp, col);
		if (canGoThere(row, col, next)) {
			return Optional.of(next);
		} else {
			return Optional.empty();
		}
	}

	private Optional<Pair<Integer, Integer>> canGoDown(Pair<Integer, Integer> pair) {
		int row = pair.getKey();
		int col = pair.getValue();
		int rowDown = row;
		do {
			rowDown = rowDown + 1 < rows ? rowDown + 1: 0;
		} while (rowDown != row && board[rowDown][col][0] == 0);
		Pair<Integer, Integer> next = new Pair<>(rowDown, col);
		if (canGoThere(row, col, next)) {
			return Optional.of(next);
		} else {
			return Optional.empty();
		}
	}

	private Optional<Pair<Integer, Integer>> canGoLeft(Pair<Integer, Integer> pair) {
		int row = pair.getKey();
		int col = pair.getValue();
		int colLeft = col;
		do {
			colLeft = colLeft - 1 >= 0 ? colLeft - 1: cols - 1;
		} while (colLeft != col && board[row][colLeft][0] == 0);
		Pair<Integer, Integer> next = new Pair<>(row, colLeft);
		if (canGoThere(row, col, next)) {
			return Optional.of(next);
		} else {
			return Optional.empty();
		}
	}

	private Optional<Pair<Integer, Integer>> canGoRight(Pair<Integer, Integer> pair) {
		int row = pair.getKey();
		int col = pair.getValue();
		int colRight = col;
		do {
			colRight = colRight + 1 < cols ? colRight + 1: 0;
		} while (colRight != col && board[row][colRight][0] == 0);
		Pair<Integer, Integer> next = new Pair<>(row, colRight);
		if (canGoThere(row, col, next)) {
			return Optional.of(next);
		} else {
			return Optional.empty();
		}
	}

	boolean isFinal() {
		return occupiedSquares.size() == totalSquares;
	}

	Set<Pair<Integer, Integer>> getNeighbours(){
		return neighbours;
	}

	public Set<Pair<Integer, Integer>> getOccupiedSquares() {
		return occupiedSquares;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				char c1, c2;
				if (occupiedSquares.contains(new Pair<>(i, j))) {
					if (i == row && j == col) {
						c1 = '[';
						c2 = ']';
					} else {
						c1 = '(';
						c2 = ')';
					}
				} else {
					c1 = ' '; c2 = ' ';
				}
				builder.append(new Formatter().format("%c%2d,%2d%c", c1, board[i][j][0], board[i][j][1], c2));
			}
			builder.append('\n');
		}
		return builder.toString();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ChainReactionState that = (ChainReactionState) o;
		return totalSquares == that.totalSquares &&
				forms == that.forms &&
				colors == that.colors &&
				row == that.row &&
				col == that.col &&
				rows == that.rows &&
				cols == that.cols &&
				Arrays.equals(board, that.board) &&
				Objects.equals(occupiedSquares, that.occupiedSquares) &&
				Objects.equals(occupiedSquaresOrdered, that.occupiedSquaresOrdered) &&
				Objects.equals(neighbours, that.neighbours);
	}

	@Override
	public int hashCode() {

		int result = Objects.hash(totalSquares, forms, colors, row, col, rows, cols, occupiedSquares, occupiedSquaresOrdered, neighbours);
		result = 31 * result + Arrays.hashCode(board);
		return result;
	}

	public int getRows() {
		return rows;
	}

	public int getCols() {
		return cols;
	}

	public boolean checkPairNeighbours(Pair<Integer, Integer> pair) {
		return Stream.of(canGoDown(pair), canGoLeft(pair), canGoRight(pair), canGoUp(pair)).filter(Optional::isPresent)
				.map(Optional::get)
				.count() != 0;
	}

	public boolean isZeroZeroSquare(Pair<Integer, Integer> pair) {
		return board[pair.getKey()][pair.getValue()][0] != 0;
	}

	public int getLeft() {
		return totalSquares - occupiedSquares.size();
	}
}
