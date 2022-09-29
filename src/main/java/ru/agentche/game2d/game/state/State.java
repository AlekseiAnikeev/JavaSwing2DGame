package ru.agentche.game2d.game.state;

import ru.agentche.game2d.core.Position;
import ru.agentche.game2d.core.Size;
import ru.agentche.game2d.display.Camera;
import ru.agentche.game2d.entity.GameObject;
import ru.agentche.game2d.game.Time;
import ru.agentche.game2d.gfx.SpriteLibrary;
import ru.agentche.game2d.input.Input;
import ru.agentche.game2d.map.GameMap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Aleksey Anikeev aka AgentChe
 * Date of creation: 22.09.2022
 */
public abstract class State {

    protected GameMap gameMap;
    protected List<GameObject> gameObjects;
    protected SpriteLibrary spriteLibrary;
    protected Input input;
    protected Camera camera;
    protected Time time;

    public State(Size windowSize, Input input) {
        this.input = input;
        this.gameObjects = new ArrayList<>();
        this.spriteLibrary = new SpriteLibrary();
        this.camera = new Camera(windowSize);
        this.time = new Time();
    }

    public void update() {
        sortObjectsByPosition();
        gameObjects.forEach(gameObjects -> gameObjects.update(this));
        camera.update(this);
    }

    /**
     * Метод для сортировки по оси Y наших спрайтов
     * дабы не было перекрытий объектами во время рендера
     * и наш плеер не перекрывался другими объектами
     * находясь возле них
     */
    private void sortObjectsByPosition() {
        gameObjects.sort(Comparator.comparing(gameObjects -> gameObjects.getPosition().getY()));
    }

    public List<GameObject> getGameObject() {
        return gameObjects;
    }

    public GameMap getGameMap() {
        return gameMap;
    }

    public Camera getCamera() {
        return camera;
    }

    public Time getTime() {
        return time;
    }

    public Position getRandomPosition() {
        return gameMap.getRandomPosition();
    }

    /**
     * Метод получения списка столкновений
     */
    public List<GameObject> getCollidingGameObjects(GameObject gameObject) {
        return gameObjects.stream()
                .filter(other -> other.collidesWith(gameObject))
                .collect(Collectors.toList());
    }
}
