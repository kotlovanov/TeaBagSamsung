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

public class ScreenMenu implements Screen {
    MyGdxGame myGdxGame;

    TextButton buttonGame;
    TextButton buttonExit;
    MovingBackground background;

    public ScreenMenu(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;
        buttonGame = new TextButton("button/button_bg.png", 400, 400, "Play", myGdxGame);
        buttonExit = new TextButton("button/button_bg.png", 400, 100, "Exit", myGdxGame);
        background = new MovingBackground("background/menu_bg.jpg", 0);
    }

    @Override
    public void show() {
        myGdxGame.camera.position.set(SCR_WIDTH / 2, SCR_HEIGHT / 2, 0);
        background = new MovingBackground("background/menu_bg.jpg", 0);

    }

    @Override
    public void render(float delta) {
        if (Gdx.input.justTouched()) {

            Vector3 touch = myGdxGame.camera.unproject(
                    new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0)
            );

            if (buttonGame.isHit(myGdxGame, (int) touch.x, (int) touch.y)) {
                myGdxGame.setScreen(myGdxGame.screenLevel);
            }
            if (buttonExit.isHit(myGdxGame, (int) touch.x, (int) touch.y)) {
                Gdx.app.exit();
            }
        }
        ScreenUtils.clear(1, 0, 0, 1);
        myGdxGame.camera.update();
        myGdxGame.batch.setProjectionMatrix(myGdxGame.camera.combined);
        myGdxGame.batch.begin();
//        background.move(-1);
        background.draw(myGdxGame);
        buttonGame.draw(myGdxGame);
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
        buttonExit.dispose();
        buttonGame.dispose();
        background.dispose();
    }
}
