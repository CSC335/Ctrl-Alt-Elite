package model;

/**
 * Represents a square on the board of a game of Snake
 *
 * @author Vannela Chatla
 */

public class Point {
    private int x;
    private int y;
    
    /**
     * Create a new Point object
     *
     * @param x The x-coordinate of the specified square
     * @param y The y-coordinate of the specified square
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    /**
     * Get the x-coordinate of the current square
     *
     * @return An integer representing the x-coordinate
     */
    public int getX() {
        return x;
    }
    
    /**
     * Get the y-coordinate of the current square
     *
     * @return An integer representing the y-coordinate
     */
    public int getY() {
        return y;
    }
    
    public int getTileX() {
    	return Math.floorDiv(x, 20);
    }
    
    public int getTileY() {
    	return Math.floorDiv(y, 20);
    }
    
    /**
     * Set the x-coordinate to a new square
     *
     * @param x An integer representing the new x-coordinate
     */
    public void setX(int x) {
        this.x = x;
    }
    
    /**
     * Set the y-coordinate to a new square
     *
     * @param y An integer representing the new y-coordinate
     */
    public void setY(int y) {
        this.y = y;
    }
    
    /**
     * Determine if this Point object is equal to another Point object
     *
     * @param obj Another object to be compared with this Point object
     * @return True if the other object is both a Point object and has the same x- and y-coordinates, False otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Point point = (Point) obj;
        return x == point.x && y == point.y;
    }
    
    /**
     * Calculate a hash code for this Point object
     *
     * @return An integer that represents the hash code
     */
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + x;
        result = 31 * result + y;
        return result;
    }
}
