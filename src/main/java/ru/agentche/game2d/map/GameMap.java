package ru.agentche.game2d.map;

import ru.agentche.game2d.core.Size;
import ru.agentche.game2d.gfx.SpriteLibrary;

import java.util.Arrays;

import static ru.agentche.game2d.game.Game.SPRITE_SIZE;

/**
 * @author Aleksey Anikeev aka AgentChe
 * Date of creation: 23.09.2022
 */
public class GameMap {
    private Tile[][] tiles;

    public GameMap(Size size, SpriteLibrary spriteLibrary) {
        tiles = new Tile[size.getWidth()][size.getHeight()];
        initializeTiles(spriteLibrary);
    }

    private void initializeTiles(SpriteLibrary spriteLibrary) {
        for(Tile[] row : tiles) {
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
}