package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TextMapTest {

    @Test
    void canMoveTo() {
        TextMap map = new TextMap();

        try {
            map.place("Ala");
            map.place("ma");
            map.place("sowoniedzwiedzia");
        } catch (PositionAlreadyOccupiedException ex){
            fail(ex.getMessage());
        }

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

        assertDoesNotThrow(() -> map.place(str1));
        assertDoesNotThrow(() -> map.place(str2));
        assertDoesNotThrow(() -> map.place(str3));

        assertTrue(map.objectAt(0).isPresent());
        assertEquals(str1, map.objectAt(0).get());
        assertTrue(map.objectAt(1).isPresent());
        assertEquals(str2, map.objectAt(1).get());
        assertTrue(map.objectAt(2).isPresent());
        assertEquals(str3, map.objectAt(2).get());
    }

    @Test
    void move() {
        TextMap map = new TextMap();

        String str1 = "Ala";
        String str2 = "ma";
        String str3 = "sowoniedzwiedzia";

        try {
            map.place(str1);
            map.place(str2);
            map.place(str3);
        } catch (PositionAlreadyOccupiedException ex){
            fail(ex.getMessage());
        }

        map.move(str2, MoveDirection.FORWARD);
        assertTrue(map.objectAt(1).isPresent());
        assertEquals(str2, map.objectAt(1).get());

        map.move(str2, MoveDirection.RIGHT);
        map.move(str2, MoveDirection.FORWARD);

        assertTrue(map.objectAt(1).isPresent());
        assertEquals(str3, map.objectAt(1).get());
        assertTrue(map.objectAt(2).isPresent());
        assertEquals(str2, map.objectAt(2).get());

        map.move(str2, MoveDirection.FORWARD);
        assertTrue(map.objectAt(2).isPresent());
        assertEquals(str2, map.objectAt(2).get());

        map.move(str2, MoveDirection.BACKWARD);
        assertTrue(map.objectAt(1).isPresent());
        assertEquals(str2, map.objectAt(1).get());
        assertTrue(map.objectAt(2).isPresent());
        assertEquals(str3, map.objectAt(2).get());

        map.move(str2, MoveDirection.LEFT);
        map.move(str2, MoveDirection.LEFT);
        map.move(str2, MoveDirection.FORWARD);

        assertTrue(map.objectAt(0).isPresent());
        assertEquals(str2, map.objectAt(0).get());
        assertTrue(map.objectAt(1).isPresent());
        assertEquals(str1, map.objectAt(1).get());

        map.move(str2, MoveDirection.BACKWARD);

        assertTrue(map.objectAt(0).isPresent());
        assertEquals(str1, map.objectAt(0).get());
        assertTrue(map.objectAt(1).isPresent());
        assertEquals(str2, map.objectAt(1).get());
    }

    @Test
    void isOccupied()  {
        TextMap map = new TextMap();

        String str1 = "Ala";

        try {
            map.place(str1);
        } catch (PositionAlreadyOccupiedException ex){
            fail(ex.getMessage());
        }

        assertTrue(map.isOccupied(0));
        assertFalse(map.isOccupied(1));
        assertFalse(map.isOccupied(-1));
    }

    @Test
    void objectAt() {
        TextMap map = new TextMap();

        String str1 = "Ala";

        try {
            map.place(str1);
        } catch(PositionAlreadyOccupiedException ex){
            fail(ex.getMessage());
        }

        assertTrue(map.objectAt(0).isPresent());
        assertEquals(str1, map.objectAt(0).get());
        assertTrue(map.objectAt(1).isEmpty());
    }
}