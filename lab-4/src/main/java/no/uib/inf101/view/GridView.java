package no.uib.inf101.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.IGrid;

public class GridView extends JPanel {
    private final IGrid grid;
    private final ColorTheme colorTheme;
    private static final int CELL_SIZE = 50;
    private static final int OUTER_MARGIN = 10;

    public GridView(IGrid grid) {
        this.grid = grid;
        this.colorTheme = new ColorTheme(); // Bruk farge-temaet
        int width = grid.cols() * CELL_SIZE + 2 * OUTER_MARGIN;
        int height = grid.rows() * CELL_SIZE + 2 * OUTER_MARGIN;
        this.setPreferredSize(new Dimension(width, height));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        drawGrid(g2);
    }

    private void drawGrid(Graphics2D g2) {
        for (int row = 0; row < grid.rows(); row++) {
            for (int col = 0; col < grid.cols(); col++) {
                int x = OUTER_MARGIN + col * CELL_SIZE;
                int y = OUTER_MARGIN + row * CELL_SIZE;

                char symbol = grid.get(new CellPosition(row, col));

                g2.setColor(colorTheme.getColor(symbol));
                g2.fillRect(x, y, CELL_SIZE, CELL_SIZE);


                g2.setColor(Color.BLACK);
                g2.drawRect(x, y, CELL_SIZE, CELL_SIZE);


                if (symbol != '-') {
                    g2.setColor(Color.BLACK);
                    g2.setFont(new Font("Arial", Font.BOLD, 20));
                    FontMetrics fm = g2.getFontMetrics();
                    int textX = x + (CELL_SIZE - fm.charWidth(symbol)) / 2;
                    int textY = y + (CELL_SIZE + fm.getAscent()) / 2 - 2;
                    g2.drawString(String.valueOf(symbol), textX, textY);
                }
            }
        }
    }
}
