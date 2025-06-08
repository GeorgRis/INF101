package no.uib.inf101.main;

import javax.swing.SwingUtilities;

import no.uib.inf101.gui.MainFrame;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}