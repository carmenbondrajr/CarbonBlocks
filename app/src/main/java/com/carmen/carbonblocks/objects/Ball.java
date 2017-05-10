package com.carmen.carbonblocks.objects;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

import com.carmen.carbonblocks.Constants;

/**
 * Created by carmen on 5/1/2017.
 */

public class Ball implements GameObject {
    private Circle circle;
    private float radius;
    private float dx, dy;     // Direction
    private float vx, vy;     // Velocity

    private GameRect bottomBar;
    private BlockManager blockManager;

    public Circle getCircle() { return this.circle; }
    public void setVx(float vx) { this.vx = vx; }
    public void setVy(float vy) { this.vy = vy; }

    public Ball(float x, float y, float radius, int color, GameRect bottomBar, BlockManager blockManager) {
        circle = new Circle(x, y, radius, color);
        this.dx = 0;
        this.dy = 0;
        this.vx = 1;
        this.vy = -2;

        this.radius = radius;

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
    }

    public void releaseBall(float tx, float ty, double theta) {
        this.dx = tx > 0 ? -1 : 1;
        this.dy = ty > 0 ? -1 : 1;
        System.out.println("dx: " + dx + "\t dy: " + dy);
        this.vx = ((float)(Math.abs(Math.cos(theta)) * Constants.BALL_VELOCITY)) * dx;
        this.vy = ((float)(Math.abs(Math.sin(theta)) * Constants.BALL_VELOCITY)) * dy;
    }

    private void checkBlockCollisions() {
        float oldVx = vx;
        float oldVy = vy;
        for(Block block : blockManager.getBlocks()) {
            checkBlockCollision(block.getGameRect().getRectangle());
        }
        if(vx == oldVx) {
            this.circle.increaseX(vx);
        }

        if(vy == oldVy) {
            this.circle.increaseY(vy);
        }
    }

    private void checkBlockCollision(Rect rect) {
        if(collidesWithBlock(circle.x + vx, circle.y, rect.left, rect.top)) {
            vx *= -1;
        }

        if(collidesWithBlock(circle.x, circle.y + vy, rect.left, rect.top)) {
            vy *= -1;
        }
    }

    private boolean collidesWithBlock(float circleX, float circleY, float blockX, float blockY) {
        float halfBlock = Constants.BLOCK_SIZE / 2;
        float distX = Math.abs(circleX - blockX - halfBlock);
        float distY = Math.abs(circleY - blockY - halfBlock);

        if (distX > (halfBlock + circle.radius)) { return false; }
        if (distY > (halfBlock + circle.radius)) { return false; }

        if (distX <= (halfBlock)) { return true; }
        if (distY <= (halfBlock)) { return true; }

        float dx = distX - halfBlock;
        float dy = distY - halfBlock;
        return (dx*dx + dy*dy <= (circle.radius * circle.radius));
    }

    private void checkWallCollisions() {
        if(circle.x - circle.radius <= 0 || circle.x + circle.radius >= Constants.SCREEN_WIDTH) {
            this.vx *= -1;
        }

        if(circle.y - circle.radius <= 0 || circle.y + circle.radius >= bottomBar.getRectangle().top) {
            this.vy *= -1;
        }
    }
}

