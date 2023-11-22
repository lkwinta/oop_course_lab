package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractWorldMap implements IWorldMap<IWorldElement<Vector2d>, Vector2d> {
    protected final MapVisualizer mapVisualizer;
    protected final Map<Vector2d, Animal> animalsMap;

    protected AbstractWorldMap(){
        animalsMap = new HashMap<>();
        mapVisualizer = new MapVisualizer(this);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return !animalsMap.containsKey(position);
    }

    @Override
    public boolean place(IWorldElement<Vector2d> object) {
        if(!canMoveTo(object.getPosition()))
            return false;

        if(!(object instanceof Animal animal)){
           return false;
        }

        animalsMap.put(animal.getPosition(), animal);

        return true;
    }

    @Override
    public void move(IWorldElement<Vector2d> object, MoveDirection direction) {
        if(!(object instanceof Animal animal))
            throw new IllegalArgumentException("You can't move object that is not an Animal'");

        Vector2d oldPosition = animal.getPosition();
        animal.move(direction, this);

        if(!animal.getPosition().equals(oldPosition)){
            animalsMap.remove(oldPosition);
            place(animal);
        }
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return objectAt(position) != null;
    }

    @Override
    public IWorldElement<Vector2d> objectAt(Vector2d position) {
        return animalsMap.get(position);
    }

    @Override
    public List<IWorldElement<Vector2d>> getElements() {
        return new ArrayList<>(animalsMap.values());
    }
}
