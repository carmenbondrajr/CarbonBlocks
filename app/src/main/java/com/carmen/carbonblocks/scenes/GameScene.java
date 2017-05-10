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
    private GameRect bottomBar;
    private Ball ball;
    private Tracer tracer;
    private BlockManager blockManager;

    private boolean activeVolley = false;
    private boolean isDragging = false;
    private float dx, dy, theta;

    public GameScene() {
        bottomBar = new GameRect(0, Constants.SCREEN_HEIGHT - 15, Constants.SCREEN_WIDTH, Constants.BALL_SIZE, Color.CYAN);
        blockManager = new BlockManager();
        ball = new Ball(Constants.SCREEN_WIDTH / 2, Constants.SCREEN_HEIGHT - 150, Constants.BALL_SIZE, Constants.BALL_COLOR, bottomBar, blockManager);
        tracer = new Tracer(ball.getCircle().getX(), ball.getCircle().getY(), 10, Color.GRAY);

        blockManager.generateBlocks();
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
        blockManager.draw(canvas);
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
                if(!activeVolley && isTouchingBall(event)) {
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
        ball.getCircle().setX(Constants.SCREEN_WIDTH / 2);
        ball.getCircle().setY(Constants.SCREEN_HEIGHT - 250);
        ball.setVx(0);
        ball.setVy(0);
        activeVolley = false;
        isDragging = false;
        theta = 0;
    }

    private float calculateTheta(MotionEvent e) {
        dx = e.getX() - ball.getCircle().getX();
        dy = e.getY() - ball.getCircle().getY();
        return (float)Math.atan(dy/dx);
    }

    private boolean isTouchingBall(MotionEvent e) {
        return ball.getCircle().contains((int)e.getX(), (int)e.getY());
    }
}
