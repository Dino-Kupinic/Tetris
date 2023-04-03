package at.ac.htlsteyr.tetris.Model;

import at.ac.htlsteyr.tetris.Exceptions.InvalidShapeException;
import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Objects;

public class Game {
	Field[][] gameGrid;
	TetrominoFactory tetroFactory;
	Tetromino currentTetromino;

	public Game(Grid grid) {
		gameGrid = grid.getGrid();
		tetroFactory = new TetrominoFactory();
		currentTetromino = null;
	}

	public void createTetromino() {
		try {
			// generate random number
			int MIN_NUM = 0;
			int MAX_NUM = TetrominoShapes.values().length;
			int randNumber = (int) (Math.random() * MAX_NUM + MIN_NUM);

			// assign random number to corresponding Shape
			TetrominoShapes shape = null;
			for (TetrominoShapes s : TetrominoShapes.values()) {
				if (randNumber == s.ordinal()) {
					shape = s;
				}
			}

			// create Tetromino and add to grid
			currentTetromino = tetroFactory.createTetromino(Objects.requireNonNull(shape));
			int[][] tetroGrid = currentTetromino.getTetroGrid();
			ArrayList<Block> blocks = currentTetromino.getTetroBlocks();
			Color color = blocks.get(0).blockColor;

			for (int row = 0; row < tetroGrid.length; row++) {
				for (int col = 0; col < tetroGrid[row].length; col++) {
					gameGrid[row + 1][col].getFieldNode().setFill(Color.GRAY);
					if (tetroGrid[row][col] == 1) {
						gameGrid[row + 1][col].getFieldNode().setFill(color);
						gameGrid[row + 1][col].setContainsBlock(true);
						gameGrid[row + 1][col].updateDebugText();
					}
				}
			}
		} catch (InvalidShapeException e) {
			throw new RuntimeException(e);
		}
	}

	public void startGameLoop() {
		AnimationTimer animationTimer = new AnimationTimer() {
			int count = 0;
			final int ticks = 30;
			int xOffset = 0;
			int yOffset = 0;

			@Override
			public void handle(long l) {
				count++;
				if (count == ticks) {
					xOffset++;
					yOffset++;
					assert currentTetromino != null;
					Tetromino currentTetro = currentTetromino;
					int[][] tetroGrid = currentTetro.getTetroGrid();
					ArrayList<Block> blocks = currentTetromino.getTetroBlocks();
					Color color = blocks.get(0).blockColor;

					// move tetro
					for (int row = 0; row < tetroGrid.length; row++) {
						for (int col = 0; col < tetroGrid[row].length; col++) {
							gameGrid[row][col].getFieldNode().setFill(Color.GRAY);
							if (tetroGrid[row][col] == 1) {
								gameGrid[row][col + yOffset].getFieldNode().setFill(color);
								gameGrid[row][col + yOffset].setContainsBlock(true);
							}
						}
					}
                    count = 0;
				}
			}
		};
		animationTimer.start();
	}
}
