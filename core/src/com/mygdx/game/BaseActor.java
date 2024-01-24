package com.mygdx.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Array;


import java.util.ArrayList;


public class BaseActor extends Actor {

    private Stage stage;
    //Begin 1.1.
    private Animation<TextureRegion> animation;
    private float elapsedTime;
    private boolean animationPaused;

    private static Rectangle worldBounds;

    private int frameCount;

    private Vector2 velocityVec;

    private Vector2 accelerationVec;
    private float acceleration;

    private Polygon boundaryPolygon;

    private float maxSpeed;
    private float baseSpeed;
    private float deceleration;

    private Direction movementDirection = Direction.DOWN;


    public BaseActor(float x, float y, Stage s)
    {
        //Begin 1.0.
        //Call constructor from Actor class.
        super();

        Gdx.app.setLogLevel(Application.LOG_DEBUG);

        //Perform additional initialization tasks.
        setPosition(x,y);
        s.addActor(this);
        //End 1.0.

        this.stage = s;



        //Begin 1.1.
        animation = null;
        elapsedTime = 0;
        animationPaused = false;
        //End 1.1.


        //Begin 2.6.
        velocityVec = new Vector2(0,0);
        //End 2.6.



        //Begin 2.8.
        accelerationVec = new Vector2(0,0);
        acceleration = 0;
        //End 2.8.



        //Begin 3.1.
        maxSpeed = 1000;
        deceleration = 0;
        //End 3.1.
    }

    public void setAnimationAndSize(Animation<TextureRegion> anim)
    {
        setAnimation(anim);
        TextureRegion tr = animation.getKeyFrame(0);
        float w = tr.getRegionWidth();
        float h = tr.getRegionHeight();
        setSize( w, h );
        setOrigin( w/2, h/2 );
    }

    public void setAnimation(Animation<TextureRegion> anim){
        animation = anim;
    }


    //Begin 1.3.
    public void setAnimationPaused(boolean pause)
    {
        animationPaused = pause;
    }
    //End 1.3.

    //Begin 1.4.
    @Override
    public void act(float dt)
    {
        super.act( dt );

        frameCount++;

        if (!animationPaused)
            elapsedTime += dt;
    }
    //end 1.4.

    //Begin 1.5.
    @Override
    public void draw(Batch batch, float parentAlpha)
    {
        super.draw( batch, parentAlpha );

        //Apply color tint effect.
        Color c = getColor();
        batch.setColor(c.r, c.g, c.b, c.a);

        if ( animation != null && isVisible() )
            batch.draw( animation.getKeyFrame(elapsedTime),
                    getX(), getY(), getOriginX(), getOriginY(),
                    getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation() );
    }

    public void resetAnimations(){
        elapsedTime = 0;
    }
    //End 1.5.

    //Begin 1.6.
    public Animation<TextureRegion> loadAnimationFromFiles(String[] fileNames,
                                                           float frameDuration,
                                                           boolean loop)
    {
        int fileCount = fileNames.length;
        Array<TextureRegion> textureArray = new Array<TextureRegion>();

        for (int n = 0; n < fileCount; n++)
        {
            String fileName = fileNames[n];
            Texture texture = new Texture( Gdx.files.internal(fileName) );
            texture.setFilter( TextureFilter.Linear, TextureFilter.Linear );
            textureArray.add( new TextureRegion( texture ) );
        }

        Animation<TextureRegion> anim = new Animation<TextureRegion>(frameDuration, textureArray);

        if (loop)
            anim.setPlayMode(PlayMode.LOOP);
        else
            anim.setPlayMode(PlayMode.NORMAL);

        if (animation == null)
            setAnimationAndSize(anim);

        return anim;
    }
    //End 1.6.



    //Begin 1.7.
    public Animation<TextureRegion> loadAnimationFromSheet(String fileName,
                                                           int rows, int cols,
                                                           float frameDuration, boolean loop)
    {
        Texture texture = new Texture(Gdx.files.internal(fileName), true);
        texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        int frameWidth = texture.getWidth() / cols;
        int frameHeight = texture.getHeight() / rows;

        TextureRegion[][] temp = TextureRegion.split(texture,
                frameWidth,
                frameHeight);

        Array<TextureRegion> textureArray = new Array<TextureRegion>();

        for (int r = 0; r < rows; r++)
            for (int c = 0; c < cols; c++)
                textureArray.add( temp[r][c] );

        Animation<TextureRegion> anim = new Animation<TextureRegion>(frameDuration, textureArray);

        if (loop)
            anim.setPlayMode(PlayMode.LOOP);
        else
            anim.setPlayMode(PlayMode.NORMAL);

        if (animation == null)
            setAnimationAndSize(anim);

        return anim;
    }
    //End 1.7.



