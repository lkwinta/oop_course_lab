package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Vector2dTest {

    @Test
    void precedes() {
        Vector2d vec1 = new Vector2d(0,1);

        assertTrue(vec1.precedes(new Vector2d(0, 1)));
        assertTrue(vec1.precedes(new Vector2d(1, 1)));
        assertTrue(vec1.precedes(new Vector2d(1, 2)));
        assertFalse(vec1.precedes(new Vector2d(0, 0)));
    }

    @Test
    void follows() {
        Vector2d vec1 = new Vector2d(0,1);

        assertTrue(vec1.follows(new Vector2d(0, 1)));
        assertTrue(vec1.follows(new Vector2d(0, 0)));
        assertTrue(vec1.follows(new Vector2d(0, -1)));
        assertFalse(vec1.follows(new Vector2d(1, 1)));
    }

    @Test
    void add() {
        Vector2d vec1 = new Vector2d(0,1);

        assertEquals(new Vector2d(1,3), vec1.add(new Vector2d(1, 2)));
        assertEquals(new Vector2d(-1,-1), vec1.add(new Vector2d(-1, -2)));
    }

    @Test
    void subtract() {
        Vector2d vec1 = new Vector2d(0,1);

        assertEquals(new Vector2d(-1,-1), vec1.subtract(new Vector2d(1, 2)));
        assertEquals(new Vector2d(1,3), vec1.subtract(new Vector2d(-1, -2)));
    }

    @Test
    void opposite() {
        Vector2d vec1 = new Vector2d(1,1);
        Vector2d vec2 = new Vector2d(-1,-1);

        assertEquals(vec2, vec1.opposite());
        assertEquals(vec1, vec2.opposite());
    }

    @Test
    void upperRight() {
        Vector2d vec1 = new Vector2d(2,5);

        assertEquals(new Vector2d(7, 5), vec1.upperRight(new Vector2d(7, 3)));
    }

    @Test
    void lowerLeft() {
        Vector2d vec1 = new Vector2d(2,5);

        assertEquals(new Vector2d(2, 3), vec1.lowerLeft(new Vector2d(7, 3)));
    }

    @Test
    void getX() {
        Vector2d vec1 = new Vector2d(2,1);

        assertEquals(2, vec1.getX());
    }

    @Test
    void getY() {
        Vector2d vec1 = new Vector2d(0,1);

        assertEquals(1, vec1.getY());
    }

    @Test
    void testToString() {
        Vector2d vec1 = new Vector2d(0,1);

        assertEquals("(0, 1)", vec1.toString());
    }

    @Test
    void testEquals() {
        Vector2d vec1 = new Vector2d(0,1);

        assertEquals(new Vector2d(0, 1), vec1);
        assertNotEquals(vec1, new Vector2d(0, 2));
        assertNotEquals(vec1, new Vector2d(2, 0));
    }
}