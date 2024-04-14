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

public class ScreenLevel implements Screen {
    MyGdxGame myGdxGame;
    int count_world;
    public int select_world = 0;
    TextButton[] worldList;
    MovingBackground background;

    public ScreenLevel(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;
        int i;
        int x, y;
        count_world = 3;
        worldList = new TextButton[count_world];
        for (i = 0; i <= count_world - 1; i++) {
            x = ((i / 5)) * 500;
            y = (i - (x / 500) * 5) * 150;
            worldList[i] = new TextButton("button/button_bg.png", x + 300, 600 - y, "world " + i, 400, 100, myGdxGame);
            System.out.println(i + " " + x + " " + y);
        }
        String[] strings = new String[]{"background/game_bg.png", "background/game_bg2.png", "background/game_bg3.jpg"};
        background = new MovingBackground(strings[select_world], 0);
    }

    @Override
    public void show() {
        myGdxGame.camera.position.set(SCR_WIDTH / 2, SCR_HEIGHT / 2, 0);
        String[] strings = new String[]{"background/game_bg.png", "background/game_bg2.png", "background/game_bg3.jpg"};
        background = new MovingBackground(strings[myGdxGame.screenLevel.select_world], 0);
    }

    @Override
    public void render(float delta) {
        int i;
        if (Gdx.input.justTouched()) {
            Vector3 touch = myGdxGame.camera.unproject(
                    new Vector3(Gdx.input.getX(0), Gdx.input.getY(0), 0)
            );
            for (i = 0; i <= count_world - 1; i++) {
                if (worldList[i].isHit(myGdxGame, (int) touch.x, (int) touch.y)) {
                    select_world = i;
                    myGdxGame.setScreen(myGdxGame.screenGame);
                }
            }
        }
        ScreenUtils.clear(1, 0, 0, 1);
        myGdxGame.camera.update();
        myGdxGame.batch.setProjectionMatrix(myGdxGame.camera.combined);
        myGdxGame.batch.begin();
//        background.move(-1, myGdxGame, 0);
        background.draw(myGdxGame);
        for (i = 0; i <= count_world - 1; i++) {
            worldList[i].draw(myGdxGame);
        }


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
    }
}
