package com.me.justanothertowerdefense;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;

public interface GameObject {

    public void update(float deltaTime);

    public void draw(Batch batch);

    public void setVelocity(Vector2 velocity);

    public Vector2 getPosition();

    public void setPosition(Vector2 position);
}
