package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class Snake {
    private final List<Point> body;
    private Direction direction;
    private int x;
    private int y;
    private Color color;


    public Snake() {
        body = new ArrayList<>();
        body.add(new Point(10, 10));
        direction = Direction.RIGHT;
    }
    public Snake(int x, int y, Color color) {
        this.body = new ArrayList<>();
        body.add(new Point(x, y));
		this.x = x;
        this.y = y;
        this.color = color;
        direction = Direction.RIGHT;
    }
    public Point getHead() {
        return body.get(0);
    }
    public void draw(GraphicsContext gc) {
        gc.setFill(color); // Set the fill color for the snake
        for (Point point : body) {
            gc.fillRect(point.getX(), point.getY(), 10, 10); // Draw each body segment of the snake
        }
    }


    public List<Point> getBody() {
        return body;
    }

    public void move() {
    
        Point head = body.get(0);
        Point newHead = new Point(head.getX() + direction.getX(), head.getY() + direction.getY());
        body.add(0, newHead); 
        body.remove(body.size() - 1);
    }

    public void grow() {
        Point head = body.get(0);
        Point newHead = new Point(head.getX() + direction.getX(), head.getY() + direction.getY());
        body.add(0, newHead);
    }

    public void setDirection(Direction direction) {
      
        if (this.direction == Direction.UP && direction == Direction.DOWN ||
                this.direction == Direction.DOWN && direction == Direction.UP ||
                this.direction == Direction.LEFT && direction == Direction.RIGHT ||
                this.direction == Direction.RIGHT && direction == Direction.LEFT) {
            return;
        }
        this.direction = direction;
    }


    public enum Direction {
        UP(0, -1),
        DOWN(0, 1),
        LEFT(-1, 0),
        RIGHT(1, 0);

        private final int x;
        private final int y;

        Direction(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

	public void updatePosition() {
		// TODO Auto-generated method stub
		
	}

	public void render(GraphicsContext gc) {
		// TODO Auto-generated method stub
		
	}
}
