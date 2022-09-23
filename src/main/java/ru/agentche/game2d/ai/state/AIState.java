package ru.agentche.game2d.ai.state;

import ru.agentche.game2d.ai.AITransition;

/**
 * @author Aleksey Anikeev aka AgentChe
 * Date of creation: 23.09.2022
 */
public abstract class AIState {
    private AITransition transition;

    public AIState() {
        this.transition = initializeTransition();
    }

    protected abstract AITransition initializeTransition();
}
