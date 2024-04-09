package model;

import java.util.ArrayList;
import java.util.Random;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Board {
	private int width;
	private int height;
	private Color backgroundColor;
	private ArrayList<FoodPellet> foodPellets;
	private Color[] pelletColors = { Color.RED, Color.ORANGE, Color.YELLOW, 
			Color.GREEN, Color.BLUE, Color.PINK, Color.PURPLE };
	private GraphicsContext gc;

	public Board(int width, int height, Color backgroundColor, GraphicsContext gc) {
		this.gc = gc;
		this.width = width;
		this.height = height;
		this.backgroundColor = backgroundColor;

	}

	public void initialize() {
		gc.setFill(backgroundColor);
		gc.fillRect(0, 0, width, height);
	}

	public void spawnFoodPellet() {
		Random random = new Random();
		int index = random.nextInt(6);
		Color color = pelletColors[index];
		FoodPellet pellet = new FoodPellet(color, width, height, gc);
		foodPellets.add(pellet);
	}

	// updates the board with current positions of snake and food pellets
	public void update() {
		initialize();

		for (FoodPellet pellet : foodPellets) {
			pellet.draw();
		}
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public boolean isCollision() {
		return false;
	}

	public void checkCollisions() {
		// TODO Auto-generated method stub
		
	}

}
