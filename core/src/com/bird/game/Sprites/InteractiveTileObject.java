package com.bird.game.Sprites;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.bird.game.Bird;
import com.bird.game.Screens.PlayScreen;
import com.sun.org.apache.regexp.internal.RE;

/**
 * Created by Ahoura on 1/27/2018.
 */

public abstract class InteractiveTileObject {
    protected World world;
    protected TiledMap map;
    protected TiledMapTile tile;
    protected Rectangle bounds;
    protected Body body;
    protected PlayScreen screen;
    protected MapObject object;
    protected Fixture fixture;
    public  InteractiveTileObject(PlayScreen screen, MapObject object){
        this.object = object;
        this.world = screen.getWorld();
        this.map = screen.getMap();
        this.screen = screen;
        this.bounds = ((RectangleMapObject) object).getRectangle();
        BodyDef bdef = new BodyDef();
        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        bdef.type = BodyDef.BodyType.StaticBody;
        bdef.position.set((bounds.getX() + bounds.getWidth() / 2) / Bird.PPM, (bounds.getY() + bounds.getHeight() / 2) / Bird.PPM);
        body = world.createBody(bdef);
        shape.setAsBox(bounds.getWidth()/(2*Bird.PPM), bounds.getHeight() /(2*Bird.PPM));
        fdef.shape = shape;
        fdef.friction=0;
        fdef.restitution=1;



    }
    public abstract void onHit();
    public void setCategoryFilter(short filterBit){
        Filter filter = new Filter();
        filter.categoryBits = filterBit;
        fixture.setFilterData(filter);
    }
    public TiledMapTileLayer.Cell getCell(){
        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(1);
        return layer.getCell((int)(body.getPosition().x * Bird.PPM / 16),
                (int)(body.getPosition().y * Bird.PPM / 16));
    }
}
