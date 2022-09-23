package ru.agentche.game2d.ai;

import ru.agentche.game2d.entity.NPC;
import ru.agentche.game2d.game.state.State;

/**
 * @author Aleksey Anikeev aka AgentChe
 * Date of creation: 23.09.2022
 */
public interface AICondition {
    boolean isMet(State state, NPC currentCharacter);
}
