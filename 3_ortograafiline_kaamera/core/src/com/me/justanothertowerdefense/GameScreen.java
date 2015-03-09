package com.me.justanothertowerdefense;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

public class GameScreen implements Screen {

    private World world;

    private OrthographicCamera camera;

    public GameScreen() {
        world = new World(new TmxMapLoader().load("data/level1.tmx"));
        setCamera();
    }

    private void setCamera() {
        System.out.println("setcamera");
        camera = new OrthographicCamera();
        camera.setToOrtho(false, world.getViewPortDimension().x, world.getViewPortDimension().y);
        camera.zoom = Settings.CAMERA_ZOOM;
        camera.update();
    }

    @Override
    public void render(float deltaTime) {

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