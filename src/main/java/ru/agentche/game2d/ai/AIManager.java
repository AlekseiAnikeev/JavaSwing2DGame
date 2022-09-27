package ru.agentche.game2d.ai;

import ru.agentche.game2d.ai.state.AIState;
import ru.agentche.game2d.ai.state.Stand;
import ru.agentche.game2d.ai.state.Wander;
import ru.agentche.game2d.entity.NPC;
import ru.agentche.game2d.game.state.State;

/**
 * @author Aleksey Anikeev aka AgentChe
 * Date of creation: 23.09.2022
 */
public class AIManager {
    private AIState currentAIState;

    public AIManager() {
        transitionTo("stand");
    }

    public void update(State state, NPC currentCharacter) {
        currentAIState.update(state, currentCharacter);
        if (currentAIState.shouldTransition(state, currentCharacter)) {
            transitionTo(currentAIState.getNextState());
        }
    }

    private void transitionTo(String nextState) {
        switch (nextState) {
            case "wander" -> currentAIState = new Wander();

            case "stand", default -> currentAIState = new Stand();
        }
    }
}
