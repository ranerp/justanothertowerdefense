package com.me.justanothertowerdefense;

import com.badlogic.gdx.math.Vector2;

public class TowerGuidanceSystem {

    World world;

    public TowerGuidanceSystem(World world) {
        this.world = world;
    }

    public Enemy acquireTarget(Tower tower) {
        for (Enemy enemy : world.getEnemies()) {
            if(tower.getPosition().epsilonEquals(enemy.getPosition(), tower.getTargetLockRadius()))
                return enemy;
        }
        return null;
    }

    public void contactAchieved(Rocket rocket, Enemy enemy) {
        if (enemy.hit(rocket.getDamage())) {
            world.removeEnemy(enemy);

            for (Tower tower : world.getTowers()) {
                if (enemy.equals(tower.getTarget()))
                    tower.setTargetLockOff();
            }
        }

        world.removeRocket(rocket);
    }

    public void fireRocket(RocketType rocketType, Vector2 position, Enemy target, TowerGuidanceSystem towerGuidanceSystem) {
        world.createRocket(rocketType, position, target, towerGuidanceSystem);
    }
}
