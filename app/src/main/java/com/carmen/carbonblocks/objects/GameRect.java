package com.carmen.carbonblocks.objects;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by carmen on 5/1/2017.
 */

public class GameRect implements GameObject {
    private int x, y, width, height, color;

    public int getX() { return this.x; }
    public int getY() { return this.y; }

    public void increaseY(int dy) {
        this.y += dy;
    }

    public GameRect(int x, int y, int width, int height, int color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }
    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawRect(x, y, x + width, y + height, paint);
    }

    @Override
    public void update() {

    }
}
