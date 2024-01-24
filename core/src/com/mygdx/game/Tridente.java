package com.mygdx.game;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Tridente extends Proiettile{

    private static final int TIMEOUT = 90;


    public Tridente(float x, float y, Stage s)
    {
        super(x, y, s,"tridente_singolo.png" );

        setBoundaryRectangle();

        setTimeout(TIMEOUT);

    }

    @Override
    public void setMovementDirection(Direction movementDirection) {
        super.setMovementDirection(movementDirection);

        switch (getMovementDirection()) {
            case DOWN:
                setRotation(180);
                break;
            case UP:
                setRotation(0);
                break;
            case LEFT:
                setRotation(90);
                break;
            case RIGHT:
                setRotation(270);
                break;
        }
    }
}
