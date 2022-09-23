package ru.agentche.game2d.core;

/**
 * @author Aleksey Anikeev aka AgentChe
 * Date of creation: 21.09.2022
 */
public class Position {
    private double x;
    private double y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int intX() {
        return (int) Math.round(x);
    }

    public int intY() {
        return (int) Math.round(y);
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void apply(Motion motion) {
        Vector2D vector = motion.getVector();
        x += vector.getX();
        y += vector.getY();
    }
}
