package ru.agentche.game2d.entity;

import ru.agentche.game2d.controller.Controller;
import ru.agentche.game2d.core.*;
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

    protected Size collisionBoxSize;

    public MovingEntity(Controller controller, SpriteLibrary spriteLibrary) {
        super();
        this.controller = controller;
        this.motion = new Motion(2);
        this.direction = Direction.S;
        this.animationManager = new AnimationManager(spriteLibrary.getUnit("alex"));
        this.effects = new ArrayList<>();
        this.action = Optional.empty();

        this.collisionBoxSize = new Size(16, 28);
    }

    @Override
    public void update(State state) {
        handlerAction(state);
        handleMotion();
        animationManager.update(direction);

        effects.forEach(effect -> effect.update(state, this));

        //вычислим коллизию
        handleCollision(state);
        manageDirection();
        decideAnimation();
        position.apply(motion);

        cleanup();
    }

    /**
     * Метод получения столкновений
     */
    private void handleCollision(State state) {
        state.getCollidingGameObjects(this).forEach(this::handleCollision);
    }

    protected abstract void handleCollision(GameObject other);

    private void handleMotion() {
        if (action.isEmpty()) {
            motion.update(controller);
        } else {
            motion.stop(true,true);
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
    public boolean collidesWith(GameObject other) {
         return getCollisionBox().collidesWith(other.getCollisionBox());
    }

    @Override
    public CollisionBox getCollisionBox() {
        Position positionWithMotion = Position.copyOf(position);
        positionWithMotion.apply(motion);
        return new CollisionBox(
                new Rectangle(
                        positionWithMotion.intX(),
                        positionWithMotion.intY(),
                        collisionBoxSize.getWidth(),
                        collisionBoxSize.getHeight()
                )
        );
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

    protected void clearEffects() {
        effects.clear();
    }

    public boolean willCollideX(GameObject other) {
        CollisionBox otherBox = other.getCollisionBox();
        Position positionWithXApplied = Position.copyOf(position);
        positionWithXApplied.applyX(motion);

        return CollisionBox.of(positionWithXApplied,collisionBoxSize).collidesWith(otherBox);
    }

    public boolean willCollideY(GameObject other) {
        CollisionBox otherBox = other.getCollisionBox();
        Position positionWithYApplied = Position.copyOf(position);
        positionWithYApplied.applyY(motion);

        return CollisionBox.of(positionWithYApplied,collisionBoxSize).collidesWith(otherBox);
    }
}
