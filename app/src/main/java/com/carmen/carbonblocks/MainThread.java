package com.carmen.carbonblocks;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

/**
 * Created by carmen on 5/1/2017.
 */

public class MainThread extends Thread {
    public static final int MAX_FPS = 30;
    private double avgFps;
    private SurfaceHolder surfaceHolder;
    private GameView gameView;

    private boolean running;
    public static Canvas canvas;

    public void setRunning(boolean running) { this.running = running; }

    public MainThread(SurfaceHolder surfaceHolder, GameView gameView) {
        super();
        this.surfaceHolder = surfaceHolder;
        this.gameView = gameView;
    }

    @Override
    public void run() {
        while(running) {
            long startTime = System.nanoTime();
            canvas = null;

            updateAndDraw();
            maintainFps(startTime);
        }
    }

    private void updateAndDraw() {
        try {
            canvas = this.surfaceHolder.lockCanvas();
            synchronized (surfaceHolder) {
                this.gameView.update();
                this.gameView.draw(canvas);
            }
        } catch (Exception e) { e.printStackTrace(); }
        finally {
            if (canvas != null) {
                try {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                } catch (Exception e) { e.printStackTrace(); }
            }
        }
    }
    private void maintainFps(long startTime) {
        long targetTime = 1000/MAX_FPS;

        long timeMillis = (System.nanoTime() - startTime) / 1000000;
        long waitTime = targetTime - timeMillis;
        if(waitTime > 0) {
            try {
                this.sleep(waitTime);
            } catch (Exception e) { e.printStackTrace(); }
        }
    }
}
