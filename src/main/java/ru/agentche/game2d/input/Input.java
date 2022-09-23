package ru.agentche.game2d.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @author Aleksey Anikeev aka AgentChe
 * Date of creation: 21.09.2022
 */
public class Input implements KeyListener {
    private boolean[] pressed;

    public Input() {
        this.pressed = new boolean[255];
    }
    public boolean isPressed(int keyCode) {
        return pressed[keyCode];
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // если нажмем кнопку, то запишем в массив значение для нее в true
        pressed[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // если отпустим кнопку, то запишем в массив значение для нее в false
        pressed[e.getKeyCode()] = false;
    }
}
