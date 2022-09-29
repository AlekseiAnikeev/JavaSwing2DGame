package ru.agentche.game2d.display;

import ru.agentche.game2d.core.CollisionBox;
import ru.agentche.game2d.entity.GameObject;
import ru.agentche.game2d.game.state.State;

import java.awt.*;

/**
 * @author Aleksey Anikeev aka AgentChe
 * Date of creation: 29.09.2022
 */
public class DebugRenderer {
    public void render(State state, Graphics graphics) {
        Camera camera = state.getCamera();
        state.getGameObject().stream()
                .filter(camera::isInView)
                .map(GameObject::getCollisionBox)
                .forEach(collisionBox -> drawCollisionBox(collisionBox, graphics, camera));
    }

    private void drawCollisionBox(CollisionBox collisionBox, Graphics graphics, Camera camera) {
        graphics.setColor(Color.RED);
        graphics.drawRect(
                (int) collisionBox.getBounds().getX() - camera.getPosition().intX(),
                (int) collisionBox.getBounds().getY() - camera.getPosition().intY(),
                (int) collisionBox.getBounds().getWidth(),
                (int) collisionBox.getBounds().getHeight()
        );
    }
}
