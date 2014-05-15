package com.me.justanothertowerdefense;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class WorldRenderer {

    private World world;
    private OrthographicCamera camera;
    private OrthogonalTiledMapRenderer renderer;

    private SpriteBatch batch;
    private BitmapFont font;

    public WorldRenderer(World world, OrthographicCamera camera) {
        this.world = world;
        this.camera = camera;
        this.batch = new SpriteBatch();
        this.font = new BitmapFont();

        renderer = new OrthogonalTiledMapRenderer(world.getMap(), 1 / world.getUnitScale());
    }

    public void render(float deltaTime) {
        camera.update();
        renderer.setView(camera);
        renderer.render();
        Batch tiledMapBatch = renderer.getSpriteBatch();

        tiledMapBatch.begin();
        for (Enemy enemy : world.getEnemies()) {
            enemy.update(deltaTime);
            enemy.draw(tiledMapBatch);
        }
        for (Tower tower : world.getTowers()) {
            tower.update(deltaTime);
            tower.draw(tiledMapBatch);
        }
        tiledMapBatch.end();

        batch.begin();
        font.draw(batch, "FPS: " + Gdx.graphics.getFramesPerSecond(), 10, 20);
        batch.end();
    }
}
