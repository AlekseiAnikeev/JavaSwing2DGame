package ru.agentche.game2d.entity.effect;

import ru.agentche.game2d.entity.MovingEntity;
import ru.agentche.game2d.entity.action.Cough;
import ru.agentche.game2d.game.GameLoop;
import ru.agentche.game2d.game.state.State;

/**
 * @author Aleksey Anikeev aka AgentChe
 * Date of creation: 27.09.2022
 */
public class Sick extends Effect {

    //задаем период для отработки действия болезни
    private static final double COUGH_RATE = 1d / GameLoop.UPDATES_PER_SECOND / 10;

    public Sick() {
        super(Integer.MAX_VALUE);
    }

    @Override
    public void update(State state, MovingEntity entity) {
        super.update(state, entity);

        if (Math.random() < COUGH_RATE) {
            entity.perform(new Cough());
        }

    }
}
