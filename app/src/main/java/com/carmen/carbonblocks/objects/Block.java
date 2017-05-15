package com.carmen.carbonblocks.objects;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.carmen.carbonblocks.Constants;

/**
 * Created by carmen on 5/3/2017.
 */

public class Block implements GameObject {
    private GameRect gameRect;
    private int health;

    public GameRect getGameRect() { return this.gameRect; }
    public int getHealth() { return this.health; }
    public void decreaseHealth() { this.health--; }

    public Block(int x, int y, int color, int health) {
        gameRect = new GameRect(x, y, Constants.BLOCK_SIZE, Constants.BLOCK_SIZE, color);
        this.health = health;
    }

    @Override
    public void draw(Canvas canvas) {
        gameRect.draw(canvas);
        Paint paint = new Paint();
        paint.setTextSize(60);
        paint.setColor(Color.BLACK);
        paint.setTextAlign(Paint.Align.CENTER);
        float textY = gameRect.getY() + (Constants.BLOCK_SIZE / 2) + (paint.getTextSize() / 3);

        canvas.drawText("" + this.health, gameRect.getX() + Constants.BLOCK_SIZE / 2,
                textY, paint);
    }

    @Override
    public void update() {

    }
}
