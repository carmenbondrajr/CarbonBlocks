package com.carmen.carbonblocks.objects;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by carmen on 5/3/2017.
 */

public class Circle implements GameObject {
    public int x, y, radius, color;

    public int getRadius() { return this.radius; }
    public int getX() { return this.x; }
    public int getY() { return this.y; }
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
    public void increaseX(int dx) { this.x += dx; }
    public void increaseY(int dy) { this.y += dy; }


    public Circle(int x, int y, int radius, int color) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.color = color;
    }

    public boolean contains(int xp, int yp) {
        double d = Math.sqrt(
                Math.pow(xp - x, 2) + Math.pow(yp - y, 2)
        );

        return d < radius;
    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawCircle(x, y, radius, paint);
    }

    @Override
    public void update() {

    }
}
