package ru.agentche.game2d.gfx;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Aleksey Anikeev aka AgentChe
 * Date of creation: 21.09.2022
 */
public class SpriteSet {
    // лист с анимациями
    private final Map<String, Image> animationSheets;

    public SpriteSet() {
        this.animationSheets = new HashMap<>();
    }

    /**
     * Метод для добавления в лист
     */
    public void addSheet(String name, Image animationSheet) {
        animationSheets.put(name, animationSheet);
    }
    /**
     * Метод для получения листа
     */
    public Image get(String name) {
        return animationSheets.get(name);
    }
}
