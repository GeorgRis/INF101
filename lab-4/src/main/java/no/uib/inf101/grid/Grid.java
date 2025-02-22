package no.uib.inf101.grid;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Grid implements IGrid {
    private final int rows;
    private final int cols;
    private final Character[][] grid;

    public Grid(int rows, int cols, Character defaultValue) {
        this.rows = rows;
        this.cols = cols;
        this.grid = new Character[rows][cols];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                this.grid[r][c] = defaultValue;
            }
        }
    }

    @Override
    public void set(CellPosition pos, Character symbol) {
        if (!positionIsOnGrid(pos)) {
            throw new IndexOutOfBoundsException("Position out of bounds: " + pos);
        }
        grid[pos.row()][pos.col()] = symbol;
    }

    @Override
    public Character get(CellPosition pos) {
        if (!positionIsOnGrid(pos)) {
            throw new IndexOutOfBoundsException("Position out of bounds: " + pos);
        }
        return grid[pos.row()][pos.col()];
    }

    @Override
    public boolean positionIsOnGrid(CellPosition pos) {
        return pos.row() >= 0 && pos.row() < rows && pos.col() >= 0 && pos.col() < cols;
/*************  ✨ Codeium Command ⭐  *************/
/******  4df44093-cc18-4806-9f97-6c79741e291c  *******/    }


    @Override
    public int rows() {
        return rows;
    }

    @Override
    public int cols() {
        return cols;
    }

    @Override
    public Iterator<GridCell> iterator() {
        List<GridCell> cells = new ArrayList<>();
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                cells.add(new GridCell(new CellPosition(r, c), grid[r][c]));
            }
        }
        return cells.iterator();
    }
}
