package com.designpatterns.paint.base.Models;

import com.designpatterns.paint.base.Models.Shapes.Ellipse;
import com.designpatterns.paint.base.Models.Shapes.Rectangle;
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
        //Add employees to list
        JSONArray shapesList = new JSONArray();

        for (Object s : shapes) {
            JSONObject newShapeDetailsObject = new JSONObject();
            if (s instanceof Ellipse) {
                newShapeDetailsObject.put("type", "Ellipse");
                newShapeDetailsObject.put("top", ((Ellipse) s).getX());
                newShapeDetailsObject.put("left", ((Ellipse) s).getY());
                newShapeDetailsObject.put("height", ((Ellipse) s).getHeight());
                newShapeDetailsObject.put("width", ((Ellipse) s).getWidth());
            }
            if (s instanceof Rectangle) {
                newShapeDetailsObject.put("type", "Rectangle");
                newShapeDetailsObject.put("top", ((Rectangle) s).getX());
                newShapeDetailsObject.put("left", ((Rectangle) s).getY());
                newShapeDetailsObject.put("height", ((Rectangle) s).getHeight());
                newShapeDetailsObject.put("width", ((Rectangle) s).getWidth());
            }
            JSONObject newShapesObject = new JSONObject();
            newShapesObject.put("shape", newShapeDetailsObject);
            shapesList.add(newShapesObject);
        }

        //Write JSON file
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        try (FileWriter file = new FileWriter("./saves/save_"+ timeStamp + ".json")) {
            file.write(shapesList.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
