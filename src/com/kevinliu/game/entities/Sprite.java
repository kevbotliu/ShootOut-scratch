package com.kevinliu.game.entities;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Sprite
{
    private static BufferedImage spriteSheet;
    private static final int TILE_SIZE = 80;

    public static BufferedImage loadSprite(String file)
    {
        BufferedImage sprite = null;
        try
        {
            sprite = ImageIO.read(new File("res/" + file + ".png"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return sprite;
    }

    public static BufferedImage getSprite(int xGrid, int yGrid)
    {
        if (spriteSheet == null) {
            spriteSheet = loadSprite("playerspritesheet");
        }
        return spriteSheet.getSubimage(xGrid * 80, yGrid * 80, 80, 80);
    }
}
