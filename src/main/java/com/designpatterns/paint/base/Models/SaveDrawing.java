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

public class SaveDrawing {

    public SaveDrawing() {
    }

    /**
     * Save drawing data to json
     * TODO: optimizen en dingen fixen, dit kan erg easy opgelost worden maar dit is een start
     */
    public void save(List<Object> shapes) {
        //Add employees to list
        JSONArray shapesList = new JSONArray();

        for (Object s : shapes) {
            JSONObject newShapeDetailsObject = new JSONObject();
            if (s instanceof Ellipse) {
                newShapeDetailsObject.put("type", "Ellipse");
                newShapeDetailsObject.put("height", ((Ellipse) s).getHeight());
                newShapeDetailsObject.put("height", ((Ellipse) s).getWidth());
            }
            if (s instanceof Rectangle) {
                newShapeDetailsObject.put("shape", "Rectangle");
                newShapeDetailsObject.put("height", ((Rectangle) s).getHeight());
                newShapeDetailsObject.put("height", ((Rectangle) s).getWidth());
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

    public void load() {
        // Do stuff
    }
}
