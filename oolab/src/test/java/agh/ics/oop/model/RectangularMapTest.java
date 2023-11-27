package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RectangularMapTest {
    @Test
    void canMoveTo() {
        RectangularMap rectMap = new RectangularMap(5, 10);

        assertTrue(rectMap.canMoveTo(new Vector2d(4, 4)));

        assertFalse(rectMap.canMoveTo(new Vector2d(5, 4)));
        assertFalse(rectMap.canMoveTo(new Vector2d(4, 10)));

        assertTrue(rectMap.canMoveTo(new Vector2d(0, 0)));

        assertFalse(rectMap.canMoveTo(new Vector2d(-1, 0)));
        assertFalse(rectMap.canMoveTo(new Vector2d(0, -1)));
    }

    @Test
    void place() {
        RectangularMap rectMap = new RectangularMap(5, 5);

        assertFalse(rectMap.place(new Animal(new Vector2d(5,5))));

        assertTrue(rectMap.place(new Animal(new Vector2d(3, 3))));
        assertFalse(rectMap.place(new Animal(new Vector2d(3,3))));

        assertTrue(rectMap.isOccupied(new Vector2d(3, 3)));
    }

    @Test
    void move() {
        RectangularMap rectMap = new RectangularMap(5, 5);

        Animal animal = new Animal(new Vector2d(3, 3));

        rectMap.place(animal);

        rectMap.move(animal, MoveDirection.FORWARD);
        assertEquals(animal, rectMap.objectAt(new Vector2d(3, 4)));
        assertNull(rectMap.objectAt(new Vector2d(3, 3)));

        rectMap.move(animal, MoveDirection.FORWARD);
        assertEquals(animal, rectMap.objectAt(new Vector2d(3, 4)));
        assertNull(rectMap.objectAt(new Vector2d(3, 5)));
    }

    @Test
    void isOccupied() {
        RectangularMap rectMap = new RectangularMap(5, 5);

        rectMap.place(new Animal(new Vector2d(5,5)));
        rectMap.place(new Animal(new Vector2d(3, 3)));

        assertTrue(rectMap.isOccupied(new Vector2d(3, 3)));
        assertFalse(rectMap.isOccupied(new Vector2d(5, 5)));
    }

    @Test
    void objectAt() {
        RectangularMap rectMap = new RectangularMap(5, 5);

        Animal animal1 = new Animal(new Vector2d(5,5));
        Animal animal2 = new Animal(new Vector2d(3, 3));

        rectMap.place(animal1);
        rectMap.place(animal2);

        assertNull(rectMap.objectAt(new Vector2d(5,5)));

        /* Animal doesn't have overloaded equals operator, so using default reference comparison*/
        assertEquals(animal2, rectMap.objectAt(new Vector2d(3, 3)));
    }

    @Test
    void testToString() {
        String expectedOutput =
                " y\\x  0 1 2 3 4" + System.lineSeparator() +
                "  5: -----------" + System.lineSeparator() +
                "  4: | | | | | |" + System.lineSeparator() +
                "  3: | | |^| | |" + System.lineSeparator() +
                "  2: | | | | | |" + System.lineSeparator() +
                "  1: | | | | | |" + System.lineSeparator() +
                "  0: | | | | | |" + System.lineSeparator() +
                " -1: -----------" + System.lineSeparator();

        RectangularMap rectMap = new RectangularMap(5, 5);
        rectMap.place(new Animal(new Vector2d(2, 3)));

        assertEquals(expectedOutput, rectMap.toString());
    }
}