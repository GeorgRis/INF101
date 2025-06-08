package no.uib.inf101.logic;

public class HighscoreEntry implements Comparable<HighscoreEntry> {
    private String name;
    private int score;

    public HighscoreEntry(String name, int score) {
        this.name = name;
        this.score = score;
    }

    /**
     * Returns the name of the player who achieved this high score.
     * 
     * @return the name of the player
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the score achieved by the player.
     * 
     * @return the score
     */
    public int getScore() {
        return score;
    }

    @Override
    public int compareTo(HighscoreEntry other) {
        return Integer.compare(other.score, this.score); // Sorterer i synkende rekkefølge
    }
}