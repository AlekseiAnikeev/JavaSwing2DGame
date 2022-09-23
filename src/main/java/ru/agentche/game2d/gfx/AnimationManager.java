package ru.agentche.game2d.gfx;

import ru.agentche.game2d.core.Direction;
import ru.agentche.game2d.game.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

import static ru.agentche.game2d.game.Game.SPRITE_SIZE;

/**
 * @author Aleksey Anikeev aka AgentChe
 * Date of creation: 22.09.2022
 */
public class AnimationManager {
    private SpriteSet spriteSet;
    private BufferedImage currentAnimationSheet;
    //количество обновлений анимаций за кадр
    private int updatePerFrame;
    private int currentFrameTime;
    //для отслеживания текущего кадра
    private int frameIndex;
    //для отслеживания направления движением
    private int directionIndex;

    public AnimationManager(SpriteSet spriteSet) {
        this.spriteSet = spriteSet;
        this.updatePerFrame = 20;
        this.frameIndex = 0;
        this.currentFrameTime = 0;
        this.directionIndex = 0;
        playAnimation("stand");
    }

    public Image getSprite() {
        return currentAnimationSheet.getSubimage(
                frameIndex * SPRITE_SIZE,
                directionIndex * SPRITE_SIZE,
                SPRITE_SIZE,
                SPRITE_SIZE
        );
    }

    public void update(Direction direction) {
        currentFrameTime++;
        directionIndex = direction.getAnimationRow();
        if (currentFrameTime >= updatePerFrame) {
            currentFrameTime = 0;
            frameIndex++;

            if (frameIndex >= currentAnimationSheet.getWidth() / SPRITE_SIZE) {
                frameIndex = 0;
            }
        }
    }

    public void playAnimation(String name) {
        this.currentAnimationSheet = (BufferedImage) spriteSet.get(name);
    }
}
