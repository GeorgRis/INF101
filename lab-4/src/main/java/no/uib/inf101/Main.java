package no.uib.inf101;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.Grid;
import no.uib.inf101.grid.IGrid;
import no.uib.inf101.view.GridView;

public class Main {
    public static void main(String[] args) {
        IGrid grid = new Grid(5, 5, '-');

        grid.set(new CellPosition(0, 0), 'A');
        grid.set(new CellPosition(2, 2), 'B');
        grid.set(new CellPosition(4, 4), 'C');
        grid.set(new CellPosition(3, 1), 'D');

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Grid View");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setContentPane(new GridView(grid));
            frame.pack();
            frame.setVisible(true);
        });
    }
}
