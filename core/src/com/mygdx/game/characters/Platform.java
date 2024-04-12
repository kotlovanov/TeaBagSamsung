package com.mygdx.game.characters;

import static com.mygdx.game.MyGdxGame.SCR_WIDTH;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class Platform {
    Texture tPlatform;
    int speed = 8;
    public int width;
    public int height;
    public int x, y;

    public Platform(String texture, int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        tPlatform = new Texture(texture);
    }

    public void draw(Batch batch) {
        batch.draw(tPlatform, x, y, width, height);
    }

    public void dispose() {
        tPlatform.dispose();
    }
}
