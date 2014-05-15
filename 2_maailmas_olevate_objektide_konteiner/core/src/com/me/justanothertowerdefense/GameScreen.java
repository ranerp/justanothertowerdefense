package com.me.justanothertowerdefense;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

public class GameScreen implements Screen {

    private World world;

    public GameScreen() {
        world = new World(new TmxMapLoader().load("data/level1.tmx"));
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