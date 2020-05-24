package com.designpatterns.paint.base.Models.File.JSON;

import com.designpatterns.paint.base.Models.Position;
import com.designpatterns.paint.base.Models.Shapes.CompositeShape;
import com.designpatterns.paint.base.Models.Shapes.Ellipse;
import com.designpatterns.paint.base.Models.Shapes.Rectangle;
import com.designpatterns.paint.base.Models.Shapes.Shape.IShape;
import com.designpatterns.paint.base.Models.Shapes.Shape.Shape;
import com.designpatterns.paint.base.Models.Shapes.Shape.ShapeType;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class LoadJSON {

    private static LoadJSON instance = null;

    /**
     * Private Constructor prevents any other class from instantiating.
     */
    private LoadJSON() {}

    /**
     * Get instance of LoadFile.
     */
    public static LoadJSON getInstance()
    {
        if (instance == null)  {
            instance = new LoadJSON();
        }
        return instance;
    }

    /**
     * Parse raw JSON to correct classes.
     */
    public List<Shape> loadJSON(JSONArray rawJSON) {
        List<Shape> loadedData = new ArrayList<>();

        // Iterate over the loaded raw json, we do this since its possible to have multiple groups
        rawJSON.forEach(item -> {
            JSONObject jsonObject = (JSONObject) item;

            // Temporarily store shapes that we got here
            List<IShape> tempShapeList = new ArrayList<>();

            // Check if the file starts with an Group object or not, if not we just add the shapes
            if (jsonObject.containsKey("CompositeShape")) {
                // Since every file starts with an group, we start with loading the first group
                JSONObject parentGroupObject = (JSONObject) jsonObject.get("CompositeShape");
                Long count = (Long) parentGroupObject.get("count");
                // System.out.println("Parent group object:" + parentGroupObject);

                // Get the children of said group and loop through them
                JSONArray jsonArray = (JSONArray) parentGroupObject.get("children");

                // Get count and loop based on count
                for (int i = 0; i < count; i++) {
                    JSONObject object = (JSONObject) jsonArray.get(i);
                    tempShapeList.add(turboParser(object));
                }

                // Since every file starts with an group, we create one here
                // TODO: code snapt niet dat we met 1 group beginnen, selected pleurt uit elkaar in de menu view
                loadedData.add(new CompositeShape(tempShapeList, ShapeType.CompositeShape,new Position(0,0),0,0));
            } else {
                loadedData.add(turboParser(jsonObject));
            }
        });
        return loadedData;
    }

    /**
     * We get the main object and iterate through it
     */
    private Shape turboParser(JSONObject object) {
        if(object.containsKey("Ellipse")) {
            JSONObject ellipse = (JSONObject) object.get("Ellipse");
            return parseEllipse(ellipse);
        }else if(object.containsKey("Rectangle")) {
            JSONObject rectangle = (JSONObject) object.get("Rectangle");
            return parseRectangle(rectangle);
        } else if(object.containsKey("Ornament")) {
            // TODO: implement ornament save and load
            return null;
        } else if(object.containsKey("CompositeShape")) {
            JSONObject compositeShape = (JSONObject) object.get("CompositeShape");
            return parseCompositeShape(compositeShape);
        }
        System.out.println("Error Loading something form JSON!, returning NULL");
        return null;
    }

    /**
     * Parse JSON to Rectangle.
     * TODO: Dit kan met betere recursie
     */
    private CompositeShape parseCompositeShape(JSONObject compositeShape) {
        // Temporarily store data of loaded group
        List<IShape> compositeShapeData = new ArrayList<>();
        Long count = (Long) compositeShape.get("count");

        // Get the children of said group and loop through them
        JSONArray jsonArray = (JSONArray) compositeShape.get("children");

        // Get count and loop based on count
        for (int i = 0; i < count; i++) {
            JSONObject object = (JSONObject) jsonArray.get(i);
            if (object.containsKey("Ellipse")) {
                JSONObject ellipse = (JSONObject) object.get("Ellipse");
                compositeShapeData.add(parseEllipse(ellipse));
            } if(object.containsKey("Rectangle")) {
                JSONObject rectangle = (JSONObject) object.get("Rectangle");
                compositeShapeData.add(parseRectangle(rectangle));
            } else if(object.containsKey("CompositeShape")) {
                JSONObject compositeShapeJson = (JSONObject) object.get("CompositeShape");
                compositeShapeData.add(parseCompositeShape(compositeShapeJson));
            }
        }
        return new CompositeShape(compositeShapeData, ShapeType.CompositeShape,new Position(0,0),0,0);
    }

    /**
     * Parse JSON to Rectangle.
     */
    private Rectangle parseRectangle(JSONObject rectangle) {
        Double top = (Double) rectangle.get("top");
        Double left = (Double) rectangle.get("left");
        Double width = (Double) rectangle.get("width");
        Double height = (Double) rectangle.get("height");

        return new Rectangle(new Position(top, left), width, height);
    }

    /**
     * Parse JSON to Ellipse.
     */
    private Ellipse parseEllipse(JSONObject ellipse) {
        Double top = (Double) ellipse.get("top");
        Double left = (Double) ellipse.get("left");
        Double width = (Double) ellipse.get("width");
        Double height = (Double) ellipse.get("height");

        return new Ellipse(new Position(top, left), width, height);
    }
}
