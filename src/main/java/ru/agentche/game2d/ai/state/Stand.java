package ru.agentche.game2d.ai.state;

import ru.agentche.game2d.ai.AITransition;
import ru.agentche.game2d.entity.NPC;
import ru.agentche.game2d.game.state.State;

/**
 * @author Aleksey Anikeev aka AgentChe
 * Date of creation: 23.09.2022
 */
public class Stand extends AIState{

    // сколько времени нпц стоит
    private int updatesAlive;
    @Override
    protected AITransition initializeTransition() {
        return new AITransition("wander", ((state, currentCharacter)-> updatesAlive >= state.getTime().getUpdatesFromSeconds(3)));
    }

    @Override
    public void update(State state, NPC currentCharacter) {
        updatesAlive++;
    }
}
