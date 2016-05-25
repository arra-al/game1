package com.learn.game1.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.learn.game1.util.Constants;

/**
 * Created by User on 5/16/2016.
 */
public class WorldRender implements Disposable {
    private OrthographicCamera camera;
    private Viewport viewport;
    private SpriteBatch batch;
    private final WorldController worldController;
    private ShaderProgram shaderMonochrome;

    private static final boolean DEBUG_DRAW_BOX2D_WORLD = false;
    private Box2DDebugRenderer b2debugRenderer;

    public WorldRender(WorldController worldController) {
        this.worldController = worldController;
        init();
    }

    private void init() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        viewport = new ScreenViewport(camera);
        viewport.apply();

        b2debugRenderer = new Box2DDebugRenderer();
    }

    public void render() {
        renderWorld(batch);
        renderGUI(batch);
    }

    private void renderWorld(SpriteBatch batch) {

        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        worldController.level.render(batch);
        batch.end();
    }


    private void renderGUI(SpriteBatch batch) {
    }

    public void resize(int width, int height) {
        viewport.update(width, height, false);
        camera.position.set(viewport.getWorldWidth()/ 2f, viewport.getWorldHeight() / 2f, 0);
    }

    @Override
    public void dispose() {
        b2debugRenderer.dispose();
        batch.dispose();
    }
}
