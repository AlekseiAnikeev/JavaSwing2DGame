package ru.agentche.game2d.entity;

import ru.agentche.game2d.controller.Controller;
import ru.agentche.game2d.gfx.SpriteLibrary;

/**
 * @author Aleksey Anikeev aka AgentChe
 * Date of creation: 21.09.2022
 */
public class Player extends MovingEntity {
    public Player(Controller controller, SpriteLibrary spriteLibrary) {
        super(controller, spriteLibrary);
    }

    @Override
    protected void handleCollision(GameObject other) {
        if(other instanceof NPC npc) {
            npc.clearEffects();
        }
    }
}
