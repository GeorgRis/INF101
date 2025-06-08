package no.uib.inf101.tetris.controller;

import no.uib.inf101.tetris.model.GameState;

public interface ControllableTetrisModel {
    boolean moveTetromino(int deltaRow, int deltaCol);
    boolean rotateTetromino();
    void dropTetromino();
    GameState getGameState();
    int getTimerDelay();
    void clockTick();
}