package com.learn.game1.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Disposable;
import com.learn.game1.MyLearnGame;
import com.learn.game1.screen.MainMenuScreen;

/**
 * Created by User on 5/16/2016.
 */
public class WorldController extends InputAdapter implements Disposable{
    private static final String TAG = WorldController.class.getName();
    private final MyLearnGame game;

    public World b2world;

    // Rectangles for collision detection
    private final Rectangle r1 = new Rectangle();
    private final Rectangle r2 = new Rectangle();

    public WorldController(MyLearnGame game) {
        this.game = game;
        init();
    }

    private void init() {}

    public void update(float delta) {

    }

    private void handleInputGame(float delta) {

    }

    @Override
    public void dispose() {

    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.R) {
            init();
            Gdx.app.debug(TAG, "Game world resetted");
        }
        // Back to Menu
        else if (keycode == Input.Keys.ESCAPE || keycode == Input.Keys.BACK) {
            backToMenu();
        }
        return false;
    }

    private void backToMenu() {
        game.setScreen(new MainMenuScreen(game));
    }
}
