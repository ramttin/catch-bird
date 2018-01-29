package com.bird.game.Tool;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.bird.game.Bird;
import com.bird.game.Screens.PlayScreen;
import com.bird.game.Sprites.InteractiveTileObject;
import com.bird.game.Sprites.birdbird;

/**
 * Created by Ahoura on 1/27/2018.
 */

public class WorldContactListener implements ContactListener {
    public float VX;
    @Override
    public void beginContact(Contact contact) {

    }

    @Override
    public void endContact(Contact contact) {
        Gdx.app.log("here2","herher");
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
        Gdx.app.log("here3","herher");
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
