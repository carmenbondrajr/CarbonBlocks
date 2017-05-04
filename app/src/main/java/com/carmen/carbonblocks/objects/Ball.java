package com.carmen.carbonblocks.objects;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import com.carmen.carbonblocks.Constants;

/**
 * Created by carmen on 5/1/2017.
 */

public class Ball implements GameObject {
    private Circle circle;
    private int radius, color;
    private int dx, dy;     // Direction
    private int vx, vy;     // Velocity

    private GameRect bottomBar;
    private BlockManager blockManager;

    public Circle getCircle() { return this.circle; }
    public void setVx(int vx) { this.vx = vx; }
    public void setVy(int vy) { this.vy = vy; }

    public Ball(int x, int y, int radius, int color, GameRect bottomBar, BlockManager blockManager) {
        circle = new Circle(x, y, radius, color);
        this.dx = 0;
        this.dy = 0;
        this.vx = 1;
        this.vy = -2;

        this.radius = radius;
        this.color = color;

        this.bottomBar = bottomBar;
        this.blockManager = blockManager;
    }

    @Override
    public void draw(Canvas canvas) {
        circle.draw(canvas);
    }

    @Override
    public void update() {
        checkWallCollisions();
        checkBlockCollisions();
        this.circle.increaseX(vx);
        this.circle.increaseY(vy);
    }

    public void releaseBall(double tx, double ty, double theta) {
        this.dx = tx > 0 ? -1 : 1;
        this.dy = ty > 0 ? -1 : 1;
        System.out.println("dx: " + dx + "\t dy: " + dy);
        this.vx = ((int)(Math.abs(Math.cos(theta)) * Constants.BALL_VELOCITY)) * dx;
        this.vy = ((int)(Math.abs(Math.sin(theta)) * Constants.BALL_VELOCITY)) * dy;
    }

    private void checkWallCollisions() {
        if(ballCollidesX(0, Constants.SCREEN_WIDTH)) {
            this.vx *= -1;
        }

        if(ballCollidesY(0, bottomBar.getRectangle().top)) {
            this.vy *= -1;
        }
    }

    private void checkBlockCollisions() {
        for(Block block : blockManager.getBlocks()) {
            checkBlockCollision(block.getGameRect().getRectangle());
        }
    }

    private void checkBlockCollision(Rect rect) {
        int dx = circle.getX() - Math.max(rect.centerX(), Math.min(circle.getX(), rect.centerX() + rect.width()));
        int dy = circle.getY() - Math.max(rect.centerY(), Math.min(circle.getY(), rect.centerY() + rect.height()));
        boolean intersect = (dx * dx + dy * dy) < (radius * radius);

        if(intersect) {
            // Collision happened
            if(circle.getY() > rect.top && circle.getY() < rect.bottom) {
                // hit left or right side
                this.vx *= -1;
            }
            if(circle.getX() > rect.left && circle.getX() < rect.right) {
                // hit top or bottom side
                this.vy *= -1;
            }
        }
    }

    private void collideWithBlock(Rect rect) {
        // ball is above box
        if(Math.abs(circle.getY() - rect.top) < radius)
        {
            if(circle.getX() >= rect.left)
            {
                if(circle.getY() <= rect.right)
                {
                    // ball hit the top edge
                    this.vx *= -1;
                }
                else
                {
                    // ball hit the top right corner
                    this.vx *= -1;
                    this.vy *= -1;
                }
            }
            else
            {
                // hit top left corner
                this.vx *= -1;
                this.vy *= -1;
            }
        } else {

        }
    }

    private boolean ballCollidesX(int left, int right) {
        return this.getBoundingLeft() <= left || this.getBoundingRight() >= right;
    }
    private boolean ballCollidesY(int top, int bottom) {
        return this.getBoundingTop() <= top || this.getBoundingBottom() >= bottom;
    }

    private int getBoundingLeft() {
        return this.getCircle().getX() - (this.getCircle().getRadius() / 2);
    }
    private int getBoundingRight() {
        return this.getCircle().getX() + (this.getCircle().getRadius() / 2);
    }
    private int getBoundingTop() {
        return this.getCircle().getY() - (this.getCircle().getRadius() / 2);
    }
    private int getBoundingBottom() {
        return this.getCircle().getY() + (this.getCircle().getRadius() / 2);
    }
}

