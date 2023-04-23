/*-----------------------------------------------------------------------------
 *              Hoehere Technische Bundeslehranstalt STEYR
 *----------------------------------------------------------------------------*/
/**
 * Kurzbeschreibung
 *
 * @author  : Dino Kupinic
 * @date    : 23.4.2023
 *
 * @details
 * Class used to handle the score
 */

package at.ac.htlsteyr.tetris.Model;

public class Score {
    private static int score = 0;
    private static final int BASE_SCORE_GAIN = 100;

    public static int getScore() {
        return score;
    }

    public static void setScore(int score) {
        Score.score = score;
    }

    /**
     * increases the score when the tetromino hits something
     */
    public static void increaseScoreOnCollision() {
        Score.score += BASE_SCORE_GAIN;
    }

    /**
     * increases the score by the amount of rows cleared
     * @param rows amount of rows
     */
    public static void increaseScoreOnRowsCleared(int rows) {
        final int ROW_WIDTH = 10;
        Score.score += BASE_SCORE_GAIN * (ROW_WIDTH + rows);
    }
}





