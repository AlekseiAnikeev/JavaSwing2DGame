package ru.agentche.game2d.game.state;

import ru.agentche.game2d.controller.NPCController;
import ru.agentche.game2d.controller.PlayerController;
import ru.agentche.game2d.core.Position;
import ru.agentche.game2d.core.Size;
import ru.agentche.game2d.entity.NPC;
import ru.agentche.game2d.entity.Player;
import ru.agentche.game2d.input.Input;
import ru.agentche.game2d.map.GameMap;

import java.util.List;

import static ru.agentche.game2d.game.Game.SPRITE_SIZE;

/**
 * @author Aleksey Anikeev aka AgentChe
 * Date of creation: 23.09.2022
 */
public class GameState extends State {
    public GameState(Size windowSize, Input input) {
        super(windowSize, input);
        gameMap = new GameMap(new Size(20, 20), spriteLibrary);
        initializeCharacters();
    }

    private void initializeCharacters() {
        Player player = new Player(new PlayerController(input), spriteLibrary);
        NPC npc = new NPC(new NPCController(), spriteLibrary);
        npc.setPosition(new Position(3 * SPRITE_SIZE, 2 * SPRITE_SIZE));
        gameObject.addAll(List.of(player, npc));
        //следим за игроком
        camera.focusOn(player);
    }
}
