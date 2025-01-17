package com.mygdx.game.screens;


import static com.mygdx.game.MyGdxGame.SCR_HEIGHT;
import static com.mygdx.game.MyGdxGame.SCR_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.characters.Character;
import com.mygdx.game.characters.Platform;
import com.mygdx.game.components.MovingBackground;
import com.mygdx.game.components.TextButton;

import java.util.Random;


public class ScreenGame implements Screen {

    MyGdxGame myGdxGame;
    Random random = new Random();
    TextButton buttonRight;
    TextButton buttonLeft;
    TextButton buttonJump;
    Character character;
    MovingBackground background;
    Platform platform1;
    Platform[] platforms;
    int platformsCount = 10;

    boolean isGameOver;

    public ScreenGame(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;

        buttonRight = new TextButton("button/button_bg2.png", 300, 100, ">", myGdxGame);
        buttonLeft = new TextButton("button/button_bg2.png", 150, 100, "<", myGdxGame);
        buttonJump = new TextButton("button/button_bg2.png", 1100, 100, "", myGdxGame);
        String[] strings = new String[]{"background/game_bg.png", "background/game_bg2.png", "background/game_bg3.jpg"};
        background = new MovingBackground(strings[myGdxGame.screenLevel.select_world], 2);
        character = new Character(SCR_WIDTH / 2, SCR_HEIGHT / 2, 10, 10, 75, 225, false);

        initPlatforms();
        platform1 = new Platform("platforms/finish.png", 6600, 0, 100, SCR_HEIGHT);
    }


    @Override
    public void show() {
        isGameOver = false;
        character.setY(SCR_HEIGHT / 2);
        character.setX(SCR_WIDTH / 2);
        initPlatforms();
        String[] strings = new String[]{"background/game_bg.png", "background/game_bg2.png", "background/game_bg3.jpg"};
        background = new MovingBackground(strings[myGdxGame.screenLevel.select_world], 2);
    }

    @Override
    public void render(float delta) {

        if (isGameOver) {
            myGdxGame.setScreen(myGdxGame.screenRestart);
        }
        Vector3 touch = myGdxGame.camera.unproject(
                new Vector3(Gdx.input.getX(0), Gdx.input.getY(0), 0)
        );
        Vector3 touch2 = myGdxGame.camera.unproject(
                new Vector3(Gdx.input.getX(1), Gdx.input.getY(1), 0)
        );
        character.forMove(0);
        if (Gdx.input.isTouched(0)) {
            if (buttonLeft.isHit(myGdxGame, (int) touch.x, (int) touch.y)) {
                character.forMove(1);
            }
            if (buttonRight.isHit(myGdxGame, (int) touch.x, (int) touch.y)) {
                character.forMove(-1);
            }
            if (buttonJump.isHit(myGdxGame, (int) touch.x, (int) touch.y)) {
                character.onClick();
            }
        } else character.forJump = true;
        if (Gdx.input.isTouched(1)) {
            if (buttonLeft.isHit(myGdxGame, (int) touch2.x, (int) touch2.y)) {
                character.forMove(1);
            }
            if (buttonRight.isHit(myGdxGame, (int) touch2.x, (int) touch2.y)) {
                character.forMove(-1);
            }
            if (buttonJump.isHit(myGdxGame, (int) touch2.x, (int) touch2.y)) {
                character.onClick();
            }
        } else character.forJump = true;
        myGdxGame.camera.position.set(character.x, SCR_HEIGHT / 2, 0);

        character.move();

        character.jump();
        if (!character.isInField()) {
            isGameOver = true;
        }


        for (Platform platform : platforms) {
            if (character.isHit(platform.x, platform.y, platform.width, platform.height)) {
                character.isStop = true;
                break;
            }
            character.isStop = false;
        }
        for (Platform platform : platforms) {
            character.flag = character.stop(platform.x, platform.y, platform.width, platform.height);
            if (character.flag != 0) break;
        }
        if (character.flag == 0) background.move(character.a, myGdxGame, character.x);
        if (character.x >= 6600) myGdxGame.setScreen(myGdxGame.screenWin);


        ScreenUtils.clear(1, 0, 0, 1);
        myGdxGame.camera.update();
        myGdxGame.batch.setProjectionMatrix(myGdxGame.camera.combined);
        myGdxGame.batch.begin();

        background.draw(myGdxGame);

        platform1.draw(myGdxGame.batch);
        for (int i = 0; i < platformsCount; i++) {
            platforms[i].draw(myGdxGame.batch);
        }

        character.draw(myGdxGame.batch);
        buttonRight.draw(myGdxGame);
        buttonJump.draw(myGdxGame);
        buttonLeft.draw(myGdxGame);

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

        platform1.dispose();
        for (int i = 0; i < platformsCount; i++) {
            platforms[i].dispose();
        }

        buttonRight.dispose();
        buttonLeft.dispose();
        buttonJump.dispose();
    }

    void initPlatforms() {
        String[] strings = new String[]{"platforms/platform1.png", "platforms/platform2.png", "platforms/platform3.png"};
        platforms = new Platform[platformsCount];
        platforms[0] = new Platform(strings[myGdxGame.screenLevel.select_world], SCR_WIDTH / 2 - 50, SCR_HEIGHT / 2 - 120, 400, 100);
        int oldY = SCR_HEIGHT / 2 - 120;
        for (int i = 1; i < platformsCount; i++) {
            int y = (int) (random.nextInt() % 100 * Math.pow(-1, random.nextInt() % 2) + oldY);
            if (y >= SCR_HEIGHT) y -= 100;
            if (y <= 0) y += 100;
            System.out.println(y);
//            int a = 200;
            platforms[i] = new Platform(strings[myGdxGame.screenLevel.select_world], (i + 1) * 600 - y + oldY, y, 400, 100);
            oldY = y;
        }
    }

}
