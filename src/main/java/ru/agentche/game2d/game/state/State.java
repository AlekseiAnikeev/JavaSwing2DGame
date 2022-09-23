package ru.agentche.game2d.game.state;

import ru.agentche.game2d.core.Size;
import ru.agentche.game2d.display.Camera;
import ru.agentche.game2d.entity.GameObject;
import ru.agentche.game2d.gfx.SpriteLibrary;
import ru.agentche.game2d.input.Input;
import ru.agentche.game2d.map.GameMap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author Aleksey Anikeev aka AgentChe
 * Date of creation: 22.09.2022
 */
public abstract class State {

    protected GameMap gameMap;
    protected List<GameObject> gameObject;
    protected SpriteLibrary spriteLibrary;
    protected Input input;
    protected Camera camera;

    public State(Size windowSize, Input input) {
        this.input = input;
        gameObject = new ArrayList<>();
        spriteLibrary = new SpriteLibrary();
        camera = new Camera(windowSize);
    }

    public void update() {
        sortObjectsByPosition();
        gameObject.forEach(GameObject::update);
        camera.update(this);
    }

    /**
     * Метод для сортировки по оси Y наших спрайтов
     * дабы не было перекрытий объектами во время рендера
     * и наш плеер не перекрывался другими объектами
     * находясь возле них
     */
    private void sortObjectsByPosition() {
        gameObject.sort(Comparator.comparing(gameObject -> gameObject.getPosition().getY()));
    }

    public List<GameObject> getGameObject() {
        return gameObject;
    }

    public GameMap getGameMap() {
        return gameMap;
    }

    public Camera getCamera() {
        return camera;
    }
}
