package no.uib.inf101.view;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

public class ColorTheme {
    private final Map<Character, Color> colorMap;
    private final Color defaultColor = Color.WHITE; // Standard farge for tomme celler

    public ColorTheme() {
        colorMap = new HashMap<>();
        initializeColors();
    }

    private void initializeColors() {
        colorMap.put('A', Color.RED);
        colorMap.put('B', Color.BLUE);
        colorMap.put('C', Color.GREEN);
        colorMap.put('D', Color.ORANGE);
        colorMap.put('E', Color.MAGENTA);
    }

    public Color getColor(Character symbol) {
        return colorMap.getOrDefault(symbol, defaultColor);
    }
}
