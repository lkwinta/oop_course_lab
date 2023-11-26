package agh.ics.oop.model;

import java.util.ArrayList;
import java.util.List;

public class Simulation {
    /** using array list because we iterate over the animals list
     * so continuous memory gives us extra performance compared to
     * LinkedList, also we are not adding more elements at runtime,
     * so we don't benefit from LinkedList fast append
     * */
    private final List<Animal> animalsList;
    private final List<MoveDirection> moveDirections;
    private final IWorldMap<IWorldElement<Vector2d>, Vector2d> worldMap;

    public Simulation(List<Vector2d> startingPositions, List<MoveDirection> moveDirections, IWorldMap<IWorldElement<Vector2d>, Vector2d> worldMap){
        this.moveDirections = new ArrayList<>(moveDirections);
        this.worldMap = worldMap;

        animalsList = new ArrayList<>(startingPositions.size());
        for(Vector2d position : startingPositions) {
            Animal animal = new Animal(position);

            try {
                worldMap.place(animal);
                animalsList.add(animal);
            } catch (PositionAlreadyOccupiedException ex){
                System.out.println("Tried to place animal on occupied position");
            }

        }
    }

    public void run(){
        int animalIndex = 0;

        for(MoveDirection direction : moveDirections){
            worldMap.move(animalsList.get(animalIndex), direction);
            animalIndex = ++animalIndex % animalsList.size();
        }
    }

    public Animal getAnimal(int index){
        return animalsList.get(index);
    }
}
