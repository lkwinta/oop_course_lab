package agh.ics.oop;

import agh.ics.oop.model.*;

import java.util.List;

public class World {
    public static void main(String[] args){
        List<MoveDirection> directions = OptionsParser.parse(args);
        List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3,4));
        Simulation simulation = new Simulation(positions, directions);
        simulation.run();
    }

    // Not necessary anymore
    /*
    public static void run(List<MoveDirection> directions){
        for(MoveDirection direction : directions) {
            System.out.printf("Zwierzak %s%n", switch(direction){
                case FORWARD -> "idzie do przodu";
                case BACKWARD -> "idzie do tylu";
                case LEFT -> "skreca w lewo";
                case RIGHT -> "skreca w prawo";
            });
        }
    }*/
}