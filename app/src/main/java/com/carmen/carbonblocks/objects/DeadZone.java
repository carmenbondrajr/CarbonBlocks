package com.carmen.carbonblocks.objects;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by carmen on 5/15/2017.
 */

public class DeadZone implements GameObject {
    private float x, y, width, height;
    private Paint paint;

    public float getY() { return this.y; }

    private void configure(int color) {
        if(paint == null) {
            paint = new Paint();
        }
        paint.setAntiAlias(true);
        paint.setColor(color);
    }

    public DeadZone(float x, float y, float width, float height, int color) {
        configure(color);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawRect(x, y, x + width, y + height, paint);
    }

    @Override
    public void update() {

    }
}
