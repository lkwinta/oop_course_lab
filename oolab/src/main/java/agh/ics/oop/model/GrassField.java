package agh.ics.oop.model;

import agh.ics.oop.RandomPositionGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.lang.Math;
import java.util.stream.Stream;

public class GrassField extends AbstractWorldMap {
    private final Map<Vector2d, Grass> grassMap;

    public GrassField(int numberOfGrassFields){
        super();

        grassMap = new HashMap<>(numberOfGrassFields);

        RandomPositionGenerator positionGenerator = new RandomPositionGenerator(
                (int)Math.sqrt(numberOfGrassFields*10),
                (int)Math.sqrt(numberOfGrassFields*10),
                numberOfGrassFields);

        for (Vector2d grassPosition : positionGenerator){
            grassMap.put(grassPosition, new Grass(grassPosition));
        }
    }

    @Override
    public boolean place(IWorldElement<Vector2d> object) {
        if(super.place(object))
            return true;

        if (object instanceof Grass grass) {
            grassMap.put(grass.getPosition(), grass);
        } else {
            return false;
        }

        return true;
    }

    @Override
    public IWorldElement<Vector2d> objectAt(Vector2d position) {
        if (animalsMap.get(position) != null)
            return super.objectAt(position);

        return grassMap.get(position);
    }

    @Override
    public List<IWorldElement<Vector2d>> getElements() {
        return new ArrayList<>(
                Stream.concat(
                        grassMap.values().stream(),
                        super.getElements().stream()
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

        return mapVisualizer.draw(leftBottom, new Vector2d(rightTop.getX(), rightTop.getY()));
    }
}
