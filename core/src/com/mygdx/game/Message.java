package com.mygdx.game;

import com.badlogic.gdx.scenes.scene2d.Stage;

public class Message extends BaseActor{


    public Message(float x, float y, Stage s, String immagine)
    {
        super(x,y,s);


        loadTexture(immagine);


    }


}

