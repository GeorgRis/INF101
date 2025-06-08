package no.uib.inf101.tetris.model.tetromino;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class TestPatternedTetrominoFactory {

    @Test
    void testPatternedTetrominoFactory() {
        TetrominoFactory factory = new PatternedTetrominoFactory("LJSZTIO");
        assertEquals('L', factory.getNext().iterator().next().symbol());
        assertEquals('J', factory.getNext().iterator().next().symbol());
        assertEquals('S', factory.getNext().iterator().next().symbol());
        assertEquals('Z', factory.getNext().iterator().next().symbol());
        assertEquals('T', factory.getNext().iterator().next().symbol());
        assertEquals('I', factory.getNext().iterator().next().symbol());
        assertEquals('O', factory.getNext().iterator().next().symbol());
        assertEquals('L', factory.getNext().iterator().next().symbol());
    }
}