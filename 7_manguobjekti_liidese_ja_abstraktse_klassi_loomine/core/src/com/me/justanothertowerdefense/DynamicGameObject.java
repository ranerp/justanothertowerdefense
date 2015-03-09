package com.me.justanothertowerdefense;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;


public abstract class DynamicGameObject implements GameObject, Comparable {

    protected String ID;

    protected Vector2 velocity = new Vector2();
    protected Vector2 position;
    protected float rotation = 0;

    protected TextureRegion textureRegion;

    public DynamicGameObject(Texture texture, String ID) {
        this(texture, ID, new Vector2());
    }

    public DynamicGameObject(Texture texture, String ID, Vector2 position) {
        this.textureRegion = new TextureRegion(texture);
        this.ID = ID;
        this.position = position;
        this.velocity = new Vector2();
    }

    public String getID() {
        return ID;
    }

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof DynamicGameObject))
            return false;

        return ID.equals(((DynamicGameObject)o).getID());
    }

    @Override
    public int hashCode() {
        int hash = 1;
        hash = hash * 31 + ID.hashCode();
        return hash;
    }

    @Override
    public int compareTo(Object o) {
        return getID().hashCode() - ((DynamicGameObject)o).getID().hashCode();
    }

    @Override
    public void update(float deltaTime) {

    }

    @Override
    public void draw(Batch batch) {
        if(rotation != 0)
            batch.draw(textureRegion, position.x, position.y, 0.5f, 0.5f, 1, 1, 1, 1, -rotation);
        else
            batch.draw(textureRegion, position.x, position.y, 0, 0, 1, 1, 1, 1, -rotation);
    }

    protected void calculateRotation(Vector2 directionNormal) {
        float angle = MathUtils.atan2(directionNormal.x, directionNormal.y);
        rotation = MathUtils.radiansToDegrees * angle;
    }

    @Override
    public Vector2 getPosition() {
        return new Vector2(position.x, position.y);
    }

    @Override
    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

    @Override
    public void setPosition(Vector2 position) {
        this.position = position;
    }
}
