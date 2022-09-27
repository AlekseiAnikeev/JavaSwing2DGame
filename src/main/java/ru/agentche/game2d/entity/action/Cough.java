package ru.agentche.game2d.entity.action;

import ru.agentche.game2d.entity.MovingEntity;
import ru.agentche.game2d.game.GameLoop;
import ru.agentche.game2d.game.state.State;

/**
 * @author Aleksey Anikeev aka AgentChe
 * Date of creation: 27.09.2022
 */
public class Cough extends Action {

    private int lifeSpanInSeconds;

    public Cough() {
        this.lifeSpanInSeconds = GameLoop.UPDATES_PER_SECOND;

    }

    @Override
    public void update(State state, MovingEntity entity) {
        lifeSpanInSeconds--;
    }

    @Override
    public boolean isDone() {
        return lifeSpanInSeconds <= 0;
    }

    @Override
    public String getAnimationName() {
        return "cough";
    }
}
