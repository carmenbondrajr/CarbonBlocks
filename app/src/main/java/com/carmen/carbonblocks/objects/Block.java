package com.carmen.carbonblocks.objects;

import android.graphics.Canvas;

import com.carmen.carbonblocks.Constants;

/**
 * Created by carmen on 5/3/2017.
 */

public class Block implements GameObject {
    private GameRect gameRect;

    public GameRect getGameRect() { return this.gameRect; }

    public Block(int x, int y, int color) {
        gameRect = new GameRect(x, y, Constants.BLOCK_SIZE, Constants.BLOCK_SIZE, color);
    }

    @Override
    public void draw(Canvas canvas) {
        gameRect.draw(canvas);
    }

    @Override
    public void update() {

    }
}
