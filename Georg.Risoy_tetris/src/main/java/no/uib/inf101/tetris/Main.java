package no.uib.inf101.tetris;

import javax.swing.JFrame;

import no.uib.inf101.tetris.controller.TetrisController;
import no.uib.inf101.tetris.model.TetrisModel;
import no.uib.inf101.tetris.model.tetromino.RandomTetrominoFactory;
import no.uib.inf101.tetris.view.TetrisView;

public class Main {
    public static void main(String[] args) {
        TetrisModel model = new TetrisModel(new RandomTetrominoFactory());
        TetrisView view = new TetrisView(model);

        // Opprett kontrolleren og koble den til modellen og visningen
        TetrisController controller = new TetrisController(model, view);

        // Opprett hovedvinduet (JFrame) for spillet
        JFrame frame = new JFrame("INF101 Tetris");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Avslutt programmet når vinduet lukkes
        frame.setContentPane(view); // Legg til visningen i vinduet
        frame.pack(); // Tilpass vinduet til innholdet
        frame.setVisible(true); // Gjør vinduet synlig
    }
}