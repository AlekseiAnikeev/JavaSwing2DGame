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
    private final Map<String, SpriteSet> units;
    //мапа с плитками
    private final Map<String, Image> tiles;

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
    }

    /**
     * Метод загрузки листа анимаций
     * @param path путь к файлу
     */
    private void loadUnits(String path) {
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

    /**
     * Метод поиска файлов в каталоге
     * @return список файлов в каталоге
     */
    private String[] getImageInFolder(String basePath) {
        URL resource = SpriteLibrary.class.getResource(basePath);
        assert resource != null;
        File file = new File(resource.getFile());
        return file.list((current, name) -> new File(current, name).isFile());

    }

    /**
     * Метод поиска каталогов по указанному пути
     * @param basePath - базовый каталог
     * @return - список каталогов
     */
    private String[] getFolderNames(String basePath) {
        URL resource = SpriteLibrary.class.getResource(basePath);
        assert resource != null;
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
