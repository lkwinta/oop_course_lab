package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

import java.util.List;
import java.util.Objects;

import static java.util.Arrays.stream;

public class OptionsParser {

    /**
     * Method parsing motion arguments into {@linkplain agh.ics.oop.model.MoveDirection MoveDirection} enum,
     * allowed move directions are: {f, b, l, r}, invalid inputs are ignored
     *
     * @param args String[] array of program arguments
     *
     * @return MoveDirection[] array of move directions in enum format
     */
    public static List<MoveDirection> parse(String[] args){
        /*Stream already used here*/
        return stream(args)
                .map(OptionsParser::mapArgToDirection)
                .filter(Objects::nonNull)
                .toList();
    }

    private static MoveDirection mapArgToDirection(String arg) {
        return switch(arg) {
            case "f" -> MoveDirection.FORWARD;
            case "b" -> MoveDirection.BACKWARD;
            case "l" -> MoveDirection.LEFT;
            case "r" -> MoveDirection.RIGHT;
            default -> throw new IllegalArgumentException(arg + " is not legal move specification");
        };
    }
}
