package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OptionParserTest {

    @Test
    void parseProperInput() {
        String[] args = {"l", "f", "b", "r", "l", "b", "r", "f"};
        MoveDirection[] directions = {
                MoveDirection.LEFT,
                MoveDirection.FORWARD,
                MoveDirection.BACKWARD,
                MoveDirection.RIGHT,
                MoveDirection.LEFT,
                MoveDirection.BACKWARD,
                MoveDirection.RIGHT,
                MoveDirection.FORWARD
        };

        assertArrayEquals(directions, OptionParser.parse(args));
    }

    @Test
    void parseDistortedInput() {
        String[] args = {"l", "c", "f", "b", "g", "a", "r", "l", "b", "r", "v", "f"};
        MoveDirection[] directions = {
                MoveDirection.LEFT,
                MoveDirection.FORWARD,
                MoveDirection.BACKWARD,
                MoveDirection.RIGHT,
                MoveDirection.LEFT,
                MoveDirection.BACKWARD,
                MoveDirection.RIGHT,
                MoveDirection.FORWARD
        };

        assertArrayEquals(directions, OptionParser.parse(args));
    }
}