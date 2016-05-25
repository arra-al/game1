package com.learn.game1.game;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.learn.game1.game.object.Bucket;
import com.learn.game1.game.object.Droplet;

import java.util.Iterator;

/**
 * Created by User on 5/16/2016.
 */
public class Level {
    public static final String TAG = Level.class.getName();

    private Sound dropSound;
    private Music rainMusic;
    //list of raindrops
    public Array<Droplet> raindrops;

    //bucket
    public Bucket bucket;

    public Level(int level) {
        initLevel();
    }

    private void initLevel() {
        dropSound = Assets.instance.sounds.drop;

        rainMusic = Assets.instance.music.rain;
        rainMusic.setLooping(true);
        rainMusic.play();

        bucket = new Bucket();
        raindrops = new Array<Droplet>();
    }

    private void spawDroplet() {
        Droplet droplet = new Droplet();
        raindrops.add(droplet);
    }

    public void update(float delta) {
        bucket.update(delta);

        for (Droplet d:
              raindrops) {
            d.update(delta);
        }

        spawDroplet();

        Iterator<Droplet> iter = raindrops.iterator();
        while (iter.hasNext()) {
            Droplet raindrop = iter.next();
            raindrop.position.y -= 400 * delta;
            if (raindrop.position.y + 64 < 0) {
                iter.remove();
                continue;
            }
            if (raindrop.bounds.overlaps(bucket.bounds)) {
                dropSound.play();
                iter.remove();
                continue;
            }
        }
    }

    public void render(SpriteBatch batch) {
        bucket.render(batch);

        for (Droplet d :
                raindrops) {
            d.render(batch);
        }
    }
}
