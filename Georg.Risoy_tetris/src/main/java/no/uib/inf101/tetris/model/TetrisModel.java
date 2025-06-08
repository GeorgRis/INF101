package no.uib.inf101.tetris.model;

import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.GridDimension;
import no.uib.inf101.tetris.controller.ControllableTetrisModel;
import no.uib.inf101.tetris.model.tetromino.Tetromino;
import no.uib.inf101.tetris.model.tetromino.TetrominoFactory;
import no.uib.inf101.tetris.view.ViewableTetrisModel;

public class TetrisModel implements ViewableTetrisModel, ControllableTetrisModel {
    private final TetrisBoard board;
    private final TetrominoFactory tetrominoFactory;
    private Tetromino fallingTetromino;
    private GameState gameState; // tilstandsvariabel
    private int score; // Poengscore

    public TetrisModel(TetrominoFactory tetrominoFactory) {
        this.board = new TetrisBoard(15, 10);
        this.tetrominoFactory = tetrominoFactory;
        this.fallingTetromino = tetrominoFactory.getNext().shiftedToTopCenterOf(this.board);
        this.gameState = GameState.ACTIVE_GAME; // Initialiser til aktivt spill
        this.score = 0; // Initaliser poengscore
    }

    @Override
    public GridDimension getDimension() {
        return board;
    }

    @Override
    public Iterable<GridCell> getTilesOnBoard() {
        return board;
    }

    @Override
    public Iterable<GridCell> getFallingTetromino() {
        return fallingTetromino;
    }

    @Override
    public GameState getGameState() {
        return gameState;
    }

    public int getScore() {
        return score;
    }

    @Override
    public boolean moveTetromino(int deltaRow, int deltaCol) {
        if (gameState != GameState.ACTIVE_GAME) {
            return false; // Ikke tillatt å flytte hvis spillet er over
        }
        Tetromino newTetromino = fallingTetromino.shiftedBy(deltaRow, deltaCol);
        if (canPlaceTetromino(newTetromino)) {
            fallingTetromino = newTetromino;
            return true;
        }
        return false;
    }

    @Override // Fikse denne sånn kan rotere i starten. Done
    public boolean rotateTetromino() {
        if (gameState != GameState.ACTIVE_GAME) {
            return false; // Ikke tillatt å rotere hvis spillet er over
        }
        Tetromino rotated = fallingTetromino.rotatedCopy();
    
        // Sjekk om den roterte tetrominoen kan plasseres
        if (canPlaceTetromino(rotated)) {
            fallingTetromino = rotated;
            return true;
        }
    
        // Prøv å justere posisjonen for å få tetrominoen innenfor brettet
        for (int deltaRow = 0; deltaRow <= 1; deltaRow++) { // Prøv først ingen justering, deretter flytt nedover
            for (int deltaCol = -1; deltaCol <= 1; deltaCol++) { // Prøv å flytte til venstre, ingen justering, eller til høyre
                Tetromino shifted = rotated.shiftedBy(deltaRow, deltaCol);
                if (canPlaceTetromino(shifted)) {
                    fallingTetromino = shifted;
                    return true;
                }
            }
        }
    
        // Hvis vi ikke klarer å plassere tetrominoen etter justering, returner false
        return false;
    }

    @Override
    public void dropTetromino() {
        if (gameState != GameState.ACTIVE_GAME) {
            return; // Ikke tillatt å slippe hvis spillet er over
        }
        while (moveTetromino(1, 0)) {
            // Fortsett å flytte nedover
        }
    }


    // BONUS: Øk forsinkelsen basert på poengsummen for å øke vanskeligheten
    @Override
    public int getTimerDelay() {
        // Reduser forsinkelsen med 50 ms for hver 1000 poeng
        return Math.max(200, 1000 - (score / 1000) * 50);
    }

    /**
     * Handles the periodic game tick event.
     * 
     * If the game is active, attempts to move the falling tetromino one row downward.
     * If the tetromino cannot move further down, places it on the board and checks 
     * for filled rows to clear. Updates the score based on the number of rows cleared.
     * 
     * If a new tetromino cannot be placed after clearing rows, 
     * the game state is set to GAME_OVER.
     */
    public void clockTick() {
        if (gameState != GameState.ACTIVE_GAME) {
            return;
        }
        if (!moveTetromino(1, 0)) {
            placeTetrominoOnBoard();
            int rowsRemoved = board.clearFilledRows(); // Fjern fulle rader
            
            // Forbedret poengsystem basert på antall rader fjernet
            if (rowsRemoved > 0) {
                int pointsToAdd = 0;
                switch (rowsRemoved) {
                    case 1:
                        pointsToAdd = 100;
                        break;
                    case 2:
                        pointsToAdd = 300;
                        break;
                    case 3:
                        pointsToAdd = 500;
                        break;
                    case 4:
                        pointsToAdd = 800;
                        break;
                    default:
                        // For enhver annen verdi (noe som egt ikke skal skje)
                        pointsToAdd = rowsRemoved * 100;
                }
                score += pointsToAdd;
            }
            
            Tetromino newTetromino = tetrominoFactory.getNext().shiftedToTopCenterOf(this.board);
            if (!canPlaceTetromino(newTetromino)) {
                gameState = GameState.GAME_OVER;
            } else {
                fallingTetromino = newTetromino;
            }
        }
    }

    /**
     * Checks if the given tetromino can be placed on the board without collision.
     * 
     * Iterates over each cell of the tetromino and verifies that it is within the
     * board boundaries and does not overlap with existing filled positions.
     * 
     * @param tetromino The tetromino to check for placement.
     * @return true if the tetromino can be placed on the board, false otherwise.
     */
    private boolean canPlaceTetromino(Tetromino tetromino) {
        for (GridCell cell : tetromino) {
            if (!board.positionIsOnGrid(cell.pos()) || board.get(cell.pos()) != '-') {
                return false;
            }
        }
        return true;
    }


    private void placeTetrominoOnBoard() {
        for (GridCell cell : fallingTetromino) {
            board.set(cell.pos(), cell.symbol());
        }
    }

    public Tetromino getFallingTetrominoObject() {
        return fallingTetromino;
    }
}