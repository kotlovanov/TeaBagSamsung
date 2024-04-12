package com.mygdx.game.screens;

import static com.mygdx.game.MyGdxGame.SCR_HEIGHT;
import static com.mygdx.game.MyGdxGame.SCR_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.components.MovingBackground;
import com.mygdx.game.components.PointCounter;
import com.mygdx.game.components.TextButton;


public class ScreenWin implements Screen {

    MyGdxGame myGdxGame;

    MovingBackground background;
    TextButton buttonNext;
    TextButton buttonExit;
    int gameCount = 9;

    public ScreenWin(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;
        if (gameCount <= 10)
            buttonNext = new TextButton("button/button_bg.png", 300, 400, "next game", myGdxGame);
        else if(gameCount <= 20)
            buttonNext = new TextButton("button/button_bg.png", 300, 400, "next world", myGdxGame);
        buttonExit = new TextButton("button/button_bg.png", 300, 200, "Exit", myGdxGame);
        background = new MovingBackground("background/restart_bg.png");
    }

    @Override
    public void show() {
        gameCount ++;
        if (gameCount < 10)
            buttonNext = new TextButton("button/button_bg.png", 300, 400, "next game", myGdxGame);
        else{
            if(myGdxGame.screenLevel.select_world == myGdxGame.screenLevel.count_world) {
                buttonNext = new TextButton("button/button_bg.png", 300, 400, "exit", myGdxGame);
            }
            else buttonNext = new TextButton("button/button_bg.png", 300, 400, "next world", myGdxGame);
            myGdxGame.screenLevel.select_world += 1;
            gameCount = 0;
        }
        myGdxGame.camera.position.set(SCR_WIDTH/2, SCR_HEIGHT/2, 0);
    }

    @Override
    public void render(float delta) {
        myGdxGame.camera.position.set(SCR_WIDTH/2, SCR_HEIGHT/2, 0);

        if (Gdx.input.justTouched()) {

            Vector3 touch = myGdxGame.camera.unproject(
                    new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0)
            );

            if (buttonNext.isHit(myGdxGame, (int) touch.x, (int) touch.y)) {
                if(myGdxGame.screenLevel.select_world == myGdxGame.screenLevel.count_world && gameCount > 10) {
                    myGdxGame.setScreen(myGdxGame.screenMenu);
                }
                else myGdxGame.setScreen(myGdxGame.screenGame);
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
        buttonNext.draw(myGdxGame);
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
        buttonNext.dispose();
        buttonExit.dispose();
    }
}
