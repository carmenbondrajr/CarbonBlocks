package com.carmen.carbonblocks.objects;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by carmen on 5/1/2017.
 */

public class GameRect implements GameObject {
    private Rect rectangle;
    public int color;

    public Rect getRectangle() { return rectangle; }

    public GameRect(int x, int y, int width, int height, int color) {
        rectangle = new Rect(x, y - (height/2), x + width, y + (height/2));
        this.color = color;
    }
    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(color);

        canvas.drawRect(rectangle, paint);
    }

    public boolean ballCollides(GameBall ball) {
        return false;
    }

    @Override
    public void update() {

    }
}