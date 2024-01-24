package com.mygdx.game;

import com.badlogic.gdx.scenes.scene2d.Stage;

public class Vite extends BaseActor{

    private static final String Cuore = "cuore.png";


    public Vite(float x, float y, Stage s)
    {
        super(x,y,s);

        loadTexture(Cuore);
    }

}


