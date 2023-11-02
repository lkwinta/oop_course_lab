package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {

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

        animal.move(MoveDirection.BACKWARD);

        assertEquals(animal.getPosition(), new Vector2d(2, 1));
        assertEquals(animal.getOrientation(), MapDirection.NORTH);

        animal.move(MoveDirection.RIGHT);
        assertEquals(animal.getPosition(), new Vector2d(2, 1));
        assertEquals(animal.getOrientation(), MapDirection.EAST);

        animal.move(MoveDirection.FORWARD);
        assertEquals(animal.getPosition(), new Vector2d(3, 1));
        assertEquals(animal.getOrientation(), MapDirection.EAST);

        animal.move(MoveDirection.LEFT);
        assertEquals(animal.getPosition(), new Vector2d(3, 1));
        assertEquals(animal.getOrientation(), MapDirection.NORTH);
    }

    @Test
    void getOrientation() {
        Animal animal = new Animal();

        assertEquals(animal.getOrientation(), MapDirection.NORTH);
        animal.move(MoveDirection.RIGHT);

        assertEquals(animal.getOrientation(), MapDirection.EAST);
        animal.move(MoveDirection.RIGHT);

        assertEquals(animal.getOrientation(), MapDirection.SOUTH);
        animal.move(MoveDirection.RIGHT);

        assertEquals(animal.getOrientation(), MapDirection.WEST);
    }

    @Test
    void getPosition() {
        Animal animal = new Animal();

        assertEquals(animal.getPosition(), new Vector2d(2, 2));
        animal.move(MoveDirection.FORWARD);

        assertEquals(animal.getPosition(), new Vector2d(2, 3));
        animal.move(MoveDirection.BACKWARD);

        assertEquals(animal.getPosition(), new Vector2d(2, 2));
    }

    @Test
    void testToString() {
        Animal animal = new Animal(new Vector2d(1, 3));

        assertEquals("position: (1, 3), orientation: Polnoc", animal.toString());
    }
}