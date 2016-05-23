package com.mock.handlers;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;

public class MyContactListener implements ContactListener {

    @Override
    public void beginContact(Contact contact) {
        String a = (String) contact.getFixtureA().getBody().getUserData();
        String b = (String) contact.getFixtureB().getBody().getUserData();
        Body playerBody = null;
        Body otherBody = null;
        if (a.equals("player")) {
            playerBody = contact.getFixtureA().getBody();
            otherBody = contact.getFixtureB().getBody();
        } else {
            playerBody = contact.getFixtureB().getBody();
            otherBody = contact.getFixtureA().getBody();
        }
        if (otherBody.getUserData().equals("INSIDE_TP")) {
            System.out.println("TEST");
        } 
    }

    @Override
    public void endContact(Contact contact) {}
    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {}
    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {}

}
