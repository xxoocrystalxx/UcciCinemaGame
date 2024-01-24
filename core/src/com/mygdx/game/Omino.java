package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Omino extends BaseActor
{
    public enum State{
        MOVING,
        IDLE;
    }


    private static final Map<Direction, Animation<TextureRegion>> animations = new HashMap<>();
    static {
        Array<TextureRegion>[] ominoMovement = loadAnimationFromSheet("omino.png", 4, 3, 0.5f);

        for (Array <TextureRegion> arr : ominoMovement){
            arr.add(arr.get(1));
            arr.reverse();
        }

        Animation <TextureRegion> ominoMovementUp = new Animation<>(0.3f,ominoMovement[0]);
        ominoMovementUp.setPlayMode(Animation.PlayMode.LOOP);
        Animation <TextureRegion> ominoMovementDown = new Animation<>(0.3f,ominoMovement[2]);
        ominoMovementDown.setPlayMode(Animation.PlayMode.LOOP);
        Animation <TextureRegion> ominoMovementLeft = new Animation<>(0.3f,ominoMovement[3]);
        ominoMovementLeft.setPlayMode(Animation.PlayMode.LOOP);
        Animation <TextureRegion> ominoMovementRight = new Animation<>(0.3f,ominoMovement[1]);
        ominoMovementRight.setPlayMode(Animation.PlayMode.LOOP);

        animations.put(Direction.UP,ominoMovementUp);
        animations.put(Direction.DOWN,ominoMovementDown);
        animations.put(Direction.LEFT,ominoMovementLeft);
        animations.put(Direction.RIGHT,ominoMovementRight);
    }



    private State state;

    private final int speed=5;

    private boolean alive = true;

    private int damageCooldown;

    private int asciaCooldown;

    private List<Vite> cuori;



    public Omino(float x, float y, Stage s)
    {
        super(x,y,s);

        setAnimationAndSize(animations.get(getMovementDirection()));
        setBoundaryPolygon(8);
        stopMoving();
        //End 2.0.

    }

    public boolean isAlive() {
        return alive;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }


    public void startMoving(Direction direction){
        this.state = State.MOVING;
        setAnimationPaused(false);
        setMovementDirection(direction);
    }

    public void stopMoving(){
        this.state = State.IDLE;
        setAnimationPaused(true);
        resetAnimations();
    }



    public void act(float dt)
    {
        super.act(dt);
        Direction currentDirection = getMovementDirection();
        setAnimation(animations.get(currentDirection));

        if (state == State.MOVING){
            Vector2 movement = currentDirection.getData2d().cpy().scl(speed);
            this.moveBy(movement.x, movement.y);

        }

        if(damageCooldown > 0)
            damageCooldown--;
        if(asciaCooldown > 0)
            asciaCooldown--;

        boundToWorld();
    }

    public void lanciaArma(){
        float x = this.getX();
        float y = this.getY();


        if(asciaCooldown ==0) {
        Ascia ascia = new Ascia(x+25,y+25, getStage());
        ascia.setMovementDirection(this.getMovementDirection());
        ascia.setBaseSpeed(120);
        ascia.setAcceleration(0.1f);
        asciaCooldown=30;
        }
    }

    public boolean colpitoDa(BaseActor other){

        if(damageCooldown == 0 && other.overlaps(this)){
            damageCooldown = 180;
            Vite cuore = cuori.remove(cuori.size()-1);
            cuore.kill();
            if(cuori.isEmpty())
                kill();
            return true;
        }

        return false;

    }

    public void setCuori(List<Vite> cuori){
        this.cuori = cuori;
    }

    @Override
    public void kill() {
        super.kill();
        alive = false;
    }
}

