package ru.agentche.game2d.gfx;

import ru.agentche.game2d.core.Size;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

/**
 * @author Aleksey Anikeev aka AgentChe
 * Date of creation: 21.09.2022
 */
public class ImageUtils {

    public static final int ALPHA_OPAQUE = 1;
    public static final int ALPHA_BIT_MASKED = 2;
    public static final int ALPHA_BLEND = 3;

    /**
     * Метод для загрузки изображений
     * @param filePath - путь до файла
     */
    public static Image loadImage(String filePath) {
        try {
            Image imageFromDisk = ImageIO.read(Objects.requireNonNull(ImageUtils.class.getResource(filePath)));
            BufferedImage compatibleImage = (BufferedImage) createCompatibleImage(
                    new Size(
                            imageFromDisk.getWidth(null),
                            imageFromDisk.getHeight(null)
                    ), ALPHA_BIT_MASKED);

            Graphics2D graphics = compatibleImage.createGraphics();
            graphics.drawImage(imageFromDisk, 0, 0, null);

            graphics.dispose();
            return compatibleImage;
        } catch (IOException e) {
            System.out.println("Файл по пути: " + filePath + " не найден!");
        }
        return null;
    }

    public static Image createCompatibleImage(Size size, int transparency) {
        GraphicsConfiguration graphicsConfiguration = GraphicsEnvironment.getLocalGraphicsEnvironment()
                .getDefaultScreenDevice()
                .getDefaultConfiguration();
        return graphicsConfiguration.createCompatibleImage(size.getWidth(), size.getHeight(), transparency);
    }
}
