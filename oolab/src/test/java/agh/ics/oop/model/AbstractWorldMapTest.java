package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class AbstractWorldMapTest {
    @Test
    void getOrderedElements(){
        AbstractWorldMap map = new GrassField(10);

        try {
            map.place(new Animal(new Vector2d(1, 3)));
            map.place(new Animal(new Vector2d(1, 2)));
            map.place(new Animal(new Vector2d(1, -1)));
            map.place(new Animal(new Vector2d(0, 2)));
            map.place(new Animal(new Vector2d(-1, 2)));
            map.place(new Animal(new Vector2d(5, 2)));

        } catch (PositionAlreadyOccupiedException ex){
            fail(ex.getMessage());
        }

        List<IWorldElement<Vector2d>> sortedAnimals = map.getOrderedElements();

        assertEquals(new Vector2d(-1, 2), sortedAnimals.get(0).getPosition());
        assertEquals(new Vector2d(0, 2), sortedAnimals.get(1).getPosition());
        assertEquals(new Vector2d(1, -1), sortedAnimals.get(2).getPosition());
        assertEquals(new Vector2d(1, 2), sortedAnimals.get(3).getPosition());
        assertEquals(new Vector2d(1, 3), sortedAnimals.get(4).getPosition());
        assertEquals(new Vector2d(5, 2), sortedAnimals.get(5).getPosition());
    }
}
