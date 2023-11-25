package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import java.util.List;

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

        assertThrows(PositionAlreadyOccupiedException.class,
                () -> rectMap.place(new Animal(new Vector2d(5,5))));

        assertDoesNotThrow(
                () -> rectMap.place(new Animal(new Vector2d(3, 3))));
        assertThrows(PositionAlreadyOccupiedException.class,
                () -> rectMap.place(new Animal(new Vector2d(3,3))));

        assertTrue(rectMap.isOccupied(new Vector2d(3, 3)));
    }

    @Test
    void move() {
        RectangularMap rectMap = new RectangularMap(5, 5);

        Animal animal = new Animal(new Vector2d(3, 3));
        Animal animal2 = new Animal(new Vector2d(3, 2));

        try {
            rectMap.place(animal);
            rectMap.place(animal2);
        } catch(PositionAlreadyOccupiedException ex) {
            fail(ex.getMessage());
        }

        rectMap.move(animal2, MoveDirection.FORWARD);
        assertEquals(animal, rectMap.objectAt(new Vector2d(3, 3)));
        assertEquals(animal2, rectMap.objectAt(new Vector2d(3, 2)));

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

        try {
            rectMap.place(new Animal(new Vector2d(3, 3)));
        } catch (PositionAlreadyOccupiedException ex) {
            fail(ex.getMessage());
        }

        assertTrue(rectMap.isOccupied(new Vector2d(3, 3)));
        assertFalse(rectMap.isOccupied(new Vector2d(5, 5)));
    }

    @Test
    void objectAt() {
        RectangularMap rectMap = new RectangularMap(5, 5);

        Animal animal2 = new Animal(new Vector2d(3, 3));

        try {
            rectMap.place(animal2);
        } catch (PositionAlreadyOccupiedException ex) {
            fail(ex.getMessage());
        }

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

        try {
            rectMap.place(new Animal(new Vector2d(2, 3)));
        } catch(PositionAlreadyOccupiedException ex){
            fail(ex.getMessage());
        }

        assertEquals(expectedOutput, rectMap.toString());
    }

    @Test
    void testGetElements() {
        RectangularMap rectMap = new RectangularMap(5, 5);

        Animal animal2 = new Animal(new Vector2d(3, 3));
        Animal animal3 = new Animal(new Vector2d(4,4));

        try {
            rectMap.place(animal2);
            rectMap.place(animal3);
        } catch(PositionAlreadyOccupiedException ex) {
            fail(ex.getMessage());
        }

        List<IWorldElement<Vector2d>> elements = rectMap.getElements();

        assertTrue(elements.contains(animal2));
        assertTrue(elements.contains(animal3));

        assertEquals(2, elements.size());
    }
}