package draw;

import java.awt.*;

public class Circle extends AbstractShape {
    private final int radius;

    public Circle(Color color, int x, int y, int radius)  {
        super(color, x, y);
        this.radius = radius;
    }

    @Override
    public void draw(Graphics g) {
       super.draw(g);
    }

    @Override
    protected void drawImpl(Graphics g) {
        g.fillOval(x - radius, y - radius, 2 * radius, 2 * radius);
    }

    @Override
    public Circle copy() {
        return new Circle(color, x, y, radius);
    }
}
