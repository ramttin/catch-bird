package com.bird.game.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
//import Screens
import com.badlogic.gdx.utils.Array;
import com.bird.game.Bird;
import com.bird.game.Screens.PlayScreen;

/**
 * Created by Ahoura on 1/27/2018.
 */

public class birdbird extends Sprite {
    public enum State{FLAPPING,FLIPPING,GLIDING,DEAD};
    public State currentState;
    public State previousState;
    public World world;
    public Body b2body;
    private TextureRegion birdglide;
//    private Animation ;
    private Animation birdFlip;
    private Animation<TextureRegion> birdGlide;
    private Animation birdDies;
    private Animation<TextureRegion> birdFlap;

    private float stateTimer;
    private boolean goingRight;
//    public float VX;
    public birdbird(World world, PlayScreen screen){
       super(screen.getAtlas().findRegion("flap"));
        this.world = world;
        currentState = State.FLAPPING;
        previousState = State.FLAPPING;
        stateTimer = 0;
        goingRight = true;
        Array<TextureRegion> frames= new Array<TextureRegion>();


        for(int i=0;i<7;i++) {
            frames.add(new TextureRegion(getTexture(), i * 32, 65, 32, 32));
            frames.get(i).getTexture().setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        }
        birdFlap = new Animation(0.075f,frames);
        frames.clear();


        for(int i=0;i<4;i++)
            frames.add(new TextureRegion(getTexture(), 128+i*32, 96,  32, 32));
        birdGlide = new Animation(0.75f,frames);
        frames.clear();

        defbirdbird();
        birdglide = new TextureRegion(getTexture(), 518, 20,  32, 32);


        setBounds(0,0,20,20);
        setRegion(birdglide);

    }
//    public  void flip(){
//        VX=-VX;

//    }
    public void update(float dt){
        setPosition(b2body.getPosition().x-getWidth()/2,b2body.getPosition().y-getHeight()/2);
        setRegion(getFrame(dt));

    }
    public TextureRegion getFrame(float dt){
        currentState = getState();
        TextureRegion region;

        switch (currentState){
            case FLAPPING:
                region = birdFlap.getKeyFrame(stateTimer,true);
                break;
            default:
                region = birdGlide.getKeyFrame(stateTimer,true);
                break;


        }
        if((b2body.getLinearVelocity().x<0)&& !region.isFlipX()){
            region.flip(true,false);
        } else if (b2body.getLinearVelocity().x>0 && region.isFlipX()){
            region.flip(true,false);
        }
        stateTimer = currentState == previousState ? stateTimer + dt : 0;
        previousState = currentState;
        return region;
    }
    public State getState(){
        if(b2body.getLinearVelocity().y>0 || !birdFlap.isAnimationFinished(0)){
            return  State.FLAPPING;
        }else if(b2body.getLinearVelocity().y<0 & birdFlap.isAnimationFinished(2)){
            return State.GLIDING;
        }else {
            return State.GLIDING;

        }
    };


    public  void defbirdbird(){
        BodyDef bdef = new BodyDef();
        bdef.position.set(100/Bird.PPM,100/Bird.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body= world.createBody(bdef);
        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(12/Bird.PPM);

        fdef.shape = shape;
//        b2body.applyLinearImpulse(new Vector2(1000f, 0), b2body.getWorldCenter(), true);
        b2body.setLinearVelocity(new Vector2(100f, 0));

        fdef.density=5f;
        fdef.isSensor=true;
        b2body.createFixture(fdef).setUserData("Wall");
        fdef.restitution=1;
        fdef.friction=0;
    }

}
