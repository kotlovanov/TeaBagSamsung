package com.mygdx.game.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.mygdx.game.MyGdxGame;

public class TextButton {

    BitmapFont font;

    String text;
    Texture texture;

    int x, y;
    int textX, textY;
    int buttonWidth, buttonHeight;
    int textWidth, textHeight;

    public TextButton(String tTexture, int x, int y, String text, int buttonWidth, int buttonHeight, MyGdxGame myGdxGame) {
        this.text = text;
        this.x = x;
        this.y = y;

        font = new BitmapFont();
        font.getData().scale(5f);
        font.setColor(Color.WHITE);

        GlyphLayout gl = new GlyphLayout(font, text);
        textWidth = (int) gl.width;
        textHeight = (int) gl.height;

        texture = new Texture(tTexture);
        this.buttonWidth = buttonWidth;
        this.buttonHeight = buttonHeight;

        textX = x + (buttonWidth - textWidth) / 2;
        textY = y + (buttonHeight + textHeight) / 2;
    }

    public TextButton(String tTexture, int x, int y, String text, MyGdxGame myGdxGame) {
        this.text = text;
        this.x = x;
        this.y = y;

        font = new BitmapFont();
        font.getData().scale(5f);
        font.setColor(Color.WHITE);

        GlyphLayout gl = new GlyphLayout(font, text);
        textWidth = (int) gl.width;
        textHeight = (int) gl.height;

        texture = new Texture(tTexture);
        this.buttonWidth = texture.getWidth();
        this.buttonHeight = texture.getHeight();

        textX = (x + (buttonWidth - textWidth) / 2);
        textY = y + (buttonHeight + textHeight) / 2;
    }

    public boolean isHit(MyGdxGame myGdxGame, int tx, int ty) {
        int newX = (int) (x - buttonWidth / 2 - MyGdxGame.SCR_WIDTH / 2 + myGdxGame.camera.position.x);
        int newY = (int) (y - buttonHeight / 2 - MyGdxGame.SCR_HEIGHT / 2 + myGdxGame.camera.position.y);
        return tx >= newX && tx <= newX + buttonWidth
                && ty >= newY && ty <= newY + buttonHeight;
    }

    public void draw(MyGdxGame myGdxGame) {
        int newX = (int) (x - buttonWidth / 2 - MyGdxGame.SCR_WIDTH / 2 + myGdxGame.camera.position.x);
        int newY = (int) (y - buttonHeight / 2 - MyGdxGame.SCR_HEIGHT / 2 + myGdxGame.camera.position.y);
        textX = (newX + (buttonWidth - textWidth) / 2);
        textY = newY + (buttonHeight + textHeight) / 2;
        myGdxGame.batch.draw(texture, newX, newY, buttonWidth, buttonHeight);
        font.draw(myGdxGame.batch, text, textX, textY);
    }

    public void dispose() {
        texture.dispose();
        font.dispose();
    }
}
