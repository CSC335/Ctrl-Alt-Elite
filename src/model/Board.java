package model;

import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Board {
	private int width;
	private int height;
	private Color backgroundColor;
	private ArrayList<FoodPellet> foodPellets;
	private GraphicsContext gc;
	private Snake snake;

	public Board(int width, int height, Color backgroundColor, GraphicsContext gc, Snake snake) {
		this.gc = gc;
		this.width = width;
		this.height = height;
		this.backgroundColor = backgroundColor;
		this.snake = snake;
		foodPellets = new ArrayList<FoodPellet>();
		initialize();
	}

	public void initialize() {
		gc.setFill(backgroundColor);
		gc.fillRect(0, 0, width, height);

		// spawn 5 random food pellets - adjust number if not enough/too much
		for (int i = 0; i < 5; i++) {
			spawnFoodPellet();
		}
	}

	public void spawnFoodPellet() {
		FoodPellet pellet = new FoodPellet(width, height, gc);
		foodPellets.add(pellet);
	}

	// updates the board with current positions of snake and food pellets
	public void update() {
		gc.setFill(backgroundColor);
		gc.fillRect(0, 0, width, height);

		for (FoodPellet pellet : foodPellets)
			pellet.draw();

		snake.draw(gc);
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public ArrayList<FoodPellet> getFoodPellets() {
		return foodPellets;
	}

	public boolean isCollision() {
		Point head = snake.getHead();

		// collided with walls
		if (head.getX() > 0 && head.getX() < width && head.getY() > 0 && head.getY() < height)
			return true;

		// collided with its own body
		for (Point segment : snake.getBody()) {
			if (head.getX() == segment.getX() && head.getY() == segment.getY())
				return true;
		}

		return false;
	}
}
