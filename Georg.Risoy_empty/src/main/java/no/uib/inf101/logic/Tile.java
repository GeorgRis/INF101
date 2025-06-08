package no.uib.inf101.logic;

public class Tile {
    private int value;

    public Tile(int value) {
        this.value = value;
    }

    /**
     * Returns the current value of the tile.
     *
     * @return the value of the tile
     */
    public int getValue() {
        return value;
    }

    /**
     * Sets the value of this tile.
     *
     * @param value the new value of the tile
     */
    public void setValue(int value) {
        this.value = value;
    }
}
