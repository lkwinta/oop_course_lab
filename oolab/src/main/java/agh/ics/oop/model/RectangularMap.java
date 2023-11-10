package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.Map;
import java.util.HashMap;

public class RectangularMap implements IWorldMap<Animal, Vector2d> {
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
    public boolean place(Animal object) {
        if(isOccupied(object.getPosition()))
            return false;

        if(!canMoveTo(object.getPosition()))
            return false;

        animals.put(object.getPosition(), object);
        return true;
    }

    @Override
    public void move(Animal object, MoveDirection direction) {
        if (!animals.containsValue(object))
            return;

        Vector2d oldPosition = object.getPosition();
        object.move(direction, this);

        if(!object.getPosition().equals(oldPosition)){
            animals.remove(oldPosition);
            place(object);
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
