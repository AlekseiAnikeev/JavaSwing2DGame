package ru.agentche.game2d.core;

import java.awt.*;

/**
 * @author Aleksey Anikeev aka AgentChe
 * Date of creation: 29.09.2022
 */
public class CollisionBox {
    // создаем прямоугольник указывающий границы столкновения
    private final Rectangle bounds;

    public CollisionBox(Rectangle bounds) {
        this.bounds = bounds;
    }

    public static CollisionBox of(Position position, Size size) {
        return new CollisionBox(
                new Rectangle(
                        position.intX(),
                        position.intY(),
                        size.getWidth(),
                        size.getHeight()
                )
        );
    }

    /**
     * Метод проверки столкновений
     */
    public boolean collidesWith(CollisionBox other) {
        return bounds.intersects(other.getBounds());
    }

    public Rectangle getBounds() {
        return bounds;
    }
}
