package com.learn.game1.game;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.learn.game1.game.object.Bucket;
import com.learn.game1.game.object.Droplet;

import java.util.Iterator;

/**
 * Created by User on 5/16/2016.
 */
public class Level {
    public static final String TAG = Level.class.getName();

    //list of raindrops
    public Array<Droplet> raindrops;

    //bucket
    public Bucket bucket;

    private long lastDropTime;

    public Level(int level) {
        initLevel();
    }

    private void initLevel() {
        bucket = new Bucket();
        raindrops = new Array<Droplet>();
    }

    private void spawDroplet() {
        Droplet droplet = new Droplet();
        raindrops.add(droplet);
        lastDropTime = TimeUtils.nanoTime();
    }

    public void update(float delta) {
        bucket.update(delta);

        for (Droplet d:
              raindrops) {
            d.update(delta);
        }
        if (TimeUtils.nanoTime() - lastDropTime > 1000000000)
            spawDroplet();


    }

    public void render(SpriteBatch batch) {
        bucket.render(batch);

        for (Droplet d :
                raindrops) {
            d.render(batch);
        }
    }

    public void dispose() {}
}
