package agh.ics.oop.model;

public interface IMapChangeListener {
    void mapChanged(IWorldMap<IWorldElement<Vector2d>, Vector2d> worldMap, String message);
}
