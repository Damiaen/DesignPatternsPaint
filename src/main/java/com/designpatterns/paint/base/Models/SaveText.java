package com.designpatterns.paint.base.Models;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SaveText {

    private static SaveText instance = null;

    /**
     * Private Constructor prevents any other class from instantiating.
     */
    private SaveText() {}

    /**
     * Get instance of SaveFile.
     */
    public static SaveText getInstance()
    {
        if (instance == null)  {
            instance = new SaveText();
        }
        return instance;
    }

    /**
     * Save drawing data to text
     */
    public void save(String shapes) {
        // Write Text file
        System.out.println("Attempting to save to text file...");
        System.out.println(shapes);
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        try (FileWriter file = new FileWriter("./saves/text/save_"+ timeStamp + ".txt")) {
            file.write(shapes);
            file.flush();
            System.out.println("Saved drawing to: './saves/text/save_"+ timeStamp + ".txt'");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}