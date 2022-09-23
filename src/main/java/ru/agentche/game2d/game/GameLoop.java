package ru.agentche.game2d.game;

/**
 * @author Aleksey Anikeev aka AgentChe
 * Date of creation: 21.09.2022
 */
public class GameLoop implements Runnable {
    public static final int UPDATES_PER_SECOND = 60;
    private Game game;
    private boolean running;
    private final double updateRate = 1.0d / UPDATES_PER_SECOND;

    private long nextStateTime;
    private int fps;
    private int ups;

    public GameLoop(Game game) {
        this.game = game;
    }

    @Override
    public void run() {
        running = true;
        double accumulator = 0;
        long currentTime;
        long lastUpdate = System.currentTimeMillis();
        nextStateTime = System.currentTimeMillis() + 1000;


        while (running) {
            currentTime = System.currentTimeMillis();
            double lastRenderTimeSeconds = (currentTime - lastUpdate) / 1000d;
            accumulator += lastRenderTimeSeconds;
            lastUpdate = currentTime;

            //ограничим частоту кадров (fps) 60 раз в секунду
            if (accumulator >= updateRate) {
                //рендер
                while (accumulator > updateRate) {
                    update();
                    accumulator -= updateRate;
                }
                render();
            }
            printStats();
        }
    }

    private void printStats() {
        if (System.currentTimeMillis() > nextStateTime) {
            System.out.println(String.format("FPS(кадры в секунду): %d, UPS(обновления в секунду): %d", fps, ups));
            fps = 0;
            ups = 0;
            nextStateTime = System.currentTimeMillis() + 1000;
        }
    }

    private void update() {
        game.update();
        ups++;

    }

    private void render() {
        game.render();
        fps++;
    }
}
