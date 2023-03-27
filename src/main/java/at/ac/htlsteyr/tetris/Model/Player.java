package at.ac.htlsteyr.tetris.Model;

public record Player(String name, Integer highscore) {
    @Override
    public String toString() {
        return "Player=[NAME: " + name + " | HIGHSCORE: " + highscore + "]";
    }
}

