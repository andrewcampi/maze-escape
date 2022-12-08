package com.mygdx.maze.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class PlayerUpSprite extends Sprite {

    private Texture walkUp;

    public PlayerUpSprite() {

        walkUp = new Texture(Gdx.files.internal("sprites/playerUp.png"));
        setRegion(walkUp);
        setBounds(0,0, walkUp.getWidth(), walkUp.getHeight());

    }

}

