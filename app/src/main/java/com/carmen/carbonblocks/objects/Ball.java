package com.carmen.carbonblocks.objects;

import android.graphics.Canvas;
import android.graphics.Paint;

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

    public Circle getCircle() { return this.circle; }
    public void setVx(int vx) { this.vx = vx; }
    public void setVy(int vy) { this.vy = vy; }

    public Ball(int x, int y, int radius, int color, GameRect bottomBar) {
        circle = new Circle(x, y, radius);
        this.dx = 0;
        this.dy = 0;
        this.vx = 1;
        this.vy = -2;

        this.radius = radius;
        this.color = color;

        this.bottomBar = bottomBar;
    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawCircle(circle.getX(), circle.getY(), radius, paint);
    }

    @Override
    public void update() {
        checkCollisions();
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

    private void checkCollisions() {
        if((this.getCircle().getX() - (this.getCircle().getRadius() / 2) <= 0) ||
                (this.getCircle().getX() + (this.getCircle().getRadius() / 2)) >= Constants.SCREEN_WIDTH) {
            this.vx *= -1;
        }

        if((this.getCircle().getY() - (this.getCircle().getRadius() / 2) <= 0 ||
                (this.getCircle().getY() + (this.getCircle().getRadius() / 2)) >= bottomBar.getRectangle().top)) {
            this.vy *= -1;
        }
    }
}

