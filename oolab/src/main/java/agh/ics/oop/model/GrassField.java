package agh.ics.oop.model;

import agh.ics.oop.RandomPositionGenerator;
import agh.ics.oop.model.util.MapVisualizer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.lang.Math;
import java.util.stream.Stream;

public class GrassField implements IWorldMap<IWorldElement<Vector2d>, Vector2d> {
    private final Map<Vector2d, Animal> animalsMap;
    private final Map<Vector2d, Grass> grassMap;
    private final MapVisualizer visualizer;

    public GrassField(int numberOfGrassFields){
        animalsMap = new HashMap<>();
        grassMap = new HashMap<>(numberOfGrassFields);
        visualizer = new MapVisualizer(this);

        RandomPositionGenerator positionGenerator = new RandomPositionGenerator(
                (int)Math.sqrt(numberOfGrassFields*10),
                (int)Math.sqrt(numberOfGrassFields*10),
                numberOfGrassFields);

        for (Vector2d grassPosition : positionGenerator){
            grassMap.put(grassPosition, new Grass(grassPosition));
        }
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return !animalsMap.containsKey(position);
    }

    @Override
    public boolean place(IWorldElement<Vector2d> object) {
        if(!canMoveTo(object.getPosition()))
            return false;

        if(object instanceof Animal animal){
            animalsMap.put(animal.getPosition(), animal);
        }
        else if(object instanceof Grass grass){
            grassMap.put(grass.getPosition(), grass);
        } else
            throw new IllegalArgumentException("You can only place Grass or Animal on the grass field");

        return true;
    }

    @Override
    public void move(IWorldElement<Vector2d> object, MoveDirection direction) {
        if(!(object instanceof Animal animal))
            throw new IllegalArgumentException("Can't move not movable object");

        Vector2d oldPosition = animal.getPosition();
        animal.move(direction, this);

        if(!object.getPosition().equals(oldPosition)){
            animalsMap.remove(oldPosition);
            place(object);
        }
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return objectAt(position) != null;
    }

    @Override
    public IWorldElement<Vector2d> objectAt(Vector2d position) {
        if (animalsMap.get(position) != null)
            return animalsMap.get(position);

        return grassMap.get(position);
    }

    @Override
    public List<IWorldElement<Vector2d>> getElements() {
        return new ArrayList<>(
                Stream.concat(
                        grassMap.values().stream(),
                        animalsMap.values().stream()
                ).toList());
    }

    @Override
    public String toString() {
        Vector2d leftBottom = new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE);
        Vector2d rightTop = new Vector2d(Integer.MIN_VALUE, Integer.MIN_VALUE);

        for (Vector2d vec :
                Stream.concat(animalsMap.keySet().stream(), grassMap.keySet().stream()).toList()){
            rightTop = rightTop.upperRight(vec);
            leftBottom = leftBottom.lowerLeft(vec);
        }

        return visualizer.draw(leftBottom, new Vector2d(rightTop.getX(), rightTop.getY()));
    }
}
