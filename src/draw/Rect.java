package draw;

import java.awt.*;

public class Rect extends AbstractShape {
    private final int w, h;

    public Rect(Color color, int x, int y, int w, int h) {
        super(color, x, y);
        this.w = w;
        this.h = h;
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
    }

    @Override
    protected void drawImpl(Graphics g) {
        g.fillRect(this.x, this.y, this.w, this.h);
    }


    @Override
    public Rect copy() {
        return new Rect(color, x, y, w, h);
    }
}
