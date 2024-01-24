package com.mygdx.game;

import com.badlogic.gdx.scenes.scene2d.Stage;

public class Cinema extends BaseActor{

    private static final String CINEMA_TEXTURE = "cinema.jpg";

    public Cinema(float x, float y, Stage s)
    {
        super(x,y,s);

      loadTexture(CINEMA_TEXTURE);
    }
}


