package no.uib.inf101.tetris.view;

import java.awt.Color;
// Tok lang tid å skjønne at den måtte oppdateres for å funke med tetromino. Sto det i guiden?
public class ColorTheme {

    /**
     * Returns the color associated with a given cell symbol.
     *
     * @param c The symbol representing the cell (e.g., 'L', 'J', 'S', etc.)
     * @return The color associated with the symbol
     * @throws IllegalArgumentException if the symbol is not recognized
     */
    public Color getCellColor(char c) {
        return switch (c) {
            case 'L' -> Color.ORANGE;  // L-brikke
            case 'J' -> Color.BLUE;    // J-brikke
            case 'S' -> Color.GREEN;   // S-brikke
            case 'Z' -> Color.RED;     // Z-brikke
            case 'T' -> Color.MAGENTA; // T-brikke
            case 'I' -> Color.CYAN;    // I-brikke
            case 'O' -> Color.YELLOW;  // O-brikke
            case '-' -> Color.BLACK;  // Tom celle
            default -> throw new IllegalArgumentException(
                    "No available color for '" + c + "'");
        };
    }

    /**
     * Returns the color of the frame around the game board.
     *
     * @return The frame color
     */
    public Color getFrameColor() {
        return Color.WHITE;
    }

    /**
     * Returns the background color of the game board.
     *
     * @return The background color
     */
    public Color getBackgroundColor() {
        return Color.DARK_GRAY; // Bakgrunnsfarge for brettet
    }
}