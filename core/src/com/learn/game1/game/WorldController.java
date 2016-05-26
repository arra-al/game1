package com.learn.game1.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
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

    public Level level;

    public WorldController(MyLearnGame game) {
        this.game = game;
        Gdx.input.setInputProcessor(this);
        init();
    }

    private void init() {
        level = new Level(1);
    }

    public void update(float delta) {
        handleInputGame(delta);
        level.update(delta);
    }

    private void handleInputGame(float delta) {
        // process user input
        if (Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);

            level.bucket.moveTo(touchPos);

            //level.bucket.position.x = touchPos.x - 64 / 2;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            level.bucket.velocity.x = 0;
            level.bucket.position.x -= 200 * delta;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            level.bucket.velocity.x = 0;
            level.bucket.position.x += 200 * delta;
        }


        // make sure the bucket stays within the screen bounds
        if (level.bucket.position.x < 0)
            level.bucket.position.x = 0;
        if (level.bucket.position.x > Gdx.graphics.getWidth() - 64)
            level.bucket.position.x = Gdx.graphics.getWidth() - 64;
    }

    @Override
    public void dispose() {
        level.dispose();
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
