package com.me.justanothertowerdefense;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Tower extends DynamicGameObject {

    private Vector2 lookAt = new Vector2();

    private float targetLockRadius = 3f;
    private float fireRate = 2f;
    private float coolDown = 0;

    private Enemy target = null;
    private boolean targetLockOn = false;
    private TowerGuidanceSystem towerGuidanceSystem;

    public Tower(Texture texture, String ID, Vector2 position, TowerGuidanceSystem towerGuidanceSystem) {
        super(texture, ID, position);
        this.towerGuidanceSystem = towerGuidanceSystem;
    }

    @Override public void update(float deltaTime) {
        coolDown -= deltaTime;
        if (targetLockOn && coolDown <= 0) {
            towerGuidanceSystem.fireRocket(RocketType.TEST_ROCKET, new Vector2(position.x, position.y), target, towerGuidanceSystem);
            coolDown = fireRate;
        }

        if (!targetLockOn) {

            Enemy target = towerGuidanceSystem.acquireTarget(this);
            if (target != null)
                setTargetLockOn(target);

        } else {

            lookAt = target.getPosition().cpy().sub(position).nor();
            if (!position.epsilonEquals(target.getPosition(), targetLockRadius))
                setTargetLockOff();
            else
                calculateRotation(lookAt);
        }
    }

    public float getTargetLockRadius() {
        return targetLockRadius;
    }

    public Enemy getTarget() {
        return target;
    }

    public void setTargetLockOn(Enemy enemy) {
        targetLockOn = true;
        target = enemy;
    }

    public void setTargetLockOff() {
        targetLockOn = false;
        target = null;
    }

}
