package com.carmen.carbonblocks.scenes;

import android.graphics.Canvas;
import android.view.MotionEvent;

import com.carmen.carbonblocks.BlockManager;
import com.carmen.carbonblocks.CollisionChecker;
import com.carmen.carbonblocks.Constants;
import com.carmen.carbonblocks.objects.*;

/**
 * Created by carmen on 5/1/2017.
 */

public class GameScene implements Scene {
    private DeadZone deadZone;
    private Ball ball;
    private BlockManager blockManager;
    private Tracer tracer;

    private boolean activeVolley = false;
    private boolean isDragging = false;
    private float dx, dy, theta;

    public GameScene() {
        float deadZoneY = Constants.BALL_START_Y + Constants.BALL_SIZE + 1;
        float deadZoneHeight = Constants.SCREEN_HEIGHT - deadZoneY;
        deadZone = new DeadZone(0, deadZoneY, Constants.SCREEN_WIDTH, deadZoneHeight, Constants.BOTTOM_BAR_COLOR);
        blockManager = new BlockManager();
        ball = new Ball(Constants.SCREEN_WIDTH / 2, Constants.SCREEN_HEIGHT - 200, Constants.BALL_SIZE, Constants.BALL_COLOR);
        tracer = new Tracer(Constants.SCREEN_WIDTH / 2, Constants.BALL_START_Y, Constants.BALL_COLOR, Constants.BALL_SIZE / 2);
    }

    @Override
    public void update() {
        if(activeVolley) {
            if(CollisionChecker.checkDeadZoneCollision(ball, deadZone)) {
                endVolley();
            } else {
                CollisionChecker.checkWallCollisions(ball);
                CollisionChecker.checkBlockCollisions(ball, blockManager.getBlocks());
            }
            blockManager.removeDeadBlocks();
        }
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawColor(Constants.BACKGROUND_COLOR);
        deadZone.draw(canvas);
        ball.draw(canvas);
        blockManager.drawBlocks(canvas);

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
                if(!activeVolley && validPosition(event)) {
                    isDragging = true;
                } else {
                    reset();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if(validPosition(event)) {
                    tracer.setX(event.getX());
                    tracer.setY(event.getY());
                    theta = calculateTheta(event);
                } else {
                    tracer.reset();
                }
                break;
            case MotionEvent.ACTION_UP:
                if(isDragging && validPosition(event)) {
                    startVolley(dx, dy, theta);
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

        tracer.setBallX(ball.getX());
        tracer.setBallY(ball.getY());
    }

    private float calculateTheta(MotionEvent e) {
        dx = ball.getX() - e.getX();
        dy = ball.getY() - e.getY();
        return (float)Math.atan(dy/dx);
    }

    private void startVolley(float tx, float ty, double theta) {
        float dx = tx > 0 ? -1 : 1;
        float dy = ty > 0 ? -1 : 1;
        float vx = ((float)(Math.abs(Math.cos(theta)) * Constants.BALL_VELOCITY)) * dx;
        float vy = ((float)(Math.abs(Math.sin(theta)) * Constants.BALL_VELOCITY)) * dy;
        ball.setVx(vx);
        ball.setVy(vy);

        activeVolley = true;
    }

    private void endVolley() {
        activeVolley = false;
        tracer.setBallX(ball.getX());
        tracer.setBallY(ball.getY());
    }

    private boolean validPosition(MotionEvent e) {
        if(e.getY() < ball.getY()){
            return true;
        }
        return false;


    }
}
