package com.mygdx.maze.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.mygdx.maze.main.Driver;

public class IntroScreen implements Screen {

    enum Screen{
        ONE, TWO, THREE, CONTROLS;
    }

    private Music introMusic;
    final Driver game;
    private Texture knightImage;
    private Texture kingImage;
    private Texture throneImage;
    private Texture guardImage;
    private Texture guard2Image;
    private Texture gateImage;
    private BitmapFont font;
    private Texture castleImage;
    private BitmapFont font2;
    Screen currentScreen;
    public IntroScreen(final Driver gam) {
        this.game = gam;
        introMusic = Gdx.audio.newMusic(Gdx.files.internal("The Salt Crags (LOOP).wav"));
        introMusic.setLooping(true);
        knightImage = new Texture(Gdx.files.internal("sirgeorge.png"));
        kingImage = new Texture(Gdx.files.internal("evilking.png"));
        throneImage = new Texture(Gdx.files.internal("throne.png"));
        castleImage = new Texture(Gdx.files.internal("castle3.png"));
        guardImage = new Texture(Gdx.files.internal("guard.png"));
        guard2Image = new Texture(Gdx.files.internal("guard2.png"));
        gateImage = new Texture(Gdx.files.internal("gate.png"));
        font = new BitmapFont(Gdx.files.internal("font2.fnt"));
        font2 = new BitmapFont();
        font.setColor(Color.WHITE);
        currentScreen = Screen.ONE;
    }
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, .25f, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if(Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY)){
                if(currentScreen == Screen.ONE){
                    currentScreen = Screen.TWO;
                }
                else if(currentScreen == Screen.TWO){
                    currentScreen = Screen.THREE;
                }
                else if(currentScreen == Screen.THREE){
                    currentScreen = Screen.CONTROLS;
                }
                else if(currentScreen == Screen.CONTROLS){
                    game.setScreen(new GameScreen());
                    introMusic.stop();
                }
        }

        game.batch.begin();
        font2.draw(game.batch, "(Press any key to continue)", 10, 15);
            if(currentScreen == Screen.ONE) {
                font.draw(game.batch, "The courageous knight, Sir George the Gallant", 10, 400);
                font.draw(game.batch, "Is on an important Mission!", 25, 150);
                game.batch.draw(knightImage, 250, 200);
            }
            if(currentScreen == Screen.TWO) {
                game.batch.draw(throneImage, 0, 50);
                font.draw(game.batch, "Undercover in the Enemy Castle,", 10, 475);
                font.draw(game.batch, "Sir George has gathered information", 10, 450);
                font.draw(game.batch, "That will help bring down the Evil King!", 25, 45);
                game.batch.draw(knightImage, 500, 100);
                game.batch.draw(kingImage, 250, 100);
            }
            if(currentScreen == Screen.THREE) {
                game.batch.draw(castleImage, 0, 25);
                font.draw(game.batch, "Sir George must now escape the castle", 10, 475);
                font.draw(game.batch, "to deliver this information to his court!", 10, 45);
                game.batch.draw(knightImage, 350, 50);
            }
            if(currentScreen == Screen.CONTROLS) {
                game.batch.draw(knightImage, 550, 150);
                game.batch.draw(guardImage, 250, 100);
                game.batch.draw(guard2Image, 300, 100);
                game.batch.draw(gateImage, 0, 100);
                font.draw(game.batch, "Controls:\nW Key: Up\nA Key: Left\nS Key: Down\nD Key: Right", 400, 300);
                font.draw(game.batch, "Escape the maze before your torch runs out!\nDon't let the guards see you! They'll sound" +
                        " the\nalarm and the gates will close!", 25, 450);
            }
        game.batch.end();

    }

    @Override
    public void show() {
        introMusic.play();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
