package com.designpatterns.paint.base.Models;

import com.designpatterns.paint.base.Models.Shapes.Ellipse;
import com.designpatterns.paint.base.Models.Shapes.Rectangle;
import org.jetbrains.annotations.NotNull;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class SaveFile {

    private static SaveFile instance = null;

    /**
     * Private Constructor prevents any other class from instantiating.
     */
    private SaveFile() {}

    /**
     * Get instance of SaveFile.
     */
    public static SaveFile getInstance()
    {
        if (instance == null)  {
            instance = new SaveFile();
        }
        return instance;
    }

    /**
     * Save drawing data to json
     * TODO: Kunnen nu alleen vanuit de object array shapes laden en saven, dit moet aangepast worden naar groups
     * TODO: toevoegen van ornaments hiero
     */
    public void save(List<Object> shapes) {
        JSONArray shapesList = new JSONArray();

        // loop through all shapes
        for (Object s : shapes) {
            JSONObject newShapeDetailsObject = new JSONObject();

            if (s instanceof Ellipse) {
                newShapeDetailsObject = createEllipseObject((Ellipse) s);
            }
            if (s instanceof Rectangle) {
                newShapeDetailsObject = createRectangleObject((Rectangle) s);
            }
            JSONObject newShapesObject = new JSONObject();
            newShapesObject.put("shape", newShapeDetailsObject);
            shapesList.add(newShapesObject);
        }

        // Write JSON file
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        try (FileWriter file = new FileWriter("./saves/save_"+ timeStamp + ".json")) {
            file.write(shapesList.toJSONString());
            file.flush();
            System.out.println("Saved drawing to: './saves/save_"+ timeStamp + ".json'");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @NotNull
    private JSONObject getShapeJsonObject(int x, int y, int height, int width, String type) {
        JSONObject newShapeObject = new JSONObject();
        newShapeObject.put("type", type);
        newShapeObject.put("top", x);
        newShapeObject.put("left", y);
        newShapeObject.put("height", height);
        newShapeObject.put("width", width);

        return newShapeObject;
    }

    private JSONObject createEllipseObject(Ellipse ellipse) {
        return getShapeJsonObject(ellipse.getX(), ellipse.getY(), ellipse.getHeight(), ellipse.getWidth(), "Ellipse");
    }

    private JSONObject createRectangleObject(Rectangle rectangle) {
        return getShapeJsonObject(rectangle.getX(), rectangle.getY(), rectangle.getHeight(), rectangle.getWidth(), "Rectangle");
    }
}
