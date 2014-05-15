package com.me.justanothertowerdefense;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

enum EnemyType {
    TEST_ENEMY,
}

public class EnemyFactory {

    private static int idCounter = 0;

    public static Enemy makeEnemy(AStarPathFinder pathFinder, EnemyType enemyType, Vector2 startPosition, Vector2 endPosition) {
        idCounter++;

        if(enemyType == EnemyType.TEST_ENEMY)
            return makeTestEnemy(pathFinder, startPosition, endPosition);
        else
            return makeTestEnemy(pathFinder, startPosition, endPosition);
    }

    private static Enemy makeTestEnemy(AStarPathFinder pathFinder, Vector2 startPosition, Vector2 endPosition) {
        return new Enemy(new Texture(Gdx.files.internal("data/enemy.png")), pathFinder, String.valueOf(idCounter), startPosition, endPosition);
    }
}
