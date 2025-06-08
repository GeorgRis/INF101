package no.uib.inf101.tetris.model.tetromino;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridCell;

class TestTetromino {

    @Test
    void testTetrominoEquality() {
        Tetromino t1 = Tetromino.newTetromino('T');
        Tetromino t2 = Tetromino.newTetromino('T');
        assertEquals(t1, t2);
        assertEquals(t1.hashCode(), t2.hashCode());
    }

    @Test
    void testTetrominoInequality() {
        Tetromino t1 = Tetromino.newTetromino('T');
        Tetromino t2 = Tetromino.newTetromino('I');
        assertNotEquals(t1, t2);
        assertNotEquals(t1.hashCode(), t2.hashCode());
    }

    @Test
    void testTetrominoIterator() {
        Tetromino t = Tetromino.newTetromino('T');
        Iterator<GridCell> it = t.iterator();
        assertTrue(it.hasNext());
        GridCell cell = it.next();
        assertEquals(new CellPosition(1, 0), cell.pos()); // T-brikken har en del i midten
        assertEquals('T', cell.symbol());
    }

    @Test
    void testTetrominoShift() {
        Tetromino t = Tetromino.newTetromino('T');
        Tetromino shifted = t.shiftedBy(1, 1);
        assertEquals(new CellPosition(2, 1), shifted.iterator().next().pos());
    }

    @Test
    void testTetrominoRotatedCopy() {
        Tetromino t = Tetromino.newTetromino('T');
        Tetromino rotated = t.rotatedCopy();
        assertNotEquals(t, rotated); // Sjekk at den roterte kopien er forskjellig fra originalen
    }
}