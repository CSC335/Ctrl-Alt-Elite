package model;

import java.util.Random;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class FoodPellet {
	private int SIZE = 2; // width & height of pellet - circle shape
	private Color color;
	private int x;
	private int y;
	private boolean isEaten = false;
	private GraphicsContext gc;
	private Color[] pelletColors = { Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE, Color.PINK,
			Color.PURPLE };

	public FoodPellet(int boardWidth, int boardHeight, GraphicsContext gc) {
		this.gc = gc;
		spawn(boardWidth, boardHeight);
	}

	public void spawn(int boardWidth, int boardHeight) {
		Random random = new Random();
		int index = random.nextInt(6);
		Color color = pelletColors[index];
		this.color = color;

		random = new Random();
		x = random.nextInt(boardWidth);
		y = random.nextInt(boardHeight);
		draw();
	}

	public void draw() {
		gc.setStroke(color);
		gc.fillOval(x, y, SIZE, SIZE);
	}

	public void respawn(int boardWidth, int boardHeight) {
		spawn(boardWidth, boardHeight);
		isEaten = false;
	}

	// detect collision with snakes head
	public boolean detectCollision(Point head) {
		if (head.getX() == getX() && head.getY() == getY()) {
			isEaten = true;
			return true;
		}
		return false;
	}

	public boolean isEaten() {
		return isEaten;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
}
