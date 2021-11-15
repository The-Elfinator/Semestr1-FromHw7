package draw;

import java.awt.*;

public class Line extends AbstractShape {
    private int x2, y2;


    public Line(Color color, int x1, int y1, int x2, int y2) {
        super(color, x1, y1);
        this.x2 = x2;
        this.y2 = y2;
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
    }

    @Override
    protected void drawImpl(Graphics g) {
        g.drawLine(x, y, x2, y2);
    }

    @Override
    public void shift(int dx, int dy) {
        super.shift(dx, dy);
        x2 += dx;
        y2 += dy;
    }

    @Override
    public Line copy() {
        return new Line(color, x, y, x2, y2);
    }
}
