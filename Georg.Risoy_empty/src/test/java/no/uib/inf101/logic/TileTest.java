package no.uib.inf101.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class TileTest {

    @Test
    public void testGetValue() {
        Tile tile = new Tile(32);
        assertEquals(32, tile.getValue());
    }

    @Test
    public void testSetValue() {
        Tile tile = new Tile(2);
        tile.setValue(8);
        assertEquals(8, tile.getValue());
    }
}
