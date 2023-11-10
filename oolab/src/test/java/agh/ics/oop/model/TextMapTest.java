package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TextMapTest {

    @Test
    void canMoveTo() {
        TextMap map = new TextMap();

        map.place("Ala");
        map.place("ma");
        map.place("sowoniedzwiedzia");

        assertFalse(map.canMoveTo(-1));
        assertTrue(map.canMoveTo(0));
        assertFalse(map.canMoveTo(3));
    }

    @Test
    void place() {
        TextMap map = new TextMap();

        String str1 = "Ala";
        String str2 = "ma";
        String str3 = "sowoniedzwiedzia";

        map.place(str1);
        map.place(str2);
        map.place(str3);

        assertEquals(str1, map.objectAt(0));
        assertEquals(str2, map.objectAt(1));
        assertEquals(str3, map.objectAt(2));
    }

    @Test
    void move() {
        TextMap map = new TextMap();

        String str1 = "Ala";
        String str2 = "ma";
        String str3 = "sowoniedzwiedzia";

        map.place(str1);
        map.place(str2);
        map.place(str3);

        map.move(str2, MoveDirection.FORWARD);
        assertEquals(str2, map.objectAt(1));

        map.move(str2, MoveDirection.RIGHT);
        map.move(str2, MoveDirection.FORWARD);

        assertEquals(str3, map.objectAt(1));
        assertEquals(str2, map.objectAt(2));

        map.move(str2, MoveDirection.FORWARD);
        assertEquals(str2, map.objectAt(2));

        map.move(str2, MoveDirection.BACKWARD);
        assertEquals(str2, map.objectAt(1));
        assertEquals(str3, map.objectAt(2));

        map.move(str2, MoveDirection.LEFT);
        map.move(str2, MoveDirection.LEFT);
        map.move(str2, MoveDirection.FORWARD);

        assertEquals(str2, map.objectAt(0));
        assertEquals(str1, map.objectAt(1));

        map.move(str2, MoveDirection.BACKWARD);

        assertEquals(str1, map.objectAt(0));
        assertEquals(str2, map.objectAt(1));
    }

    @Test
    void isOccupied() {
        TextMap map = new TextMap();

        String str1 = "Ala";

        map.place(str1);

        assertTrue(map.isOccupied(0));
        assertFalse(map.isOccupied(1));
        assertFalse(map.isOccupied(-1));
    }

    @Test
    void objectAt() {
        TextMap map = new TextMap();

        String str1 = "Ala";

        map.place(str1);

        assertEquals(str1, map.objectAt(0));
        assertNull(map.objectAt(1));
    }
}