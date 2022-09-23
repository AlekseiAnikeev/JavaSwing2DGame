package ru.agentche.game2d.controller;

import ru.agentche.game2d.input.Input;

import java.awt.event.KeyEvent;

/**
 * @author Aleksey Anikeev aka AgentChe
 * Date of creation: 21.09.2022
 */
public class PlayerController implements Controller {

    private Input input;

    public PlayerController(Input input) {
        this.input = input;
    }

    @Override
    public boolean isRequestingUp() {
        return input.isPressed(KeyEvent.VK_UP);
    }

    @Override
    public boolean isRequestingDown() {
        return input.isPressed(KeyEvent.VK_DOWN);
    }

    @Override
    public boolean isRequestingRight() {
        return input.isPressed(KeyEvent.VK_RIGHT);
    }

    @Override
    public boolean isRequestingLeft() {
        return input.isPressed(KeyEvent.VK_LEFT);
    }
}
