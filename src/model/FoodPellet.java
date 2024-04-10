package model;
import java.util.Random;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
public class FoodPellet {
	private int SIZE = 2; // width & height of pellet - circle shape
	private Color color;
	private double x;
	private double y;
	private boolean isEaten = false;
	private GraphicsContext gc;
	public FoodPellet(Color color, int boardWidth, int boardHeight, GraphicsContext gc) {
		this.color = color;
		this.gc = gc;
		spawn(boardWidth, boardHeight);
	}
	public void spawn(int boardWidth, int boardHeight) {
		Random random = new Random();
		x = random.nextDouble(boardWidth);
		y = random.nextDouble(boardHeight);
		draw();
	}
	
	public void draw() {
		gc.setStroke(color);
		gc.fillOval(x, y, SIZE, SIZE);
	}
	// assuming board background color is white for now
	public void despawn() {
		gc.setFill(Color.WHITE);
		gc.fillOval(x, y, SIZE, SIZE);
		isEaten = true;
	}
	// uses the coordinates of the snakes head to determine if
	// pellet is eaten
	public boolean detectCollision() {
		// if collision: isEaten = true, return true
		// else: return false
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


