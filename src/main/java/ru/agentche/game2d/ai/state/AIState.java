package ru.agentche.game2d.ai.state;

import ru.agentche.game2d.ai.AITransition;
import ru.agentche.game2d.entity.NPC;
import ru.agentche.game2d.game.state.State;

/**
 * @author Aleksey Anikeev aka AgentChe
 * Date of creation: 23.09.2022
 */
public abstract class AIState {
    private final AITransition transition;

    public AIState() {
        this.transition = initializeTransition();
    }

    protected abstract AITransition initializeTransition();

    public abstract void update(State state, NPC currentCharacter);

    public boolean shouldTransition(State state, NPC currentCharacter) {
        return transition.shouldTransition(state, currentCharacter);
    }

    public String getNextState() {
        return transition.getNextState();
    }
}
