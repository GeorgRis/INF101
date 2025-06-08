package no.uib.inf101.tetris.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import no.uib.inf101.grid.CellPosition;

public class TetrisBoardTest {

    @Test
    public void prettyStringTest() {
        TetrisBoard board = new TetrisBoard(3, 4);
        board.set(new CellPosition(0, 0), 'r');
        board.set(new CellPosition(0, 3), 'y');
        board.set(new CellPosition(2, 0), 'b');
        board.set(new CellPosition(2, 3), 'w');
        String expected = String.join("\n", new String[] {
            "r--y", 
            "----",
            "b--w"  
        });

        assertEquals(expected, board.prettyString(), "prettyString() returnerte feil verdi");
    }

    @Test
    public void testRemoveFullRows() {
        TetrisBoard board = getTetrisBoardWithContents(new String[] {
            "-T",
            "TT",
            "LT",
            "L-",
            "LL"
        });
        assertEquals(3, board.clearFilledRows(), "Antall fjernede rader er feil");
        String expected = String.join("\n", new String[] {
            "--",
            "--",
            "--",
            "-T",
            "L-"
        });
        assertEquals(expected, board.prettyString(), "Brettet er ikke korrekt etter fjerning av fulle rader");
    }

    @Test
    public void testRemoveMultipleFullRows() {
        TetrisBoard board = getTetrisBoardWithContents(new String[] {
            "TT",
            "TT",
            "--",
            "LL",
            "LL"
        });
        assertEquals(4, board.clearFilledRows(), "Antall fjernede rader er feil");
        String expected = String.join("\n", new String[] {
            "--",
            "--",
            "--",
            "--",
            "--"
        });
        assertEquals(expected, board.prettyString(), "Brettet er ikke korrekt etter fjerning av fulle rader");
    }

    private TetrisBoard getTetrisBoardWithContents(String[] rows) {
        TetrisBoard board = new TetrisBoard(rows.length, rows[0].length());
        for (int row = 0; row < rows.length; row++) {
            for (int col = 0; col < rows[row].length(); col++) {
                char symbol = rows[row].charAt(col);
                if (symbol != '-') {
                    board.set(new CellPosition(row, col), symbol);
                }
            }
        }
        return board;
    }

        @Test
public void testRemoveRow() {
    TetrisBoard board = getTetrisBoardWithContents(new String[] {
        "A",
        "B",
        "C",
        "D"
    });
    board.removeRow(2); // Fjern rad 2 (C)
    String expected = String.join("\n", new String[] {
        "-",
        "A",
        "B",
        "D"
    });
    assertEquals(expected, board.prettyString(), "Brettet er ikke korrekt etter fjerning av rad");
}
}