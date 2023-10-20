import agh.ics.oop.model.MapDirection;

import agh.ics.oop.model.Vector2d;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MapDirectionTest {
    @Test
    public void MapDirectionTest_toString(){
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
    public void MapDirectionTest_next(){
        System.out.println("Test1");

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
    public void MapDirectionTest_previous(){
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
    public void MapDirectionTest_toUnitVector(){
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
