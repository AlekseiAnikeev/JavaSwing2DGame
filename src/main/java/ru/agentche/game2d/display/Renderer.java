package ru.agentche.game2d.display;

import ru.agentche.game2d.core.Position;
import ru.agentche.game2d.game.state.State;
import ru.agentche.game2d.map.GameMap;

import java.awt.*;

import static ru.agentche.game2d.game.Game.SPRITE_SIZE;

/**
 * @author Aleksey Anikeev aka AgentChe
 * Date of creation: 21.09.2022
 */
public class Renderer {
    public void render(State state, Graphics graphics) {
        renderMap(state, graphics);
        Camera camera = state.getCamera();
        state.getGameObject().stream()
                .filter(camera::isInView)
                .forEach(gameObject -> graphics.drawImage(
                gameObject.getSprite(),
                gameObject.getPosition().intX() - camera.getPosition().intX() - gameObject.getSize().getWidth() / 2,
                gameObject.getPosition().intY() - camera.getPosition().intY() - gameObject.getSize().getHeight() / 2,
                null
        ));
    }

    private void renderMap(State state, Graphics graphics) {
        GameMap map = state.getGameMap();
        Camera camera = state.getCamera();
        Position start = map.getViewableStartingGridPosition(camera);
        Position end = map.getViewableEndingGridPosition(camera);

        for (int x = start.intX(); x < end.intX(); x++) {
            for (int y = start.intY(); y < end.intY(); y++) {
                graphics.drawImage(
                        map.getTiles()[x][y].getSprite(),
                        x * SPRITE_SIZE - camera.getPosition().intX(),
                        y * SPRITE_SIZE - camera.getPosition().intY(),
                        null
                );
            }
        }
    }
}
