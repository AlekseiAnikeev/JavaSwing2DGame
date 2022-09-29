package ru.agentche.game2d.entity;

import ru.agentche.game2d.ai.AIManager;
import ru.agentche.game2d.controller.Controller;
import ru.agentche.game2d.game.state.State;
import ru.agentche.game2d.gfx.AnimationManager;
import ru.agentche.game2d.gfx.SpriteLibrary;

/**
 * @author Aleksey Anikeev aka AgentChe
 * Date of creation: 23.09.2022
 */
public class NPC extends MovingEntity {
    private final AIManager aiManager;

    public NPC(Controller controller, SpriteLibrary spriteLibrary) {
        super(controller, spriteLibrary);
        animationManager = new AnimationManager(spriteLibrary.getUnit("nikola"));
        aiManager = new AIManager();
    }

    public void update(State state) {
        super.update(state);
        aiManager.update(state, this);
    }

    /**
     * Реакция на столкновение
     */
    @Override
    protected void handleCollision(GameObject other) {
        if (other instanceof Player) {
            motion.stop(willCollideX(other), willCollideY(other));
        }
    }
}
