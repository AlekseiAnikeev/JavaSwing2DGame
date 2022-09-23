package ru.agentche.game2d.gfx;

import java.awt.*;
import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Aleksey Anikeev aka AgentChe
 * Класс для поиска в ресурсах всех спрайтив и создания их мапы
 * в виде имея/изображение
 * Date of creation: 21.09.2022
 */

public class SpriteLibrary {

    //сохраняет несколько листов с анимацией принадлежащих к одному персонажу
    private Map<String, SpriteSet> units;
    //мапа с плитками
    private Map<String, Image> tiles;

    public SpriteLibrary() {
        units = new HashMap<>();
        tiles = new HashMap<>();
        loadSpriteFromDisk();
    }

    private void loadSpriteFromDisk() {
        loadUnits("/sprites/units");
        loadTiles("/sprites/tiles");
    }

    private void loadTiles(String path) {
        String[] imagesInFolder = getImageInFolder(path);

        for (String filename : imagesInFolder) {
            tiles.put(
                    filename.substring(0, filename.length() - 4),
                    ImageUtils.loadImage(path + "/" + filename)
            );
        }
//        сетка вместо плиток
//        BufferedImage image = new BufferedImage(SPRITE_SIZE, SPRITE_SIZE,BufferedImage.TYPE_INT_RGB);
//        Graphics2D graphics = image.createGraphics();
//        graphics.setColor(Color.RED);
//        graphics.drawRect(0,0,SPRITE_SIZE,SPRITE_SIZE);
//
//        graphics.dispose();
//
//        tiles.put("default", image);
    }

    private void loadUnits(String path){
        String[] folderNames = getFolderNames(path);
        for (String folderName : folderNames) {
            SpriteSet spriteSet = new SpriteSet();
            String pathToFolder = path + "/" + folderName;
            String[] sheetsInFolder = getImageInFolder(pathToFolder);

            for (String sheetName : sheetsInFolder) {
                spriteSet.addSheet(
                        sheetName.substring(0, sheetName.length() - 4),
                        ImageUtils.loadImage(pathToFolder + "/" + sheetName)
                );
            }
            units.put(folderName, spriteSet);
        }
    }

    //поиск файлов в каталоге
    private String[] getImageInFolder(String basePath) {
        URL resource = SpriteLibrary.class.getResource(basePath);
        File file = new File(resource.getFile());
        return file.list((current, name) -> new File(current, name).isFile());

    }

    //поиск каталогов по пути
    private String[] getFolderNames(String basePath) {
        URL resource = SpriteLibrary.class.getResource(basePath);
        File file = new File(resource.getFile());
        return file.list((current, name) -> new File(current, name).isDirectory());
    }


    public SpriteSet getUnit(String name) {
        return units.get(name);
    }

    public Image getTile(String name) {
        return tiles.get(name);
    }
}
