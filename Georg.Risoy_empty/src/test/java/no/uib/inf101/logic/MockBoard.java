package no.uib.inf101.logic;

public class MockBoard extends Board {
    private final int size;
    private final Tile[][] tiles;

    public MockBoard(int size) {
        this.size = size;
        this.tiles = new Tile[size][size];
    }

    public MockBoard(Tile[][] tiles) {
        this.size = tiles.length;
        this.tiles = tiles;
    }

    @Override
    public Tile getTile(int row, int col) {
        return tiles[row][col];
    }

    @Override
    public void setTile(int row, int col, Tile tile) {
        tiles[row][col] = tile;
    }

    @Override
    public int getSize() {
        return size;
    }
}
