package com.me.justanothertowerdefense;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

enum TowerType {
    TEST_TOWER
}

public class TowerFactory {

    private static int idCounter = 0;

    public static Tower makeTower(TowerType towerType, Vector2 position) {
        idCounter++;

        if(towerType == TowerType.TEST_TOWER)
            return makeTestTower(position);
        else
            return makeTestTower(position);
    }

    private static Tower makeTestTower(Vector2 position) {
        return new Tower(new Texture(Gdx.files.internal("data/tower.png")), String.valueOf(idCounter), position);
    }
}
