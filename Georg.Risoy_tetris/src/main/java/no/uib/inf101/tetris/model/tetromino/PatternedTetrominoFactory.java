package no.uib.inf101.tetris.model.tetromino;

public class PatternedTetrominoFactory implements TetrominoFactory {
    private final String pattern;
    private int index = 0;

    public PatternedTetrominoFactory(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public Tetromino getNext() {
        char symbol = pattern.charAt(index % pattern.length());
        index++;
        return Tetromino.newTetromino(symbol);
    }
}