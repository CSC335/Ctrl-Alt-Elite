package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SnakeBoard {
    private int width;
    private int height;
    private Color backgroundColor;
    private Snake snake;
    private List<FoodPellet> foodPellets;
    private GraphicsContext gc;
    private Random random;

    public SnakeBoard(int width, int height, Color backgroundColor, GraphicsContext gc) {
        this.width = width;
        this.height = height;
        this.backgroundColor = backgroundColor;
        this.snake = new Snake(1, Color.GREEN); 
        this.foodPellets = new ArrayList<>();
        this.gc = gc;
        this.random = new Random();
    }

    public void initialize() {
   
        foodPellets.clear();

        
        for (int i = 0; i < 5; i++) {
            spawnFoodPellet();
        }
    }

    public void update() {

        snake.move();

  
        checkCollisionsWithFood();

    }

    private void spawnFoodPellet() {
        int x = random.nextInt(width);
        int y = random.nextInt(height);
        Color color = getRandomColor();
        FoodPellet pellet = new FoodPellet(color, x, y, gc);
        foodPellets.add(pellet);
        pellet.draw();
    }

    private Color getRandomColor() {
     
        Color[] colors = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE, Color.PINK, Color.PURPLE};
        return colors[random.nextInt(colors.length)];
    }

    private void checkCollisionsWithFood() {
      
        Point snakeHead = snake.getBody().get(0);
        for (FoodPellet pellet : foodPellets) {
            if (pellet.detectCollision(snakeHead)) {

                snake.grow();
   
                foodPellets.remove(pellet);
   
                spawnFoodPellet();

                break;
            }
        }
    }


    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public Snake getSnake() {
        return snake;
    }

    public void setSnake(Snake snake) {
        this.snake = snake;
    }

    public List<FoodPellet> getFoodPellets() {
        return foodPellets;
    }

    public void setFoodPellets(List<FoodPellet> foodPellets) {
        this.foodPellets = foodPellets;
    }

    public GraphicsContext getGraphicsContext() {
        return gc;
    }

    public void setGraphicsContext(GraphicsContext gc) {
        this.gc = gc;
    }

    public Random getRandom() {
        return random;
    }

    public void setRandom(Random random) {
        this.random = random;
    }

}
