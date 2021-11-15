package draw;

import java.awt.*;

public abstract class AbstractShape implements Shape {
    protected Color color;
    protected int x, y;

    public AbstractShape(Color color, int x, int y) {
        this.color = color;
        this.x = x;
        this.y = y;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public void shift(int dx, int dy) {
        x += dx;
        y += dy;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        drawImpl(g);
    }

    protected abstract void drawImpl(Graphics g);


}
