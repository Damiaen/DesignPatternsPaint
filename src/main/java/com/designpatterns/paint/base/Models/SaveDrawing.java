package com.designpatterns.paint.base.Models;

import com.designpatterns.paint.base.Models.Shapes.Ellipse;
import com.designpatterns.paint.base.Models.Shapes.Rectangle;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

    public List<Object> load() {
        // Open file selection screen
        String saveFileName = selectFile();

        // Open selected file
        if (saveFileName != null) {
            JSONParser jsonParser = new JSONParser();

            try (FileReader reader = new FileReader("./saves/" + saveFileName))
            {
                //Read JSON file
                Object obj = jsonParser.parse(reader);
                JSONArray rawJSON = (JSONArray) obj;
                System.out.println(rawJSON);

                return parseSaveFile(rawJSON);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return null;
        }
        return null;
    }

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

    private List<Object> parseSaveFile(JSONArray rawJSON) {
        //Initiate new list of objects
        // TODO: Change this to type group
        List<Object> loadedShapesData = new ArrayList<>();

        //Iterate over employee array
        rawJSON.forEach(item -> {
            JSONObject jsonObject= (JSONObject) item;
            System.out.println(item);

            // Hardcoded get shape
            // TODO: Change this so we can load more shit
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

    private Rectangle parseRectangle(JSONObject rectangle) {
        Long top = (Long) rectangle.get("top");
        Long left = (Long) rectangle.get("left");
        Long width = (Long) rectangle.get("width");
        Long height = (Long) rectangle.get("height");


        return new Rectangle(top.intValue(), left.intValue(), width.intValue(), height.intValue());
    }

    private Ellipse parseEllipse(JSONObject ellipse) {
        Long top = (Long) ellipse.get("top");
        Long left = (Long) ellipse.get("left");
        Long width = (Long) ellipse.get("width");
        Long height = (Long) ellipse.get("height");

        return new Ellipse(top.intValue(), left.intValue(), width.intValue(), height.intValue());
    }


//    private static void dwdaw(JSONObject employee)
//    {
//        //Get employee object within list
//        JSONObject employeeObject = (JSONObject) employee.get("shape");
//
//        //Create new temporary object to store the data from the selected JSON row
//
//
//        //Get employee first name
//        String type = (String) employeeObject.get("type");
//        System.out.println(type);
//
//        //Get employee last name
//        Long lastName = (Long) employeeObject.get("height");
//        System.out.println(lastName);
//
//        //Get employee website name
//        Long website = (Long) employeeObject.get("width");
//        System.out.println(website);
//
//        return new Object(type);
//
//    }
}
