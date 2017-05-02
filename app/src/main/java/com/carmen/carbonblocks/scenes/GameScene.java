package com.carmen.carbonblocks.scenes;

import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;

import com.carmen.carbonblocks.Constants;
import com.carmen.carbonblocks.objects.*;

/**
 * Created by carmen on 5/1/2017.
 */

public class GameScene implements Scene {
    private Ball ball;
    private Tracer tracer;
    private GameRect bottomBar;

    private boolean activeVolley = false;
    private boolean isDragging = false;
    private double dx, dy, theta;

    public GameScene() {
        ball = new Ball(Constants.SCREEN_WIDTH / 2, Constants.SCREEN_HEIGHT - 250, 30, Constants.BALL_COLOR);
        bottomBar = new GameRect(0, Constants.SCREEN_HEIGHT - 15, Constants.SCREEN_WIDTH, 30, Color.CYAN);
        tracer = new Tracer(ball.getX(), ball.getY(), 10, Color.GRAY);
    }

    @Override
    public void update() {
        if(activeVolley) {
            ball.update();
        }
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawColor(Constants.BACKGROUND_COLOR);
        bottomBar.draw(canvas);
        ball.draw(canvas);
        if(isDragging) {
            tracer.draw(canvas);
        }
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
                } else {
                    reset();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                // move and display tracer
                theta = calculateTheta(event);
                tracer.setRotate(theta);
                break;
            case MotionEvent.ACTION_UP:
                if(isDragging) {
                    ball.releaseBall(dx, dy, theta);
                    activeVolley = true;
                    isDragging = false;
                }
                break;
        }
    }

    private void reset() {
        ball.setX(Constants.SCREEN_WIDTH / 2);
        ball.setY(Constants.SCREEN_HEIGHT - 250);
        ball.setVx(0);
        ball.setVy(0);
        activeVolley = false;
        isDragging = false;
        theta = 0;
    }

    private double calculateTheta(MotionEvent e) {
        dx = e.getX() - ball.getX();
        dy = e.getY() - ball.getY();
        return Math.atan(dy/dx);
    }
}
