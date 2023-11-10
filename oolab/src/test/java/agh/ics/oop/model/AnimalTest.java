package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {
    private static class MoveValidator implements IMoveValidator {
        @Override
        public boolean canMoveTo(Vector2d position) {
            return position.follows(new Vector2d(0, 0)) && position.precedes(new Vector2d(4, 4));
        }
    }

    private final MoveValidator validator = new MoveValidator();

    @Test
    void isAt() {
        Animal animalDefault = new Animal();
        Animal animalCustom = new Animal(new Vector2d(1, 3));

        assertTrue(animalDefault.isAt(new Vector2d(2, 2)));
        assertFalse(animalDefault.isAt(new Vector2d(1, 1)));

        assertTrue(animalCustom.isAt(new Vector2d(1, 3)));
        assertFalse(animalCustom.isAt(new Vector2d(3, 1)));
    }

    @Test
    void move() {
        Animal animal = new Animal();

        animal.move(MoveDirection.BACKWARD, validator);

        assertEquals(animal.getPosition(), new Vector2d(2, 1));
        assertEquals(animal.getOrientation(), MapDirection.NORTH);

        animal.move(MoveDirection.RIGHT, validator);
        assertEquals(animal.getPosition(), new Vector2d(2, 1));
        assertEquals(animal.getOrientation(), MapDirection.EAST);

        animal.move(MoveDirection.FORWARD, validator);
        assertEquals(animal.getPosition(), new Vector2d(3, 1));
        assertEquals(animal.getOrientation(), MapDirection.EAST);

        animal.move(MoveDirection.LEFT, validator);
        assertEquals(animal.getPosition(), new Vector2d(3, 1));
        assertEquals(animal.getOrientation(), MapDirection.NORTH);
    }

    @Test
    void getOrientation() {
        Animal animal = new Animal();

        assertEquals(animal.getOrientation(), MapDirection.NORTH);
        animal.move(MoveDirection.RIGHT, validator);

        assertEquals(animal.getOrientation(), MapDirection.EAST);
        animal.move(MoveDirection.RIGHT, validator);

        assertEquals(animal.getOrientation(), MapDirection.SOUTH);
        animal.move(MoveDirection.RIGHT, validator);

        assertEquals(animal.getOrientation(), MapDirection.WEST);
    }

    @Test
    void getPosition() {
        Animal animal = new Animal();

        assertEquals(animal.getPosition(), new Vector2d(2, 2));
        animal.move(MoveDirection.FORWARD, validator);

        assertEquals(animal.getPosition(), new Vector2d(2, 3));
        animal.move(MoveDirection.BACKWARD, validator);

        assertEquals(animal.getPosition(), new Vector2d(2, 2));
    }

    @Test
    void testToString() {
        Animal animal = new Animal(new Vector2d(1, 3));

        assertEquals("^", animal.toString());
        animal.move(MoveDirection.RIGHT, validator);
        assertEquals(">", animal.toString());
        animal.move(MoveDirection.RIGHT, validator);
        assertEquals("v", animal.toString());
        animal.move(MoveDirection.RIGHT, validator);
        assertEquals("<", animal.toString());
    }
}