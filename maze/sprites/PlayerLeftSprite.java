package com.mygdx.maze.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class PlayerLeftSprite extends Sprite {

    private Texture walkLeft;

    public PlayerLeftSprite() {

        walkLeft = new Texture(Gdx.files.internal("sprites/playerLeft.png"));
        setRegion(walkLeft);
        setBounds(0,0, walkLeft.getWidth(), walkLeft.getHeight());

    }

}

