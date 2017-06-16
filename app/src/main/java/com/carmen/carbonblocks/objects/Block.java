package com.carmen.carbonblocks.objects;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.carmen.carbonblocks.Constants;

/**
 * Created by carmen on 5/3/2017.
 */

public class Block implements GameObject {
    private float x, y;
    private int health;
    private Paint paint, textPaint;
    private final Rect textBounds = new Rect();

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

        textPaint.setTextSize(Constants.BLOCK_TEXT_SIZE);
        textPaint.setColor(Constants.BLOCK_TEXT_COLOR);
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

        float cx = this.x + (Constants.BLOCK_SIZE / 2);
        float cy = this.y  + (Constants.BLOCK_SIZE / 2);
        String text = "" + this.health;
        textPaint.getTextBounds(text, 0, text.length(), textBounds);
        canvas.drawText(text, cx - textBounds.exactCenterX(), cy - textBounds.exactCenterY(), textPaint);
    }

    @Override
    public void update() {

    }
}
