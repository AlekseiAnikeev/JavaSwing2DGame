package ru.agentche.game2d.map;

import ru.agentche.game2d.gfx.SpriteLibrary;

import java.awt.*;

/**
 * @author Aleksey Anikeev aka AgentChe
 * Date of creation: 23.09.2022
 */
public class Tile {
    private Image sprite;

    public Tile(SpriteLibrary spriteLibrary) {
        this.sprite = spriteLibrary.getTile("woodfloor");
    }

    public Image getSprite() {
        return sprite;
    }
}
