package com.bird.game.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.bird.game.Bird;
import com.bird.game.Scenes.Hud;
import com.bird.game.Sprites.InteractiveTileObject;
import com.bird.game.Sprites.birdbird;
import com.bird.game.Tool.B2WorldCreator;
import com.bird.game.Tool.WorldContactListener;

public class PlayScreen implements Screen {
    public float VX=60000/Bird.PPM;
    private boolean flipped=false;
    private Bird game;
    private TextureAtlas atlas;

    private TmxMapLoader maploader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    private OrthographicCamera gamecam;
    private Viewport gamePort;
    private Hud hud;

    private World world;
    private Box2DDebugRenderer b2dr;
    private Texture background;

    private birdbird player;
//    private InteractiveTileObject
    private boolean firsTouch=true;

    public PlayScreen(Bird game){
        atlas = new TextureAtlas("allofthem.atlas");
        this.game = game;
        gamecam = new OrthographicCamera();
        gamePort = new FitViewport(320/Bird.PPM,530/Bird.PPM,gamecam);
//        Gdx.app.log("Woo",Integer.toString(Bird.ourdimensions()[0]));
        hud = new Hud(game.batch);
        maploader = new TmxMapLoader();
        map = maploader.load("arc3.tmx");
        renderer = new OrthogonalTiledMapRenderer(map,1/Bird.PPM);
        gamecam.position.set(gamePort.getWorldWidth()/2,gamePort.getWorldHeight()/2,0);

        world = new World((new Vector2(0,0)),true);
//        world.setGravity((new Vector2(0,-200)));
//        world.setVelocityThreshold(0);
        world.setContactListener(new ContactListener() {


            @Override
            public void beginContact(Contact contact) {
                Fixture fixtureA = contact.getFixtureA();
                Fixture fixtureB = contact.getFixtureB();
                Fixture object = fixtureA;
                if (object.getUserData()!=null) {
                    player.b2body.setLinearVelocity(new Vector2(-player.b2body.getLinearVelocity().x, player.b2body.getLinearVelocity().y));
                }
            }

            @Override
            public void endContact(Contact contact) {

            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {

            }

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {

            }
        });

        b2dr = new Box2DDebugRenderer();
        new B2WorldCreator(world,map);
        player = new birdbird(world,this);

    }

    @Override
    public void show() {

    }
    public TextureAtlas getAtlas(){
        return atlas;
    }
//    public void flip(){
//        VX=-VX;
//    }
//    public void wallcollide(){
//        VX=-VX;
//    }
    public void handleInput(float dt){

        if(Gdx.input.justTouched()) {
            if (player.b2body.getLinearVelocity().y < 50) {
                if(player.b2body.getLinearVelocity().x<0){
                    player.b2body.setLinearVelocity(-VX,10000);
                    player.b2body.applyForceToCenter(new Vector2(0, 1000000000), true);

                }else {
                    player.b2body.setLinearVelocity(VX,10000);
                    player.b2body.applyForceToCenter(new Vector2(0, 1000000000), true);


                }
//                player.b2body.applyForceToCenter(new Vector2(0, 100000f),true);
//                       player.b2body.setLinearVelocity(new Vector2(  player.b2body.getLinearVelocity().x, 200));
//                       player.b2body.applyLinearImpulse(new Vector2( player.b2body.getLinearVelocity().x,10), player.b2body.getWorldCenter(), true);
//

            }
//            if (firsTouch){
//                player.b2body.setLinearVelocity(new Vector2(1000f,0));
//                firsTouch=false;
//            }


            }


    }
    public boolean GoingLeft(){
        if(player.b2body.getLinearVelocity().x<0){
            return true;
        }else {
            return false;
        }
    }
    public void update(float dt) {
        gamecam.update();
//        player.b2body.setLinearDamping(0);


        handleInput(dt);
        player.update(dt);
        renderer.setView(gamecam);
        world.step(1 / 60f, 50, 50);
        if(player.b2body.getLinearVelocity().x<0) {
//            player.b2body.applyLinearImpulse(new Vector2(-4*(-VX-player.b2body.getLinearVelocity().x), -1500), player.b2body.getWorldCenter(), true);
            player.b2body.applyForceToCenter(new Vector2(0, -300000), true);
            Gdx.app.log("Vx is:", Float.toString(-VX-player.b2body.getLinearVelocity().x));

        }else {
//            player.b2body.applyLinearImpulse(new Vector2(4*(VX+player.b2body.getLinearVelocity().x), -1500), player.b2body.getWorldCenter(), true);
            player.b2body.applyForceToCenter(new Vector2(0, -300000), true);
            Gdx.app.log("Vx is:", "positive");
        }

            if (gamecam.position.y < player.b2body.getPosition().y) {
                gamecam.position.y = player.b2body.getPosition().y;
            }
            Gdx.app.log("list222", world.getContactList().toString());

    }
    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if ( player.b2body.getPosition().y>900/Bird.PPM) {
//            player.b2body.setTransform(new Vector2(player.b2body.getPosition().x,player.b2body.getPosition().y-700));
            player.b2body.setTransform(player.b2body.getPosition().x,player.b2body.getPosition().y-400/Bird.PPM,0);
            gamecam.position.y = player.b2body.getPosition().y;
        }
        renderer.render();


        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
//        b2dr.render(world,gamecam.combined);
        game.batch.setProjectionMatrix(gamecam.combined);
        game.batch.begin();
        player.draw(game.batch);

        game.batch.end();

    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width,height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }
    public TiledMap getMap(){
        return map;
    }
    public World getWorld(){
        return world;
    }
    @Override
    public void dispose() {
        map.dispose();
        renderer.dispose();
        world.dispose();
        b2dr.dispose();
        hud.dispose();
    }
}
