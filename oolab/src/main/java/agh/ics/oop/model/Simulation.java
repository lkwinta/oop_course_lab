package agh.ics.oop.model;

import java.util.ArrayList;
import java.util.List;

public class Simulation {

    /** using array list because we iterate over the animals list
     * so continuous memory gives us extra performance compared to
     * LinkedList, also we are not adding more elements at runtime,
     * so we don't benefit from LinkedList fast append
     * */
    private final List<Animal> animalsList = new ArrayList<>();
    private final List<MoveDirection> moveDirections;

    public Simulation(List<Vector2d> startingPositions, List<MoveDirection> moveDirections){
        startingPositions.forEach((Vector2d position) -> animalsList.add(new Animal(position)));
        this.moveDirections = new ArrayList<>(moveDirections);
    }

    public void run(){
        int animalIndex = 0;

        for(MoveDirection direction : moveDirections){
            animalsList.get(animalIndex).move(direction);
            System.out.printf("Zwierze %d : %s \n", animalIndex, animalsList.get(animalIndex));

            animalIndex = ++animalIndex % animalsList.size();
        }
    }

    public Animal getAnimal(int index){
        return animalsList.get(index);
    }
}
