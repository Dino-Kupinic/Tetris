/*-----------------------------------------------------------------------------
 *              Hoehere Technische Bundeslehranstalt STEYR
 *----------------------------------------------------------------------------*/
/**
 * Kurzbeschreibung
 *
 * @author  : Dino Kupinic
 * @date    : 22.4.2023
 *
 * @details
 * Class to store a player
 */

package at.ac.htlsteyr.tetris.Model;

public class Player {
    private String name;
    private Integer highscore;

    public Player(String name, Integer highscore) {
        this.name = name;
        this.highscore = highscore;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getHighscore() {
        return highscore;
    }

    public void setHighscore(Integer highscore) {
        this.highscore = highscore;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", highscore=" + highscore +
                '}';
    }
}

