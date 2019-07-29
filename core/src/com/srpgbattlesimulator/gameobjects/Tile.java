package com.srpgbattlesimulator.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.srpgbattlesimulator.TerrainType;
import com.srpgbattlesimulator.rendering.Shape;
import com.srpgbattlesimulator.rendering.ShapeName;
import com.srpgbattlesimulator.utilities.Timer;

/**
 * Created by Carl on 06/05/2019.
 */
public class Tile extends GameObject
{
    private int column, row;
    public TerrainType terrainType;
    private float terrainCost, accumulatedTerrainCost;
    public Shape overlay;
    public float hue, colorShiftPerFrame;
    public Timer timer;

    public Tile(Vector2 position, float width, float height, int column, int row, Shape shape)
    {
        super(position, width, height, shape);
        this.column = column;
        this.row = row;
        this.terrainCost = 1;
        this.accumulatedTerrainCost = 1;
        this.overlay = new Shape(position.cpy(), width, height, ShapeName.RECT, new Color(0f, 0f, 0f, .7f), Color.CLEAR, 0);
        this.hue = 0;
        this.timer = new Timer(.6f);
        this.colorShiftPerFrame = 1f / timer.getTargetTime();

        double r = Math.random();

        if(r <= .4)
        {
            terrainType = TerrainType.GRASS;
            shape.fillColor = Color.GREEN;
            //terrainCost = 1f;
            terrainCost = 1;
        }
        else if(r <= .7)
        {
            terrainType = TerrainType.FOREST;
            shape.fillColor = Color.FOREST;
            //terrainCost = 1.5f;
            terrainCost = 1.5f;
        }
        else if(r <= .9)
        {
            terrainType = TerrainType.MOUNTAIN;
            shape.fillColor = Color.BROWN;
            //terrainCost = 3f;
            terrainCost = 2.5f;
        }
        else
        {
            terrainType = TerrainType.WATER;
            shape.fillColor = Color.BLUE;
        }

        shape.defaultFillColor = shape.fillColor;

//        terrainType = TerrainType.GRASS;
//        shape.fillColor = Color.GREEN;
//        terrainCost = 2.5f;
        accumulatedTerrainCost = terrainCost;
    }

    @Override
    public void update()
    {
        overlay.fillColor.set(hue, hue, hue, .7f);

        hue += colorShiftPerFrame * Gdx.graphics.getDeltaTime();

        timer.update();

        if(timer.hasReachedTargetTime())
        {
            timer.reset();
            colorShiftPerFrame = -colorShiftPerFrame;
        }
    }

    public int getColumn()
    {
        return column;
    }

    public int getRow()
    {
        return row;
    }

    public float getTerrainCost()
    {
        return terrainCost;
    }

    public float getAccumulatedTerrainCost()
    {
        return accumulatedTerrainCost;
    }

    public void setAccumulatedTerrainCost(float terrainCost)
    {
        this.accumulatedTerrainCost = terrainCost;
    }
}
