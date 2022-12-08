package com.mygdx.maze.sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Gdx;

public class EnemySprite extends Sprite {

    private Texture enemyImage;

    public EnemySprite() {

        enemyImage = new Texture(Gdx.files.internal("sprites/enemy.png"));
        setRegion(enemyImage);
        setBounds(0,0, enemyImage.getWidth(), enemyImage.getHeight());

    }

}

