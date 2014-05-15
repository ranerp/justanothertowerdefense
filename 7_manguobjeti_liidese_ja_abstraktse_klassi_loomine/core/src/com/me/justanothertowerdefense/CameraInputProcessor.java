package com.me.justanothertowerdefense;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;

public class CameraInputProcessor implements InputProcessor {

    private final Vector3 currentLocation = new Vector3();
    private final Vector3 lastLocation = new Vector3(-1, -1, -1);
    private final Vector3 delta = new Vector3();

    private OrthographicCamera camera;

    private float mapWidth;
    private float mapHeight;


    public CameraInputProcessor(OrthographicCamera camera, float mapWidth, float mapHeight) {
        this.camera = camera;
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
    }

    @Override
    public boolean touchDragged (int x, int y, int pointer) {
        camera.unproject(currentLocation.set(x, y, 0));
        if (!(lastLocation.x == -1 && lastLocation.y == -1 && lastLocation.z == -1)) {
            camera.unproject(delta.set(lastLocation.x, lastLocation.y, 0));
            delta.sub(currentLocation);
            camera.position.add(delta.x, delta.y, 0);

            resolveWorldBounds();
        }
        lastLocation.set(x, y, 0);

        return true;
    }

    @Override
    public boolean touchUp (int x, int y, int pointer, int button) {
        lastLocation.set(-1, -1, -1);

        return true;
    }

    private void resolveWorldBounds() {
        float positionX = camera.viewportWidth * camera.zoom / 2;
        float positionY = camera.viewportHeight * camera.zoom / 2;

        if(camera.position.x  - positionX < 0)
            camera.position.x = positionX;
        else if(camera.position.x  + positionX > mapWidth)
            camera.position.x = mapWidth - positionX;

        if(camera.position.y  - positionY < 0)
            camera.position.y = positionY;
        else if(camera.position.y  + positionY > mapHeight)
            camera.position.y = mapHeight - positionY;

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
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        resolveWorldBounds();
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
