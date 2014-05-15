package com.me.justanothertowerdefense;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

enum RocketType {
    TEST_ROCKET,
}

public class RocketFactory {
    private static int idCounter = 0;

    public static Rocket makeRocket(RocketType rocketType, Vector2 position, Enemy target, TowerGuidanceSystem towerGuidanceSystem) {
        idCounter++;

        if(rocketType == RocketType.TEST_ROCKET)
            return makeTestRocket(position, target, towerGuidanceSystem);
        else
            return makeTestRocket(position, target, towerGuidanceSystem);
    }

    private static Rocket makeTestRocket(Vector2 position, Enemy target, TowerGuidanceSystem towerGuidanceSystem) {
        return new Rocket(new Texture(Gdx.files.internal("data/rocket.png")), String.valueOf(idCounter), position, target, towerGuidanceSystem);
    }
}
