package com.carmen.carbonblocks.objects;

import android.graphics.Canvas;
import android.graphics.Color;

import com.carmen.carbonblocks.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by carmen on 5/3/2017.
 */

public class BlockManager implements GameObject {
    private ArrayList<Block[]> blocks;
    Random rand;

    public ArrayList<Block[]> getBlocks() { return this.blocks; }

    public BlockManager() {
        blocks = new ArrayList<>();
        rand = new Random();

        generateNewRow();
        advanceRows();
        generateNewRow();
    }

    public void generateNewRow() {
        int numBlocks = rand.nextInt(3) + 1;

        Block[] row = new Block[Constants.BLOCKS_PER_ROW];

        for(int i = 0; i < numBlocks; i++) {
            int col = rand.nextInt(Constants.BLOCKS_PER_ROW);
            while(row[col] != null) {
                col = rand.nextInt(Constants.BLOCKS_PER_ROW);
            }

            int startX = (col * Constants.BLOCK_GAP) + (col * Constants.BLOCK_SIZE) + Constants.BLOCK_START_X;
            Block block = new Block(startX, Constants.BLOCK_START_Y, Color.CYAN);
            row[i] = block;
        }
        blocks.add(row);
    }

    public void advanceRows() {
        for(Block[] row : blocks) {
            for(Block block : row) {
                if(block != null) {
                    block.getGameRect().increaseY(Constants.BLOCK_SIZE + Constants.BLOCK_GAP);
                }
            }
        }
    }

    @Override
    public void draw(Canvas canvas) {
        for (Block[] row : blocks) {
            for(Block block : row) {
                if(block != null) {
                    block.draw(canvas);
                }
            }
        }
    }

    @Override
    public void update() {

    }
}
