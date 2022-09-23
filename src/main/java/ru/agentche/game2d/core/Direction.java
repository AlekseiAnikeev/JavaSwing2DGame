package ru.agentche.game2d.core;

/**
 * @author Aleksey Anikeev aka AgentChe
 * Date of creation: 22.09.2022
 */
public enum Direction {
    S(0),
    SW(1),
    W(2),
    NW(3),
    N(4),
    NE(5),
    E(6),
    SE(7);
    private int animationRow;

    Direction(int animationRow) {
        this.animationRow = animationRow;
    }

    public static Direction fromMotion(Motion motion) {
        double x = motion.getVector().getX();
        double y = motion.getVector().getY();
        //движение на юг
        if (x == 0 && y > 0) return S;
        //движение на запад
        if (x < 0 && y == 0) return W;
        //движение на север
        if (x == 0 && y < 0) return N;
        //движение на восток
        if (x > 0 && y == 0) return E;
        //движение на юго-запад
        if (x < 0 && y > 0) return SW;
        //движение на северо-запад
        if (x < 0 && y < 0) return NW;
        //движение на северо-восток
        if (x > 0 && y < 0) return NE;
        //движение на юго-восток
        if (x > 0 && y > 0) return SE;
        //по умолчанию
        return S;
    }

    public int getAnimationRow() {
        return animationRow;
    }
}
