package com.carmen.carbonblocks.scenes;

import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;

import com.carmen.carbonblocks.Constants;
import com.carmen.carbonblocks.objects.GameBall;
import com.carmen.carbonblocks.objects.GameRect;

/**
 * Created by carmen on 5/1/2017.
 */

public class GameScene implements Scene {
    private GameBall ball;
    private GameRect bottomBar;

    private boolean activeVolley = false;
    private boolean isDragging = false;

    public GameScene() {
        ball = new GameBall(Constants.SCREEN_WIDTH / 2, Constants.SCREEN_HEIGHT - 250, 30, Constants.BALL_COLOR);
        bottomBar = new GameRect(0, Constants.SCREEN_HEIGHT - 15, Constants.SCREEN_WIDTH, 30, Color.CYAN);
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawColor(Constants.BACKGROUND_COLOR);
        bottomBar.draw(canvas);
        ball.draw(canvas);
    }

    @Override
    public void terminate() {

    }

    @Override
    public void receiveTouch(MotionEvent event) {
        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if(!activeVolley) {
                    isDragging = true;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                // move and display tracer
                break;
            case MotionEvent.ACTION_UP:
                activeVolley = true;
                isDragging = false;
                break;
        }
    }
}
