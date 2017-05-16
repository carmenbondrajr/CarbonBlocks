package com.carmen.carbonblocks;

import android.graphics.Canvas;

import com.carmen.carbonblocks.objects.Ball;
import com.carmen.carbonblocks.objects.Block;
import com.carmen.carbonblocks.objects.DeadZone;

/**
 * Created by carmen on 5/13/2017.
 */

public class BoardManager {
    private Ball ball;
    private BlockManager blockManager;
    private DeadZone deadZone;

    public BoardManager(Ball ball, BlockManager blockManager, DeadZone deadZone) {
        this.ball = ball;
        this.blockManager = blockManager;
        this.deadZone = deadZone;
    }

    public void checkCollisions() {
        checkWallCollisions();
        checkBlockCollisions();
    }

    private void checkBlockCollisions() {
        float oldVx = ball.getVx();
        float oldVy = ball.getVy();

        for(Block block : blockManager.getBlocks()) {
            if(block != null) {
                if(checkBlockCollision(block)) {
                    break;
                }
            }
        }

        if(ball.getVx() == oldVx) {
            this.ball.increaseX();
        }

        if(ball.getVy() == oldVy) {
            this.ball.increaseY();
        }
    }

    private boolean checkBlockCollision(Block block) {
        boolean collision = false;

        if(collidesWithBlock(ball.getX() + ball.getVx(), ball.getY(), ball.getRadius(), block.getX(), block.getY())) {
            ball.flipVx();
            collision = true;
        }

        if(collidesWithBlock(ball.getX(), ball.getY() + ball.getVy(), ball.getRadius(), block.getX(), block.getY())) {
            ball.flipVy();
            collision = true;
        }

        if(collision) {
            blockManager.damageBlock(block);
        }

        return collision;
    }

    private boolean collidesWithBlock(float circleX, float circleY, float circleRadius, float blockX, float blockY) {
        float halfBlock = Constants.BLOCK_SIZE / 2;
        float distX = Math.abs(circleX - blockX - halfBlock);
        float distY = Math.abs(circleY - blockY - halfBlock);

        if (distX > (halfBlock + circleRadius)) { return false; }
        if (distY > (halfBlock + circleRadius)) { return false; }

        if (distX <= (halfBlock)) { return true; }
        if (distY <= (halfBlock)) { return true; }

        float dx = distX - halfBlock;
        float dy = distY - halfBlock;
        return (dx*dx + dy*dy <= (circleRadius * circleRadius));
    }

    private void checkWallCollisions() {
        if(ball.getX() - ball.getRadius() <= 0 || ball.getX() + ball.getRadius() >= Constants.SCREEN_WIDTH) {
            ball.flipVx();
        }

        if(ball.getY() - ball.getRadius() <= 0 || ball.getY() + ball.getRadius() >= deadZone.getY()) {
            ball.flipVy();
        }
    }

    public void releaseBall(float tx, float ty, double theta) {
        float dx = tx > 0 ? -1 : 1;
        float dy = ty > 0 ? -1 : 1;
        float vx = ((float)(Math.abs(Math.cos(theta)) * Constants.BALL_VELOCITY)) * dx;
        float vy = ((float)(Math.abs(Math.sin(theta)) * Constants.BALL_VELOCITY)) * dy;
        ball.setVx(vx);
        ball.setVy(vy);
    }

    public void drawBoard(Canvas canvas) {
        canvas.drawColor(Constants.BACKGROUND_COLOR);
        deadZone.draw(canvas);
        ball.draw(canvas);
        blockManager.drawBlocks(canvas);
    }
}
