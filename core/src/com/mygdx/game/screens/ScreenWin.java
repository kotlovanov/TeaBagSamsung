package com.mygdx.game.screens;

import static com.mygdx.game.MyGdxGame.SCR_HEIGHT;
import static com.mygdx.game.MyGdxGame.SCR_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.characters.Platform;
import com.mygdx.game.components.MovingBackground;
import com.mygdx.game.components.TextButton;


public class ScreenWin implements Screen {

    MyGdxGame myGdxGame;

    MovingBackground background;
    TextButton buttonExit;
    Platform platform1;
    Platform platform2;
    Platform platform;
    int gameCount = 0;
    int count = 0, count2 = 0;
    String[] string = new String[]{"win/1.png",
            "win/2.png",
            "win/3.png",
            "win/4.png",
            "win/5.png"};

    public ScreenWin(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;
        buttonExit = new TextButton("button/button_bg.png", 300, 200, "Exit", myGdxGame);
        String[] strings = new String[]{"background/game_bg.png", "background/game_bg2.png", "background/game_bg3.jpg"};
        background = new MovingBackground(strings[myGdxGame.screenLevel.select_world], 0);
    }

    @Override
    public void show() {
        gameCount++;
        String[] strings = new String[]{"cages/pups1_1.png", "cages/pups2_1.png", "cages/pups3_1.png"};
        platform1 = new Platform(strings[myGdxGame.screenLevel.select_world], 700, 200, 300, 300);
        String[] strings2 = new String[]{"cages/cage1.png", "cages/cage2.png", "cages/cage3.png"};
        platform2 = new Platform(strings2[myGdxGame.screenLevel.select_world], 650, 150, 400, 400);
        platform = new Platform(string[0], 650, 150, 400, 400);
        String[] strings3 = new String[]{"background/game_bg.png", "background/game_bg2.png", "background/game_bg3.jpg"};
        background = new MovingBackground(strings3[myGdxGame.screenLevel.select_world], 0);
//            myGdxGame.screenLevel.select_world = myGdxGame.screenLevel.select_world % myGdxGame.screenLevel.count_world;
        myGdxGame.camera.position.set(SCR_WIDTH / 2, SCR_HEIGHT / 2, 0);
    }

    @Override
    public void render(float delta) {
        myGdxGame.camera.position.set(SCR_WIDTH / 2, SCR_HEIGHT / 2, 0);

        if (Gdx.input.justTouched()) {

            Vector3 touch = myGdxGame.camera.unproject(
                    new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0)
            );
            if (buttonExit.isHit(myGdxGame, (int) touch.x, (int) touch.y)) {
                myGdxGame.setScreen(myGdxGame.screenMenu);
            }
        }

        ScreenUtils.clear(1, 0, 0, 1);
        myGdxGame.camera.update();
        myGdxGame.batch.setProjectionMatrix(myGdxGame.camera.combined);
        myGdxGame.batch.begin();

        background.draw(myGdxGame);
        buttonExit.draw(myGdxGame);

        if (gameCount == 5) {
            platform1.draw(myGdxGame.batch);
            platform2.draw(myGdxGame.batch);
            platform2.x += 5;
            String[] strings = new String[]{"cages/pups1_2.png", "cages/pups2_2.png", "cages/pups3_2.png"};
            if (platform2.x > SCR_WIDTH) {
                platform1 = new Platform(strings[myGdxGame.screenLevel.select_world], 700, 200, 300, 300);
                count++;
                if (count == 60) {
                    count = 0;
                    gameCount = 0;
                    myGdxGame.screenLevel.select_world += 1;
                    myGdxGame.screenLevel.select_world = myGdxGame.screenLevel.select_world % myGdxGame.screenLevel.count_world;
                    myGdxGame.setScreen(myGdxGame.screenGame);
                }
            }
        }
        else {
            count++;
            if (count == 60) {
                count = 0;
                myGdxGame.setScreen(myGdxGame.screenGame);
            }
        }
        count2++;
        platform = new Platform(string[count2 / 10], 0, SCR_HEIGHT-350, 800, 500);
        platform.draw(myGdxGame.batch);
        if (count2++ == string.length * 10 - 1) count2 = 0;


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
        buttonExit.dispose();
    }
}
