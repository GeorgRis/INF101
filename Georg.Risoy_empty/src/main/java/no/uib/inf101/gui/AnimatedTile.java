package no.uib.inf101.gui;

public class AnimatedTile {
    public int value;
    public float x, y;
    public float targetX, targetY;
    public boolean isMoving = false;

    public AnimatedTile(int value, float x, float y) {
        this.value = value;
        this.x = x;
        this.y = y;
        this.targetX = x;
        this.targetY = y;
    }

    /**
     * Sets the target position for this tile to move to.
     * Sets the moving flag to true.
     * @param tx target x-coordinate
     * @param ty target y-coordinate
     */
    public void setTarget(float tx, float ty) {
        this.targetX = tx;
        this.targetY = ty;
        this.isMoving = true;
    }


    /**
     * Updates the position of this tile based on the given alpha value.
     * When the difference between the current position and the target position
     * is less than 1, the moving flag is set to false and the position is set
     * to the target position.
     * @param alpha the alpha value in the range [0, 1] to control the speed of
     * the animation
     */
    public void updatePosition(float alpha) {
        x += (targetX - x) * alpha;
        y += (targetY - y) * alpha;
        if (Math.abs(x - targetX) < 1 && Math.abs(y - targetY) < 1) {
            x = targetX;
            y = targetY;
            isMoving = false;
        }
    }
}
