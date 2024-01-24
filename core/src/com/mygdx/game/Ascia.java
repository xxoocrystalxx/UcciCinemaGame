package com.mygdx.game;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Ascia extends Proiettile {


    private static final int TIMEOUT = 48;
    private Direction direction;

    public Ascia(float x, float y, Stage s)
        {
            super(x, y, s,"ascia_singola.png" );
            setBoundaryPolygon(8);
            setTimeout(TIMEOUT);
            Action spin = Actions.rotateBy(-180, 0.5f);
            this.addAction( Actions.forever(spin) );

        }




}

