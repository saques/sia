package ar.edu.itba.sia.chainreaction.graphics;

import ar.com.itba.sia.Rule;
import ar.edu.itba.sia.chainreaction.engine.Node;
import ar.edu.itba.sia.chainreaction.problem.ChainReactionRule;
import ar.edu.itba.sia.chainreaction.problem.ChainReactionState;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.Stack;

/**
 * Created by Mariano on 1/5/2018.
 */
public class ChainReactionGraphics extends JFrame {

	int rows;
	int cols;
	int cellSize;
	double figuresSize;

	Stack<Node<ChainReactionState>> states;

	public ChainReactionGraphics(Node<ChainReactionState> node, int cellSize, double figuresSize) {

		states = new Stack<>();

		Node<ChainReactionState> aux = node;
		while (aux.getParent() != null) {
			states.push(aux);
			aux = aux.getParent();
		}

		this.cellSize = cellSize;
		this.figuresSize = figuresSize;
		this.rows = node.getCurrent().getRows();
		this.cols = node.getCurrent().getCols();

		//Swing config
		setSize(new Dimension((rows + 1) * cellSize, (cols + 1) * cellSize));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public void drawSolution() {


		ChainReactionState first = states.peek().getCurrent();
		for (int i = 0; i < first.getRows(); i++) {
			for (int j = 0; j < first.getCols(); j++) {
				int[] aux = first.getBoard()[i][j];
				fillCell(i, j, aux[0], aux[1]);
			}
		}

		while (!states.empty()) {

//					try {
//						Thread.sleep(100);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}

			Node<ChainReactionState> current = states.pop();

			ChainReactionRule rule = (ChainReactionRule) current.getRule();

			int row = rule.getNextRow();
			int col = rule.getNextCol();

			int[] figureAndColor = current.getCurrent().getBoard()[row][col];

			fillOccupiedCell(row, col, figureAndColor[0], figureAndColor[1]);

		}
	}

	public void fillCell(int row, int col, int figure, int color) {
		JPanel p = new JPanel() {
			@Override
			public void paintComponent(Graphics g) {

				Graphics2D g2 = (Graphics2D) g;
				Shape rect = new Rectangle(row * cellSize, col * cellSize, cellSize, cellSize);
				g2.draw(rect);
			}
		};
		this.getContentPane().add(p);
	}

	public void fillOccupiedCell(int row, int col, int figure, int color) {
		JPanel p = new JPanel() {
			@Override
			public void paintComponent(Graphics g) {

				Graphics2D g2 = (Graphics2D) g;
				Shape rect = new Rectangle(row * cellSize, col * cellSize, cellSize, cellSize);
				g2.setColor(Color.RED);
				g2.fill(rect);
			}
		};

		this.getContentPane().add(p);
	}


}
