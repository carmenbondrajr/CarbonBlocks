package com.carmen.carbonblocks;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        Constants.SCREEN_HEIGHT = dm.heightPixels;
        Constants.SCREEN_WIDTH = dm.widthPixels;

        Constants.BALL_START_Y = Constants.SCREEN_HEIGHT - 200;
        Constants.BLOCK_START_X = (Constants.SCREEN_WIDTH -
                ((Constants.BLOCKS_PER_ROW * Constants.BLOCK_SIZE) +
                ((Constants.BLOCKS_PER_ROW - 1) * Constants.BLOCK_GAP)))
                / 2;

        setContentView(new GameView(this));
    }
}
