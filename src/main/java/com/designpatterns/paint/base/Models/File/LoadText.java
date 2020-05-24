package com.designpatterns.paint.base.Models.File;

import com.designpatterns.paint.base.Models.Position;
import com.designpatterns.paint.base.Models.Shapes.CompositeShape;
import com.designpatterns.paint.base.Models.Shapes.Shape.Shape;
import com.designpatterns.paint.base.Models.Shapes.Shape.ShapeType;
import com.designpatterns.paint.base.Models.File.SaveModels.TextData;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoadText {

    private static LoadText instance = null;

    /**
     * Temporary store all loaded lines in here.
     */
    private List<TextData> loadedTextLines;

    /**
     * Private Constructor prevents any other class from instantiating.
     */
    private LoadText() {}

    /**
     * Get instance of LoadFile.
     */
    public static LoadText getInstance()
    {
        if (instance == null)  {
            instance = new LoadText();
        }
        return instance;
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
     * Check which extension we selected, and load accordingly
     */
    public List<Shape> load() {
        // Open file selection screen
        String saveFileName = selectFile();

        // Open & Read selected file
        if (saveFileName != null) {
            String fileExtension = saveFileName.split("\\.")[saveFileName.split("\\.").length - 1];
            if (fileExtension.equals("txt")) {
                System.out.println("Attempting to load from selected TextFile");
                try (FileReader reader = new FileReader("./saves/" + saveFileName)) {
                    BufferedReader bufferedReader = new BufferedReader(reader);
                    return LoadText.getInstance().loadTextFile(bufferedReader);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }
        return null;
    }

    /**
     * Parse raw JSON to correct classes.
     */
    public List<Shape> loadTextFile(BufferedReader bufferedReader) throws IOException {
        List<Shape> loadedData = new ArrayList<>();

        String lineJustFetched = null;
        String[] wordsArray;

        loadedTextLines = new ArrayList<>();

        while(true){
            lineJustFetched = bufferedReader.readLine();
            if(lineJustFetched == null){
                break;
            }else{
                wordsArray = lineJustFetched.split("\t");

                for(String each : wordsArray){
                    if(!"".equals(each)){
                        // System.out.println("Type: " + each.split(" ")[0] + "; content: " + each +  "; tabCount: " + (wordsArray.length - 1));
                        loadedTextLines.add(new TextData((wordsArray.length - 1), each, ShapeType.valueOf(each.split(" ")[0])));
                    }
                }
            }
        }

        // Set first to loaded, since we skip the master group
        loadedTextLines.get(0).setLoaded(true);

        // Get all the shapes with TabCount one to start the loop
        for (TextData loadedTextData: loadedTextLines) {
            if (loadedTextData.getTabCount().equals(1)) {
                loadedTextData.setLoaded(true);
                loadedData.add(turboParser(loadedTextData));
            }
        }

        //loadedData.add(new CompositeShape(tempShapeList, ShapeType.CompositeShape,new Position(0,0),0,0));
        return loadedData;
    }

    /**
     * We get the main object and iterate through it
     */
    private Shape turboParser(TextData loadTextData) {
        String[] splitLine = loadTextData.getLine().split(" ");

        if (loadTextData.getType().equals(ShapeType.CompositeShape)) {
            return parseGroup(loadTextData);
        }
        if (loadTextData.getType().equals(ShapeType.Ellipse)) {
            return parseShape(ShapeType.Ellipse, splitLine);
        }
        if (loadTextData.getType().equals(ShapeType.Rectangle)) {
            return parseShape(ShapeType.Rectangle, splitLine);
        }
        System.out.println("Error Loading!, returning NULL");
        return null;
    }

    /**
     * Parse JSON to Rectangle.
     */
    private CompositeShape parseGroup(TextData loadTextLine) {
        // Temporarily store data of loaded group
        loadTextLine.setLoaded(true);
        List<Shape> compositeShapeData = new ArrayList<>();
        List<TextData> similarTabCount = getTextWithSimilarTabCount(loadTextLine);

        for (TextData line: similarTabCount) {
            String[] split = line.getLine().split(" ");
            if (line.getType().equals(ShapeType.Ellipse) && !line.isLoaded()) {
                compositeShapeData.add(parseShape(ShapeType.Ellipse, split));
                line.setLoaded(true);
            }
            if (line.getType().equals(ShapeType.Rectangle) && !line.isLoaded()) {
                compositeShapeData.add(parseShape(ShapeType.Rectangle, split));
                line.setLoaded(true);
            }
            if (line.getType().equals(ShapeType.CompositeShape) && !line.isLoaded()) {
                line.setLoaded(true);
                compositeShapeData.add(parseGroup(line));
            }
        }
        return new CompositeShape(compositeShapeData, ShapeType.CompositeShape);
    }

    /**
     * Get lines with similar tabCount
     */
    private List<TextData> getTextWithSimilarTabCount(TextData loadTextLine) {
        List<TextData> temp = new ArrayList<>();
        // Keep the our shapes count in check, since there is no easy way to check if we surpassed the limit
        int shapesCount = 0;
        for (TextData line: loadedTextLines) {
            if (line.getTabCount() == (loadTextLine.getTabCount() + 1) && !line.equals(loadTextLine) && !line.isLoaded()) {
                temp.add(line);
                if (!line.getType().equals(ShapeType.Ornament)) {
                    shapesCount++;
                }
            }
            if (shapesCount == Integer.parseInt(loadTextLine.getLine().split(" ")[1])) {
                break;
            }
        }
        return temp;
    }

    /**
     * Parse shape
     */
    private Shape parseShape(ShapeType type, String[] ellipse) {
        return new Shape(type, new Position(Double.parseDouble(ellipse[1]), Double.parseDouble(ellipse[2])), Double.parseDouble(ellipse[3]), Double.parseDouble(ellipse[4]));
    }
}