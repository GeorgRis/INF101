package no.uib.inf101.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import no.uib.inf101.gui.AnimatedTile;


public class Controller {
    private Board board;
    private Random random = new Random();
    private int score = 0;

    private boolean spawnEnabled = true;
    private boolean initTiles = true;

    // Konstruktør for ekte spill
    public Controller(Board board) {
        this.board = board;
        if (initTiles) {
            spawnNewTile();
            spawnNewTile();
        }
    }

    // Ny konstruktør for tester 
    public Controller(Board board, boolean initTiles, boolean spawnEnabled) {
        this.board = board;
        this.initTiles = initTiles;
        this.spawnEnabled = spawnEnabled;

        if (initTiles) {
            spawnNewTile();
            spawnNewTile();
        }
    }


    /**
     * Sets whether new tiles should be spawned after each move. Enabled by
     * default.
     * 
     * @param enabled whether new tiles should be spawned after each move
     */
    public void setSpawnEnabled(boolean enabled) {
        this.spawnEnabled = enabled;
    }

    /**
     * Sets whether the board should be initialized with two random tiles when a
     * new controller is created. Enabled by default.
     * 
     * @param enabled whether the board should be initialized with two random tiles
     */
    public void setInitTiles(boolean enabled) {
        this.initTiles = enabled;
    }

    /**
     * Moves the tiles in the given direction and returns a list of TileMove objects
     * representing the moves that were performed.
     * 
     * If the game is over, an empty list is returned.
     * 
     * If spawning is enabled and the move resulted in a change to the board, a
     * new tile is spawned.
     * 
     * @param direction the direction to move the tiles in, as a string. Valid
     *                  values are "up", "down", "left", and "right".
     * @return a list of TileMove objects representing the moves that were performed
     */
    public List<TileMove> moveAndGetMoves(String direction) {
        if (isGameOver()) return List.of();

        List<TileMove> moves = new ArrayList<>();
        Tile[][] oldTiles = copyBoard();

        String dir = direction.toLowerCase();
        switch (dir) {
            case "up" -> moveUp(moves);
            case "down" -> moveDown(moves);
            case "left" -> moveLeft(moves);
            case "right" -> moveRight(moves);
        }

        if (!boardsEqual(oldTiles, board)) {
            if (spawnEnabled) {
                spawnNewTile();
            }
        } else {
            if (spawnEnabled && hasEmptyTile()) {
                spawnNewTile();
            }
        }
        
        return moves;
    }
    
