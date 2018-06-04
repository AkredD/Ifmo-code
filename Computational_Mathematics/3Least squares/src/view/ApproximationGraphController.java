package view;

import function.Function;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class ApproximationGraphController {
    @FXML
    private Canvas idCanvas;

    private Paint color;

    public void setColor(Paint color){
        this.color = color;
    }

    public void draw(ArrayList<Pair<Integer, Integer>> dots, Function function){
        Double height = idCanvas.getHeight();
        Double width = idCanvas.getWidth();
        double minX = 9999999;
        double minY = 9999999;
        double maxX = -9999999;
        double kX;
        double maxY = -9999999;
        double kY;
        int x = 0;
        int y = 0;
        for (int i = 0; i < dots.size(); ++i){
            if (minX > dots.get(i).getKey()){
                minX = dots.get(i).getKey();
                x = i;
            }
            if (minY > dots.get(i).getValue()){
                minY = dots.get(i).getValue();
                y = i;
            }
            maxX = Math.max(maxX, dots.get(i).getKey());
            maxY = Math.max(maxY, dots.get(i).getValue());
        }

        /*for (int j = 0; j < dots.size(); ++j){
            dots.set(j, new Pair(dots.get(j).getKey() - minX, dots.get(j).getValue() - minY));
        }
        maxX -= minX;
        maxY -= minY;*/
        kX = width / (maxX + 2);
        kY = height / (maxY + 2);

        idCanvas.getGraphicsContext2D().setStroke(Color.BLACK);
        for (int i = 0; i < dots.size() - 1; ++i){
            idCanvas.getGraphicsContext2D().beginPath();
            //idCanvas.getGraphicsContext2D().moveTo(dots.get(i).getKey().doubleValue() * 50, dots.get(i).getValue().doubleValue() * 50);
            //idCanvas.getGraphicsContext2D().lineTo(dots.get(i+1).getKey().doubleValue() * 50, dots.get(i+1).getValue().doubleValue() * 50);
            idCanvas.getGraphicsContext2D().strokeLine((int)(dots.get(i).getKey().doubleValue() * kX),
                    height - dots.get(i).getValue().doubleValue() * kY, dots.get(i+1).getKey().doubleValue() * kX,
                    height - dots.get(i+1).getValue().doubleValue() * kY);
            idCanvas.getGraphicsContext2D().closePath();
        }
        idCanvas.getGraphicsContext2D().setStroke(color);
        for (double i = 0; i < width; ++i){
            idCanvas.getGraphicsContext2D().beginPath();
            double x1 = ((maxX) * i) / width;
            double y1 = function.evaluate(x1).doubleValue();
            double x2 =  ((maxX) * (i + 1)) / width;
            double y2 = function.evaluate(x2).doubleValue();
            idCanvas.getGraphicsContext2D().strokeLine(x1 * kX, height - y1 * kY, x2 * kX, height - y2 *kY);
            idCanvas.getGraphicsContext2D().closePath();
        }
    }

}
