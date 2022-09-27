package ru.agentche.game2d.map;

import ru.agentche.game2d.core.Position;
import ru.agentche.game2d.core.Size;
import ru.agentche.game2d.display.Camera;
import ru.agentche.game2d.gfx.SpriteLibrary;

import java.util.Arrays;

import static ru.agentche.game2d.game.Game.SPRITE_SIZE;

/**
 * @author Aleksey Anikeev aka AgentChe
 * Date of creation: 23.09.2022
 */
public class GameMap {
    private static final int SAFETY_SPACE = 2;
    private final Tile[][] tiles;

    public GameMap(Size size, SpriteLibrary spriteLibrary) {
        tiles = new Tile[size.getWidth()][size.getHeight()];
        initializeTiles(spriteLibrary);
    }

    private void initializeTiles(SpriteLibrary spriteLibrary) {
        for (Tile[] row : tiles) {
            Arrays.fill(row, new Tile(spriteLibrary));
        }
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public int getWidth() {
        return tiles.length * SPRITE_SIZE;
    }

    public int getHeight() {
        return tiles[0].length * SPRITE_SIZE;
    }

    public Position getRandomPosition() {
        double x = Math.random() * tiles.length * SPRITE_SIZE;
        double y = Math.random() * tiles[0].length * SPRITE_SIZE;
        return new Position(x, y);
    }

    public Position getViewableStartingGridPosition(Camera camera) {
        return new Position(
                Math.max(0, camera.getPosition().getX() / SPRITE_SIZE - SAFETY_SPACE),
                Math.max(0, camera.getPosition().getY() / SPRITE_SIZE - SAFETY_SPACE)
        );
    }

    public Position getViewableEndingGridPosition(Camera camera) {
        return new Position(
                Math.min(tiles.length, camera.getPosition().getX() / SPRITE_SIZE + (double) camera.getSize().getWidth() / SPRITE_SIZE + SAFETY_SPACE),
                Math.min(tiles[0].length, camera.getPosition().getY() / SPRITE_SIZE + (double) camera.getSize().getHeight() / SPRITE_SIZE + SAFETY_SPACE)
        );
    }
}
