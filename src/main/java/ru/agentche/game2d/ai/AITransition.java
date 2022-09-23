package ru.agentche.game2d.ai;

import ru.agentche.game2d.entity.NPC;
import ru.agentche.game2d.game.state.State;

/**
 * @author Aleksey Anikeev aka AgentChe
 * Date of creation: 23.09.2022
 */
public class AITransition {
    private String nextState;
    private AICondition condition;

    public AITransition(String nextState, AICondition condition) {
        this.nextState = nextState;
        this.condition = condition;
    }

    public boolean shouldTransition(State state, NPC currentCharacter) {
        return condition.isMet(state,currentCharacter);
    }

    public String getNextState() {
        return nextState;
    }
}
