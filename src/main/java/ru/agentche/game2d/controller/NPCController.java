package ru.agentche.game2d.controller;

/**
 * @author Aleksey Anikeev aka AgentChe
 * Date of creation: 23.09.2022
 */
public class NPCController implements Controller {
    @Override
    public boolean isRequestingUp() {
        return false;
    }

    @Override
    public boolean isRequestingDown() {
        return false;
    }

    @Override
    public boolean isRequestingRight() {
        return false;
    }

    @Override
    public boolean isRequestingLeft() {
        return false;
    }
}
