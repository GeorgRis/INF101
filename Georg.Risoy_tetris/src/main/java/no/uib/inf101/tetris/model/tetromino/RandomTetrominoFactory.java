package no.uib.inf101.tetris.model.tetromino;

import java.util.Random;

public class RandomTetrominoFactory implements TetrominoFactory{
    private final Random random = new Random();
    private final String tetrominoTypes = "LJSZTIO";
    @Override
    public Tetromino getNext() {
        char randomType = tetrominoTypes.charAt(random.nextInt(tetrominoTypes.length()));
        return Tetromino.newTetromino(randomType);
    }
}
