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
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.learn.game1.MyLearnGame;
import com.learn.game1.game.Assets;
import com.learn.game1.game.WorldController;
import com.learn.game1.game.WorldRender;
import com.learn.game1.game.object.Bucket;
import com.learn.game1.game.object.Droplet;
import com.learn.game1.util.Constants;

/**
 * Created by User on 5/11/2016.
 */

public class GameScreen implements Screen {
    public static final int MAX_DROPS = 5000;
    final MyLearnGame game;

    private boolean update = true;

    private WorldController worldController;
    private WorldRender worldRender;


    public GameScreen(final MyLearnGame game) {
        this.game = game;
    }

    @Override
    public void render(float delta) {
        // clear the screen with a dark blue color. The
        // arguments to glClearColor are the red, green
        // blue and alpha component in the range [0,1]
        // of the color to be used to clear the screen.
        Gdx.gl.glClearColor(0x0 / 255.0f, 0x0 / 255.0f, 0x0 / 255.0f, 0xff / 255.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if(update) {
            worldController.update(delta);
        }

        worldRender.render();
    }

    @Override
    public void resize(int width, int height) {
        worldRender.resize(width, height);
    }

    @Override
    public void show() {
        this.worldController = new WorldController(game);
        this.worldRender = new WorldRender(worldController);
    }

    @Override
    public void hide() {
        this.dispose();
    }

    @Override
    public void pause() {
        update = false;
    }

    @Override
    public void resume() {
        update = true;
    }

    @Override
    public void dispose() {
        worldRender.dispose();
        worldController.dispose();
    }

}
