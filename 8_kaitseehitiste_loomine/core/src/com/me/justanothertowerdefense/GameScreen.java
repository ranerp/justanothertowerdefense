package com.me.justanothertowerdefense;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

public class GameScreen implements Screen {

    private UserInterface ui;

    private World world;
    private WorldRenderer worldRenderer;

    private OrthographicCamera camera;

    private InputMultiplexer multiplexer;

    public GameScreen() {
        multiplexer = new InputMultiplexer();
        camera = new OrthographicCamera();
        world = new World(new TmxMapLoader().load("data/level1.tmx"));
        ui = new UserInterface(camera, world);
        setCamera();
        setInputProcessors();
        worldRenderer = new WorldRenderer(world, camera);
    }

    private void setCamera() {
        camera.setToOrtho(false, world.getViewPortDimension().x, world.getViewPortDimension().y);
        camera.zoom = Settings.CAMERA_ZOOM;
        camera.update();
    }

    private void setInputProcessors() {
        multiplexer.addProcessor(ui.getUI());
        multiplexer.addProcessor(ui);
        multiplexer.addProcessor(new CameraInputProcessor(camera, world.getMapDimension().x, world.getMapDimension().y));
        Gdx.input.setInputProcessor(multiplexer);
    }

    @Override
    public void render(float deltaTime) {
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        worldRenderer.render(deltaTime);
        ui.render(deltaTime);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }

}