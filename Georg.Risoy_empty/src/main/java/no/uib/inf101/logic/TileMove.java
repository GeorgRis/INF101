package no.uib.inf101.logic;

public class TileMove {
    public int fromRow, fromCol;
    public int toRow, toCol;
    public int value;

    public TileMove(int fromRow, int fromCol, int toRow, int toCol, int value) {
        this.fromRow = fromRow;
        this.fromCol = fromCol;
        this.toRow = toRow;
        this.toCol = toCol;
        this.value = value;
    }
}
