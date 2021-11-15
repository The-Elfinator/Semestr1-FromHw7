package draw;

import java.awt.*;

public interface Shape extends Copyable{
    /*public*/ /*abstract*/ void draw(Graphics g);

    Color getColor();

    void setColor(Color color);

    void shift(int dx, int dy);

    @Override
    Shape copy();
}
