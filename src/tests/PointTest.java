package tests;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import model.Point;

public class PointTest {

    @Test
    public void testEquals() {
        Point point1 = new Point(5, 5);
        Point point2 = new Point(5, 5);
        Point point3 = new Point(10, 10);

        assertTrue(point1.equals(point2));
        assertFalse(point1.equals(point3));
    }

    @Test
    public void testHashCode() {
        Point point1 = new Point(5, 5);
        Point point2 = new Point(5, 5);
        Point point3 = new Point(10, 10);

        assertEquals(point1.hashCode(), point2.hashCode());
        assertNotEquals(point1.hashCode(), point3.hashCode());
    }
}
