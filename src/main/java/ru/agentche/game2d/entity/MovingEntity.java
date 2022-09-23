package ru.agentche.game2d.entity;

import ru.agentche.game2d.controller.Controller;
import ru.agentche.game2d.core.Direction;
import ru.agentche.game2d.core.Motion;
import ru.agentche.game2d.game.state.State;
import ru.agentche.game2d.gfx.AnimationManager;
import ru.agentche.game2d.gfx.SpriteLibrary;

import java.awt.*;

/**
 * @author Aleksey Anikeev aka AgentChe
 * Date of creation: 21.09.2022
 */
public abstract class MovingEntity extends GameObject {
    protected Controller controller;
    protected Motion motion;
    protected AnimationManager animationManager;
    protected Direction direction;

    public MovingEntity(Controller controller, SpriteLibrary spriteLibrary) {
        super();
        this.controller = controller;
        this.motion = new Motion(2);
        this.direction = Direction.S;
        this.animationManager = new AnimationManager(spriteLibrary.getUnit("alex"));
    }

    @Override
    public void update(State state){
        motion.update(controller);
        position.apply(motion);
        manageDirection();
        decideAnimation();
        animationManager.update(direction);
    }

    private void decideAnimation() {
        if(motion.isMoving()){
            animationManager.playAnimation("walk");
        } else {
            animationManager.playAnimation("stand");
        }
    }

    private void manageDirection() {
        if(motion.isMoving()) {
            this.direction = Direction.fromMotion(motion);
        }
    }

    @Override
    public Image getSprite() {
       return animationManager.getSprite();
    }

    public Controller getController() {
        return controller;
    }
}
