package com.carmen.carbonblocks.scenes;

import android.graphics.Canvas;
import android.view.MotionEvent;

import com.carmen.carbonblocks.scenes.GameScene;
import com.carmen.carbonblocks.scenes.MenuScene;
import com.carmen.carbonblocks.scenes.Scene;

import java.util.ArrayList;

/**
 * Created by carmen on 5/1/2017.
 */

public class SceneManager {
    private ArrayList<Scene> scenes = new ArrayList<>();
    public static int ACTIVE_SCENE;

    public SceneManager() {
        ACTIVE_SCENE = 1;
        scenes.add(new MenuScene());
        scenes.add(new GameScene());
    }

    public void receiveTouch(MotionEvent event) {
        scenes.get(ACTIVE_SCENE).receiveTouch(event);
    }

    public void update() {
        scenes.get(ACTIVE_SCENE).update();
    }

    public void draw(Canvas canvas) {
        scenes.get(ACTIVE_SCENE).draw(canvas);
    }
}
