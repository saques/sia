package ar.edu.itba.sia.chainreaction.problem;

import javafx.util.Pair;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ChainReactionState {
	private final int totalSquares;
	private final int [][][] board;
	private final int forms, colors, row, col, rows, cols;
	private boolean[][] occupiedSquares;
	private List<Pair<Integer, Integer>> occupiedSquaresOrdered;
	private List<Pair<Integer, Integer>> neighbours;

	/**
	 * @param board board[rows][cols][2]
	 *              Each square in the board informs the shape and color of the element.
	 *              Empty positions should inform the shape and value [0,0].
	 *              The board should be treated as final by the states and not be modified.
	 */
	ChainReactionState(final int[][][] board, final int forms, final int colors, final int totalSquares,
					   final int row, final int col, final int rows, final int cols, boolean isInitial) {
		this.board = board;

		this.forms = forms;
		this.colors = colors;
		this.totalSquares = totalSquares;
		this.row = row;
		this.col = col;
		this.rows = rows;
		this.cols = cols;
		Pair<Integer, Integer> p = new Pair<>(row, col);
		if (isInitial) {
			this.occupiedSquares = new boolean[rows][cols];
			this.occupiedSquaresOrdered = new ArrayList<>();
			occupiedSquares[row][col] = true;
			occupiedSquaresOrdered.add(p);
			Pair<Integer, Integer> pair = new Pair<>(row, col);
			this.neighbours = findNeighbours(pair);
		}
	}

	private boolean[][] copyBidimensionalArray(boolean[][] old) {
		boolean[][] n = new boolean[old.length][];
		for(int i=0; i<old.length; i++)
			n[i] = Arrays.copyOf(old[i], old[i].length);
		return n;
	}
	/**
	 *
	 * @param previousState
	 *
	 */
	ChainReactionState(ChainReactionState previousState, final int row, final int col) {
		this(previousState.board, previousState.forms, previousState.colors, previousState.totalSquares, row, col, previousState.rows, previousState.cols, false);
		this.occupiedSquares = copyBidimensionalArray(previousState.occupiedSquares);
		occupiedSquares[row][col] = true;
		this.occupiedSquaresOrdered = new ArrayList<>(previousState.occupiedSquaresOrdered);
		Pair<Integer, Integer> pair = new Pair<>(row, col);
		this.occupiedSquaresOrdered.add(pair);
		this.neighbours = findNeighbours(pair);
//		this.neighbours.addAll(getRowNeighbours(pair));
		this.neighbours.removeIf(x -> occupiedSquares[x.getKey()][x.getValue()]);
	}

	private boolean canGoThere(int row, int col, int row2, int col2) {
		return !occupiedSquares[row2][col2] &&
				(board[row][col][0] == board[row2][col2][0] ||
						board[row][col][1] == board[row2][col2][1]);
	}

	private List<Pair<Integer, Integer>> findNeighbours(Pair<Integer, Integer> pair) {
		List<Pair<Integer, Integer>> set = new ArrayList<>(4);
		int row = pair.getKey();
		int col = pair.getValue();
		for (int i = 0; i < rows; i++) {
			if (i != row && canGoThere(row,col, i, col)) {
				set.add(new Pair<>(i, col));
			}
		}
		for (int j = 0; j < cols; j++) {
			if (j != col && canGoThere(row,col, row, j)) {
				set.add(new Pair<>(row, j));
			}
		}
		return set;
	}

	boolean isFinal() {
		return occupiedSquaresOrdered.size() == totalSquares;
	}

	List<Pair<Integer, Integer>> getNeighbours(){
		return neighbours;
	}


	double fillRate(){
		return ((double)occupiedSquaresOrdered.size())/((double)totalSquares);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				char c1, c2;
				if (occupiedSquares[i][j]) {
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
		List<Pair<Integer, Integer>> set =  findNeighbours(pair);
		return set.size() != 0;
	}

	public boolean isZeroZeroSquare(Pair<Integer, Integer> pair) {
		return board[pair.getKey()][pair.getValue()][0] != 0;
	}

	public int getLeft() {
		return totalSquares - occupiedSquaresOrdered.size();
	}

	public Set<Pair<Integer, Integer>> getOccupiedSquares() {
		return new HashSet<>();
	}
}
