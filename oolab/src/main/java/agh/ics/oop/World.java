package agh.ics.oop;

import agh.ics.oop.model.*;
import agh.ics.oop.model.util.ConsoleMapDisplay;

import java.util.List;

public class World {
    public static void main(String[] args){
        try {
            List<MoveDirection> directions = OptionsParser.parse(args);
            List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3, 4));

            RectangularMap map = new RectangularMap(5, 5);

            ConsoleMapDisplay mapDisplay = new ConsoleMapDisplay();
            map.addListener(mapDisplay);

            Simulation simulation = new Simulation(positions, directions, map);
            simulation.run();
        } catch (IllegalArgumentException ex){
            System.out.println("Passed illegal argument, please enter correct data: " + ex.getMessage());
        }
    }
}