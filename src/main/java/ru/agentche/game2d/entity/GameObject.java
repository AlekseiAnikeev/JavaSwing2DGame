package ru.agentche.game2d.entity;

import ru.agentche.game2d.core.CollisionBox;
import ru.agentche.game2d.core.Position;
import ru.agentche.game2d.core.Size;
import ru.agentche.game2d.game.state.State;

import java.awt.*;

/**
 * @author Aleksey Anikeev aka AgentChe
 * Date of creation: 21.09.2022
 */
public abstract class GameObject {
    protected Position position;
    protected Size size;

    public GameObject() {
        position = new Position(50, 50);
        size = new Size(50, 50);
    }

    public abstract void update(State state);

    public abstract Image getSprite();
    //для проверки столкновений
    public abstract CollisionBox getCollisionBox();
    //для проверки столкновений
    public abstract boolean collidesWith(GameObject other);

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Size getSize() {
        return size;
    }
}
