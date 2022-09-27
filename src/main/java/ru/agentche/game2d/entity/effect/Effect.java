package ru.agentche.game2d.entity.effect;

import ru.agentche.game2d.entity.MovingEntity;
import ru.agentche.game2d.game.state.State;

/**
 * @author Aleksey Anikeev aka AgentChe
 * Date of creation: 27.09.2022
 */
public abstract class Effect {
    private int lifeSpanInUpdate;

    public Effect(int lifeSpanInUpdate) {
        this.lifeSpanInUpdate = lifeSpanInUpdate;
    }

    public void update(State state, MovingEntity entity) {
        lifeSpanInUpdate--;
    }

    public boolean shouldDelete() {
        return lifeSpanInUpdate <= 0;
    }
}