    /**
     * Returns the current score.
     *
     * @return the player's score
     */
    public int getScore() {
        return score;
    }
    /**
     * Determines if the game is over. The game is over when there are no empty tiles
     * and no adjacent tiles with the same value.
     *
     * @return true if the game is over, false otherwise
     */
    public boolean isGameOver() {
        int size = board.getSize();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Tile current = board.getTile(i, j);
                if (current == null) return false;
                if (i < size - 1) {
                    Tile down = board.getTile(i + 1, j);
                    if (down != null && current.getValue() == down.getValue()) return false;
                }
                if (j < size - 1) {
                    Tile right = board.getTile(i, j + 1);
                    if (right != null && current.getValue() == right.getValue()) return false;
                }
            }
        }
        return true;
    }

    private void moveLeft(List<TileMove> moves) {
        int size = board.getSize();
        for (int i = 0; i < size; i++) {
            List<Tile> row = new ArrayList<>();
            List<Integer> originalIndices = new ArrayList<>();
            for (int j = 0; j < size; j++) {
                Tile t = board.getTile(i, j);
                if (t != null) {
                    row.add(new Tile(t.getValue()));
                    originalIndices.add(j);
                }
            }
    
            List<Tile> merged = new ArrayList<>();
            int k = 0;
            for (int j = 0; j < row.size(); j++) {
                if (j < row.size() - 1 && row.get(j).getValue() == row.get(j + 1).getValue()) {
                    int newVal = row.get(j).getValue() * 2;
                    merged.add(new Tile(newVal));
                    score += newVal;
                    moves.add(new TileMove(i, originalIndices.get(j), i, k, newVal));
                    j++;
                } else {
                    merged.add(new Tile(row.get(j).getValue()));
                    moves.add(new TileMove(i, originalIndices.get(j), i, k, row.get(j).getValue()));
                }
                k++;
            }
    
            for (int j = 0; j < size; j++) {
                board.setTile(i, j, j < merged.size() ? merged.get(j) : null);
            }
        }
    }
    

    private void moveRight(List<TileMove> moves) {
        int size = board.getSize();
        for (int i = 0; i < size; i++) {
            List<Tile> row = new ArrayList<>();
            List<Integer> originalIndices = new ArrayList<>();
            for (int j = size - 1; j >= 0; j--) {
                Tile t = board.getTile(i, j);
                if (t != null) {
                    row.add(new Tile(t.getValue()));
                    originalIndices.add(j);
                }
            }
    
            List<Tile> merged = new ArrayList<>();
            int k = 0;
            for (int j = 0; j < row.size(); j++) {
                if (j < row.size() - 1 && row.get(j).getValue() == row.get(j + 1).getValue()) {
                    int newVal = row.get(j).getValue() * 2;
                    merged.add(new Tile(newVal));
                    score += newVal;
                    moves.add(new TileMove(i, originalIndices.get(j), i, size - 1 - k, newVal));
                    j++;
                } else {
                    merged.add(new Tile(row.get(j).getValue()));
                    moves.add(new TileMove(i, originalIndices.get(j), i, size - 1 - k, row.get(j).getValue()));
                }
                k++;
            }
    
            for (int j = size - 1, m = 0; j >= 0; j--, m++) {
                board.setTile(i, j, m < merged.size() ? merged.get(m) : null);
            }
        }
    }
    

    private void moveUp(List<TileMove> moves) {
        int size = board.getSize();
        for (int j = 0; j < size; j++) {
            List<Tile> col = new ArrayList<>();
            List<Integer> originalIndices = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                Tile t = board.getTile(i, j);
                if (t != null) {
                    col.add(new Tile(t.getValue()));
                    originalIndices.add(i);
                }
            }
    
            List<Tile> merged = new ArrayList<>();
            int k = 0;
            for (int i = 0; i < col.size(); i++) {
                if (i < col.size() - 1 && col.get(i).getValue() == col.get(i + 1).getValue()) {
                    int newVal = col.get(i).getValue() * 2;
                    merged.add(new Tile(newVal));
                    score += newVal;
                    moves.add(new TileMove(originalIndices.get(i), j, k, j, newVal));
                    i++;
                } else {
                    merged.add(new Tile(col.get(i).getValue()));
                    moves.add(new TileMove(originalIndices.get(i), j, k, j, col.get(i).getValue()));
                }
                k++;
            }
    
            for (int i = 0; i < size; i++) {
                board.setTile(i, j, i < merged.size() ? merged.get(i) : null);
            }
        }
    }
    

    private void moveDown(List<TileMove> moves) {
        int size = board.getSize();
        for (int j = 0; j < size; j++) {
            List<Tile> col = new ArrayList<>();
            List<Integer> originalIndices = new ArrayList<>();
            for (int i = size - 1; i >= 0; i--) {
                Tile t = board.getTile(i, j);
                if (t != null) {
                    col.add(new Tile(t.getValue()));
                    originalIndices.add(i);
                }
            }
    
            List<Tile> merged = new ArrayList<>();
            int k = 0;
            for (int i = 0; i < col.size(); i++) {
                if (i < col.size() - 1 && col.get(i).getValue() == col.get(i + 1).getValue()) {
                    int newVal = col.get(i).getValue() * 2;
                    merged.add(new Tile(newVal));
                    score += newVal;
                    moves.add(new TileMove(originalIndices.get(i), j, size - 1 - k, j, newVal));
                    i++;
                } else {
                    merged.add(new Tile(col.get(i).getValue()));
                    moves.add(new TileMove(originalIndices.get(i), j, size - 1 - k, j, col.get(i).getValue()));
                }
                k++;
            }
    
            for (int i = size - 1, m = 0; i >= 0; i--, m++) {
                board.setTile(i, j, m < merged.size() ? merged.get(m) : null);
            }
        }
    }

    private void spawnNewTile() {
        List<int[]> empty = new ArrayList<>();
        int size = board.getSize();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board.getTile(i, j) == null) {
                    empty.add(new int[] { i, j });
                }
            }
        }
        if (!empty.isEmpty()) {
            int[] pos = empty.get(random.nextInt(empty.size()));
            board.setTile(pos[0], pos[1], new Tile(random.nextDouble() < 0.9 ? 2 : 4));
        }
    }

    private Tile[][] copyBoard() {
        int size = board.getSize();
        Tile[][] copy = new Tile[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Tile t = board.getTile(i, j);
                if (t != null) {
                    copy[i][j] = new Tile(t.getValue());
                } else {
                    copy[i][j] = null;
                }
            }
        }
        return copy;
    }

    private boolean boardsEqual(Tile[][] boardArray, Board boardObj) {
        int size = boardObj.getSize();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Tile t1 = boardArray[i][j];
                Tile t2 = boardObj.getTile(i, j);
                if (t1 == null && t2 == null) continue;
                if (t1 == null || t2 == null) return false;
                if (t1.getValue() != t2.getValue()) return false;
            }
        }
        return true;
    }
    /**
     * Creates a list of demo animated tiles for demonstration purposes.
     * 
     * This method initializes a list of AnimatedTile objects, each with a 
     * specific value and starting position. The first tile is created at 
     * position (0,0) and then its target position is set to move to column 1.
     * 
     * @return a list of AnimatedTile objects configured for a demo animation
     */
    public List<AnimatedTile> getDemoAnimationTiles() {
        List<AnimatedTile> animTiles = new ArrayList<>();
        animTiles.add(new AnimatedTile(2, 16, 16)); // Start fra (0,0)
        animTiles.get(0).setTarget(16 + 1 * (100 + 16), 16); // Flytt til kolonne 1
        return animTiles;
    }

    // For å spawne tiles uansett når trykker egen implementasjon som ikke er i spillet
    private boolean hasEmptyTile() {
        int size = board.getSize();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board.getTile(i, j) == null)
                    return true;
            }
        }
        return false;
    }

} 
