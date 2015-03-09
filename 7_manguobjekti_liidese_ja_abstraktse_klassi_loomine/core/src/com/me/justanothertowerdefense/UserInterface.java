package com.me.justanothertowerdefense;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class UserInterface implements InputProcessor {

    private Stage ui = new Stage();
    private Table rightPanel = new Table();
    private Table leftPanel = new Table();
    private Skin skin = new Skin(Gdx.files.internal("data/uiskin.json"));

    private int uiWidth = Gdx.graphics.getWidth() / Settings.UI_WIDTH_SCALE;

    OrthographicCamera camera;

    World world;

    public UserInterface(OrthographicCamera camera, World world) {
        this.camera = camera;
        this.world = world;

        setLeftPanel();
        setRightPanel();
    }

    private void setRightPanel() {
        rightPanel.setSize(uiWidth, Gdx.graphics.getHeight());
        rightPanel.setPosition(Gdx.graphics.getWidth() - uiWidth , 0);
        rightPanel.setSkin(skin);
        rightPanel.top();
        rightPanel.setClip(true);

        ui.addActor(rightPanel);
    }

    private void setLeftPanel() {
        leftPanel.setSize(uiWidth, Gdx.graphics.getHeight());
        leftPanel.setPosition(0, 0);
        leftPanel.top();
        leftPanel.setClip(true);

        ui.addActor(leftPanel);

        Label zoomLabel = new Label("Zoom", skin);
        leftPanel.add(zoomLabel);
        leftPanel.row();

        Image zoomPlusButton = new Image(new Texture(Gdx.files.internal("data/zoom_plus_icon.png")));
        zoomPlusButton.addListener(new InputListener() {
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                if(camera.zoom > 0.5f)
                    camera.zoom -= 0.1f;
                return false;
            }
        });
        leftPanel.add(zoomPlusButton);
        leftPanel.row();

        Image zoomMinusButton = new Image(new Texture(Gdx.files.internal("data/zoom_minus_icon.png")));
        zoomMinusButton.addListener(new InputListener() {
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                if(camera.zoom < 1f)
                    camera.zoom += 0.1f;
                return false;
            }
        });
        leftPanel.add(zoomMinusButton);
        leftPanel.row();
    }

    public Stage getUI() {
        return ui;
    }

    public void render(float deltaTime) {
        ui.act(deltaTime);
        ui.draw();

        if (Settings.DEBUG) {
            leftPanel.debug();
            rightPanel.debug();
        }
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) { return false; }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) { return false; }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }


}
