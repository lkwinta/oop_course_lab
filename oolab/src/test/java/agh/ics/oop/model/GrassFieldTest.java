package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GrassFieldTest {
    @Test
    void canMoveTo() {
        GrassField grassField = new GrassField(10);

        try {
            grassField.place(new Animal(new Vector2d(2, 2)));
        } catch(PositionAlreadyOccupiedException ex){
            fail(ex.getMessage());
        }

        assertTrue(grassField.canMoveTo(new Vector2d(1, 2)));
        assertFalse(grassField.canMoveTo(new Vector2d(2, 2)));
    }

    @Test
    void place() {
        GrassField grassField = new GrassField(10);

            assertDoesNotThrow(
                    () -> grassField.place(new Animal(new Vector2d(2, 2))));
            assertThrows(PositionAlreadyOccupiedException.class,
                    () -> grassField.place(new Animal(new Vector2d(2, 2))));
    }

    @Test
    void move() {
        GrassField grassField = new GrassField(10);

        Animal animal1 = new Animal(new Vector2d(3, 3));
        Animal animal2 = new Animal(new Vector2d(3, 2));

        try {
            grassField.place(animal1);
            grassField.place(animal2);
        } catch (PositionAlreadyOccupiedException ex){
            fail(ex.getMessage());
        }

        grassField.move(animal1, MoveDirection.FORWARD);
        assertEquals(animal1, grassField.objectAt(new Vector2d(3, 4)));
        assert(grassField.objectAt(new Vector2d(3, 3)) == null ||
                grassField.objectAt(new Vector2d(3, 3)) instanceof Grass);

        grassField.move(animal2, MoveDirection.FORWARD);
        grassField.move(animal2, MoveDirection.FORWARD);
        assertEquals(animal2, grassField.objectAt(new Vector2d(3, 3)));
        assertEquals(animal1, grassField.objectAt(new Vector2d(3, 4)));
    }

    @Test
    void isOccupied() {
        GrassField grassField = new GrassField(10);

        try {
            grassField.place(new Animal(new Vector2d(2, 2)));
        } catch(PositionAlreadyOccupiedException ex){
            fail(ex.getMessage());
        }

        assert(!grassField.isOccupied(new Vector2d(1, 2)) ||
                grassField.objectAt(new Vector2d(1, 2)) instanceof Grass);
        assertTrue(grassField.isOccupied(new Vector2d(2, 2)));
    }

    @Test
    void objectAt() {
        GrassField grassField = new GrassField(10);
        Animal animal = new Animal(new Vector2d(2, 2));

        try {
            grassField.place(animal);
        } catch(PositionAlreadyOccupiedException ex){
            fail(ex.getMessage());
        }

        assert(grassField.objectAt(new Vector2d(1, 2)) == null ||
                grassField.objectAt(new Vector2d(1, 2)) instanceof Grass);
        assertEquals(animal, grassField.objectAt(new Vector2d(2, 2)));

        int grassCount = 0;
        for (IWorldElement<Vector2d> element : grassField.getElements()){
            if(element instanceof Grass)
                grassCount += 1;
        }

        assertEquals(10, grassCount);
    }

    @Test
    void testToString() {
        GrassField grassField = new GrassField(10);

        System.out.println(grassField);
        assertEquals(10,
                grassField
                        .toString()
                        .chars()
                        .filter(ch -> ch == '*')
                        .count());

        try {
            grassField.place(new Animal(new Vector2d(2, 2)));
            grassField.place(new Animal(new Vector2d(3, 2)));
        } catch(PositionAlreadyOccupiedException ex) {
            fail(ex.getMessage());
        }

        assertEquals(2,
                grassField
                        .toString()
                        .chars()
                        .filter(ch -> ch == '^')
                        .count());
    }

    @Test
    void testGetElements() {
        GrassField grassField = new GrassField(10);
        Animal animal1 = new Animal(new Vector2d(2, 2));
        Animal animal2 = new Animal(new Vector2d(3, 3));
        Animal animal3 = new Animal(new Vector2d(4, 4));

        try {
            grassField.place(animal1);
            grassField.place(animal2);
            grassField.place(animal3);
        } catch(PositionAlreadyOccupiedException ex) {
            fail(ex.getMessage());
        }

        List<IWorldElement<Vector2d>> elements = grassField.getElements();

        assertTrue(elements.contains(animal1));
        assertTrue(elements.contains(animal2));
        assertTrue(elements.contains(animal3));
        assertFalse(elements.contains(new Animal(new Vector2d(2, 2))));

        assertEquals(10,
                elements
                        .stream()
                        .filter((IWorldElement<Vector2d> element) -> element instanceof Grass)
                        .count());
    }
}