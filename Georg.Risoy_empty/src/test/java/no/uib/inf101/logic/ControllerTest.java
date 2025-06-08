package no.uib.inf101.logic;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class ControllerTest {
    private Controller controller;
    private Board board;

    @BeforeEach
    public void setup() {
        board = new Board();
        controller = new Controller(board);
        controller.setSpawnEnabled(false); // 🔑 Deaktiver tilfeldig spawning i test

        // Nullstill brettet
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                board.setTile(i, j, null);
            }
        }
    }

    @Test
    public void testMoveLeftMergesTiles() {
        board.setTile(0, 0, new Tile(2));
        board.setTile(0, 1, new Tile(2));

        controller.moveAndGetMoves("left");

        assertNotNull(board.getTile(0, 0));
        assertEquals(4, board.getTile(0, 0).getValue());
        assertNull(board.getTile(0, 1));
    }

    @Test
    public void testIsGameOverWithMock() {
        Tile[][] fullBoard = new Tile[4][4];
        int value = 2;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                fullBoard[i][j] = new Tile(value);
                value += 2;
            }
        }

        MockBoard mockBoard = new MockBoard(fullBoard);
        Controller controller = new Controller(mockBoard);
        controller.setSpawnEnabled(false);

        assertTrue(controller.isGameOver());
    }

    @Test
    public void testNoMergeDifferentTiles() {
        board.setTile(0, 0, new Tile(2));
        board.setTile(0, 1, new Tile(4));

        controller.moveAndGetMoves("left");

        assertEquals(2, board.getTile(0, 0).getValue());
        assertEquals(4, board.getTile(0, 1).getValue());
    }

    @Test
    public void testMergeOnlyOncePerMove() {
        board.setTile(0, 0, new Tile(2));
        board.setTile(0, 1, new Tile(2));
        board.setTile(0, 2, new Tile(2));

        controller.moveAndGetMoves("left");

        assertEquals(4, board.getTile(0, 0).getValue());
        assertEquals(2, board.getTile(0, 1).getValue());
        assertNull(board.getTile(0, 2));
    }

    @Test
    public void testIsGameOverTrue() {
        int value = 2;
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                board.setTile(i, j, new Tile(value));
                value += 2;
            }
        }

        assertTrue(controller.isGameOver());
    }

    @Test
    public void testIsGameOverFalseDueToEmptyTile() {
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                board.setTile(i, j, new Tile(2));
            }
        }
        board.setTile(2, 2, null);

        assertFalse(controller.isGameOver());
    }

    @Test
    public void testSpawnTileAfterValidMove() {
        controller.setSpawnEnabled(true); // Kun for denne testen
        board.setTile(0, 0, new Tile(2));
        board.setTile(0, 1, new Tile(2));
        int before = countNonNullTiles();

        controller.moveAndGetMoves("left");

        int after = countNonNullTiles();
        assertEquals(before - 1 + 1, after);
    }

    private int countNonNullTiles() {
        int count = 0;
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                if (board.getTile(i, j) != null)
                    count++;
            }
        }
        return count;
    }

    @Test
    public void testMoveLeftWithMock() {
        Tile[][] tiles = new Tile[4][4];
        tiles[0][1] = new Tile(2);
        tiles[0][2] = new Tile(2);

        MockBoard mockBoard = new MockBoard(tiles);
        Controller controller = new Controller(mockBoard, false, false);

        controller.setSpawnEnabled(false);
        controller.setInitTiles(false);

        List<TileMove> moves = controller.moveAndGetMoves("left");

        assertEquals(4, mockBoard.getTile(0, 0).getValue());
        assertNull(mockBoard.getTile(0, 1));
        assertTrue(moves.size() >= 1);
    }

    @Test
    public void testMoveRightWithMock() {
        Tile[][] tiles = new Tile[4][4];
        tiles[2][0] = new Tile(4);
        tiles[2][1] = new Tile(4);

        MockBoard mockBoard = new MockBoard(tiles);
        Controller controller = new Controller(mockBoard, false, false);
        controller.setSpawnEnabled(false);
        controller.setInitTiles(false);

        List<TileMove> moves = controller.moveAndGetMoves("right");

        assertEquals(8, mockBoard.getTile(2, 3).getValue());
        assertNull(mockBoard.getTile(2, 2));
        assertTrue(moves.size() >= 1);
    }

    @Test
    public void testMoveUpWithMock() {
        Tile[][] tiles = new Tile[4][4];
        tiles[2][1] = new Tile(2);
        tiles[3][1] = new Tile(2);

        MockBoard mockBoard = new MockBoard(tiles);
        Controller controller = new Controller(mockBoard, false, false);
        controller.setSpawnEnabled(false);
        controller.setInitTiles(false);

        List<TileMove> moves = controller.moveAndGetMoves("up");

        assertEquals(4, mockBoard.getTile(0, 1).getValue());
        assertNull(mockBoard.getTile(1, 1));
        assertTrue(moves.size() >= 1);
    }

    @Test
    public void testMoveDownWithMock() {
        Tile[][] tiles = new Tile[4][4];
        tiles[0][3] = new Tile(8);
        tiles[1][3] = new Tile(8);

        MockBoard mockBoard = new MockBoard(tiles);
        Controller controller = new Controller(mockBoard, false, false);
        controller.setSpawnEnabled(false);
        controller.setInitTiles(false);

        List<TileMove> moves = controller.moveAndGetMoves("down");

        assertEquals(16, mockBoard.getTile(3, 3).getValue());
        assertNull(mockBoard.getTile(2, 3));
    }

    @Test
    public void testScoreIncreasesAfterMerge() {
        Tile[][] tiles = new Tile[4][4];
        tiles[0][0] = new Tile(2);
        tiles[0][1] = new Tile(2);

        MockBoard board = new MockBoard(tiles);
        Controller controller = new Controller(board, false, false);

        int scoreBefore = controller.getScore();
        controller.moveAndGetMoves("left");
        int scoreAfter = controller.getScore();

        assertEquals(scoreBefore + 4, scoreAfter); // 2 + 2 = 4
    }


    @Test
    public void testIsGameOverFalseIfMergePossible() {
        Tile[][] tiles = new Tile[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                tiles[i][j] = new Tile(4);
            }
        }

        MockBoard board = new MockBoard(tiles);
        Controller controller = new Controller(board, false, false);

        assertFalse(controller.isGameOver());
    }


    @Test
    public void testSpawnOnNoMoveButSpaceAvailable() {
        // Lag et brett med én tile, resten tomt
        Tile[][] tiles = new Tile[4][4];
        tiles[0][0] = new Tile(2);
    
        MockBoard board = new MockBoard(tiles);
        Controller controller = new Controller(board, false, true); // spawnEnabled = true
    
        int before = countNonNullTiles(board);
    
        // Gjør et trekk som ikke forandrer noe
        controller.moveAndGetMoves("up");
    
        int after = countNonNullTiles(board);
    
        
        assertEquals(before + 1, after);
    }
    // Privat for test av alltid spawne brike
    private int countNonNullTiles(Board board) {
        int count = 0;
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                if (board.getTile(i, j) != null)
                    count++;
            }
        }
        return count;
    }
    
}
