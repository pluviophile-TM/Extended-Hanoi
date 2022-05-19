package sample;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.text.Text;

public class Disk {
    int Width;
    int Height;
    int x;
    int y;
    int number;
    StackPane STpane;
    Rectangle rect;
    boolean Deafault = true;
    public Disk(int width, int height, int x, int y, int number) {
        Rectangle rectangle = new Rectangle();
        this.x=x;
        this.y=y;
        rectangle.setArcHeight(5.0);
        rectangle.setArcWidth(200.0);
        rectangle.setWidth(width);
        rectangle.setHeight(height);
        rectangle.setX(x);
        rectangle.setY(y);
        rectangle.setFill(Color.YELLOW);
        rectangle.setStroke(Color.DARKBLUE);
        rectangle.setStrokeLineCap(StrokeLineCap.BUTT);
        rectangle.setStrokeLineJoin(StrokeLineJoin.ROUND);
        this.number=number;
        this.rect=rectangle;
        STpane = new StackPane();
        STpane.setAlignment(Pos.CENTER);
        STpane.setLayoutX(x);
        STpane.setLayoutY(y);
        STpane.getChildren().addAll(rectangle,new Text(""+number));
    }

    @Override
    public String toString() {
        return "Disk[" +
                 + number +
                ']'+"[pos:"+x+","+y+" ]";
    }


    public boolean isDeafault() {
        return Deafault;
    }

    public void setDeafault(boolean deafault) {
        Deafault = deafault;
    }

    public int getWidth() {
        return Width;
    }

    public void setWidth(int width) {
        Width = width;
    }

    public int getHeight() {
        return Height;
    }

    public void setHeight(int height) {
        Height = height;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
