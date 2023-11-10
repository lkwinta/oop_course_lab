package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

import java.util.List;
import java.util.ArrayList;

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
        /* Create list for parsed option*/
        List<MoveDirection> directionsList = new ArrayList<>();

        /* Parse options ignoring invalid input*/
        for (String arg : args){
            switch(arg) {
                case "f" -> directionsList.add(MoveDirection.FORWARD);
                case "b" -> directionsList.add(MoveDirection.BACKWARD);
                case "l" -> directionsList.add(MoveDirection.LEFT);
                case "r" -> directionsList.add(MoveDirection.RIGHT);
                // no default statement -> ignore invalid input
            }
        }

        return directionsList;
    }
}
