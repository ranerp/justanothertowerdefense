package com.me.justanothertowerdefense;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;

enum TileState {
    UNWALKABLE(0), WALKABLE(1), UNCONSTRUCTABLE(2), CONSTRUCTABLE(3);
    private int state;

    private TileState(int state) {
        this.state = state;
    }

    public int getState() {
        return state;
    }
}

public class World {

    private TiledMap map;

    private TiledMapTileLayer groundLayer;
    private TiledMapTileLayer movementPathLayer;
    private TiledMapTileLayer constructionLayer;
    private MapLayer wayPointsLayer;

    private Vector2 startPosition = new Vector2();
    private Vector2 endPosition = new Vector2();

    private Vector2 mapDimensions = new Vector2();
    private Vector2 viewPortDimensions = new Vector2();
    private float unitScale;

    private int[][] constructionArea;
    private int[][] movementPath;

    public World(TiledMap map) {
        this.map = map;
        setLayers();
        calculateWorldSizes();
        setStartAndEndPosition();
        constructionArea = createConstructionAreaArray();
        movementPath = createMovementPathArray();

    }

    public TiledMap getMap() {
        return map;
    }

    public float getMapIntegerProperty(String property) {
        return map.getProperties().get(property, Integer.class);
    }

    public Vector2 getMapDimension() {
        return mapDimensions;
    }

    public Vector2 getViewPortDimension() {
        return viewPortDimensions;
    }

    public float getUnitScale() {
        return unitScale;
    }

    private void setLayers() {
        groundLayer = (TiledMapTileLayer) map.getLayers().get("Ground");

        movementPathLayer = (TiledMapTileLayer) map.getLayers().get("MovementPath");
        movementPathLayer.setOpacity(0f);

        constructionLayer = (TiledMapTileLayer) map.getLayers().get("ConstructionArea");
        constructionLayer.setOpacity(0f);

        wayPointsLayer = map.getLayers().get("WayPoints");
        wayPointsLayer.setOpacity(0f);
    }

    private void calculateWorldSizes() {
        mapDimensions.x = getMapIntegerProperty("width");
        mapDimensions.y = getMapIntegerProperty("height");
        unitScale = getMapIntegerProperty("tilewidth");

        viewPortDimensions.x = Gdx.graphics.getWidth() / Settings.VIEWPORT_SCALE;
        viewPortDimensions.y = Gdx.graphics.getHeight() / Settings.VIEWPORT_SCALE;
    }

    private void setStartAndEndPosition() {
        MapProperties startProperties = wayPointsLayer.getObjects().get("StartWayPoint").getProperties();
        startPosition.set(startProperties.get("x", Float.class) / unitScale, startProperties.get("y", Float.class) / unitScale);

        MapProperties endProperties = wayPointsLayer.getObjects().get("EndWayPoint").getProperties();
        endPosition.set(endProperties.get("x", Float.class) / unitScale, endProperties.get("y", Float.class) / unitScale);
    }

    private int[][] createMovementPathArray() {
        int[][] pathArray = new int[movementPathLayer.getWidth()][movementPathLayer.getHeight()];
        for (int x = 0; x < movementPathLayer.getWidth(); x++) {
            for (int y = 0; y < movementPathLayer.getHeight(); y++) {
                if (movementPathLayer.getCell(x, y) != null &&
                        Boolean.parseBoolean(movementPathLayer.getCell(x, y).getTile().getProperties().get("Walkable", String.class))
                        )
                    pathArray[x][y] = TileState.WALKABLE.getState();
                else
                    pathArray[x][y] = TileState.UNWALKABLE.getState();

            }
        }

        return pathArray;
    }

    private int[][] createConstructionAreaArray() {
        int[][] constructionArray = new int[constructionLayer.getWidth()][constructionLayer.getHeight()];
        for (int x = 0; x < constructionLayer.getWidth(); x++) {
            for (int y = 0; y < constructionLayer.getHeight(); y++) {
                if (constructionLayer.getCell(x, y) != null &&
                        Boolean.parseBoolean(constructionLayer.getCell(x, y).getTile().getProperties().get("Constructable", String.class)))
                    constructionArray[x][y] = TileState.CONSTRUCTABLE.getState();
                else
                    constructionArray[x][y] = TileState.UNCONSTRUCTABLE.getState();
            }
        }

        return constructionArray;
    }
}