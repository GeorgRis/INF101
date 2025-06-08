package no.uib.inf101.tetris.model.tetromino;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.GridDimension;

public class Tetromino implements Iterable<GridCell> {
    private final char symbol;
    private final boolean[][] shape;
    private final CellPosition position;

    private Tetromino(char symbol, boolean[][] shape, CellPosition position) {
        this.symbol = symbol;
        this.shape = shape;
        this.position = position;
    }

    /**
     * Creates a new Tetromino with the given symbol at position (0,0).
     *
     * @param symbol The symbol of the Tetromino.
     * @return A new Tetromino with the given symbol at position (0,0).
     * @throws IllegalArgumentException if the symbol is unknown.
     */
    public static Tetromino newTetromino(char symbol) {
        boolean[][] shape = getShape(symbol);
        return new Tetromino(symbol, shape, new CellPosition(0, 0));
    }

    /**
     * Returns the shape of a Tetromino based on its symbol.
     *
     * @param symbol The symbol of the Tetromino.
     * @return The shape of the Tetromino as a 2D array of booleans.
     * @throws IllegalArgumentException if the symbol is unknown.
     */
    private static boolean[][] getShape(char symbol) {
        return switch (symbol) {
            case 'L' -> new boolean[][] {
                { false, false, false },
                { true, true, true },
                { true, false, false }
            };
            case 'J' -> new boolean[][] {
                { false, false, false },
                { true, true, true },
                { false, false, true }
            };
            case 'S' -> new boolean[][] {
                { false, false, false },
                { false, true, true },
                { true, true, false }
            };
            case 'Z' -> new boolean[][] {
                { false, false, false },
                { true, true, false },
                { false, true, true }
            };
            case 'T' -> new boolean[][] {
                { false, false, false },
                { true, true, true },
                { false, true, false }
            };
            case 'I' -> new boolean[][] {
                { false, false, false, false },
                { true, true, true, true },
                { false, false, false, false },
                { false, false, false, false }
            };
            case 'O' -> new boolean[][] {
                { false, false, false, false },
                { false, true, true, false },
                { false, true, true, false },
                { false, false, false, false }
            };
            default -> throw new IllegalArgumentException("Unknown symbol: " + symbol);
        };
    }

    public Tetromino shiftedBy(int deltaRow, int deltaCol) {
        CellPosition newPosition = new CellPosition(position.row() + deltaRow, position.col() + deltaCol);
        return new Tetromino(symbol, shape, newPosition);
    }

    public Tetromino shiftedToTopCenterOf(GridDimension grid) {
    int centerCol = grid.cols() / 2 - shape[0].length / 2;
    int topRow = 0; // Start på rad 0

    // Hvis brikken har en tom øverste rad, flytt den oppover
    boolean hasEmptyTopRow = true;
    for (int col = 0; col < shape[0].length; col++) {
        if (shape[0][col]) {
            hasEmptyTopRow = false;
            break;
        }
    }
    if (hasEmptyTopRow) {
        topRow = -1; // Flytt brikken opp én rad
    }

    return shiftedBy(topRow, centerCol);
}

    @Override
    public Iterator<GridCell> iterator() {
        ArrayList<GridCell> cells = new ArrayList<>();
        for (int row = 0; row < shape.length; row++) {
            for (int col = 0; col < shape[row].length; col++) {
                if (shape[row][col]) {
                    CellPosition pos = new CellPosition(position.row() + row, position.col() + col);
                    cells.add(new GridCell(pos, symbol));
                }
            }
        }
        return cells.iterator();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tetromino tetromino = (Tetromino) o;
        return symbol == tetromino.symbol &&
                Arrays.deepEquals(shape, tetromino.shape) &&
                position.equals(tetromino.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol, Arrays.deepHashCode(shape), position);
    }

    public Tetromino rotatedCopy() {
        int rows = shape.length;
        int cols = shape[0].length;
        boolean[][] rotatedShape = new boolean[cols][rows];

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                rotatedShape[col][rows - 1 - row] = shape[row][col];
            }
        }
        return new Tetromino(symbol, rotatedShape, position);
    }

    public CellPosition getPosition() {
        return position;
    }
}