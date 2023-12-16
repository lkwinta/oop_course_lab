package agh.ics.oop.model.util;

import agh.ics.oop.model.IMapChangeListener;
import agh.ics.oop.model.IWorldElement;
import agh.ics.oop.model.IWorldMap;
import agh.ics.oop.model.Vector2d;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileMapDisplay implements IMapChangeListener {
    @Override
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public synchronized void mapChanged(IWorldMap<IWorldElement<Vector2d>, Vector2d> worldMap, String message) {
        File logFile = new File("logs/%s.log".formatted(worldMap.getId()));

        try {
            logFile.getParentFile().mkdirs();
            logFile.createNewFile();
        } catch (IOException ex){
            System.out.printf("Failed to open a log file: %s, with error: %s\n", logFile.getPath(), ex.getMessage());
        }

        try (FileWriter writer = new FileWriter(logFile, true)) {
            writer.append("map move: %s\n".formatted(message));
            writer.append(worldMap.toString());
        } catch (IOException ex) {
            System.out.printf("Failed to write to a log file: %s, with error: %s\n", logFile.getPath(), ex.getMessage());
        }
    }
}
