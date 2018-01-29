package com.bird.game.Tool;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.bird.game.Bird;
import com.bird.game.Sprites.Wall;

/**
 * Created by Ahoura on 1/27/2018.
 */

public class B2WorldCreator {
//    public Fixture fixture;
    public B2WorldCreator(World world, TiledMap map){
        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;
        for(MapObject object: map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2) / Bird.PPM, (rect.getY() + rect.getHeight() / 2) / Bird.PPM);
            body = world.createBody(bdef);
            shape.setAsBox(rect.getWidth()/(2*Bird.PPM), rect.getHeight() /(2*Bird.PPM));
            fdef.shape = shape;
            fdef.filter.categoryBits = Bird.OBJECT_BIT;
//            body.createFixture(fdef);
            body.createFixture(fdef).setUserData("RIGHT WALL");

//            fixture = body.createFixture(fdef);1



        }
        for(MapObject object: map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2) / Bird.PPM, (rect.getY() + rect.getHeight() / 2) / Bird.PPM);
            body = world.createBody(bdef);
            shape.setAsBox(rect.getWidth()/(2*Bird.PPM), rect.getHeight() /(2*Bird.PPM));
            fdef.shape = shape;
            fdef.filter.categoryBits = Bird.OBJECT_BIT;
            body.createFixture(fdef);
            body.createFixture(fdef).setUserData("LEFT WALL");

//            fixture = body.createFixture(fdef);


        }
        for(MapObject object: map.getLayers().get(6).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2) / Bird.PPM, (rect.getY() + rect.getHeight() / 2) / Bird.PPM);
            body = world.createBody(bdef);
            shape.setAsBox(rect.getWidth()/(2*Bird.PPM), rect.getHeight() /(2*Bird.PPM));
            fdef.shape = shape;
            fdef.filter.categoryBits = Bird.OBJECT_BIT;
            body.createFixture(fdef);
//            fixture = body.createFixture(fdef);

        }

    }
}
