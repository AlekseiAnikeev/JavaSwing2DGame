package ru.agentche.game2d.game;

import ru.agentche.game2d.core.Size;
import ru.agentche.game2d.display.Display;
import ru.agentche.game2d.game.settings.GameSettings;
import ru.agentche.game2d.game.state.GameState;
import ru.agentche.game2d.game.state.State;
import ru.agentche.game2d.input.Input;

/**
 * @author Aleksey Anikeev aka AgentChe
 * Date of creation: 21.09.2022
 */
public class Game {
    public static int SPRITE_SIZE = 64;
    private final Display display;

    private final Input input;
    private final State state;
    private final GameSettings settings;

    public Game(int width, int height) {
        this.input = new Input();
        this.display = new Display(width, height, input);
        this.state = new GameState(new Size(width, height), input);
        this.settings = new GameSettings(true);
    }

    public void update() {
        state.update();
    }

    public void render() {
        display.render(state, settings.isDebugMode());
    }
}
