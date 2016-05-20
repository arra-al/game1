package com.learn.game1.screen;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.learn.game1.MyLearnGame;
import com.learn.game1.game.Assets;
import com.learn.game1.game.object.Bucket;
import com.learn.game1.game.object.Droplet;

/**
 * Created by User on 5/11/2016.
 */

public class GameScreen implements Screen {
    public static final int MAX_DROPS = 500;
    final MyLearnGame game;

    Bucket bucket;

    Sound dropSound;
    Music rainMusic;
    OrthographicCamera camera;
    Array<Droplet> raindrops;
    long lastDropTime;
    int dropsGathered, countDrops = MAX_DROPS;

    public GameScreen(final MyLearnGame gam) {
        this.game = gam;

        // load the drop sound effect and the rain background "music"
        dropSound = Assets.instance.sounds.drop;
        rainMusic = Assets.instance.music.rain;
        rainMusic.setLooping(true);

        // create the camera and the SpriteBatch
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        // create a Rectangle to logically represent the bucket
        bucket = new Bucket();
        bucket.position.x = 800 / 2 - 64 / 2; // center the bucket horizontally
        bucket.position.y = 20; // bottom left corner of the bucket is 20 pixels above


        // create the raindrops array and spawn the first raindrop
        raindrops = new Array<Droplet>();
        spawnRaindrop();
    }

    private void spawnRaindrop() {
        Droplet raindrop = new Droplet();

        raindrops.add(raindrop);
        countDrops--;
        lastDropTime = TimeUtils.nanoTime();
    }

    @Override
    public void render(float delta) {
        // clear the screen with a dark blue color. The
        // arguments to glClearColor are the red, green
        // blue and alpha component in the range [0,1]
        // of the color to be used to clear the screen.
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // tell the camera to update its matrices.
        camera.update();

        bucket.update(delta);

        // tell the SpriteBatch to render in the
        // coordinate system specified by the camera.
        game.batch.setProjectionMatrix(camera.combined);

        // begin a new batch and draw the bucket and
        // all drops
        game.batch.begin();
        if(countDrops <= 0) {
            //lets count how much drops you missed
            Assets.instance.fonts.defaultBig.draw(game.batch, "Drops missed: " + (MAX_DROPS - dropsGathered), Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
            rainMusic.stop();
        } else {
            Assets.instance.fonts.defaultNormal.draw(game.batch, "Drops Collected: " + dropsGathered, 0, 480);
            bucket.render(game.batch);
            for (Droplet raindrop : raindrops) {
                raindrop.update(delta);
                raindrop.render(game.batch);
            }
        }
        game.batch.end();

        // process user input
        if (Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            bucket.position.x = touchPos.x - 64 / 2;
        }
        if (Gdx.input.isKeyPressed(Keys.LEFT)) {
            bucket.position.x -= 200 * Gdx.graphics.getDeltaTime();
        }
        if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
            bucket.position.x += 200 * Gdx.graphics.getDeltaTime();
        }


        // make sure the bucket stays within the screen bounds
        if (bucket.position.x < 0)
            bucket.position.x = 0;
        if (bucket.position.x > 800 - 64)
            bucket.position.x = 800 - 64;

        // check if we need to create a new raindrop
        //if (TimeUtils.nanoTime() - lastDropTime > 1000000000)
            spawnRaindrop();

        if(countDrops <= 0) {
            //lets count how much drops you missed
        } else {
            // move the raindrops, remove any that are beneath the bottom edge of
            // the screen or that hit the bucket. In the later case we increase the
            // value our drops counter and add a sound effect.
            Iterator<Droplet> iter = raindrops.iterator();
            while (iter.hasNext()) {
                Droplet raindrop = iter.next();
                raindrop.position.y -= 400 * delta;
                if (raindrop.position.y + 64 < 0) {
                    iter.remove();
                    continue;
                }
                /*if (raindrop.bounds.overlaps(bucket.bounds)) {
                    dropsGathered++;
                    dropSound.play();
                    iter.remove();
                    continue;
                }*/
            }
        }
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
        // start the playback of the background music
        // when the screen is shown
        rainMusic.play();
    }

    @Override
    public void hide() {
        this.dispose();
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        dropSound.dispose();
        rainMusic.dispose();
    }

}
