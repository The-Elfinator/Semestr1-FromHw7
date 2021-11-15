package draw;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Draw {
    public static void main(String[] args) {
        int width = 1000;
        int height = 500;

        BufferedImage image = ImageUtil.createImage(width, height);
        Graphics g = image.getGraphics();


        int steps = 20;
        int dx = width / 2 / steps;
        int dy = height / 2 / steps;

        List<Shape> shapes = new ArrayList<>();

        for (int i = 0; i < steps; i++) {
            shapes.add(new Rect(
                    i % 2 == 0 ? Color.GREEN : Color.YELLOW,
                    dx * i, dy * i,
                    width - dx * i * 2, height - dy * i * 2
            ));
            shapes.add(new Circle(
                    i % 2 == 0 ? Color.RED : Color.MAGENTA,
                    width/2, height/2,
                    dx * (steps - i)
            ));
        }


        for (int i = 0; i <= steps; i++) {
            final Shape line = new Line(
                    Color.CYAN,
                    i * dx * 2, 0,
                    width - i * dx * 2, height
            );
            final Shape copy = line.copy();
            shapes.add(copy);
        }

        //ImageUtil.toGrayScale();
        for (Shape shape : shapes) {
            shape.shift(width / 4, height / 4);
        }

        for (Shape shape : shapes) {
            //System.out.println(shape.getClass());
            shape.draw(g);
        }

        ImageUtil.showImage(image);

        try {
            ImageUtil.writeImage("C:\\Users\\artem\\IdeaProjects\\untitled1\\src\\draw\\draw.png", image);
        } catch (IOException e) {
            System.out.println("Error. Couldn't write image..." + e.getMessage());

        }
    }
}
