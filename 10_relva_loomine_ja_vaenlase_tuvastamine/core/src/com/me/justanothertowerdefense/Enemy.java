package com.me.justanothertowerdefense;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.util.Stack;

public class Enemy extends DynamicGameObject {

    private float speed = 2f;
    private float health = 10f;

    private AStarPathFinder pathFinder;


    private Vector2 endPosition = new Vector2();
    private Vector2 wayPoint = new Vector2();
    private Stack<Vector2> wayPoints = new Stack<Vector2>();

    public Enemy(Texture texture, AStarPathFinder pathFinder, String ID, Vector2 startPosition, Vector2 endPosition) {
        super(texture, ID);
        this.pathFinder = pathFinder;
        this.position.set(startPosition);
        this.endPosition.set(endPosition);

        getMovementPath();
    }

    public void getMovementPath() {
        if(!wayPoints.empty())
            wayPoints.clear();

        velocity.set(0, 0);
        wayPoints.addAll(pathFinder.getPathList((int) position.x, (int) position.y, (int) endPosition.x, (int) endPosition.y));

        if(wayPoints.empty())
           wayPoints.add(endPosition);
    }

    @Override
    public void update(float deltaTime) {
        calculateVelocity();
        this.position.add(velocity.x * deltaTime, velocity.y * deltaTime);
    }

    private void calculateVelocity() {

        if(!wayPoints.empty() && (velocity.isZero() || position.epsilonEquals(wayPoint, 0.3f))) {
            wayPoint = wayPoints.pop();
            Vector2 normal = wayPoint.cpy().sub(position).nor();
            velocity.set(normal.x * speed, normal.y * speed);

            calculateRotation(normal);

        }
        else if(position.epsilonEquals(endPosition, 0.2f))
            velocity.set(0, 0);
    }

    public boolean hit(float healthLost) {
        health -= healthLost;
        if (health <= 0)
            return true;
        else
            return false;
    }
}
