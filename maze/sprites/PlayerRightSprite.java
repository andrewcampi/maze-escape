package com.mygdx.maze.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class PlayerRightSprite extends Sprite {

    private Texture walkRight;

    public PlayerRightSprite() {

        walkRight = new Texture(Gdx.files.internal("sprites/playerRight.png"));
        setRegion(walkRight);
        setBounds(0,0, walkRight.getWidth(), walkRight.getHeight());

    }

}

