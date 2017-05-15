package com.carmen.carbonblocks.objects;

import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;

/**
 * Created by carmen on 5/1/2017.
 */

public class Tracer implements GameObject {
    private float ballX, ballY;
    private float x, y;
    private Paint paint;

    public void setX(float x) { this.x = x; }
    public void setY(float y) { this.y = y; }

    private void configure(int color, float thickness){
        if(paint == null) {
            paint = new Paint();
        }
        paint.setAntiAlias(true);
        paint.setColor(color);
        paint.setStrokeWidth(thickness);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStyle(Paint.Style.STROKE);
        paint.setPathEffect(new DashPathEffect(new float[]{20, 50}, 0));
    }

    public Tracer(float ballX, float ballY, int color, float thickness) {
        configure(color, thickness);
        this.ballX = ballX;
        this.ballY = ballY;
        this.x = ballX;
        this.y = ballY;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawLine(x, y, ballX, ballY, paint);
    }

    @Override
    public void update() {

    }

    public void reset() {
        x = ballX;
        y = ballY;
    }
}
