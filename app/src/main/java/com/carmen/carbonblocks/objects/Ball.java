package com.carmen.carbonblocks.objects;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.carmen.carbonblocks.Constants;

/**
 * Created by carmen on 5/1/2017.
 */

public class Ball implements GameObject {
    private int x, y, radius, color;
    private int vx, vy;

    public int getX() { return this.x; }
    public int getY() { return this.y; }
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
    public void setVx(int vx) { this.vx = vx; }
    public void setVy(int vy) { this.vy = vy; }

    public Ball(int x, int y, int radius, int color) {
        this.x = x;
        this.y = y;
        this.vx = 1;
        this.vy = -2;
        this.radius = radius;
        this.color = color;
    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawCircle(x, y, radius, paint);
    }

    @Override
    public void update() {
        this.x += vx;
        this.y += vy;
    }

    public void releaseBall(double dx, double dy, double theta) {
        this.vx = -((int)(Math.cos(theta) * Constants.BALL_VELOCITY));
        this.vy = -((int)(Math.sin(theta) * Constants.BALL_VELOCITY));
    }
}
