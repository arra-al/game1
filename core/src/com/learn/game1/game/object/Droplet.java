package com.learn.game1.game.object;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.learn.game1.game.Assets;

/**
 * Created by User on 5/13/2016.
 */
public class Droplet extends AbstractObject{
    private TextureRegion region;

    public Droplet() {
        init();
    }

    private void init() {
        region = Assets.instance.droplet.droplet;

        position.x = MathUtils.random(0, Gdx.graphics.getWidth() - 64);
        position.y = Gdx.graphics.getHeight();

        dimension.set(64f, 64f);
        //Center image on game object;
        origin.set(dimension.x / 2, dimension.y / 2);
        // Bounding box for collision detection
        bounds.set(position.x, position.y, dimension.x, dimension.y);

        // Set physics values
        velocity.set(0f, -MathUtils.random(100, 500));
        terminalVelocity.set(0f, 600.0f);
        friction.set(0.0f, 0.0f);
        acceleration.set(0.0f, -9.81f);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
    }

    @Override
    public void render(SpriteBatch batch) {
        TextureRegion reg = region;
        batch.draw(reg.getTexture(), position.x, position.y, origin.x, origin.y, dimension.x, dimension.y, scale.x, scale.y, rotation, //
                reg.getRegionX(), reg.getRegionY(), reg.getRegionWidth(), reg.getRegionHeight(), false, false);
    }
}
