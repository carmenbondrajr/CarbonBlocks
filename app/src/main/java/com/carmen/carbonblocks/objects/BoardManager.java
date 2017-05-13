package com.carmen.carbonblocks.objects;

import android.graphics.Canvas;

import com.carmen.carbonblocks.Constants;

/**
 * Created by carmen on 5/13/2017.
 */

public class BoardManager implements GameObject {
    private Ball ball;
    private BlockManager blockManager;
    private GameRect bottomBar;

    public BoardManager(Ball ball, BlockManager blockManager, GameRect bottomBar) {
        this.ball = ball;
        this.blockManager = blockManager;
        this.bottomBar = bottomBar;
    }

    public void checkCollisions() {
        checkWallCollisions();
        checkBlockCollisions();
    }

    private void checkBlockCollisions() {
        float oldVx = ball.getVx();
        float oldVy = ball.getVy();
        for(Block[] row : blockManager.getBlocks()) {
            for(Block block : row) {
                if(block != null) {
                    checkBlockCollision(block.getGameRect());
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

    private void checkBlockCollision(GameRect gameRect) {
        if(collidesWithBlock(ball.getX() + ball.getVx(), ball.getY(), ball.getRadius(), gameRect.getX(), gameRect.getY())) {
            ball.flipVx();
        }

        if(collidesWithBlock(ball.getX(), ball.getY() + ball.getVy(), ball.getRadius(), gameRect.getX(), gameRect.getY())) {
            ball.flipVy();
        }
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

        if(ball.getY() - ball.getRadius() <= 0 || ball.getY() + ball.getRadius() >= bottomBar.getY()) {
            ball.flipVy();
        }
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawColor(Constants.BACKGROUND_COLOR);
        bottomBar.draw(canvas);
        ball.draw(canvas);
        blockManager.draw(canvas);
    }

    @Override
    public void update() {

    }
}
