package agh.ics.oop.model.util;

import agh.ics.oop.model.IMapChangeListener;
import agh.ics.oop.model.IWorldElement;
import agh.ics.oop.model.IWorldMap;
import agh.ics.oop.model.Vector2d;

public class ConsoleMapDisplay implements IMapChangeListener {
    private int updateCounter = 0;
    @Override
    public synchronized void mapChanged(IWorldMap<IWorldElement<Vector2d>, Vector2d> worldMap, String message) {
        System.out.printf("update no: %d map id: %s -> %s \n", ++updateCounter, worldMap.getId().toString(), message);
        System.out.println(worldMap);
    }
}
