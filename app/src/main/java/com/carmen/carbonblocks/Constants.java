package com.carmen.carbonblocks;

import android.content.Context;
import android.graphics.Color;

/**
 * Created by carmen on 5/1/2017.
 */

public class Constants {
    public static int SCREEN_WIDTH;
    public static int SCREEN_HEIGHT;

    public static Context CURRENT_CONTEXT;
    public static int MAX_FPS = 30;

    // GAME CONSTANTS
    public static int BACKGROUND_COLOR = Color.rgb(30, 30, 30);
    public static int BOTTOM_BAR_COLOR = Color.rgb(45, 45, 45);

    public static int BALL_SIZE = 20;
    public static int BALL_COLOR = Color.RED;
    public static float BALL_VELOCITY = 40;
    public static int BALL_START_Y;

    public static int BLOCK_SIZE = 120;
    public static int BLOCKS_PER_ROW = 8;
    public static int BLOCK_GAP = 12;
    public static int BLOCK_START_X;
    public static int BLOCK_START_Y = 100;
}
