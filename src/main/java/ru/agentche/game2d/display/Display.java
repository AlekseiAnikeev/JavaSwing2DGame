package ru.agentche.game2d.display;

import ru.agentche.game2d.game.state.State;
import ru.agentche.game2d.input.Input;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

/**
 * @author Aleksey Anikeev aka AgentChe
 * Date of creation: 21.09.2022
 */
public class Display extends JFrame {
    private final Canvas canvas;
    private final Renderer renderer;
    private final DebugRenderer debugRenderer;

    public Display(int width, int height, Input input) {
        setTitle("Новая 2D игра");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        this.renderer = new Renderer();
        this.debugRenderer = new DebugRenderer();

        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setFocusable(false);
        add(canvas);
        addKeyListener(input);
        pack();

        //для отрисовки следующей картинки и удаления предыдущей(чтобы экран не мерцал)
        canvas.createBufferStrategy(2);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void render(State state, boolean debugMode) {
        BufferStrategy bufferStrategy = canvas.getBufferStrategy();
        Graphics graphics = bufferStrategy.getDrawGraphics();

        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        renderer.render(state, graphics);
        if(debugMode) {
            debugRenderer.render(state, graphics);
        }

        graphics.dispose();

        //вывод на передний план
        bufferStrategy.show();

    }
}
