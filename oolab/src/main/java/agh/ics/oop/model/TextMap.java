package agh.ics.oop.model;

import java.util.*;

public class TextMap implements IWorldMap<String, Integer> {
    public record Pair<T1, T2>(T1 first,  T2 second) {}
    private final List<Pair<String, MapDirection>> texts;
    private final Map<String, Integer> textsPositions;
    private final UUID mapId;

    TextMap(){
        texts = new ArrayList<>();
        textsPositions = new HashMap<>();

        mapId = UUID.randomUUID();
    }

    @Override
    public boolean canMoveTo(Integer position) {
        return position >= 0 && position <= texts.size() - 1;
    }

    @Override
    public void place(String object) throws PositionAlreadyOccupiedException {
        textsPositions.put(object, texts.size());
        texts.add(new Pair<>(object, MapDirection.NORTH));
    }

    @Override
    public void move(String object, MoveDirection direction) {
        Integer position = textsPositions.get(object);

        if(direction == MoveDirection.LEFT){
            texts.set(position, new Pair<>(object, texts.get(position).second.previous()));
            return;
        }

        if(direction == MoveDirection.RIGHT){
            texts.set(position, new Pair<>(object, texts.get(position).second.next()));
            return;
        }


        if(texts.get(position).second == MapDirection.NORTH ||
           texts.get(position).second == MapDirection.SOUTH)
            return;

        int directionInt = direction == MoveDirection.FORWARD ? 1 : -1;
        int deltaPosition = texts.get(position).second.toUnitVector().getX() * directionInt;
        int newPosition = position + deltaPosition;

        if(canMoveTo(newPosition)){
            Collections.swap(texts, position, newPosition);
            textsPositions.put(object, newPosition);
        }
    }

    @Override
    public boolean isOccupied(Integer position) {
        return position >= 0 && position <= texts.size() - 1;
    }

    @Override
    public String objectAt(Integer position) {
        if(!isOccupied(position))
            return null;

        return texts.get(position).first;
    }

    @Override
    public List<String> getElements() {
        return new ArrayList<>(textsPositions.keySet());
    }

    @Override
    public List<String> getOrderedElements() {
        return new ArrayList<>(texts.stream().map((a) -> a.first).toList());
    }

    @Override
    public Boundary getCurrentBounds() {
        return new Boundary(new Vector2d(0, 0), new Vector2d(texts.size(), 0));
    }

    @Override
    public UUID getId() {
        return mapId;
    }
}
