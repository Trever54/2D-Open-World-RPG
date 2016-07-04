package com.mock.handlers;

import java.util.Stack;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.RayCastCallback;
import com.mock.actions.TextAction;
import com.mock.states.TopDownState;

public class ContactHandler implements ContactListener {

    public static Stack<String> zoneStrings = new Stack<String>();
    public static Stack<TextAction> textActions = new Stack<TextAction>();
    
    @Override
    public void beginContact(Contact contact) {
        String a = (String) contact.getFixtureA().getBody().getUserData().toString();
        String b = (String) contact.getFixtureB().getBody().getUserData().toString();
        Body playerBody = null;
        Body otherBody = null;
        if (a.equals("player")) {
            playerBody = contact.getFixtureA().getBody();
            otherBody = contact.getFixtureB().getBody();
        } else {
            playerBody = contact.getFixtureB().getBody();
            otherBody = contact.getFixtureA().getBody();
        }
    }
    
    // --------------- RAY CAST CALLBACK INTERFACE ----------------
    public RayCastCallback callback = new RayCastCallback() {
        @Override
        public float reportRayFixture(Fixture fixture, Vector2 point,
                Vector2 normal, float fraction) {
            if (fixture.getBody().getUserData().getClass().equals(TextAction.class)) {
                textActions.push((TextAction) fixture.getBody().getUserData());
            }
            if (fixture.getBody().getUserData().getClass().equals(String.class)) {
                zoneStrings.push(fixture.getBody().getUserData().toString());
            }
            return 0;
        } 
    };

    @Override
    public void endContact(Contact contact) {}
    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {}
    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {}

}
