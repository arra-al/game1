package com.learn.game1.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.utils.Disposable;
import com.learn.game1.util.Constants;

/**
 * Created by User on 5/16/2016.
 */
public class WorldRender implements Disposable {
    private OrthographicCamera camera;
    private OrthographicCamera cameraGUI;
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
        camera = new OrthographicCamera(Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT);
        camera.position.set(0, 0, 0);
        camera.update();

        cameraGUI = new OrthographicCamera(Constants.VIEWPORT_GUI_WIDTH, Constants.VIEWPORT_GUI_HEIGHT);
        cameraGUI.position.set(0, 0, 0);
        cameraGUI.setToOrtho(true);
        cameraGUI.update();

        b2debugRenderer = new Box2DDebugRenderer();
    }

    public void render() {
        renderWorld(batch);
        renderGUI(batch);
    }

    private void renderWorld(SpriteBatch batch) {
    }


    private void renderGUI(SpriteBatch batch) {
    }

    public void resize(int width, int height) {
        camera.viewportWidth = Constants.VIEWPORT_WIDTH / height * width;
        camera.update();

        cameraGUI.viewportHeight = Constants.VIEWPORT_GUI_HEIGHT;
        cameraGUI.viewportWidth = Constants.VIEWPORT_GUI_HEIGHT / height * width;
        cameraGUI.position.set(cameraGUI.viewportWidth / 2, cameraGUI.viewportHeight / 2, 0);
        cameraGUI.update();
    }

    @Override
    public void dispose() {

    }
}
