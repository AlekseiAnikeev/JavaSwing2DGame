package ru.agentche.game2d.display;


import ru.agentche.game2d.core.Position;
import ru.agentche.game2d.core.Size;
import ru.agentche.game2d.entity.GameObject;
import ru.agentche.game2d.game.state.State;

import java.awt.*;
import java.util.Optional;

import static ru.agentche.game2d.game.Game.SPRITE_SIZE;

/**
 * @author Aleksey Anikeev aka AgentChe
 * Date of creation: 23.09.2022
 */
public class Camera {
    private static final int SAFETY_SPACE = 2 * SPRITE_SIZE;
    private final Position position;
    private final Size windowSize;
    //для области отображения
    private Rectangle viewBounds;

    private Optional<GameObject> objectWithFocus;

    public Camera(Size windowSize) {
        this.position = new Position(0, 0);
        this.windowSize = windowSize;
        calculateViewBounds();
    }

    private void calculateViewBounds() {
        viewBounds = new Rectangle(
                position.intX(),
                position.intY(),
                windowSize.getWidth() + SAFETY_SPACE,
                windowSize.getHeight() + SAFETY_SPACE
        );
    }

    public void focusOn(GameObject object) {
        this.objectWithFocus = Optional.of(object);
    }

    public void update(State state) {
        if (objectWithFocus.isPresent()) {
            Position objectPosition = objectWithFocus.get().getPosition();
            this.position.setX(objectPosition.getX() - (double) windowSize.getWidth() / 2);
            this.position.setY(objectPosition.getY() - (double) windowSize.getHeight() / 2);
            //ограничим камеру внутри сетки плиток(чтобы не видеть черный экран за картой)

            clampWithinBounds(state);
            calculateViewBounds();
        }
    }

    //метод для сброса камеры при достижении края карты
    private void clampWithinBounds(State state) {
        if (position.getX() < 0) {
            position.setX(0);
        }
        if (position.getY() < 0) {
            position.setY(0);
        }
        if (position.getX() + windowSize.getWidth() > state.getGameMap().getWidth()) {
            position.setX(state.getGameMap().getWidth() - windowSize.getWidth());
        }
        if (position.getY() + windowSize.getHeight() > state.getGameMap().getHeight()) {
            position.setY(state.getGameMap().getHeight() - windowSize.getHeight());
        }
    }

    public Position getPosition() {
        return position;
    }

    public boolean isInView(GameObject gameObject) {
        return viewBounds.intersects(
                gameObject.getPosition().intX(),
                gameObject.getPosition().intY(),
                gameObject.getSize().getWidth(),
                gameObject.getSize().getHeight()
        );
    }

    public Size getSize() {
        return windowSize;
    }
}
