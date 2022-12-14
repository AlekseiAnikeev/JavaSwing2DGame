package ru.agentche.game2d.core;

import ru.agentche.game2d.controller.Controller;

/**
 * @author Aleksey Anikeev aka AgentChe
 * Date of creation: 21.09.2022
 */
public class Motion {
    private Vector2D vector;
    private final double speed;

    public Motion(double speed) {
        this.speed = speed;
        vector = new Vector2D(0, 0);
    }

    public void update(Controller controller) {
        int deltaX = 0;
        int deltaY = 0;
        if (controller.isRequestingUp()) {
            deltaY--;
        }
        if (controller.isRequestingDown()) {
            deltaY++;
        }
        if (controller.isRequestingLeft()) {
            deltaX--;
        }
        if (controller.isRequestingRight()) {
            deltaX++;
        }
        vector = new Vector2D(deltaX, deltaY);
        vector.normalize();
        vector.multiply(speed);
    }

    public Vector2D getVector() {
        return vector;
    }

    public boolean isMoving() {
        return vector.length() > 0;
    }

    public void multiply(double multiplier) {
        vector.multiply(multiplier);
    }

    public void stop(boolean stopX, boolean stopY) {
        vector = new Vector2D(
                stopX ? 0 : vector.getX(),
                stopY ? 0 : vector.getY()
        );
    }
}
