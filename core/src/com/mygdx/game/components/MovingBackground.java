package com.mygdx.game.components;

import static com.mygdx.game.MyGdxGame.SCR_HEIGHT;
import static com.mygdx.game.MyGdxGame.SCR_WIDTH;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.mygdx.game.MyGdxGame;


public class MovingBackground {

    Texture texture;

    int texture1X, texture2X;
    int speed;

    public MovingBackground(String pathToTexture, int speed) {
        texture1X = 0;
        this.speed = speed;
        System.out.println(speed);
        texture2X = MyGdxGame.SCR_WIDTH;
        texture = new Texture(pathToTexture);
    }

    public void move(int a, MyGdxGame myGdxGame, int x) {
        a = a * speed;
        System.out.println(a);
        texture1X += a;
        texture2X += a;


        if (texture1X <= -MyGdxGame.SCR_WIDTH + (x - SCR_WIDTH/2)) {
            texture1X += SCR_WIDTH * 2;
        }
        if (texture2X <= -MyGdxGame.SCR_WIDTH + (x - SCR_WIDTH/2)) {
            texture2X += SCR_WIDTH * 2;
        }

        if (texture1X > MyGdxGame.SCR_WIDTH + (x - SCR_WIDTH/2)) {
            texture1X -= SCR_WIDTH * 2;
        }
        if (texture2X > MyGdxGame.SCR_WIDTH + (x - SCR_WIDTH/2)) {
            texture2X -= SCR_WIDTH * 2;
        }
    }

    public void draw(MyGdxGame myGdxGame) {
        myGdxGame.batch.draw(texture, texture1X, 0, MyGdxGame.SCR_WIDTH + 4, MyGdxGame.SCR_HEIGHT);
        myGdxGame.batch.draw(texture, texture2X, 0, MyGdxGame.SCR_WIDTH + 4, MyGdxGame.SCR_HEIGHT);
    }

    public void dispose() {
        texture.dispose();
    }

}
