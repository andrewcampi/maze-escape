package com.mygdx.maze.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

//Will need texture atlas to cut up png of knight images
//Made an atlas from scratch, not sure if it is cut up correctly

public class AtlasPlayerSprite extends Sprite {

    private TextureAtlas textureAtlas;

    private TextureRegion walkDown;
    private TextureRegion walkLeft;
    private TextureRegion walkRight;
    private TextureRegion walkUp;

    private Sound walkingSound;

    public AtlasPlayerSprite() {

        textureAtlas = new TextureAtlas(Gdx.files.internal("sprites/player.atlas"));

        walkDown = textureAtlas.findRegion("down");
        walkLeft = textureAtlas.findRegion("left");
        walkRight = textureAtlas.findRegion("right");
        walkUp = textureAtlas.findRegion("up");

        // A Sprite is not drawable until its texture (region) and bounds are set
        setBounds(0, 0, walkDown.getRegionWidth(), walkDown.getRegionHeight());
        setBounds(0, 0, walkLeft.getRegionWidth(), walkLeft.getRegionHeight());
        setBounds(0, 0, walkRight.getRegionWidth(), walkRight.getRegionHeight());
        setBounds(0, 0, walkUp.getRegionWidth(), walkUp.getRegionHeight());

        walkingSound = Gdx.audio.newSound(Gdx.files.internal("sounds/walking_sound.wav"));

    }

}

