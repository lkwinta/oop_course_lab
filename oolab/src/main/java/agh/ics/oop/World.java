package agh.ics.oop;

import agh.ics.oop.model.*;
import agh.ics.oop.model.util.ConsoleMapDisplay;
import javafx.application.Application;

import java.util.ArrayList;
import java.util.List;

public class World {
    public static void main(String[] args){
        try {
            List<Vector2d> positions = List.of(
                    new Vector2d(0,0),
                    new Vector2d(2,2),
                    new Vector2d(3, 4)
            );


            GrassField map2 = new GrassField(10);

            ConsoleMapDisplay mapDisplay = new ConsoleMapDisplay();

            //map2.addListener(mapDisplay);

            /*List<Simulation> simulationsList = List.of(
                            new Simulation(positions, OptionsParser.parse("f l f r f r f l b l".split(" ")), map1),
                            new Simulation(positions, OptionsParser.parse("f l f r f r f l b l".split(" ")), map2)
                    );*/

            List<Simulation> simulations = new ArrayList<>();
            for (int i = 0; i < 100000; i++){
                RectangularMap map1 = new RectangularMap(5, 5);
                //map1.addListener(mapDisplay);
                simulations.add(new Simulation(positions,OptionsParser.parse("f l f r f r f l b l".split(" ")), map1));
            }

            SimulationEngine engine = new SimulationEngine(simulations);

            /*
             * Using thread pool is better than using low-level threads because spawning a lot of threads is heavy task
             * and still they can't all run at the same time, so queuing tasks into thread pool is faster when creating
             * multiple small tasks than creating bare threads
            */
            engine.runAsyncInThreadPool();
            engine.awaitSimulationsEnd();

            System.out.println("System stopped working! ");
        } catch (IllegalArgumentException ex){
            System.out.println("Passed illegal argument, please enter correct data: " + ex.getMessage());
        } catch (InterruptedException ex){
            System.out.println("Simulation thread, was interrupted, exiting...");
        }
    }
}