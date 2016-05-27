package com.learn.game1.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Disposable;
import com.learn.game1.util.Constants;

/**
 * Created by User on 5/12/2016.
 */
public class Assets implements Disposable, AssetErrorListener {
    private static final String TAG = Assets.class.getName();

    public static final Assets instance = new Assets();
    private AssetManager assetManager;

    public AssetSounds sounds;
    public AssetMusic music;

    public AssetsFonts fonts;

    public AssetBucket bucket;
    public AssetDroplet droplet;

    private Assets() {}

    public void init(AssetManager assetManager) {
        this.assetManager = assetManager;

        //set asset manager error handler
        assetManager.setErrorListener(this);

        //load texture atlas
        assetManager.load(Constants.TEXTURE_ATLAS_OBJECTS, TextureAtlas.class);

        //load sounds
        assetManager.load("sounds/drop.wav", Sound.class);
        //load music
        assetManager.load("music/rain.mp3", Music.class);

        assetManager.finishLoading();

        Gdx.app.debug(TAG, "# of assets loaded: " + assetManager.getAssetNames().size);
        for (String asset : assetManager.getAssetNames()) {
            Gdx.app.debug(TAG, "asset: " + asset);
        }

        TextureAtlas atlas = assetManager.get(Constants.TEXTURE_ATLAS_OBJECTS);
        for (Texture texture : atlas.getTextures()) {
            texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        }

        fonts = new AssetsFonts();
        music = new AssetMusic(assetManager);
        sounds = new AssetSounds(assetManager);

        bucket = new AssetBucket(atlas);
        droplet = new AssetDroplet(atlas);
    }

    @Override
    public void error(AssetDescriptor asset, Throwable throwable) {
        Gdx.app.error(TAG, "Couldn't load asset '" + asset.fileName + "'", throwable);
    }

    @Override
    public void dispose() {
        assetManager.dispose();
        fonts.defaultSmall.dispose();
        fonts.defaultNormal.dispose();
        fonts.defaultBig.dispose();
    }

    public static class AssetBucket {
        public final TextureAtlas.AtlasRegion bucket;

        public AssetBucket(TextureAtlas atlas) {
            this.bucket = atlas.findRegion("bucket");
        }
    }

    public static class AssetDroplet {
        public final TextureAtlas.AtlasRegion droplet;

        public AssetDroplet(TextureAtlas atlas) {
            this.droplet = atlas.findRegion("droplet");
        }
    }

    public static class AssetsFonts {

        public final BitmapFont defaultSmall;
        public final BitmapFont defaultNormal;
        public final BitmapFont defaultBig;

        public AssetsFonts() {
            // create three fonts using Libgdx's 15px bitmap font
            defaultSmall = new BitmapFont(Gdx.files.internal("images/arial-15.fnt"), false);
            defaultNormal = new BitmapFont(Gdx.files.internal("images/arial-15.fnt"), false);
            defaultBig = new BitmapFont(Gdx.files.internal("images/arial-15.fnt"), false);
            // set font sizes
            defaultSmall.getData().setScale(0.75f);
            defaultNormal.getData().setScale(1.0f);
            defaultBig.getData().setScale(2.0f);
            // enable linear texture filtering for smooth fonts
            defaultSmall.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
            defaultNormal.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
            defaultBig.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        }

    }

    public static class AssetSounds {

        public final Sound drop;

        public AssetSounds(AssetManager am) {
            drop = am.get("sounds/drop.wav", Sound.class);
        }
    }

    public static class AssetMusic {

        public final Music rain;

        public AssetMusic(AssetManager am) {
            rain = am.get("music/rain.mp3", Music.class);
        }
    }

}
