package com.mygdx.maze.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.mygdx.maze.main.Driver;

public class TitleScreen implements Screen {
    final Driver game;
    private Texture castleImage;
    private Music gameMusic;
    private BitmapFont font;
    private Screen currentScreen;
    private Sprite castle;
    private Texture knightImage;
    private Texture guardImage;
    private Texture guard2Image;
    private Texture gateImage;
    private TextButton playButton;
    private TextButton instructionButton;
    private TextButton creditButton;
    private TextButton backButton;
    private Skin skin;
    private Stage stage;
    TextButton.TextButtonStyle buttonStyle;
    TextureAtlas buttonAtlas;

    enum Screen {
        MAIN, INSTRUCTIONS, CREDITS
    }

    public TitleScreen(final Driver gam) {
        this.game = gam;
        currentScreen = Screen.MAIN;
        gameMusic = Gdx.audio.newMusic(Gdx.files.internal("Behind Foreign Walls (LOOP).wav"));
        gameMusic.setLooping(true);

        font = new BitmapFont(Gdx.files.internal("luminari.fnt"));
        font.setColor(Color.WHITE);

        skin = new Skin();
        buttonAtlas = new TextureAtlas(Gdx.files.internal("plain-james-ui.atlas"));
        skin.addRegions(buttonAtlas);

        playButton = createButton("Play", 25, 25);
        playButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                gameMusic.stop();
                game.setScreen(new IntroScreen(game));
            }
        });

        instructionButton = createButton("Instructions", 200, 25);
        instructionButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                stage.clear();
                currentScreen = Screen.INSTRUCTIONS;
            }
        });

        creditButton = createButton("Credits", 500, 25);
        creditButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                stage.clear();
                currentScreen = Screen.CREDITS;
            }
        });

        backButton = createButton("Back", 10, 25);
        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                stage.clear();
                currentScreen = Screen.MAIN;
            }
        });


        castleImage = new Texture(Gdx.files.internal("introcastle.png"));
        knightImage = new Texture(Gdx.files.internal("sirgeorge.png"));
        guardImage = new Texture(Gdx.files.internal("guard.png"));
        guard2Image = new Texture(Gdx.files.internal("guard2.png"));
        gateImage = new Texture(Gdx.files.internal("gate.png"));
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        castle = new Sprite(castleImage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, .25f, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        if(currentScreen == Screen.MAIN) {
            game.batch.draw(castle, 0, 0);
            font.draw(game.batch, "Castle Escape", 25, 450);
            stage.addActor(playButton);
            stage.addActor(instructionButton);
            stage.addActor(creditButton);
            stage.draw();
        }
        if(currentScreen == Screen.INSTRUCTIONS) {
            game.batch.draw(knightImage, 550, 150);
            game.batch.draw(guardImage, 225, 75);
            game.batch.draw(guard2Image, 275, 75);
            game.batch.draw(gateImage, -25, 75);
            font.draw(game.batch, "Controls:\nW Key: Up\nA Key: Left\nS Key: Down\nD Key: Right", 370, 250);
            font.draw(game.batch, "Escape the maze before your torch runs\nout! Don't let the guards see you!\nThey'll sound" +
                    " the alarm and the gates\nwill close!", 10, 450);
            stage.addActor(backButton);
            stage.draw();
        }
        if(currentScreen == Screen.CREDITS) {
            font.draw(game.batch, "Credits:\nAndrew Campi\nAnna Nardelli\nArielle Sinicin\n" +
                    "CS-360 Monmouth University Fall 2022\nMusic: Nicole Lyria", 25, 450);
            stage.addActor(backButton);
            stage.draw();
        }
        game.batch.end();
    }

    public TextButton createButton(String text, int x, int y) {
        buttonStyle = new TextButton.TextButtonStyle();
        buttonStyle.font = font;
        buttonStyle.down = skin.getDrawable("round-gray");
        TextButton button = new TextButton(text, buttonStyle);
        button.setPosition(x, y);
        return button;
    }

    @Override
    public void show() {
        gameMusic.play();
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
