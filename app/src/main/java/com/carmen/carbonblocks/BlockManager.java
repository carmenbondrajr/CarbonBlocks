package com.carmen.carbonblocks;

import android.graphics.Canvas;
import android.graphics.Color;

import com.carmen.carbonblocks.Constants;
import com.carmen.carbonblocks.objects.Block;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by carmen on 5/3/2017.
 */

public class BlockManager {
    private ArrayList<Block> blocks;
    Random rand;

    public ArrayList<Block> getBlocks() { return this.blocks; }

    public BlockManager() {
        blocks = new ArrayList<>();
        rand = new Random();

        initializeBoard();
    }

    private void initializeBoard() {
        generateNewRow(8, 1);
        advanceBlocks();
        generateNewRow(8, 1);
    }

    public void generateNewRow(int baseNumBlocks, int baseHealth) {
        int numBlocks = rand.nextInt(baseNumBlocks) + 1;

        boolean[] columnOccupied = new boolean[Constants.BLOCKS_PER_ROW];
        ArrayList<Block> row = new ArrayList<>();

        for(int i = 0; i < numBlocks; i++) {
            int col = rand.nextInt(Constants.BLOCKS_PER_ROW);
            while(columnOccupied[col]) {
                col = rand.nextInt(Constants.BLOCKS_PER_ROW);
            }
            int mod = rand.nextInt(3);
            int health = baseHealth + mod;

            int startX = (col * Constants.BLOCK_GAP) + (col * Constants.BLOCK_SIZE) + Constants.BLOCK_START_X;
            Block block = new Block(startX, Constants.BLOCK_START_Y, Color.CYAN, health);
            row.add(block);
            columnOccupied[col] = true;
        }
        blocks.addAll(row);
    }

    public void advanceBlocks() {
        for(Block block : blocks) {
            block.increaseY(Constants.BLOCK_SIZE + Constants.BLOCK_GAP);
        }
    }

    public void removeDeadBlocks() {
        ArrayList<Block> dead = new ArrayList<>();
        for(Block block : blocks) {
            if(block.getHealth() <= 0) {
                dead.add(block);
            }
        }
        blocks.removeAll(dead);
    }

    public void drawBlocks(Canvas canvas) {
        for(Block block : blocks) {
            block.draw(canvas);
        }
    }
}
