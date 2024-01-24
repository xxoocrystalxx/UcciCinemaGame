package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;

import java.util.HashMap;
import java.util.Map;

public class Spettatore extends BaseActor{

        public enum State{
            SITTING,
            STANDING;
        };

    private static final int DEFAULT_COOLDOWN = 120;
    private State state = State.SITTING;
    private Texture textureSitting;

    private int cooldown = DEFAULT_COOLDOWN;


    public static final Map<Texture, Map<Direction, TextureRegion>> textures = new HashMap<>();


    public Spettatore(float x, float y, Stage s, String textureSitting, String textureStanding)
    {
        super(x,y,s);
        loadTexture(textureSitting);
        loadSpetactorTextures(textureSitting,textureStanding);
        super.setMovementDirection(Direction.UP);

    }


    @Override
    public void setMovementDirection(Direction movementDirection) {
        super.setMovementDirection(movementDirection);
        setTexture(textures.get(textureSitting).get(getMovementDirection()));

    }

    public State getState() {
        return state;
    }

    private void loadSpetactorTextures(String sittingTexture, String standingTextures){
        Array<TextureRegion>[] standing = loadAnimationFromSheet(standingTextures, 4, 3, 0.5f);
        Map<Direction,TextureRegion> mapSpettatore = new HashMap<>();
        Direction[] directions = Direction.values();
        for (int i = 0; i < directions.length; i++ ){
            mapSpettatore.put(directions[i],standing[i].get(1));
        }
        this.textureSitting =  getTexture(sittingTexture);
        textures.put(textureSitting, mapSpettatore);
    }

    public void standUp(){
        state = State.STANDING;
        setTexture(textures.get(textureSitting).get(getMovementDirection()));
        setBoundaryPolygon(8);

    }

    @Override
    public void act(float dt) {
        super.act(dt);
        cooldown--;
        if(cooldown <= 0 && state == State.STANDING){
            setMovementDirection(Direction.getRandom());
            lanciaArma();
            cooldown = DEFAULT_COOLDOWN;
        }
    }

    public void lanciaArma(){
        float x = this.getX();
        float y = this.getY();

        Tridente tridente = new Tridente(x+25,y+25, getStage());
        tridente.setMovementDirection(this.getMovementDirection());
        tridente.setBaseSpeed(70);
        tridente.setAcceleration(0.1f);
    }
}
