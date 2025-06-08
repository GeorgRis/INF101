package no.uib.inf101.tetris.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Timer;

import no.uib.inf101.tetris.midi.TetrisSong;
import no.uib.inf101.tetris.model.GameState;
import no.uib.inf101.tetris.view.TetrisView;

public class TetrisController implements KeyListener, ActionListener {
    private final ControllableTetrisModel model;
    private final TetrisView view;
    private final Timer timer;
    private final TetrisSong tetrisSong = new TetrisSong();

    public TetrisController(ControllableTetrisModel model, TetrisView view) {
        this.model = model;
        this.view = view;

        // Sett opp tastelytter
        this.view.setFocusable(true);
        this.view.addKeyListener(this);

        // Sett opp timeren med forsinkelse fra modellen
        this.timer = new Timer(model.getTimerDelay(), this);
        this.timer.start(); // Start timeren
        
        tetrisSong.run();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (model.getGameState() != GameState.ACTIVE_GAME) {
            return; // Ignorer tastetrykk hvis spillet er over
        }
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                model.moveTetromino(0, -1);
                break;
            case KeyEvent.VK_RIGHT:
                model.moveTetromino(0, 1);
                break;
            case KeyEvent.VK_DOWN:
                if (model.moveTetromino(1, 0)) {
                    timer.restart(); // Restart timeren hvis tetrominoen flyttet seg
                }
                break;
            case KeyEvent.VK_UP:
                model.rotateTetromino();
                break;
            case KeyEvent.VK_SPACE:
                model.dropTetromino();
                timer.restart(); // Restart timeren etter dropping
                break;
        }
        view.repaint(); // Oppdater visningen
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Ikke i bruk
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Ikke i bruk
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (model.getGameState() == GameState.ACTIVE_GAME) {
            model.clockTick(); // Håndter klokkeslag
            view.repaint();     // Oppdater visningen
        }
    }

    // Hjelpemetode for å oppdatere timeren
    public void updateTimerDelay() {
        timer.setDelay(model.getTimerDelay());
        timer.setInitialDelay(model.getTimerDelay());
    }
}