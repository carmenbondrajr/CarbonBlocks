package com.carmen.carbonblocks.objects;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by carmen on 5/1/2017.
 */

public class Tracer implements GameObject {
    private float ballX, ballY;
    private float x, y;
    private int color;
    private double rotate;

    public void setRotate(double rotate) { this.rotate = rotate; }
    public void setX(float x) { this.x = x; }
    public void setY(float y) { this.y = y; }

    public Tracer(float ballX, float ballY, int thickness, int color) {
        this.ballX = ballX;
        this.ballY = ballY;
        this.color = color;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.save();
        canvas.rotate(-(float)rotate, this.ballX, this.ballY);
        Paint paint = new Paint();
        paint.setColor(this.color);
        canvas.drawRect(
                ballX,
                y,
                x,
                y
                , paint);
        canvas.restore();
    }

    @Override
    public void update() {

    }
}
