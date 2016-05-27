package com.learn.game1.game.object;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.learn.game1.game.Assets;

/**
 * Created by User on 5/13/2016.
 */
public class Bucket extends AbstractObject {
    private TextureRegion region;
    private Vector3 moveToPoint;

    private STATUS bucketStatus;

    public enum STATUS {
        MOVE_LEFT, MOVE_RIGHT, IDLE;
    }

    public Bucket() {
        init();
    }
    private void init() {
        region = Assets.instance.bucket.bucket;

        moveToPoint = new Vector3(0, 0, 0);
        bucketStatus = STATUS.IDLE;

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
        terminalVelocity.set(5000.0f, 0.0f);
        friction.set(0f, 0.0f);
        acceleration.set(0.0f, 0.0f);
    }

    public void moveTo(Vector3 point) {
        this.moveToPoint = point;
        this.moveToPoint.x = this.moveToPoint.x - 32; //subs 32 to center the bucket
        if(point.x > position.x) {
            velocity.x = 1000;
            bucketStatus = STATUS.MOVE_RIGHT;
        }
        else if(point.x < position.x) {
            velocity.x = -1000;
            bucketStatus = STATUS.MOVE_LEFT;
        } else {
            velocity.x = 0;
        }
    }

    @Override
    public void update(float delta) {
        if(moveToPoint.x >= position.x && bucketStatus.equals(STATUS.MOVE_LEFT)) {
            velocity.x = 0;
            bucketStatus = STATUS.IDLE;
        } else if(moveToPoint.x <= position.x && bucketStatus.equals(STATUS.MOVE_RIGHT)) {
            velocity.x = 0;
            bucketStatus = STATUS.IDLE;
        } else if(bucketStatus.equals(STATUS.IDLE) && velocity.x != 0) {
            velocity.x = 0;
        }
        super.update(delta);
    }

    @Override
    public void render(SpriteBatch batch) {
        TextureRegion reg = region;
        batch.draw(reg.getTexture(), position.x, position.y, origin.x, origin.y, dimension.x, dimension.y, scale.x, scale.y, rotation, //
                reg.getRegionX(), reg.getRegionY(), reg.getRegionWidth(), reg.getRegionHeight(), false, false);
    }
}
