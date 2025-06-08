package no.uib.inf101.logic;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class HighscoreEntryTest {

    @Test
    public void testCompareTo() {
        HighscoreEntry entry1 = new HighscoreEntry("Alice", 1000);
        HighscoreEntry entry2 = new HighscoreEntry("Bob", 500);
        
        assertTrue(entry1.compareTo(entry2) < 0); // 1000 > 500 skal gi negativ verdi
        assertTrue(entry2.compareTo(entry1) > 0);
    }
}