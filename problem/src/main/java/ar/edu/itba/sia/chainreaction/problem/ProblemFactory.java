package ar.edu.itba.sia.chainreaction.problem;

import java.io.*;
import java.util.Scanner;

public class ProblemFactory {

	public static ChainReactionProblem createChainReactionProblem(File problemFile) throws IOException{
		try (Scanner scanner = new Scanner(problemFile)) {
			scanner.useDelimiter("(\\p{javaWhitespace}|,)+");
			int rows = scanner.nextInt();
			int cols = scanner.nextInt();
			int forms = scanner.nextInt();
			int colors = scanner.nextInt();
			int initX = scanner.nextInt();
			int initY = scanner.nextInt();
			int [][][] board = new int[rows][cols][2];
			int totalDimension = 0;
			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < cols; j++) {
					int f = scanner.nextInt();
					int c = scanner.nextInt();
					if (f > forms || c > colors || f < 0 || c < 0) {
						throw new RuntimeException("An unaccounted form or color was found.");
					}
					if ((c == 0 && f != 0) || (c != 0 && f == 0)) {
						throw new RuntimeException("Either both color and form are 0, or neither are.");
					}
					// Check if it is a useful square, if it is add to totalDimension
					if (f != 0) {
						totalDimension++;
					}
					board[i][j][0] = f;
					board[i][j][1] = c;
				}
			}
			return new ChainReactionProblem(board, forms, colors, totalDimension, rows, cols, initX, initY);
		}

	}
}
