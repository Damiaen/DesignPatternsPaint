package com.designpatterns.paint.base.Models;

import com.designpatterns.paint.base.Models.Shapes.Ellipse;
import com.designpatterns.paint.base.Models.Shapes.Rectangle;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoadFile {

    private static LoadFile instance = null;

    /**
     * Private Constructor prevents any other class from instantiating.
     */
    private LoadFile() {}

    /**
     * Get instance of LoadFile.
     */
    public static LoadFile getInstance()
    {
        if (instance == null)  {
            instance = new LoadFile();
        }
        return instance;
    }

    /**
     * Select a file to load and start to read the data.
     */
    public List<Object> load() {
        // Open file selection screen
        String saveFileName = selectFile();

        // Open & Read selected file
        if (saveFileName != null) {
            JSONParser jsonParser = new JSONParser();
            // Try to get the selected file
            try (FileReader reader = new FileReader("./saves/" + saveFileName)) {
                Object obj = jsonParser.parse(reader);
                JSONArray rawJSON = (JSONArray) obj;
                System.out.println("Raw JSON Data: " + rawJSON);
                return parseSaveFile(rawJSON);

            } catch (IOException | ParseException e) {
                e.printStackTrace();
            }
            return null;
        }
        return null;
    }

    /**
     * Open the file selector screen in the ./saves folder.
     */
    private String selectFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("./saves"));
        int result = fileChooser.showOpenDialog(fileChooser);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            return selectedFile.getName();
        }
        return null;
    }

    /**
     * Parse raw JSON to correct classes.
     * TODO: Change this so we can load more shit
     */
    private List<Object> parseSaveFile(JSONArray rawJSON) {
        // TODO: Change this to type group
        List<Object> loadedShapesData = new ArrayList<>();

        //Iterate over employee array
        rawJSON.forEach(item -> {
            JSONObject jsonObject= (JSONObject) item;
            System.out.println(item);

            JSONObject shape = (JSONObject) jsonObject.get("shape");
            String type = (String) shape.get("type");

            if (type.equals("Rectangle")) {
                loadedShapesData.add(parseRectangle(shape));
            } else if (type.equals("Ellipse")) {
                loadedShapesData.add(parseEllipse(shape));
            }
        });
        return loadedShapesData;
    }

    /**
     * Parse JSON to Rectangle.
     */
    private Rectangle parseRectangle(JSONObject rectangle) {
        Long top = (Long) rectangle.get("top");
        Long left = (Long) rectangle.get("left");
        Long width = (Long) rectangle.get("width");
        Long height = (Long) rectangle.get("height");

        return new Rectangle(top.intValue(), left.intValue(), width.intValue(), height.intValue());
    }

    /**
     * Parse JSON to Ellipse.
     */
    private Ellipse parseEllipse(JSONObject ellipse) {
        Long top = (Long) ellipse.get("top");
        Long left = (Long) ellipse.get("left");
        Long width = (Long) ellipse.get("width");
        Long height = (Long) ellipse.get("height");

        return new Ellipse(top.intValue(), left.intValue(), width.intValue(), height.intValue());
    }
}
