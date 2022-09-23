package ru.agentche.game2d.game;

import static ru.agentche.game2d.game.GameLoop.UPDATES_PER_SECOND;

/**
 * @author Aleksey Anikeev aka AgentChe
 * Date of creation: 23.09.2022
 */
public class Time {
    private int updateSinceStart;

    public Time() {
        this.updateSinceStart = 0;
    }

    public int getUpdatesFromSeconds(int seconds) {
        return seconds * UPDATES_PER_SECOND;
    }
}
