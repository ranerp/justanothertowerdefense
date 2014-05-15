package com.me.justanothertowerdefense;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class JustAnotherTowerDefense extends Game {

    @Override
    public void create() {
        setScreen(new GameScreen());
    }

    @Override
    public void dispose() {
    }

    @Override
    public void render() {
        getScreen().render(Gdx.graphics.getDeltaTime());
    }

    @Override
    public void resize(int width, int height) {
        Settings.WIDTH = width;
        Settings.HEIGHT = height;
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }
}


