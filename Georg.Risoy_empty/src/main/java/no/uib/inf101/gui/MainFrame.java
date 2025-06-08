package no.uib.inf101.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import no.uib.inf101.logic.Board;
import no.uib.inf101.logic.Controller;
import no.uib.inf101.logic.HighscoreEntry;

public class MainFrame extends JFrame {
    public GamePanel gamePanel;
    public Controller controller;
    private List<HighscoreEntry> highscores = new ArrayList<>();
    private int currentHighscore = 0;

    public MainFrame() {
        setTitle("2048");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 650);

        Board board = new Board();
        controller = new Controller(board);

        gamePanel = new GamePanel(controller, board);
        gamePanel.setHighscore(currentHighscore);
        gamePanel.setRestartCallback(() -> restartGame());

        add(gamePanel);
        setVisible(true);
        setLocationRelativeTo(null);
        gamePanel.requestFocusInWindow();
    }

    /**
     * Restarts the game.
     *
     * This method is called when the player wants to restart the game, or when
     * the game is over.
     *
     * The method first shows the highscore dialog if the player has gained a
     * score higher than the current highscore. Then it creates a new
     * {@link Board}, a new {@link Controller} for that board, and a new
     * {@link GamePanel} for that controller. The {@link GamePanel} is then
     * added to the frame, and the frame is repainted.
     *
     * @see #showHighscoreDialog
     */
    public void restartGame() {
        int lastScore = controller.getScore();
        boolean newHigh = lastScore > currentHighscore;
        
        if (lastScore > 0) {
            showHighscoreDialog(lastScore, newHigh);
        }

        Board newBoard = new Board();
        controller = new Controller(newBoard);
        gamePanel = new GamePanel(controller, newBoard);
        gamePanel.setHighscore(currentHighscore);
        gamePanel.setHighscores(highscores);
        gamePanel.setRestartCallback(() -> restartGame());

        getContentPane().removeAll();
        add(gamePanel);
        revalidate();
        repaint();
        gamePanel.requestFocusInWindow();
    }

    private void showHighscoreDialog(int lastScore, boolean newHigh) {
        // 1. Bygg highscore-listen med pen formatering
        StringBuilder highscoresText = new StringBuilder();
        highscoresText.append(String.format("%-5s %-15s %-10s%n", "Plass", "Navn", "Poeng"));
        highscoresText.append("------------------------------\n");
        
        for (int i = 0; i < Math.min(highscores.size(), 5); i++) {
            HighscoreEntry entry = highscores.get(i);
            highscoresText.append(String.format("%-5d %-15s %-10d%n", 
                i+1, 
                entry.getName(), 
                entry.getScore()));
        }

        // 2. Opprett en tekstboks med fast bredde-font
        JTextArea textArea = new JTextArea(highscoresText.toString());
        textArea.setEditable(false);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 12)); // Bruk Monospaced for jevn spacing
        textArea.setBackground(new Color(240, 240, 240)); // Lys grå bakgrunn

        // 3. Lag en panel for organisering
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // 4. Legg til overskrift
        JLabel title = new JLabel(newHigh ? "- NY HIGHSCORE! -" : "Highscore-liste");
        title.setFont(new Font("SansSerif", Font.BOLD, 14));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(title, BorderLayout.NORTH);

        // 5. Legg til highscore-tabellen
        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);

        // 6. Legg til din poengsum
        JLabel yourScore = new JLabel("Din poengsum: " + lastScore, SwingConstants.CENTER);
        yourScore.setFont(new Font("SansSerif", Font.BOLD, 12));
        panel.add(yourScore, BorderLayout.SOUTH);

        // 7. Vis dialog og be om navn
        String playerName = JOptionPane.showInputDialog(
            this,
            panel,
            "Highscore",
            JOptionPane.PLAIN_MESSAGE
        );

        // 8. Lagre hvis navn er oppgitt
        if (playerName != null && !playerName.trim().isEmpty()) {
            highscores.add(new HighscoreEntry(playerName.trim(), lastScore));
            Collections.sort(highscores);
            if (highscores.size() > 5) {
                highscores = highscores.subList(0, 5);
            }
            if (newHigh) {
                currentHighscore = lastScore;
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainFrame::new);
    }
}