    //Begin 1.8.
    public Animation<TextureRegion> loadTexture(String fileName)
    {
        String[] fileNames = new String[1];
        fileNames[0] = fileName;
        return loadAnimationFromFiles(fileNames, 1, true);
    }
    //End 1.8.

    public void setTexture(Texture texture){
       setTexture(new TextureRegion(texture));
    }

    public void setTexture(TextureRegion texture) {
        TextureRegion[] textureRegions = new TextureRegion[1];
        textureRegions[0] = texture;
        setAnimationAndSize(new Animation<>(1.0F, textureRegions));
    }


    //Begin 1.9.
    public boolean isAnimationFinished()
    {
        return animation.isAnimationFinished(elapsedTime);
    }
    //End 1.9.

    public static Array <TextureRegion>[] loadAnimationFromSheet(String fileName,int rows, int cols, float frameDuration){

        Texture texture = new Texture(Gdx.files.internal(fileName), true);
        texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        int frameWidth = texture.getWidth() / cols;
        int frameHeight = texture.getHeight() / rows;

        TextureRegion[][] temp = TextureRegion.split(texture,
                frameWidth,
                frameHeight);


        Array <TextureRegion>[] anims = new Array[rows];

        for (int r = 0; r < rows; r++) {
            Array<TextureRegion> textureArray = new Array<TextureRegion>();
            for (int c = 0; c < cols; c++) {
                textureArray.add(temp[r][c]);
            }
            anims[r] = textureArray;
        }

        return anims;
    }

    public void setBaseSpeed(float baseSpeed) {
        this.baseSpeed = baseSpeed;
    }

    public void setSpeed(float speed)
    {
        //If length is zero, then assume motion angle is zero degrees.
        if (velocityVec.len() == 0)
            velocityVec.set(speed, 0);
        else
            velocityVec.setLength(speed);
    }

    public Direction getMovementDirection() {
        return movementDirection;
    }

    public void setMovementDirection(Direction movementDirection) {
        this.movementDirection = movementDirection;
    }

    public int getFrameCount() {
        return frameCount;
    }

    public float getSpeed()
    {
        return velocityVec.len();
    }

    public void setMotionAngle(float angle)
    {
        velocityVec.setAngleDeg(angle);
    }

    public float getMotionAngle()
    {
        return velocityVec.angleDeg();
    }

    public boolean isMoving()
    {
        return (getSpeed() > 0);
    }
//End 2.7.

    //Begin 2.9.
    public void setAcceleration(float acc)
    {
        acceleration = acc;
    }

    public void accelerateAtAngle(float angle)
    {
        accelerationVec.add( new Vector2(acceleration, 0).setAngleDeg(angle) );
    }
//End 2.9.


    @Override
    public Stage getStage() {
        return stage;
    }

    //Begin 3.0.
    public void accelerateForward()
    {
        accelerateAtAngle( getRotation() );
    }
//End 3.0.



    //Begin 3.2.
    public void setMaxSpeed(float ms)
    {
        maxSpeed = ms;
    }

    public void setDeceleration(float dec)
    {
        deceleration = dec;
    }
//End 3.2.



    //Begin 3.3.
    public void applyPhysics(float dt)
    {
        //Apply acceleration.
        velocityVec.add( accelerationVec.x * dt, accelerationVec.y * dt );

        float speed = getSpeed();

        //Decrease speed (decelerate) when not accelerating.
        if (accelerationVec.len() == 0)
            speed -= deceleration * dt;

        //Keep speed within set bounds.
        speed = MathUtils.clamp(speed, 0, maxSpeed);

        //Update velocity.
        setSpeed(speed);

        //Apply velocity.
        moveBy( (velocityVec.x * dt) + baseSpeed * velocityVec.x, (velocityVec.y * dt) + baseSpeed * velocityVec.y );

        //Reset acceleration.
        accelerationVec.set(0,0);
    }

    public static Texture getTexture(String fileName){
        Texture texture = new Texture(Gdx.files.internal(fileName));
        texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        return texture;
    }

