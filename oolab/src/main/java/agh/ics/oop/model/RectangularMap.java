package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.Map;
import java.util.HashMap;

public class RectangularMap implements IWorldMap, IMoveValidator {
    private final Map<Vector2d, Animal> animals;
    private final MapVisualizer mapVisualizer;
    private final Vector2d MAP_TOP_RIGHT;
    private final Vector2d MAP_BOTTOM_LEFT;

    public RectangularMap(int width, int height){
        animals = new HashMap<>();
        mapVisualizer = new MapVisualizer(this);

        MAP_TOP_RIGHT = new Vector2d(width - 1, height - 1);
        MAP_BOTTOM_LEFT = new Vector2d(0, 0);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.follows(MAP_BOTTOM_LEFT) && position.precedes(MAP_TOP_RIGHT);
    }

    @Override
    public boolean place(Animal animal) {
        if(isOccupied(animal.getPosition()))
            return false;

        if(!canMoveTo(animal.getPosition()))
            return false;

        animals.put(animal.getPosition(), animal);
        return true;
    }

    @Override
    public void move(Animal animal, MoveDirection direction) {
        if (!animals.containsValue(animal))
            return;

        Vector2d oldPosition = animal.getPosition();
        animal.move(direction, this);

        if(!animal.getPosition().equals(oldPosition)){
            animals.remove(oldPosition);
            place(animal);
        }
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return animals.containsKey(position);
    }

    @Override
    public Animal objectAt(Vector2d position) {
        return animals.get(position);
    }

    @Override
    public String toString() {
        return mapVisualizer.draw(MAP_BOTTOM_LEFT, MAP_TOP_RIGHT);
    }
}
