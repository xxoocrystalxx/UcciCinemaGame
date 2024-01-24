package com.mygdx.game;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Proiettile extends BaseActor{

    private int timeout;
    public Proiettile(float x, float y, Stage s, String texture) {
        super(x, y, s);

        loadTexture(texture);
        s.addActor(this);
    }

    public int getTimeout() {
        return timeout;
    }

    protected void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public void act(float dt) {
        super.act(dt);

        if(getFrameCount()>timeout){
            kill();
        }

        switch (getMovementDirection()) {
            case DOWN:
                accelerateAtAngle(270);
                break;
            case UP:
                accelerateAtAngle(90);
                break;
            case LEFT:
                accelerateAtAngle(180);
                break;
            case RIGHT:
                accelerateAtAngle(0);
                break;
        }

        applyPhysics(dt);

        setAnimationPaused(!isMoving());
    }

}
