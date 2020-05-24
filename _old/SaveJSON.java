package com.designpatterns.paint.base.Models.File.JSON;

import com.designpatterns.paint.base.Models.Shapes.CompositeShape;
import com.designpatterns.paint.base.Models.Shapes.Ellipse;
import com.designpatterns.paint.base.Models.Shapes.Rectangle;
import com.designpatterns.paint.base.Models.Shapes.Shape.IShape;
import com.designpatterns.paint.base.Models.Shapes.Shape.Shape;
import org.jetbrains.annotations.NotNull;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class SaveJSON {

    private static SaveJSON instance = null;

    /**
     * Private Constructor prevents any other class from instantiating.
     */
    private SaveJSON() {}

    /**
     * Get instance of SaveFile.
     */
    public static SaveJSON getInstance()
    {
        if (instance == null)  {
            instance = new SaveJSON();
        }
        return instance;
    }

    /**
     * Save drawing data to json
     * TODO: Kunnen nu alleen vanuit de object array shapes laden en saven, dit moet aangepast worden naar groups
     * TODO: toevoegen van ornaments hiero
     * @param shapes
     */
    public void save(List<Shape> shapes) {
        JSONArray shapesList = new JSONArray();

        // Iterate through all shapes
        for (Shape s : shapes) {
            JSONObject newShapeDetailsObject = new JSONObject();

            if (s instanceof CompositeShape) {
                newShapeDetailsObject = createGroupObject((CompositeShape) s);
            }
            if (s instanceof Ellipse) {
                newShapeDetailsObject = createEllipseObject((Ellipse) s);
            }
            if (s instanceof Rectangle) {
                newShapeDetailsObject = createRectangleObject((Rectangle) s);
            }
            JSONObject newShapesObject = new JSONObject();
            newShapesObject.put(s.getType(), newShapeDetailsObject);
            shapesList.add(newShapesObject);
        }

//        // After we are done storing all the required data, we make one "master" shapeGroup with all shapes and data
//        JSONObject masterGroupObject = new JSONObject();
//        // Set count and children
//        masterGroupObject.put("count", shapesList.size());
//        masterGroupObject.put("children", shapesList);
//
//        JSONObject finalGroupObject = new JSONObject();
//        finalGroupObject.put(ShapeType.CompositeShape, masterGroupObject);
//
//        JSONArray finalJSONArray = new JSONArray();
//        finalJSONArray.add(finalGroupObject);


//        shapesList.add(newShapesObject);

//        dwadawdawdwad.put("children", shapesList);
//        JSONArray ktuthj = new JSONArray();
//        ktuthj.add(dwadawdawdwad);
//
//        JSONObject saveFileData = new JSONObject();
//        saveFileData.put("count", shapesList.size());
//        saveFileData.put("CompositeShape", ktuthj);
//
//        JSONArray dawadw = new JSONArray();
//        dawadw.add(saveFileData);


        // Write JSON file
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        try (FileWriter file = new FileWriter("./saves/json/save_"+ timeStamp + ".json")) {
            file.write(shapesList.toJSONString());
            file.flush();
            System.out.println("Saved drawing to: './saves/json/save_"+ timeStamp + ".json'");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @NotNull
    private JSONObject getShapeJsonObject(double x, double y, double height, double width, String type) {
        JSONObject newShapeObject = new JSONObject();
        newShapeObject.put("top", x);
        newShapeObject.put("left", y);
        newShapeObject.put("height", height);
        newShapeObject.put("width", width);

        return newShapeObject;
    }

    private JSONObject createEllipseObject(Ellipse ellipse) {
        return getShapeJsonObject(ellipse.getPosition().x, ellipse.getPosition().y, ellipse.getHeight(), ellipse.getWidth(), ellipse.getType().toString());
    }

    private JSONObject createRectangleObject(Rectangle rectangle) {
        return getShapeJsonObject(rectangle.getPosition().x, rectangle.getPosition().y, rectangle.getHeight(), rectangle.getWidth(), rectangle.getType().toString());
    }

    private JSONObject createGroupObject(CompositeShape compositeShape) {
        // List with all shapes
        JSONArray shapesList = new JSONArray();

        // Object with group details
        JSONObject newGroupObject = new JSONObject();

        // Set group details
        newGroupObject.put("count", compositeShape.getCount());

        // Loop through elements in group, if we find another group we loop through that group
        for (IShape s : compositeShape.getShapes()) {
            JSONObject newShapeDetailsObject = new JSONObject();

            if (s instanceof CompositeShape) {
                newShapeDetailsObject = createGroupObject((CompositeShape) s);
            }
            if (s instanceof Ellipse) {
                newShapeDetailsObject = createEllipseObject((Ellipse) s);
            }
            if (s instanceof Rectangle) {
                newShapeDetailsObject = createRectangleObject((Rectangle) s);
            }
            JSONObject newShapesObject = new JSONObject();

            newShapesObject.put(s.getType(), newShapeDetailsObject);
            shapesList.add(newShapesObject);
        }

        newGroupObject.put("children", shapesList);

        System.out.println(newGroupObject);

        return newGroupObject;

        // return getShapeJsonObject(ellipse.getPosition().x, ellipse.getPosition().y, ellipse.getHeight(), ellipse.getWidth(), ellipse.getType().toString());
    }
}
