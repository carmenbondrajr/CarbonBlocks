package com.carmen.carbonblocks.scenes;

import android.graphics.Canvas;
import android.view.MotionEvent;

/**
 * Created by carmen on 5/1/2017.
 */

public interface Scene {
    void update();
    void draw(Canvas canvas);
    void terminate();
    void receiveTouch(MotionEvent event);
}
