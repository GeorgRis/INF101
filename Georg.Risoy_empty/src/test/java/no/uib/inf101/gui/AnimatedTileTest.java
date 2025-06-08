package no.uib.inf101.gui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class AnimatedTileTest {

    @Test
    public void testConstructorAndInitialPosition() {
        AnimatedTile tile = new AnimatedTile(8, 10f, 20f);

        assertEquals(8, tile.value);
        assertEquals(10f, tile.x);
        assertEquals(20f, tile.y);
        assertEquals(10f, tile.targetX);
        assertEquals(20f, tile.targetY);
        assertFalse(tile.isMoving);
    }

    @Test
    public void testSetTargetMarksMovingTrue() {
        AnimatedTile tile = new AnimatedTile(2, 0f, 0f);
        tile.setTarget(100f, 100f);

        assertTrue(tile.isMoving);
        assertEquals(100f, tile.targetX);
        assertEquals(100f, tile.targetY);
    }

    @Test
    public void testUpdateMovesCloserToTarget() {
        AnimatedTile tile = new AnimatedTile(2, 0f, 0f);
        tile.setTarget(100f, 0f);

        tile.updatePosition(0.5f); // halfway
        assertTrue(tile.x > 0f && tile.x < 100f);
        assertEquals(0f, tile.y);
        assertTrue(tile.isMoving);
    }

    @Test
    public void testUpdateFinishesMovement() {
        AnimatedTile tile = new AnimatedTile(2, 0f, 0f);
        tile.setTarget(0.5f, 0f); // veldig nærme

        tile.updatePosition(0.5f); // burde gå rett i mål
        assertEquals(0.5f, tile.x, 0.01f);
        assertFalse(tile.isMoving);
    }
}
