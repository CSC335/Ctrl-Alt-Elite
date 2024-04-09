package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Snake {
    public static class Direction {
        public static final int UP = 0;
        public static final int DOWN = 1;
        public static final int LEFT = 2;
        public static final int RIGHT = 3;

        private Direction() {}

        public static int getX(int direction) {
            if (direction == LEFT) {
                return -1;
            } else if (direction == RIGHT) {
                return 1;
            }
            return 0;
        }

        public static int getY(int direction) {
            if (direction == UP) {
                return -1;
            } else if (direction == DOWN) {
                return 1;
            }
            return 0;
        }
    }

    private List<Point> body;
    private Color color;
    private int direction;

    public Snake(int initialLength, Color color) {
        this.body = new ArrayList<>();
        this.color = color;
        this.direction = Direction.RIGHT; 

  
        for (int i = 0; i < initialLength; i++) {
            body.add(new Point(i, 0));
        }
    }

    public void move() {
        
        Point head = body.get(0);
        Point newHead = new Point();
        body.add(0, newHead); 
        body.remove(body.size() - 1); 
    }

    public void grow() {

        Point tail = body.get(body.size() - 1);
        Point newTail = new Point(tail.getX(), tail.getY());
        body.add(newTail); 
    }


    public List<Point> getBody() {
        return body;
    }

    public void setBody(List<Point> body) {
        this.body = body;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

	public void updatePosition() {
		// TODO Auto-generated method stub
		
	}

	public void render(GraphicsContext gc) {
		// TODO Auto-generated method stub
		
	}
}
