package ru.agentche.game2d.entity;

import ru.agentche.game2d.controller.Controller;
import ru.agentche.game2d.gfx.AnimationManager;
import ru.agentche.game2d.gfx.SpriteLibrary;

/**
 * @author Aleksey Anikeev aka AgentChe
 * Date of creation: 23.09.2022
 */
public class NPC extends MovingEntity{
     public NPC(Controller controller, SpriteLibrary spriteLibrary) {
        super(controller, spriteLibrary);
        animationManager = new AnimationManager(spriteLibrary.getUnit("dave"));
    }
}
