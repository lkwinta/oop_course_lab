package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapDirectionTest {

    @Test
    void testToString() {
        MapDirection direction = MapDirection.NORTH;
        assertEquals(direction.toString(), "Północ");

        direction = MapDirection.SOUTH;
        assertEquals(direction.toString(), "Południe");

        direction = MapDirection.EAST;
        assertEquals(direction.toString(), "Wschód");

        direction = MapDirection.WEST;
        assertEquals(direction.toString(), "Zachód");
    }

    @Test
    void next() {
        MapDirection direction = MapDirection.NORTH;
        direction = direction.next();
        assertEquals(direction, MapDirection.EAST);

        direction = direction.next();
        assertEquals(direction, MapDirection.SOUTH);

        direction = direction.next();
        assertEquals(direction, MapDirection.WEST);

        direction = direction.next();
        assertEquals(direction, MapDirection.NORTH);
    }

    @Test
    void previous() {
        MapDirection direction = MapDirection.NORTH;
        direction = direction.previous();
        assertEquals(direction, MapDirection.WEST);

        direction = direction.previous();
        assertEquals(direction, MapDirection.SOUTH);

        direction = direction.previous();
        assertEquals(direction, MapDirection.EAST);

        direction = direction.previous();
        assertEquals(direction, MapDirection.NORTH);
    }

    @Test
    void toUnitVector() {
        MapDirection direction = MapDirection.NORTH;
        assertEquals(direction.toUnitVector(), new Vector2d(0, 1));

        direction = MapDirection.EAST;
        assertEquals(direction.toUnitVector(), new Vector2d(1, 0));

        direction = MapDirection.SOUTH;
        assertEquals(direction.toUnitVector(), new Vector2d(0, -1));

        direction = MapDirection.WEST;
        assertEquals(direction.toUnitVector(), new Vector2d(-1, 0));
    }
}