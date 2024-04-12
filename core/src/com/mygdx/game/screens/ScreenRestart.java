package com.mygdx.game.screens;

import static com.mygdx.game.MyGdxGame.SCR_HEIGHT;
import static com.mygdx.game.MyGdxGame.SCR_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.components.MovingBackground;
import com.mygdx.game.components.TextButton;


public class ScreenRestart implements Screen {

    MyGdxGame myGdxGame;

    MovingBackground background;
    TextButton buttonRestart;
    TextButton buttonExit;

    int gamePoints;

    public ScreenRestart(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;
        buttonRestart = new TextButton("button/button_bg.png", 400, 400, "Restart", myGdxGame);
        buttonExit = new TextButton("button/button_bg.png", 400, 100, "Exit", myGdxGame);
        String[] strings = new String[]{"background/game_bg.png", "background/game_bg2.png"};
        background = new MovingBackground(strings[myGdxGame.screenLevel.select_world], 0);
    }

    @Override
    public void show() {
        myGdxGame.camera.position.set(SCR_WIDTH / 2, SCR_HEIGHT / 2, 0);
        String[] strings = new String[]{"background/game_bg.png", "background/game_bg2.png"};
        background = new MovingBackground(strings[myGdxGame.screenLevel.select_world], 0);
    }

    @Override
    public void render(float delta) {
        myGdxGame.camera.position.set(SCR_WIDTH / 2, SCR_HEIGHT / 2, 0);

        if (Gdx.input.justTouched()) {

            Vector3 touch = myGdxGame.camera.unproject(
                    new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0)
            );

            if (buttonRestart.isHit(myGdxGame, (int) touch.x, (int) touch.y)) {
                myGdxGame.setScreen(myGdxGame.screenGame);
            }
            if (buttonExit.isHit(myGdxGame, (int) touch.x, (int) touch.y)) {
                myGdxGame.setScreen(myGdxGame.screenMenu);
            }
        }

        ScreenUtils.clear(1, 0, 0, 1);
        myGdxGame.camera.update();
        myGdxGame.batch.setProjectionMatrix(myGdxGame.camera.combined);
        myGdxGame.batch.begin();

        background.draw(myGdxGame);
        buttonRestart.draw(myGdxGame);
        buttonExit.draw(myGdxGame);

        myGdxGame.batch.end();
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
        background.dispose();
        buttonRestart.dispose();
        buttonExit.dispose();
    }
}
