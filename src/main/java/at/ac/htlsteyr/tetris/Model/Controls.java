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
 * Class responsible for holding data regarding controls
 */

package at.ac.htlsteyr.tetris.Model;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controls {
    private Boolean musicCheckbox;
    private String moveRight;
    private String moveLeft;
    private String fastDrop;
    private String rotate;
    private String hold;
    private String softdrop;

    public Controls(
            Boolean musicCheckbox,
            String moveRight,
            String moveLeft,
            String fastDrop,
            String rotate,
            String hold,
            String softdrop
    ) {
        this.moveRight = moveRight;
        this.moveLeft = moveLeft;
        this.fastDrop = fastDrop;
        this.rotate = rotate;
        this.musicCheckbox = musicCheckbox;
        this.hold = hold;
        this.softdrop = softdrop;
    }

    public String getMoveRight() {
        return moveRight;
    }

    public void setMoveRight(String moveRight) {
        this.moveRight = moveRight;
    }

    public String getMoveLeft() {
        return moveLeft;
    }

    public void setMoveLeft(String moveLeft) {
        this.moveLeft = moveLeft;
    }

    public String getFastDrop() {
        return fastDrop;
    }

    public void setFastDrop(String fastDrop) {
        this.fastDrop = fastDrop;
    }

    public String getRotate() {
        return rotate;
    }

    public void setRotate(String rotate) {
        this.rotate = rotate;
    }

    public Boolean getMusicCheckbox() {
        return musicCheckbox;
    }

    public void setMusicCheckbox(Boolean musicCheckbox) {
        this.musicCheckbox = musicCheckbox;
    }

    public String getHold() {
        return hold;
    }

    public void setHold(String hold) {
        this.hold = hold;
    }

    public String getSoftdrop() {
        return softdrop;
    }

    public void setSoftdrop(String softdrop) {
        this.softdrop = softdrop;
    }

    /**
     * Checks if the controls are correct
     * @param controlsObject object containing the controls
     * @return boolean whether everything is alright or not
     */
    public boolean checkValidValues(Controls controlsObject) {
        Pattern pattern = Pattern.compile("^[A-Z]");

        for (Method method : ((Object) controlsObject).getClass().getMethods()) {
            if (
                    method.getName().startsWith("get") &&
                    !method.getName().contains("MusicCheckbox") && // Non-string output
                    !method.getName().contains("Class") && // .getClass()
                    method.getParameterTypes().length == 0
            ) {
                try {
                    Object getterObj = method.invoke(controlsObject);
                    String value = getterObj.toString();
                    Matcher matcher = pattern.matcher(value);
                    if (!matcher.matches()) {
                        return false;
                    }
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return true;
    }
}
