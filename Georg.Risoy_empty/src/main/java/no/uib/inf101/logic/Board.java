package no.uib.inf101.logic;

public class Board {
    private final int SIZE = 4;
    private Tile[][] tiles;

    public Board() {
        tiles = new Tile[SIZE][SIZE];
        initBoard();

    }

    /**
     * Initializes the board by setting all the tile positions to null.
     * This method is called during the construction of the Board object
     * to ensure that the board is empty at the start.
     */
    private void initBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                tiles[i][j] = null;
            }
        }
    }

    /**
     * Gets the tile at a given position on the board.
     * @param row row of the tile to get
     * @param col column of the tile to get
     * @return the tile at the given position, or null if there is no tile.
     */
    public Tile getTile(int row, int col) {
        return tiles[row][col];
    }

    /**
     * Sets the tile at a given position on the board.
     * @param row row of the tile to set
     * @param col column of the tile to set
     * @param tile the tile to set at the given position
     */
    public void setTile(int row, int col, Tile tile) {
        tiles[row][col] = tile;
    }


    public int getSize() {
        return SIZE;
    }
}

