package com.learn.game1.game;

import com.badlogic.gdx.utils.Array;
import com.learn.game1.game.object.Bucket;
import com.learn.game1.game.object.Droplet;

/**
 * Created by User on 5/16/2016.
 */
public class Level {
    public static final String TAG = Level.class.getName();

    //list of droplets
    public Array<Droplet> droplets;

    //bucket
    public Bucket bucket;

    public Level(int level) {
        initLevel();
    }

    private void initLevel() {
        bucket = new Bucket();
        droplets = new Array<Droplet>();
    }

    public void update(float delta) {

    }
}
