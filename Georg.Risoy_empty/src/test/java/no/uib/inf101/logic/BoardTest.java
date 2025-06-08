package no.uib.inf101.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BoardTest {
    private Board board;

    @BeforeEach
    public void setup() {
        board = new Board();
    }

    @Test
    public void testBoardIsEmptyAtStart() {
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                assertNull(board.getTile(i, j), "Expected tile at (" + i + "," + j + ") to be null");
            }
        }
    }

    @Test
    public void testSetAndGetTile() {
        Tile tile = new Tile(8);
        board.setTile(1, 2, tile);

        Tile result = board.getTile(1, 2);
        assertNotNull(result);
        assertEquals(8, result.getValue());
    }

    @Test
    public void testGetSizeIsFour() {
        assertEquals(4, board.getSize(), "Board size should be 4x4");
    }

    @Test
    public void testSetTileOverwritesOldValue() {
        Board board = new Board();
        board.setTile(1, 1, new Tile(2));
        board.setTile(1, 1, new Tile(8));

        Tile result = board.getTile(1, 1);
        assertNotNull(result);
        assertEquals(8, result.getValue());
    }

}
