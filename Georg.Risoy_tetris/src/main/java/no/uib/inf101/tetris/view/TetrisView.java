package no.uib.inf101.tetris.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.GridDimension;
import no.uib.inf101.tetris.model.GameState;

public class TetrisView extends JPanel {
    private final ViewableTetrisModel model;
    private final ColorTheme colorTheme;
    private static final int MARGIN = 20; // Margin rundt brettet
    private static final int CELL_SIZE = 30; // Størrelse på hver celle

    public TetrisView(ViewableTetrisModel model) {
        this.model = model;
        this.colorTheme = new ColorTheme();
        this.setBackground(colorTheme.getBackgroundColor());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Beregn størrelsen på brettet
        GridDimension dim = model.getDimension();
        int boardWidth = dim.cols() * CELL_SIZE;
        int boardHeight = dim.rows() * CELL_SIZE;

        // Tegn brettet
        for (GridCell cell : model.getTilesOnBoard()) {
            Rectangle2D rect = getCellRectangle(cell.pos(), boardWidth, boardHeight);
            g2.setColor(colorTheme.getCellColor(cell.symbol()));
            g2.fill(rect);
        }

        // Tegn den fallende tetrominoen
        for (GridCell cell : model.getFallingTetromino()) {
            Rectangle2D rect = getCellRectangle(cell.pos(), boardWidth, boardHeight);
            g2.setColor(colorTheme.getCellColor(cell.symbol()));
            g2.fill(rect);
        }

        // Tegn rutenettet
        g2.setColor(colorTheme.getFrameColor());
        // Tegn horisontale linjer
        for (int row = 0; row <= dim.rows(); row++) {
            int y = MARGIN + row * CELL_SIZE;
            g2.drawLine(MARGIN, y, MARGIN + boardWidth, y);
        }
        // Tegn vertikale linjer
        for (int col = 0; col <= dim.cols(); col++) {
            int x = MARGIN + col * CELL_SIZE;
            g2.drawLine(x, MARGIN, x, MARGIN + boardHeight);
        }

        // Tegn scoren
        drawScore(g2);

        // Tegn "Game Over"-skjermen hvis spillet er over
        if (model.getGameState() == GameState.GAME_OVER) {
            g2.setColor(new Color(0, 0, 0, 128)); // Gjennomsiktig svart
            g2.fillRect(0, 0, getWidth(), getHeight());

            g2.setColor(Color.WHITE);
            g2.setFont(new Font("Arial", Font.BOLD, 40));
            String gameOverText = "Game Over";
            int textWidth = g2.getFontMetrics().stringWidth(gameOverText);
            int textHeight = g2.getFontMetrics().getHeight();
            g2.drawString(gameOverText, (getWidth() - textWidth) / 2, (getHeight() - textHeight) / 2);
        }
    }
    // BONUS: Legge til scoren
    private void drawScore(Graphics2D g2) {
        g2.setColor(Color.WHITE); // Sett fargen til scoren
        g2.setFont(new Font("Arial", Font.BOLD, 20)); // Sett fonten

        // Hent scoren fra modellen
        int score = model.getScore();
        String scoreText = "Score: " + score;

        // Plasser scoren øverst til høyre
        int x = getWidth() - MARGIN - g2.getFontMetrics().stringWidth(scoreText);
        int y = MARGIN + g2.getFontMetrics().getAscent();
        g2.drawString(scoreText, x, y);
    }

    private Rectangle2D getCellRectangle(CellPosition pos, int boardWidth, int boardHeight) {
        double cellWidth = (double) boardWidth / model.getDimension().cols();
        double cellHeight = (double) boardHeight / model.getDimension().rows();
        double x = MARGIN + pos.col() * cellWidth;
        double y = MARGIN + pos.row() * cellHeight;
        return new Rectangle2D.Double(x, y, cellWidth, cellHeight);
    }

    @Override
    public Dimension getPreferredSize() {
        GridDimension dim = model.getDimension();
        int width = dim.cols() * CELL_SIZE + 2 * MARGIN;
        int height = dim.rows() * CELL_SIZE + 2 * MARGIN;
        return new Dimension(width, height);
    }
}