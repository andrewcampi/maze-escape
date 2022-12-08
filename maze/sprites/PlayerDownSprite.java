package com.mygdx.maze.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class PlayerDownSprite extends Sprite {

    private Texture walkDown;

    public PlayerDownSprite() {

        walkDown = new Texture(Gdx.files.internal("sprites/playerDown.png"));
        setRegion(walkDown);
        setBounds(0,0, walkDown.getWidth(), walkDown.getHeight());

    }

}

