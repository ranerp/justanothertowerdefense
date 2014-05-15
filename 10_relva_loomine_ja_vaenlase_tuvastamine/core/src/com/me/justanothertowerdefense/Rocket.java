package com.me.justanothertowerdefense;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Rocket extends DynamicGameObject {

    private float speed = 3f;
    private float damage = 2f;

    private TowerGuidanceSystem towerGuidanceSystem;
    private Enemy target;

    public Rocket(Texture texture, String ID, Vector2 position, Enemy target, TowerGuidanceSystem towerGuidanceSystem) {
        super(texture, ID, position);
        this.towerGuidanceSystem = towerGuidanceSystem;
        this.target = target;
    }

    @Override
    public void update(float deltaTime) {
        Vector2 normal = target.getPosition().sub(position).nor();
        velocity.set(normal.x * speed, normal.y * speed);
        position.add(velocity.x * deltaTime, velocity.y * deltaTime);

        if (target.getPosition().epsilonEquals(position, 0.5f)) {
            towerGuidanceSystem.contactAchieved(this, target);
        }

        calculateRotation(normal);
    }

    public float getDamage() {
        return damage;
    }
}