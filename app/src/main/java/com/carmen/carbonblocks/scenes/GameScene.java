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
    private BlockManager blockManager;
    private BoardManager boardManager;

    private boolean activeVolley = false;
    private boolean isDragging = false;
    private float dx, dy, theta;

    public GameScene() {
        bottomBar = new GameRect(0, Constants.BALL_START_Y + Constants.BALL_SIZE + 1, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, Constants.BOTTOM_BAR_COLOR);
        blockManager = new BlockManager();
        ball = new Ball(Constants.SCREEN_WIDTH / 2, Constants.SCREEN_HEIGHT - 200, Constants.BALL_SIZE, Constants.BALL_COLOR);

        boardManager = new BoardManager(ball, blockManager, bottomBar);
    }

    @Override
    public void update() {
        if(activeVolley) {
            boardManager.checkCollisions();
        }
    }

    @Override
    public void draw(Canvas canvas) {
        boardManager.draw(canvas);
        if(isDragging) {
            // draw tracer
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
                //tracer.setRotate(theta);
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
        ball.setY(Constants.BALL_START_Y);
        ball.setVx(0);
        ball.setVy(0);
        activeVolley = false;
        isDragging = false;
        theta = 0;
    }

    private float calculateTheta(MotionEvent e) {
        dx = e.getX() - ball.getX();
        dy = e.getY() - ball.getY();
        return (float)Math.atan(dy/dx);
    }

    private boolean isTouchingBall(MotionEvent e) {
        return ball.contains((int)e.getX(), (int)e.getY());
    }
}
