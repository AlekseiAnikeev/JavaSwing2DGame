package ru.agentche.game2d.gfx;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

/**
 * @author Aleksey Anikeev aka AgentChe
 * Date of creation: 21.09.2022
 */
public class ImageUtils {

    public static Image loadImage(String filePath){
        try{
            return ImageIO.read(ImageUtils.class.getResource(filePath));
        } catch (IOException e) {
            System.out.println("Файл по пути: " + filePath + " не найден!");
        }
        return null;
    }
}
