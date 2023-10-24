package agh.ics.oop;

import agh.ics.oop.model.*;

public class World {
    public static void main(String[] args){
        System.out.println("Start");
        run(OptionParser.parse(args));
        System.out.println("Stop");
    }

    public static void run(MoveDirection[] directions){
        for(MoveDirection direction : directions) {
            System.out.printf("Zwierzak %s%n", switch(direction){
                case FORWARD -> "idzie do przodu";
                case BACKWARD -> "idzie do tylu";
                case LEFT -> "skręca w lewo";
                case RIGHT -> "skręca w prawo";
            });
        }
    }
}