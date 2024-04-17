package tests;

import model.FoodPellet;
import model.Tile;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


public class FoodPelletTest {

    private FoodPellet foodPellet;
    private final int boardWidth = 30;
    private final int boardHeight = 20;

    @Test
    public void testFoodPelletCreation() {
        assertNotNull(foodPellet);
        assertNotNull(foodPellet.getCurrentTile());
    }

    @Test
    public void testFoodPelletSpawn() {
        Tile currentTile = foodPellet.getCurrentTile();
        assertTrue(currentTile.getTileX() >= 0 && currentTile.getTileX() < boardWidth);
        assertTrue(currentTile.getTileY() >= 0 && currentTile.getTileY() < boardHeight);
    }

    @Test
    public void testFoodPelletRespawn() {
        Tile oldTile = foodPellet.getCurrentTile();
        foodPellet.respawn(boardWidth, boardHeight);
        Tile newTile = foodPellet.getCurrentTile();

        assertFalse(foodPellet.isEaten());
        assertNotEquals(oldTile, newTile); // This might fail due to random chance, consider mocking Random
    }

    @Test
    public void testFoodPelletDetectCollision() {
        Tile head = foodPellet.getCurrentTile();
        assertTrue(foodPellet.detectCollision(head)); // Should detect collision since it's the same tile
        assertTrue(foodPellet.isEaten());
    }

    @Test
    public void testFoodPelletNoCollision() {
        Tile head = new Tile(foodPellet.getCurrentTile().getTileX() + 1, foodPellet.getCurrentTile().getTileY() + 1); // Different location
        assertFalse(foodPellet.detectCollision(head));
        assertFalse(foodPellet.isEaten());
    }

}
