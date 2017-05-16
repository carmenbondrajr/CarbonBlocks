package com.carmen.carbonblocks;

import com.carmen.carbonblocks.objects.Ball;
import com.carmen.carbonblocks.objects.Block;
import com.carmen.carbonblocks.objects.DeadZone;

import java.util.ArrayList;

/**
 * Created by carmen on 5/13/2017.
 */

public class CollisionChecker {

    public static void checkWallCollisions(Ball ball) {
        if(ball.getX() - ball.getRadius() <= 0 || ball.getX() + ball.getRadius() >= Constants.SCREEN_WIDTH) {
            ball.flipVx();
        }

        if(ball.getY() - ball.getRadius() <= 0) {
            ball.flipVy();
        }
    }

    public static boolean checkDeadZoneCollision(Ball ball, DeadZone deadZone) {
        if (ball.getY() + ball.getRadius() >= deadZone.getY()) {
            ball.setVx(0);
            ball.setVy(0);
            ball.setY(Constants.BALL_START_Y);

            return true;
        }

        return false;
    }

    public static void checkBlockCollisions(Ball ball, ArrayList<Block> blocks) {
        float oldVx = ball.getVx();
        float oldVy = ball.getVy();

        for(Block block : blocks) {
            if(checkBlockCollision(ball, block)) {
                block.decreaseHealth();
                break;
            }
        }

        if(ball.getVx() == oldVx) {
            ball.increaseX();
        }

        if(ball.getVy() == oldVy) {
            ball.increaseY();
        }
    }

    private static boolean checkBlockCollision(Ball ball, Block block) {
        boolean collision = false;

        if(collidesWithBlock(ball.getX() + ball.getVx(), ball.getY(), ball.getRadius(), block.getX(), block.getY())) {
            ball.flipVx();
            collision = true;
        }

        if(collidesWithBlock(ball.getX(), ball.getY() + ball.getVy(), ball.getRadius(), block.getX(), block.getY())) {
            ball.flipVy();
            collision = true;
        }

        return collision;
    }

    private static boolean collidesWithBlock(float circleX, float circleY, float circleRadius, float blockX, float blockY) {
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
}
