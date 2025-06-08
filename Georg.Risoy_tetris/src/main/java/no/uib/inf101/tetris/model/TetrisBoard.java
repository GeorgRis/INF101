package no.uib.inf101.tetris.model;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.Grid;

public class TetrisBoard extends Grid {

    public TetrisBoard(int rows, int cols) {
        // superkonstruktøren til Grid med rader, kolonner og en standardverdi '-'
        super(rows, cols, '-');
    }


    /**
     * Returns a string representation of the board, where each row is a line in
     * the string, and each cell is represented by its value. Empty cells are
     * represented by the '-' character.
     * 
     * @return a string representation of the board
     */
    public String prettyString() {
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < rows(); row++) {
            for (int col = 0; col < cols(); col++) {
                // Verdier i cellene
                char cellValue = get(new CellPosition(row, col));
                sb.append(cellValue);
            }
            if (row < rows() - 1) {
                sb.append("\n"); // Legger til ny linje etter hver rad-1
            }
        }
        return sb.toString();
    }


    /**
     * Removes all filled rows from the board and returns the number of rows
     * removed. Filled rows are rows where all cells are non-empty. After
     * removing each row, the remaining rows are shifted downwards and the top
     * row is filled with the empty character '-'.
     * 
     * The method starts at the bottom of the board and works its way up. If a
     * loop counter is incremented to check the same row again after removal
     * (since the row above is shifted down to replace the removed row).
     * 
     * @return the number of rows removed
     */

    public int clearFilledRows() {
        int rowsRemoved = 0;
        for (int row = rows() - 1; row >= 0; row--) {
            if (isRowFilled(row)) {
                removeRow(row);
                rowsRemoved++;
                row++;// Sjekk samme rad på nytt etter fjerning
            }
        }
        return rowsRemoved;
    }


    /**
     * Checks if a row is filled by checking all its cells.
     *
     * @param row The row to check
     * @return true if the row is filled, false if it is not
     */
    private boolean isRowFilled(int row) {
        for (int col = 0; col < cols(); col++) {
            if (get(new CellPosition(row, col)) == '-') {
                return false;
            }
        }
        return true;
    }

    /**
     * Removes a specific row and shifts all rows above it down.
     *
     * @param row The row to remove
     */
    protected void removeRow(int row) {
        // Flytt alle rader over den fjernede raden nedover
        for (int r = row; r > 0; r--) {
            copyRowTo(r - 1, r);
        }
        // Tøm den øverste raden
        fillTopRowWithEmpty(0);
    }

    /**
     * Copies the contents of one row to another.
     *
     * @param originalRow The row to copy from
     * @param targetRow   The row to copy to
     */
    private void copyRowTo(int originalRow, int targetRow) {
        for (int col = 0; col < cols(); col++) {
            CellPosition originalPos = new CellPosition(originalRow, col);
            CellPosition targetPos = new CellPosition(targetRow, col);
            set(targetPos, get(originalPos));
        }
    }

    /**
     * Fills a specific row with the placeholder character ('-').
     *
     * @param row The row to fill
     */
    private void fillTopRowWithEmpty(int row) {
        for (int col = 0; col < cols(); col++) {
            set(new CellPosition(row, col), '-');
        }
    }
}