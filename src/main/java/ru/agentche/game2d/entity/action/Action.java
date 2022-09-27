package ru.agentche.game2d.entity.action;

import ru.agentche.game2d.entity.MovingEntity;
import ru.agentche.game2d.game.state.State;

/**
 * @author Aleksey Anikeev aka AgentChe
 * Date of creation: 27.09.2022
 */
public abstract class Action {
    public abstract void update(State state, MovingEntity entity);

    public abstract boolean isDone();

    public abstract String getAnimationName();
}
