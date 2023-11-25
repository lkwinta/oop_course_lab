package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OptionsParserTest {

    @Test
    void parseProperInput() {
        String[] args = {"l", "f", "b", "r", "l", "b", "r", "f"};
        List<MoveDirection> directions = List.of(
                MoveDirection.LEFT,
                MoveDirection.FORWARD,
                MoveDirection.BACKWARD,
                MoveDirection.RIGHT,
                MoveDirection.LEFT,
                MoveDirection.BACKWARD,
                MoveDirection.RIGHT,
                MoveDirection.FORWARD
        );

        assertDoesNotThrow(() -> OptionsParser.parse(args));
        assertEquals(directions, OptionsParser.parse(args));
    }

    @Test
    void parseDistortedInput() {
        String[] args = {"l", "c", "f", "b", "g", "a", "r", "l", "b", "r", "v", "f"};

        assertThrows(IllegalArgumentException.class, () -> OptionsParser.parse(args));
    }
}