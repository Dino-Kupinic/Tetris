package at.ac.htlsteyr.tetris.Model;

public class Controls {
    private String moveRight;
    private String moveLeft;
    private String fastDrop;
    private String rotate;
    private Boolean musicCheckbox;
    private String hold;
    private String softdrop;

    public Controls(
            String moveRight,
            String moveLeft,
            String fastDrop,
            String rotate,
            Boolean musicCheckbox,
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
}
