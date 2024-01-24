package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Sedie extends BaseActor{

    private static final String SEDIE_TEXTURE = "sedie.png";


    public Sedie(float x, float y, Stage s)
    {
        super(x,y,s);

        loadTexture(SEDIE_TEXTURE);

        setBoundaryRectangle();
    }


}
