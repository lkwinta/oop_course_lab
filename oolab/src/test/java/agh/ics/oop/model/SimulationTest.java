package agh.ics.oop.model;

import agh.ics.oop.OptionsParser;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SimulationTest {
    @Test
    void runOrientation() {
        Simulation simulation = new Simulation(List.of(new Vector2d(2, 2)),
                OptionsParser.parse("f l f r r l b l".split(" ")), new RectangularMap(5, 5));
        simulation.run();

        assertEquals(MapDirection.WEST, simulation.getAnimal(0).getOrientation());
    }

    @Test
    void runPosition() {
        Simulation simulation = new Simulation(List.of(new Vector2d(2, 2)),
                OptionsParser.parse("f l f r f r f l b l".split(" ")), new RectangularMap(5, 5));
        simulation.run();

        assertEquals(new Vector2d(2, 3), simulation.getAnimal(0).getPosition());
    }

    @Test
    void runMapBoarder()
    {
        Simulation simulation = new Simulation(List.of(new Vector2d(2, 2)),
                OptionsParser.parse("f f f f".split(" ")), new RectangularMap(5, 5));
        simulation.run();

        assertEquals(new Vector2d(2, 4), simulation.getAnimal(0).getPosition());
    }

    @Test
    void runMultipleAnimals(){
        Simulation simulation = new Simulation(List.of(
                new Vector2d(2, 2),
                new Vector2d(1, 1),
                new Vector2d(0, 0)),
                OptionsParser.parse("f f f r r r f f f l l l b b b l l l".split(" ")),
                new RectangularMap(5, 5));

        simulation.run();

        assertEquals(new Vector2d(3, 2), simulation.getAnimal(0).getPosition());
        assertEquals(MapDirection.WEST, simulation.getAnimal(0).getOrientation());

        assertEquals(new Vector2d(2, 1), simulation.getAnimal(1).getPosition());
        assertEquals(MapDirection.WEST, simulation.getAnimal(1).getOrientation());

        assertEquals(new Vector2d(1, 0), simulation.getAnimal(2).getPosition());
        assertEquals(MapDirection.WEST, simulation.getAnimal(2).getOrientation());
    }

    @Test
    void runMultipleAnimals_Overlapping(){
        Simulation simulation = new Simulation(List.of(
                new Vector2d(2, 2),
                new Vector2d(1, 1),
                new Vector2d(0, 0),
                new Vector2d(0, 0)),
                OptionsParser.parse("f f f r r r f f f l l l b b b l l l".split(" ")),
                new RectangularMap(5, 5));

        simulation.run();

        assertEquals(new Vector2d(3, 2), simulation.getAnimal(0).getPosition());
        assertEquals(MapDirection.WEST, simulation.getAnimal(0).getOrientation());

        assertEquals(new Vector2d(2, 1), simulation.getAnimal(1).getPosition());
        assertEquals(MapDirection.WEST, simulation.getAnimal(1).getOrientation());

        assertEquals(new Vector2d(1, 0), simulation.getAnimal(2).getPosition());
        assertEquals(MapDirection.WEST, simulation.getAnimal(2).getOrientation());
    }
}