    @SuppressWarnings("unchecked")
    public static <T extends BaseActor> ArrayList<T> getList(Stage stage, Class<T> clazz) {
        ArrayList<T> list = new ArrayList<>();

        for (Actor actor : stage.getActors()) {
            if (clazz.isInstance(actor)) {
                list.add((T)actor);
            }
        }
        return list;
    }

    public static int count(Stage stage, Class<? extends BaseActor> clazz) {
        return getList(stage, clazz).size();
    }

    public void setBoundaryRectangle()
    {
        float w = getWidth();
        float h = getHeight();
        float[] vertices = {0,0, w,0, w,h, 0,h};
        boundaryPolygon = new Polygon(vertices);
    }
//End 1.1.

    public void setBoundaryPolygon(int numSides)
    {
        float w = getWidth();
        float h = getHeight();
        float angleDiv = 6.28f / numSides;
        float[] vertices = new float[2*numSides];
        for (int i = 0; i < numSides; i++)
        {
            float angle = i * angleDiv;

            //x-coordinate.
            vertices[2*i] = w/2 * MathUtils.cos(angle) + w/2; //1
            //y-coordinate.
            vertices[2*i+1] = h/2 * MathUtils.sin(angle) + h/2; //0
        }
        boundaryPolygon = new Polygon(vertices);
    }

    public Polygon getBoundaryPolygon()
    {
        boundaryPolygon.setPosition( getX(), getY() );
        boundaryPolygon.setOrigin( getOriginX(), getOriginY() );
        boundaryPolygon.setRotation ( getRotation() );
        boundaryPolygon.setScale( getScaleX(), getScaleY() );

        return boundaryPolygon;
    }
    //End 1.3.

    //Begin 1.5.
    public boolean overlaps(BaseActor other)
    {

        Polygon poly1 = this.getBoundaryPolygon();
        Polygon poly2 = other.getBoundaryPolygon();

        //Initial test to improve performance.
        if(!poly1.getBoundingRectangle().overlaps(poly2.getBoundingRectangle()) ) {
            return false;
        }

        return Intersector.overlapConvexPolygons( poly1, poly2 );
    }
    //End 1.5.
    //Begin 1.6.
    public void centerAtPosition(float x, float y)
    {
        setPosition( x - getWidth()/2, y - getHeight()/2 );
    }

    public void centerAtActor(BaseActor other)
    {
        centerAtPosition( other.getX() + other.getWidth()/2 ,
                other.getY() + other.getHeight()/2 );
    }

    public void setOpacity(float opacity) {
        this.getColor().a = opacity;
    }
    //End 1.6.

    //Begin 1.1.
    public Vector2 preventOverlap(BaseActor other)
    {
        Polygon poly1 = this.getBoundaryPolygon();
        Polygon poly2 = other.getBoundaryPolygon();

        //Initial test to improve performance
        if (!poly1.getBoundingRectangle().overlaps(poly2.getBoundingRectangle()))
            return null;

        Intersector.MinimumTranslationVector mtv = new Intersector.MinimumTranslationVector();
        boolean polygonOverlap = Intersector.overlapConvexPolygons(poly1, poly2, mtv);

        if (!polygonOverlap)
            return null;

        this.moveBy(mtv.normal.x * mtv.depth, mtv.normal.y * mtv.depth);

        return mtv.normal;
    }

    public void kill() {
        clearActions();
        addAction(Actions.removeActor());
    }

    public static void setWorldBounds(float x, float y, float width, float height)
    {
        worldBounds = new Rectangle( x,y, width, height );
    }

    public static void setWorldBounds(BaseActor ba)
    {
        setWorldBounds( ba.getX(), ba.getY(), ba.getWidth(), ba.getHeight() );
    }
//End 2.1.
//End 1.2.

    //Begin 2.2.
    public void boundToWorld()
    {
        //Check left edge.
        if( getX() < worldBounds.x +100)
            setX(worldBounds.x+100);
        //Check right edge.
        if( getX() + getWidth() > (worldBounds.x + worldBounds.width) )
            setX( (worldBounds.x + worldBounds.width) - getWidth() );
        //Check bottom edge.
        if( getY() < worldBounds.y +90)
            setY(worldBounds.y +90);
        //Check top edge.
        if( getY() + getHeight() > worldBounds.height )
            setY( worldBounds.height - getHeight() );
    }


}



//End 1.1.
