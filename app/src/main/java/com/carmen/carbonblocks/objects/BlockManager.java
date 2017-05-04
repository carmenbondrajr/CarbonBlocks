package com.carmen.carbonblocks.objects;

import android.graphics.Canvas;
import android.graphics.Color;

import java.util.ArrayList;

/**
 * Created by carmen on 5/3/2017.
 */

public class BlockManager implements GameObject {
    public ArrayList<Block> blocks;

    public ArrayList<Block> getBlocks() { return this.blocks; }

    public BlockManager() {
        blocks = new ArrayList<>();
    }

    public void generateBlocks() {
        blocks.add(new Block(100, 300, Color.BLUE));
        blocks.add(new Block(700, 150, Color.BLUE));
        blocks.add(new Block(500, 1200, Color.BLUE));
        blocks.add(new Block(900, 900, Color.BLUE));

    }

    @Override
    public void draw(Canvas canvas) {
        for (Block block : blocks) {
            block.draw(canvas);
        }
    }

    @Override
    public void update() {

    }
}
