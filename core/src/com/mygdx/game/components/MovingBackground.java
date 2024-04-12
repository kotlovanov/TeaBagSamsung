package com.mygdx.game.components;

import static com.mygdx.game.MyGdxGame.SCR_WIDTH;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.mygdx.game.MyGdxGame;


public class MovingBackground {

    Texture texture;

    int texture1X, texture2X;
    int speed = 3;

    public MovingBackground(String pathToTexture, int speed) {
        texture1X = -1;
        this.speed = speed;
        texture2X = MyGdxGame.SCR_WIDTH;
        texture = new Texture(pathToTexture);
    }
    public MovingBackground(String pathToTexture) {
        texture1X = -1;
        texture2X = MyGdxGame.SCR_WIDTH;
        texture = new Texture(pathToTexture);
    }

    public void move(int a, MyGdxGame myGdxGame, int x) {
        texture1X += a * speed;
        texture2X += a * speed;
        int new1X = (x - SCR_WIDTH/2) + texture1X;
        int new2X = (x - SCR_WIDTH/2) + texture2X;

//
        if (new1X <= -MyGdxGame.SCR_WIDTH + (x - SCR_WIDTH/2)) {
            texture2X = SCR_WIDTH + (x - SCR_WIDTH/2);
        }
        if (new2X <= -MyGdxGame.SCR_WIDTH + (x - SCR_WIDTH/2)) {
            texture2X = SCR_WIDTH + (x - SCR_WIDTH/2);
        }
//
        if (new1X > MyGdxGame.SCR_WIDTH + (x - SCR_WIDTH/2)) {
            texture1X = -(SCR_WIDTH + (x - SCR_WIDTH/2));
        }
        if (new2X > MyGdxGame.SCR_WIDTH + (x - SCR_WIDTH/2)) {
            texture2X = -(SCR_WIDTH + (x - SCR_WIDTH/2));
        }
        System.out.print(new1X + " ");
        System.out.println(new2X);
    }

    public void draw(MyGdxGame myGdxGame) {
        int new1X = (int) (texture1X - MyGdxGame.SCR_WIDTH / 2 - MyGdxGame.SCR_WIDTH / 2 + myGdxGame.camera.position.x);
        int new2X = (int) (texture2X - MyGdxGame.SCR_WIDTH / 2 - MyGdxGame.SCR_WIDTH / 2 + myGdxGame.camera.position.x);
        int newY = (int) (0 - MyGdxGame.SCR_HEIGHT / 2 + myGdxGame.camera.position.y);
        myGdxGame.batch.draw(texture, new1X, newY, MyGdxGame.SCR_WIDTH + 4, MyGdxGame.SCR_HEIGHT);
        myGdxGame.batch.draw(texture, new2X, newY, MyGdxGame.SCR_WIDTH + 4, MyGdxGame.SCR_HEIGHT);
    }

    public void dispose() {
        texture.dispose();
    }

}
