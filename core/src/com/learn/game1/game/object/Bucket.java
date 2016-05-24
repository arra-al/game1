package com.learn.game1.game.object;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.learn.game1.game.Assets;

/**
 * Created by User on 5/13/2016.
 */
public class Bucket extends AbstractObject {
    private TextureRegion region;

    public Bucket() {
        init();
    }
    private void init() {
        region = Assets.instance.bucket.bucket;
        //set bucket position
        float posX = Gdx.graphics.getWidth() / 2 - 64 / 2; //center the bucket horizontally
        float posY = 20; // bottom left corner of the bucket is 20 pixels above
        position.set(posX, posY);

        dimension.set(64f, 64f);
        //Center image on game object;
        origin.set(dimension.x / 2, dimension.y / 2);
        // Bounding box for collision detection
        bounds.set(position.x, position.y, dimension.x, dimension.y);

        // Set physics values
        terminalVelocity.set(3.0f, 0.0f);
        friction.set(12.0f, 0.0f);
        acceleration.set(0.0f, 0.0f);
    }

    @Override
    public void render(SpriteBatch batch) {
        TextureRegion reg = region;
        batch.draw(reg.getTexture(), position.x, position.y, origin.x, origin.y, dimension.x, dimension.y, scale.x, scale.y, rotation, //
                reg.getRegionX(), reg.getRegionY(), reg.getRegionWidth(), reg.getRegionHeight(), false, false);
    }
}
