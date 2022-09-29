package ru.agentche.game2d.game.settings;

/**
 * @author Aleksey Anikeev aka AgentChe
 * Date of creation: 29.09.2022
 */
public class GameSettings {

    private final boolean debugMode;

    public GameSettings(boolean debugMode) {
        this.debugMode = debugMode;
    }

    public boolean isDebugMode() {
        return debugMode;
    }
}
