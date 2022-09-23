package ru.agentche.game2d.game.state;

import ru.agentche.game2d.controller.NPCController;
import ru.agentche.game2d.controller.PlayerController;
import ru.agentche.game2d.core.Size;
import ru.agentche.game2d.entity.NPC;
import ru.agentche.game2d.entity.Player;
import ru.agentche.game2d.input.Input;
import ru.agentche.game2d.map.GameMap;

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
        gameObject.add(player);
        //следим за игроком
        camera.focusOn(player);

        initializeNPCs(100);
    }

    private void initializeNPCs(int numberOfNPCs) {
        for (int i = 0; i < numberOfNPCs; i++) {
            NPC npc = new NPC(new NPCController(), spriteLibrary);
            npc.setPosition(gameMap.getRandomPosition());
            gameObject.add(npc);
        }
    }
}
