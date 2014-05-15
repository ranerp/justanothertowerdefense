package com.me.justanothertowerdefense;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class TextureActor extends Actor {

    TextureRegion textureRegion = new TextureRegion();

    private float alpha = 1;

    private Color drawColor = new Color(1, 1, 1, alpha);

    public TextureActor() {
    }

    public void setTexture(TextureRegion texture) {
        this.textureRegion = texture;
    }

    public void setDrawColor(float r, float g, float b) {
        drawColor.r = r;
        drawColor.g = g;
        drawColor.b = b;
    }

    @Override
    public void draw(Batch batch, float alpha) {
        batch.setColor(drawColor.r, drawColor.g, drawColor.b, alpha);
        batch.draw(textureRegion, getX(), getY(), 0, 0, textureRegion.getRegionWidth(), textureRegion.getRegionHeight(), 1, 1, 0);
    }
}
