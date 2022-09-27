package ru.agentche.game2d.entity;

import ru.agentche.game2d.controller.Controller;
import ru.agentche.game2d.core.Direction;
import ru.agentche.game2d.core.Motion;
import ru.agentche.game2d.entity.action.Action;
import ru.agentche.game2d.entity.effect.Effect;
import ru.agentche.game2d.game.state.State;
import ru.agentche.game2d.gfx.AnimationManager;
import ru.agentche.game2d.gfx.SpriteLibrary;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Aleksey Anikeev aka AgentChe
 * Date of creation: 21.09.2022
 */
public abstract class MovingEntity extends GameObject {
    protected Controller controller;
    protected Motion motion;
    protected AnimationManager animationManager;
    protected Direction direction;
    protected List<Effect> effects;
    protected Optional<Action> action;

    public MovingEntity(Controller controller, SpriteLibrary spriteLibrary) {
        super();
        this.controller = controller;
        this.motion = new Motion(2);
        this.direction = Direction.S;
        this.animationManager = new AnimationManager(spriteLibrary.getUnit("alex"));
        this.effects = new ArrayList<>();
        this.action = Optional.empty();
    }

    @Override
    public void update(State state) {
        handlerAction(state);
        handleMotion();
        animationManager.update(direction);

        effects.forEach(effect -> effect.update(state, this));

        manageDirection();
        decideAnimation();
        position.apply(motion);

        cleanup();
    }

    private void handleMotion() {
        if (action.isEmpty()) {
            motion.update(controller);
        } else {
            motion.stop();
        }
    }

    private void handlerAction(State state) {
        action.ifPresent(value -> value.update(state, this));
    }

    private void cleanup() {
        List.copyOf(effects).stream()
                .filter(Effect::shouldDelete)
                .forEach(effects::remove);
        if (action.isPresent() && action.get().isDone()) {
            action = Optional.empty();
        }
    }

    private void decideAnimation() {
        if (action.isPresent()) {
            animationManager.playAnimation(action.get().getAnimationName());
        } else if (motion.isMoving()) {
            animationManager.playAnimation("walk");
        } else {
            animationManager.playAnimation("stand");
        }
    }

    private void manageDirection() {
        if (motion.isMoving()) {
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

    public void multiplySpeed(double multiplier) {
        motion.multiply(multiplier);
    }

    public void perform(Action action) {
        this.action = Optional.of(action);
    }

    public void addEffect(Effect effect) {
        effects.add(effect);
    }
}
