package ru.agentche.game2d.ai.state;

import ru.agentche.game2d.ai.AITransition;
import ru.agentche.game2d.controller.NPCController;
import ru.agentche.game2d.core.Position;
import ru.agentche.game2d.entity.NPC;
import ru.agentche.game2d.game.state.State;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Aleksey Anikeev aka AgentChe
 * Date of creation: 23.09.2022
 */
public class Wander extends AIState {
    private final List<Position> targets;

    public Wander() {
        super();
        this.targets = new ArrayList<>();
    }

    @Override
    protected AITransition initializeTransition() {
        return new AITransition("stand", ((state, currentCharacter) -> arrived(currentCharacter)));
    }

    @Override
    public void update(State state, NPC currentCharacter) {
        if (targets.isEmpty()) {
            targets.add(state.getRandomPosition());
        }

        NPCController controller = (NPCController) currentCharacter.getController();
        controller.moveToTarget(targets.get(0), currentCharacter.getPosition());

        if (arrived(currentCharacter)) {
            // чтобы после смены состояния контроллер перестал эмулировать нажатие клавиш
            controller.stop();
        }
    }

    private boolean arrived(NPC currentCharacter) {
        return currentCharacter.getPosition().isInRangeOf(targets.get(0));
    }
}
