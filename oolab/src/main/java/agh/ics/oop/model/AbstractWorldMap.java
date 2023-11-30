package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.*;

public abstract class AbstractWorldMap implements IWorldMap<IWorldElement<Vector2d>, Vector2d> {
    protected final MapVisualizer mapVisualizer;
    protected final Map<Vector2d, Animal> animalsMap;
    private final List<IMapChangeListener> listeners;

    protected AbstractWorldMap(){
        animalsMap = new HashMap<>();
        mapVisualizer = new MapVisualizer(this);
        listeners = new ArrayList<>();
    }

    public void addListener(IMapChangeListener listener){
        this.listeners.add(listener);
    }
    public void removeListener(IMapChangeListener listener) {
        this.listeners.remove(listener);
    }

    protected void mapChanged(String message){
        listeners.forEach((listener) -> listener.mapChanged(this, message));
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return !animalsMap.containsKey(position);
    }

    @Override
    public void place(IWorldElement<Vector2d> object) throws PositionAlreadyOccupiedException {
        if(!canMoveTo(object.getPosition()))
            throw new PositionAlreadyOccupiedException(object.getPosition());

        if(object instanceof Animal animal) {
            animalsMap.put(animal.getPosition(), animal);
            mapChanged("Animal placed at: " + animal.getPosition());
        }
    }

    @Override
    public void move(IWorldElement<Vector2d> object, MoveDirection direction) throws IllegalArgumentException {
        if(!(object instanceof Animal animal))
            throw new IllegalArgumentException("You can't move object that is not an Animal'");

        Vector2d oldPosition = animal.getPosition();
        MapDirection oldOrientation = animal.getOrientation();

        animal.move(direction, this);

        if(!animal.getPosition().equals(oldPosition)){
            animalsMap.remove(oldPosition);
            animalsMap.put(animal.getPosition(), animal);
            mapChanged("Animal moved to: " + animal.getPosition());
        }
        if(animal.getOrientation().equals(oldOrientation))
            mapChanged("Animal changed orientation to " + animal.getOrientation());
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

    @Override
    public String toString() {
        Boundary currentBounds = getCurrentBounds();
        return mapVisualizer.draw(currentBounds.bottomLeft(), currentBounds.topRight());
    }
}
