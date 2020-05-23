package com.designpatterns.paint.base.Models;

import com.designpatterns.paint.base.Models.Shapes.Shape.Shape;
import com.designpatterns.paint.base.Models.Shapes.Shape.ShapeType;
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
     * @return
     */
    public List<Shape> load() {
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
    private List<Shape> parseSaveFile(JSONArray rawJSON) {
        // TODO: Change this to type group
        List<Shape> loadedShapesData = new ArrayList<>();

        //Iterate over employee array
        rawJSON.forEach(item -> {
            JSONObject jsonObject= (JSONObject) item;
            System.out.println(item);

            JSONObject shape = (JSONObject) jsonObject.get("shape");
            String type = (String) shape.get("type");

            if (type.equals("Rectangle")) {
                loadedShapesData.add(parseShape(shape, ShapeType.Rectangle));
            } else if (type.equals("Ellipse")) {
                loadedShapesData.add(parseShape(shape, ShapeType.Ellipse));
            }
        });
        return loadedShapesData;
    }

    /**
     * Parse JSON to Rectangle.
     */
    private Shape parseShape(JSONObject rectangle, ShapeType type) {
        Long top = (Long) rectangle.get("top");
        Long left = (Long) rectangle.get("left");
        Long width = (Long) rectangle.get("width");
        Long height = (Long) rectangle.get("height");

        return new Shape(type, new Position(top.doubleValue(), left.doubleValue()), width.doubleValue(), height.doubleValue());
    }
}
