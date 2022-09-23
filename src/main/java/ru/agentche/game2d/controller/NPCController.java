package ru.agentche.game2d.controller;

import ru.agentche.game2d.core.Position;

/**
 * @author Aleksey Anikeev aka AgentChe
 * Date of creation: 23.09.2022
 */
public class NPCController implements Controller {
    private boolean up;
    private boolean right;
    private boolean down;
    private boolean left;

    @Override
    public boolean isRequestingUp() {
        return up;
    }

    @Override
    public boolean isRequestingRight() {
        return right;
    }

    @Override
    public boolean isRequestingDown() {
        return down;
    }

    @Override
    public boolean isRequestingLeft() {
        return left;
    }

    public void moveToTarget(Position target, Position current) {
        double deltaX = target.getX() - current.getX();
        double deltaY = target.getY() - current.getY();

        up = deltaY < 0 && Math.abs(deltaY) > Position.CLOSE_RANGE;
        right = deltaX > 0 && Math.abs(deltaX) > Position.CLOSE_RANGE;
        down = deltaY > 0 && Math.abs(deltaY) > Position.CLOSE_RANGE;
        left = deltaX < 0 && Math.abs(deltaX) > Position.CLOSE_RANGE;
    }

    public void stop() {
        up = false;
        right = false;
        down = false;
        left = false;
    }
}
