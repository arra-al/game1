package com.learn.game1.util;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.learn.game1.game.object.AbstractObject;

/**
 * Created by User on 5/16/2016.
 */
public class CameraHelper {
    private final float MAX_ZOOM_IN = 0.25f;
    private final float MAX_ZOOM_OUT = 10.0f;
    private final float FOLLOW_SPEED = 4.0f;
    private final Vector2 position;
    private float zoom;
    private AbstractObject target;

    /**
     * Default constructor.
     */
    public CameraHelper() {
        position = new Vector2();
        zoom = 1.0f;
    }

    /**
     * Updates the camera position.
     *
     * @param deltaTime
     */
    public void update(float deltaTime) {
        if (!hasTarget()) {
            return;
        }
        position.lerp(target.position, FOLLOW_SPEED * deltaTime);
        // Prevent camera from moving down too far
        position.y = Math.max(-1f, position.y);
    }

    public void setPosition(float x, float y) {
        position.set(x, y);
    }

    public Vector2 getPosition() {
        return position;
    }

    public void addZoom(float amount) {
        setZoom(zoom + amount);
    }

    public void setZoom(float zoom) {
        this.zoom = MathUtils.clamp(zoom, MAX_ZOOM_IN, MAX_ZOOM_OUT);
    }

    public float getZoom() {
        return zoom;
    }

    public void setTarget(AbstractObject target) {
        this.target = target;
    }

    public AbstractObject getTarget() {
        return target;
    }

    public boolean hasTarget() {
        return target != null;
    }

    public boolean hasTarget(AbstractObject target) {
        return hasTarget() && this.target.equals(target);
    }

    public void applyTo(OrthographicCamera camera) {
        camera.position.x = position.x;
        camera.position.y = position.y;
        camera.zoom = zoom;
        camera.update();
    }
}
