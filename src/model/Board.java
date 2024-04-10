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
		initialize();
	}

	public void initialize() {
		gc.setFill(backgroundColor);
		gc.fillRect(0, 0, width, height);
		
		// spawn 100 random food pellets - adjust number if not enough/too much
		for (int i = 0; i<100; i++) {
			spawnFoodPellet();
		}
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
		gc.setFill(backgroundColor);
		gc.fillRect(0, 0, width, height);
		
		for (FoodPellet pellet : foodPellets) {
			pellet.detectCollision();
			if (pellet.isEaten())
				foodPellets.remove(pellet);
			else
				pellet.draw();
		}
		
		// snake.draw();
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	// determine if snake has collided with walls
	public boolean isCollision() {
		return false;
	}

}




















