package com.carmen.carbonblocks.objects;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

import com.carmen.carbonblocks.Constants;

/**
 * Created by carmen on 5/1/2017.
 */

public class Ball implements GameObject {
    private float x, y;       // Position
    private float vx, vy;     // Velocity
    private float radius;
    private int color;

    public float getX() { return this.x; }
    public float getY() { return this.y; }
    public void setX(float x) { this.x = x; }
    public void setY(float y) { this.y = y; }

    public float getVx() { return this.vx; }
    public float getVy() { return this.vy; }
    public void setVx(float vx) { this.vx = vx; }
    public void setVy(float vy) { this.vy = vy; }

    public void flipVx() { this.vx *= -1; }
    public void flipVy() { this.vy *= -1; }
    public float getRadius() { return this.radius; }

    public void increaseX() {
        this.x += vx;
    }

    public void increaseY() {
        this.y += vy;
    }

    public Ball(float x, float y, float radius, int color) {
        this.x = x;
        this.y = y;
        this.vx = 0;
        this.vy = 0;
        this.radius = radius;
        this.color = color;
    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(this.color);
        canvas.drawCircle(x, y, radius, paint);
    }

    public boolean contains(float xp, float yp) {
        double d = Math.sqrt(
                Math.pow(xp - x, 2) + Math.pow(yp - y, 2)
        );

        return d < radius;
    }

    @Override
    public void update() {

    }

    public void releaseBall(float tx, float ty, double theta) {
        float dx = tx > 0 ? -1 : 1;
        float dy = ty > 0 ? -1 : 1;
        this.vx = ((float)(Math.abs(Math.cos(theta)) * Constants.BALL_VELOCITY)) * dx;
        this.vy = ((float)(Math.abs(Math.sin(theta)) * Constants.BALL_VELOCITY)) * dy;
    }
}

