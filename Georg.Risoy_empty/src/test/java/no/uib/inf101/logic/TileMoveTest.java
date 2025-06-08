package no.uib.inf101.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class TileMoveTest {

    @Test
    public void testTileMoveValues() {
        TileMove move = new TileMove(1, 2, 3, 4, 16);

        assertEquals(1, move.fromRow);
        assertEquals(2, move.fromCol);
        assertEquals(3, move.toRow);
        assertEquals(4, move.toCol);
        assertEquals(16, move.value);
    }
}
