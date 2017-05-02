package com.carmen.carbonblocks.objects;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by carmen on 5/1/2017.
 */

public class GameBall implements GameObject {
    private int x, y, radius, color;

    public GameBall(int x, int y, int radius, int color) {
        this.x = x;
        this.y = y;
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

    }
}
