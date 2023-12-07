package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoveDirectionTest {

    @Test
    void testToString() {
        assertEquals("Forward", MoveDirection.FORWARD.toString());
        assertEquals("Backward", MoveDirection.BACKWARD.toString());
        assertEquals("Left", MoveDirection.LEFT.toString());
        assertEquals("Right", MoveDirection.RIGHT.toString());
    }
}