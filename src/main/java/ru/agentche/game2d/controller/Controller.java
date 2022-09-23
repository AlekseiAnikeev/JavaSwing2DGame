package ru.agentche.game2d.controller;

/**
 * @author Aleksey Anikeev aka AgentChe
 * Date of creation: 21.09.2022
 */
public interface Controller {
    boolean isRequestingUp();
    boolean isRequestingDown();
    boolean isRequestingRight();
    boolean isRequestingLeft();
}
