package com.carmen.carbonblocks.objects;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.carmen.carbonblocks.Constants;

/**
 * Created by carmen on 5/3/2017.
 */

public class Block implements GameObject {
    private float x, y;
    private int health;
    private Paint paint, textPaint;

    public float getX() { return this.x; }
    public float getY() { return this.y; }
    public void increaseY(float dy) {
        this.y += dy;
    }
    public int getHealth() { return this.health; }
    public void decreaseHealth() { this.health--; }

    private void configure(int color) {
        if(paint == null) {
            paint = new Paint();
        }
        if(textPaint == null) {
            textPaint = new Paint();
        }
        paint.setAntiAlias(true);
        paint.setColor(color);

        textPaint.setTextSize(60);
        textPaint.setColor(Color.BLACK);
        textPaint.setTextAlign(Paint.Align.CENTER);
    }

    public Block(float x, float y, int color, int health) {
        configure(color);
        this.x = x;
        this.y = y;
        this.health = health;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawRect(x, y, x + Constants.BLOCK_SIZE, y + Constants.BLOCK_SIZE, paint);

        float textY = y + (Constants.BLOCK_SIZE / 2) + (paint.getTextSize() / 2);
        canvas.drawText("" + this.health, x + Constants.BLOCK_SIZE / 2,
                textY, textPaint);
    }

    @Override
    public void update() {

    }
}
