package com.carmen.carbonblocks.scenes;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;

import com.carmen.carbonblocks.helpers.TextHelper;

/**
 * Created by carmen on 5/1/2017.
 */

public class MenuScene implements Scene {
    private Rect r = new Rect();
    @Override
    public void update() {

    }

    @Override
    public void draw(Canvas canvas) {
        System.out.println("Main Scene Draw");
        canvas.drawColor(Color.MAGENTA);
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(200);
        TextHelper.drawCenterText(r, canvas, paint, "Welcome!");
    }

    @Override
    public void terminate() {

    }

    @Override
    public void receiveTouch(MotionEvent event) {

    }
}
