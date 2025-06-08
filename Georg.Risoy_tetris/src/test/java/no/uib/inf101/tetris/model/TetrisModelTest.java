package no.uib.inf101.tetris.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import no.uib.inf101.tetris.model.tetromino.PatternedTetrominoFactory;
import no.uib.inf101.tetris.model.tetromino.Tetromino;

class TetrisModelTest {

    @Test
    void testMoveTetromino() {
        TetrisModel model = new TetrisModel(new PatternedTetrominoFactory("T"));
        Tetromino initialTetromino = model.getFallingTetrominoObject();

        // Flytt til høyre
        assertTrue(model.moveTetromino(0, 1));
        Tetromino movedRight = model.getFallingTetrominoObject();
        assertEquals(initialTetromino.getPosition().col() + 1, movedRight.getPosition().col());

        // Flytt tilbake til venstre
        assertTrue(model.moveTetromino(0, -1));
        Tetromino movedBack = model.getFallingTetrominoObject();
        assertEquals(initialTetromino.getPosition().col(), movedBack.getPosition().col());

        // Prøv å flytte utenfor brettet
        assertFalse(model.moveTetromino(0, -100)); // Utenfor til venstre
        assertFalse(model.moveTetromino(0, 100));  // Utenfor til høyre
    }

    @Test
    void testRotateTetromino() {
        TetrisModel model = new TetrisModel(new PatternedTetrominoFactory("T"));
        Tetromino initialTetromino = model.getFallingTetrominoObject();

        // Roter 
        assertTrue(model.rotateTetromino());
        Tetromino rotatedTetromino = model.getFallingTetrominoObject();
        assertNotEquals(initialTetromino.getPosition(), rotatedTetromino.getPosition()); // Posisjonen kan endres ved rotasjon
    }

    @Test
    void testDropTetromino() {
        TetrisModel model = new TetrisModel(new PatternedTetrominoFactory("T"));
        Tetromino initialTetromino = model.getFallingTetrominoObject();

        // drop an
        model.dropTetromino();
        Tetromino droppedTetromino = model.getFallingTetrominoObject();
        assertNotEquals(initialTetromino.getPosition().row(), droppedTetromino.getPosition().row()); // Den skal ha falt ned
    }

    @Test
    void testClockTick() {
        TetrisModel model = new TetrisModel(new PatternedTetrominoFactory("T"));
        Tetromino initialTetromino = model.getFallingTetrominoObject();

        // Simuler et klokkeslag
        model.clockTick();

        // Sjekk at tetrominoen har flyttet seg nedover
        Tetromino newTetromino = model.getFallingTetrominoObject();
        assertEquals(initialTetromino.getPosition().row() + 1, newTetromino.getPosition().row());
    }
}