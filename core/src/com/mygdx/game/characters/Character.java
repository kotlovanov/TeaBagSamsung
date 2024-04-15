package com.mygdx.game.characters;


import static com.mygdx.game.MyGdxGame.SCR_HEIGHT;
import static com.mygdx.game.MyGdxGame.SCR_WIDTH;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Character {

    public int x, y;
    int width, height;

    int speed;
    int speedX;
    public int jumpHeight;
    final int maxHeightOfJump = 150;
    boolean jump = false;
    public boolean isStop;
    public int a, oldA;
    public boolean forJump = true;
    public int flag = 0;

    int frameCounter;
    Sprite[] framesArray;

    public Character(int x, int y, int speed, int speedX, int width, int height, boolean isStop) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.speedX = speedX;
        this.width = width;
        this.height = height;

        this.isStop = isStop;
        frameCounter = 0;

        framesArray = new Sprite[]{new Sprite(new Texture("characterTiles/char1.png")), new Sprite(new Texture("characterTiles/char2.png")), new Sprite(new Texture("characterTiles/char3.png")), new Sprite(new Texture("characterTiles/char4.png"))};
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void onClick() {
        if (isStop && !jump && forJump) {
            forJump = false;
            jump = true;
            jumpHeight = maxHeightOfJump + y;
        }
    }

    public void jump() {
        if (y >= jumpHeight) {
            jump = false;
        }


        if (jump) {
            y += speed;
        } else if (!(isStop)) {
            y -= speed;
        }
    }

    public void move() {
        if (-flag != a) x -= a * speed;

    }

    public void forMove(int a) {
        this.a = a;
    }

    public boolean isInField() {
        if (y + height < 0) return false;
        return true;
    }

    public void draw(Batch batch) {
        int frameMultiplier = 10;
        if (a == -1) flipX(false);
        else if (a == 1) flipX(true);
        if (a != 0) {
            batch.draw(framesArray[frameCounter / frameMultiplier], x, y, width, height);
            if (frameCounter++ == framesArray.length * frameMultiplier - 1) frameCounter = 0;
        } else batch.draw(framesArray[0], x, y, width, height);
    }

    void flipX(boolean rotate) {
        for (Sprite sprite : framesArray) {
            sprite.setFlip(rotate, false);
        }
    }

    public int stop(int px, int py, int pwidth, int pHeight) {
        if (x + width > px && x + width <= px + 10 && (y + height > py && y - 1 < py + pHeight))
            return 1;
        else if (x > px + pwidth && x <= px + pwidth + 10 && (y + height > py && y - 1 < py + pHeight))
            return -1;
        else return 0;
    }

    public boolean isHit(int px, int py, int pwidth, int pheight) {
        if ((x + width - 5 > px && x + 5 < px + pwidth) && (y <= py + pheight && y >= py + pheight - 10))
            return true;
        return false;
    }

//    public void dispose() {
//        for (Sprite sprite : framesArray) {
//            sprite.dispose();
//        }
//    }

}
