package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

public abstract class GameBeta extends ApplicationAdapter
{
    protected Stage mainStage;

    /*protected float baseHRatio;

    public void resize(int width, int height) {
        Gdx.app.log("#INFO", "resize().");
        Gdx.app.log("#INFO, width: ", String.valueOf(width));
        Gdx.app.log("#INFO, height: ", String.valueOf(height));

        //Use true here to center the camera
        //that's what you probably want in case of a UI
        mainStage.getViewport().update(width, height, true);
    }*/

    @Override
    public void create()
    {
        mainStage = new Stage();

        initialize();
    }

    /*@Override
    public void create() {
        //---------------------------------------------------------------------------
        Gdx.app.log("#INFO", "create().");
        //camera = new PerspectiveCamera();
        //viewport = new FitViewport(800, 480, camera);

        //float baseAspectRatio = 854/(float)480; //Dati base da cui partire (del mio cell.).
        baseHRatio = 480 / (float) Gdx.graphics.getHeight();

        //Gdx.app.log("#INFO, baseAspectRatio: ",String.valueOf(baseAspectRatio));
        Gdx.app.log("#INFO, baseHRatio: ", String.valueOf(baseHRatio));

        ////https://math.stackexchange.com/questions/180804/how-to-get-the-aspect-ratio-of-an-image
        Gdx.app.log("#INFO, Gdx.graphics.getWidth(): ", String.valueOf(Gdx.graphics.getWidth()));
        Gdx.app.log("#INFO, Gdx.graphics.getHeight(): ", String.valueOf(Gdx.graphics.getHeight()));

        float aspectratio = Gdx.graphics.getWidth() / (float) Gdx.graphics.getHeight();

        Gdx.app.log("#INFO, aspectratio: ", String.valueOf(aspectratio));

        //float newH = Gdx.graphics.getHeight()*0.5f;
        float newH = Gdx.graphics.getHeight() * baseHRatio; //480.
        float newW = newH * aspectratio;

        Gdx.app.log("#INFO, newW: ", String.valueOf(newW));
        Gdx.app.log("#INFO, newH: ", String.valueOf(newH));

        mainStage = new Stage(new FitViewport(newW, newH));
        //uiStage = new Stage(new FitViewport(newW,newH));
        //---------------------------------------------------------------------------

        initialize();
    }*/

    public abstract void initialize();
    public abstract void update(float dt);

    @Override
    public void render()
    {
        float dt = Gdx.graphics.getDeltaTime();

        processStage(dt);
        //Defined by user.
        update(dt);

        //Clear the screen.
        Gdx.gl.glClearColor(0.95f,0.56f,0.05f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Draw the graphics.
        mainStage.draw();
    }

    public void processStage(float dt){
        mainStage.act(dt);
    }

    @Override
    public void dispose () {
    }
}