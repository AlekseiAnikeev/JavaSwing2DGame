package ru.agentche.game2d;

import ru.agentche.game2d.game.Game;
import ru.agentche.game2d.game.GameLoop;

/**
 * @author Aleksey Anikeev aka AgentChe
 * Date of creation: 21.09.2022
 */
public class Launcher {
    public static void main(String[] args) {
        new Thread(new GameLoop(new Game(800, 600))).start();
    }
}
