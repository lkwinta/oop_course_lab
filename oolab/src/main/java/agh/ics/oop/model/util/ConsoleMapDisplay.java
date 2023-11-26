package agh.ics.oop.model.util;

import agh.ics.oop.model.IMapChangeListener;
import agh.ics.oop.model.IWorldElement;
import agh.ics.oop.model.IWorldMap;
import agh.ics.oop.model.Vector2d;

public class ConsoleMapDisplay implements IMapChangeListener {
    private static int updateCounter = 0;
    @Override
    public void mapChanged(IWorldMap<IWorldElement<Vector2d>, Vector2d> worldMap, String message) {
        System.out.println(message);
        System.out.println(worldMap.toString());
        System.out.println(++updateCounter);
    }
}
