package com.designpatterns.paint.base.Models;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * PATTERN: Singleton
 */
public class SaveScreenshot {

    private static SaveScreenshot instance = null;

    /**
     * Private Constructor prevents any other class from instantiating.
     */
    private SaveScreenshot() {}

    /**
     * Get instance of LoadFile.
     */
    public static SaveScreenshot getInstance()
    {
        if (instance == null)  {
            instance = new SaveScreenshot();
        }
        return instance;
    }

    /**
     * Get the bufferedImage, process and store it.
     */
    public void store(BufferedImage bufferedImage) throws IOException {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

        File imageFile = new File("./screenshots/screenshot_" + timeStamp + ".jpg");
        imageFile.createNewFile();
        ImageIO.write(bufferedImage, "jpg", imageFile);
        System.out.println("Created screenshot at: ./screenshots/screenshot_" + timeStamp + ".jpg");
    }


}
