package no.uib.inf101.gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import no.uib.inf101.logic.Board;
import no.uib.inf101.logic.Controller;

public class GamePanelTest {
    @Test
    public void testGamePanelCanBeShownInFrame() {
        Controller controller = new Controller(new Board());
        GamePanel panel = new GamePanel(controller, new Board());

        JFrame frame = new JFrame();
        frame.add(panel);
        frame.pack();

        assertNotNull(panel);
        assertTrue(panel.isFocusable());
    }

    @Test
    public void testStartAnimationRuns() {
        Controller controller = new Controller(new Board(), false, false);
        GamePanel panel = new GamePanel(controller, new Board());

        List<AnimatedTile> tiles = new ArrayList<>();
        AnimatedTile tile = new AnimatedTile(2, 0, 0);
        tile.setTarget(100, 0);
        tiles.add(tile);

        assertDoesNotThrow(() -> panel.startAnimation(tiles));
    }

}
