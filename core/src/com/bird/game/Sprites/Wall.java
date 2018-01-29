package com.bird.game.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.bird.game.Bird;
import com.bird.game.Screens.PlayScreen;


/**
 * Created by Ahoura on 1/27/2018.
 */

public class Wall extends InteractiveTileObject {
    private static TiledMapTileSet tileSet;

    public Wall(PlayScreen screen, MapObject object) {
        super(screen, object);
        tileSet = map.getTileSets().getTileSet("tileset_gutter");
        fixture.setUserData(this);
        }
        ;


        @Override
        public void onHit (){
            Gdx.app.log("SOMETHING HAPPENDDDD","");
        }
        ;

}
