package no.uib.inf101.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.Timer;

import no.uib.inf101.logic.Board;
import no.uib.inf101.logic.Controller;
import no.uib.inf101.logic.HighscoreEntry;
import no.uib.inf101.logic.Tile;
import no.uib.inf101.logic.TileMove;

public class GamePanel extends JPanel {
    private final Controller controller;
    private final Board board;
    private final int TILE_SIZE = 100;
    private final int TILE_MARGIN = 16;
    private List<AnimatedTile> animatedTiles = new ArrayList<>();
    private int highscore = 0;
    private Runnable restartCallback;
    private List<HighscoreEntry> highscores = new ArrayList<>();



    public GamePanel(Controller controller, Board board) {
        this.controller = controller;
        this.board = board;
        setFocusable(true);
        requestFocusInWindow();

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (controller.isGameOver() && e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (restartCallback != null) {
                        restartCallback.run();
                    }
                    return;
                }
        
                String direction = switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP -> "up";
                    case KeyEvent.VK_DOWN -> "down";
                    case KeyEvent.VK_LEFT -> "left";
                    case KeyEvent.VK_RIGHT -> "right";
                    default -> null;
                };
        
                if (direction != null) {
                    List<TileMove> moves = controller.moveAndGetMoves(direction);
                    List<AnimatedTile> animTiles = new ArrayList<>();
                    int tileSize = 100;
                    int tileMargin = 16;
        
                    for (TileMove move : moves) {
                        float startX = move.fromCol * (tileSize + tileMargin) + tileMargin;
                        float startY = move.fromRow * (tileSize + tileMargin) + tileMargin;
                        float endX = move.toCol * (tileSize + tileMargin) + tileMargin;
                        float endY = move.toRow * (tileSize + tileMargin) + tileMargin;
        
                        AnimatedTile anim = new AnimatedTile(move.value, startX, startY);
                        anim.setTarget(endX, endY);
                        animTiles.add(anim);
                    }
        
                    startAnimation(animTiles);
                }
            }
        });
        
    }
    
    /**
     * Sets the highscores list. A copy of the list is stored, not the original
     * list. The highscores are used to display the highscore list in the game
     * over screen.
     * @param highscores the list of highscores
     */
    public void setHighscores(List<HighscoreEntry> highscores) {
        this.highscores = new ArrayList<>(highscores);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Beregn størrelser og posisjoner
        int boardSize = board.getSize() * (TILE_SIZE + TILE_MARGIN) + TILE_MARGIN;
        int scorePanelHeight = 100;

        // Tegn bakgrunn
        g2d.setColor(new Color(0xbbada0));
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // Tegn brettet (forskyv litt ned for å gi plass til score)
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                drawTile(g2d, null, j, i);
            }
        }

        // Tegn brikker (hvis ikke animering)
        if (animatedTiles.isEmpty()) {
            for (int i = 0; i < board.getSize(); i++) {
                for (int j = 0; j < board.getSize(); j++) {
                    Tile tile = board.getTile(i, j);
                    if (tile != null) {
                        drawTile(g2d, tile, j, i);
                    }
                }
            }
        }

        // Tegn animerte brikker
        for (AnimatedTile tile : animatedTiles) {
            drawAnimatedTile(g2d, tile);
        }

        // Tegn score-panelet UNDER brettet
        drawScorePanel(g2d, boardSize, scorePanelHeight);

        if (controller.isGameOver()) {
            drawGameOverScreen(g2d);
        }
    }
    private void drawScorePanel(Graphics2D g2d, int boardTop, int panelHeight) {
        int panelWidth = getWidth() - 40;
        int x = 20;
        int y = boardTop + 20; // Plasser under brettet
        int cornerRadius = 10;
    
        // Bakgrunn
        g2d.setColor(new Color(0xeee4da));
        g2d.fillRoundRect(x, y, panelWidth, panelHeight, cornerRadius, cornerRadius);
        g2d.setColor(new Color(0xbbada0));
        g2d.setStroke(new BasicStroke(2));
        g2d.drawRoundRect(x, y, panelWidth, panelHeight, cornerRadius, cornerRadius);
    
        // Del opp panelet i to kolonner
        int leftCol = x + 20;
        int rightCol = x + panelWidth/2 + 20;
    
        // Nåværende score
        g2d.setColor(new Color(0x776e65));
        g2d.setFont(new Font("SansSerif", Font.BOLD, 20));
        g2d.drawString("Score: " + controller.getScore(), leftCol, y + 30);
    
        // Highscore
        g2d.drawString("Highscore: " + highscore, leftCol, y + 60);
    
        // Highscore-liste (topp 3)
        g2d.setFont(new Font("SansSerif", Font.BOLD, 16));
        g2d.drawString("Toppspillere:", rightCol, y + 30);
        
        g2d.setFont(new Font("SansSerif", Font.PLAIN, 14));
        for (int i = 0; i < Math.min(highscores.size(), 3); i++) {
            HighscoreEntry entry = highscores.get(i);
            String entryText = String.format("%d. %s: %d", i+1, entry.getName(), entry.getScore());
            g2d.drawString(entryText, rightCol, y + 55 + i*20);
        }
    }

    private void drawTile(Graphics2D g, Tile tile, int x, int y) {
        int value = tile != null ? tile.getValue() : 0;
        int xOffset = offsetCoors(x);
        int yOffset = offsetCoors(y);

        g.setColor(getTileColor(value));
        g.fillRoundRect(xOffset, yOffset, TILE_SIZE, TILE_SIZE, 14, 14);

        g.setColor(value < 16 ? new Color(0x776e65) : new Color(0xf9f6f2));
        Font font = new Font("SansSerif", Font.BOLD, 36);
        g.setFont(font);

        if (value != 0) {
            String s = String.valueOf(value);
            FontMetrics fm = getFontMetrics(font);
            int w = fm.stringWidth(s);
            int h = -(int) fm.getLineMetrics(s, g).getBaselineOffsets()[2];
            g.drawString(s, xOffset + (TILE_SIZE - w) / 2, yOffset + TILE_SIZE / 2 + h / 2);
        }
    }

    private void drawAnimatedTile(Graphics2D g, AnimatedTile tile) {
        g.setColor(getTileColor(tile.value));
        g.fillRoundRect((int) tile.x, (int) tile.y, TILE_SIZE, TILE_SIZE, 14, 14);

        g.setColor(tile.value < 16 ? new Color(0x776e65) : new Color(0xf9f6f2));
        Font font = new Font("SansSerif", Font.BOLD, 36);
        g.setFont(font);

        String s = String.valueOf(tile.value);
        FontMetrics fm = getFontMetrics(font);
        int w = fm.stringWidth(s);
        int h = -(int) fm.getLineMetrics(s, g).getBaselineOffsets()[2];
        g.drawString(s, (int) tile.x + (TILE_SIZE - w) / 2, (int) tile.y + TILE_SIZE / 2 + h / 2);
    }

    private int offsetCoors(int arg) {
        return arg * (TILE_MARGIN + TILE_SIZE) + TILE_MARGIN;
    }

    private Color getTileColor(int value) {
        return switch (value) {
            case 2 -> new Color(0xeee4da);
            case 4 -> new Color(0xede0c8);
            case 8 -> new Color(0xf2b179);
            case 16 -> new Color(0xf59563);
            case 32 -> new Color(0xf67c5f);
            case 64 -> new Color(0xf65e3b);
            case 128 -> new Color(0xedcf72);
            case 256 -> new Color(0xedcc61);
            case 512 -> new Color(0xedc850);
            case 1024 -> new Color(0xedc53f);
            case 2048 -> new Color(0xedc22e);
            default -> new Color(0xcdc1b4);
        };
    }

    /**
     * Starts an animation for the given tiles. The animation moves the tiles
     * from their current position to their target position.
     *
     * The animation is performed by a Timer that updates the position of each
     * tile every 16 ms. The Timer stops when all tiles have reached their target
     * position.
     *
     * @param tiles the tiles to animate
     */
    public void startAnimation(List<AnimatedTile> tiles) {
        this.animatedTiles = tiles;

        Timer timer = new Timer(16, null); // 60 FPS
        timer.addActionListener(e -> {
            boolean moving = false;
            for (AnimatedTile tile : animatedTiles) {
                tile.updatePosition(0.2f);
                if (tile.isMoving) moving = true;
            }
            repaint();
            if (!moving) {
                ((Timer) e.getSource()).stop();
                animatedTiles.clear();
                repaint(); // tegn sluttposisjoner
            }
        });
        timer.start();
    }

    /**
     * Sets the current highscore for this game.
     * @param hs the highscore.
     */
    public void setHighscore(int hs) {
        this.highscore = hs;
    }
    
    /**
     * Sets the callback to be executed when the game restarts.
     *
     * @param callback the Runnable to be executed upon game restart.
     */
    public void setRestartCallback(Runnable callback) {
        this.restartCallback = callback;
    }
 

    private void drawGameOverScreen(Graphics2D g2d) {
        // Halvgjennomsiktig overlay
        g2d.setColor(new Color(0, 0, 0, 150));
        g2d.fillRect(0, 0, getWidth(), getHeight());
    
        // Hovedmelding
        String message = "Game Over";
        Font font = new Font("SansSerif", Font.BOLD, 48);
        g2d.setFont(font);
        FontMetrics metrics = g2d.getFontMetrics(font);
        
        int textWidth = metrics.stringWidth(message);
        int textHeight = metrics.getHeight();
        int x = (getWidth() - textWidth) / 2;
        int y = (getHeight() - textHeight) / 2;
        
        // Tegn tekst med skyggeeffekt
        g2d.setColor(Color.BLACK);
        g2d.drawString(message, x+2, y+2);
        g2d.setColor(Color.WHITE);
        g2d.drawString(message, x, y);
    
        // Instruksjon om restart
        Font smallFont = new Font("SansSerif", Font.PLAIN, 18);
        g2d.setFont(smallFont);
        String restartMsg = "Trykk ENTER for å starte på nytt";
        int restartWidth = g2d.getFontMetrics().stringWidth(restartMsg);
        g2d.drawString(restartMsg, (getWidth() - restartWidth)/2, y + 50);
    }
}
