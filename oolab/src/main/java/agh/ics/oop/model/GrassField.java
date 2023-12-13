package agh.ics.oop.model;

import agh.ics.oop.model.util.RandomPositionGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.lang.Math;
import java.util.stream.Stream;

public class GrassField extends AbstractWorldMap {
    private final Map<Vector2d, Grass> grassMap;

    public GrassField(int numberOfGrassFields){
        grassMap = new HashMap<>(numberOfGrassFields);
        createGrassFields(numberOfGrassFields);
    }

    private void createGrassFields(int numberOfGrassFields){
        RandomPositionGenerator positionGenerator = new RandomPositionGenerator(
                new Vector2d(
                        -(int)Math.sqrt(numberOfGrassFields*4),
                        -(int)Math.sqrt(numberOfGrassFields*4)
                ),
                new Vector2d(
                        (int)Math.sqrt(numberOfGrassFields*4),
                        (int)Math.sqrt(numberOfGrassFields*4)
                ),
                numberOfGrassFields);

        for (Vector2d grassPosition : positionGenerator){
            grassMap.put(grassPosition, new Grass(grassPosition));
        }
    }

    @Override
    public void place(IWorldElement<Vector2d> object) throws PositionAlreadyOccupiedException {
        super.place(object);

        if (object instanceof Grass grass) {
            grassMap.put(grass.getPosition(), grass);
            super.mapChanged("Grass placed at: " + grass.getPosition());
        }
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
    public Boundary getCurrentBounds() {
        Vector2d leftBottom = new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE);
        Vector2d rightTop = new Vector2d(Integer.MIN_VALUE, Integer.MIN_VALUE);

        for (Vector2d vec :
                Stream.concat(animalsMap.keySet().stream(), grassMap.keySet().stream()).toList()){
            rightTop = rightTop.upperRight(vec);
            leftBottom = leftBottom.lowerLeft(vec);
        }

        return new Boundary(leftBottom, rightTop);
    }
}
