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
    public static int BACKGROUND_COLOR = Color.rgb(38,50,56);
    public static int BOTTOM_BAR_COLOR = Color.rgb(33, 33, 33);
    public static int[] ROW_COLORS =
            {Color.rgb(244,67,54),
                Color.rgb(255,152,0),
                Color.rgb(255,235,59),
                Color.rgb(76,175,80),
                Color.rgb(33,150,243),
                Color.rgb(63,81,181),
                Color.rgb(156,39,176)};

    public static int TRACER_COLOR = Color.rgb(224,224,224);
    public static int BALL_SIZE = 20;
    public static int BALL_COLOR = Color.rgb(158,158,158);
    public static float BALL_VELOCITY = 40;
    public static int BALL_START_Y;

    public static int BLOCK_SIZE = 120;
    public static int BLOCKS_PER_ROW = 8;
    public static int BLOCK_GAP = 12;
    public static int BLOCK_START_X;
    public static int BLOCK_START_Y = 120;
    public static int BLOCK_TEXT_SIZE = 65;
    public static int BLOCK_TEXT_COLOR = Color.rgb(33,33,33);
}
