package com.mygdx.HorrorGame.main.states;

import static com.mygdx.HorrorGame.main.handlers.B2DVars.PPM;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.HorrorGame.main.MyHorrorGame;
import com.mygdx.HorrorGame.main.handlers.GameStateManager;

/**
 * Created by Kai on 3/11/2015.
 */
public class Play extends GameState {

    // The world the game takes place in
    private World world;

    // This renders all the bodies we use for box2d
    private Box2DDebugRenderer b2dr;

    private OrthographicCamera b2dCam;

    public Play(GameStateManager gsm){
        super(gsm);

        // First parameter sets gravity up, 0 in the x direction, 9.81 m/s in the negative y direction
        // Second parameter sets the world up so that any body not active, is not account for.
        world = new World(new Vector2(0, -9.81f), true);

        b2dr = new Box2DDebugRenderer();

        // Create platform
        BodyDef bdef = new BodyDef();
        bdef.position.set(160 / PPM ,120 / PPM);
        bdef.type = BodyDef.BodyType.StaticBody;
        Body body = world.createBody(bdef);

        // 3 types of bodies
        // Static body - don't move, unaffected by forces
        // kinematic body - don't get affected by the world forces, but their velocities can change
        // dynamic body - always get affected by forces
        // The ground is a static body, the player is a dynamic body, and a moving platform is a kinematic body

        // Create a new shape
        PolygonShape shape = new PolygonShape();
        // This will make it so it's 100 by 10, it's half the size.
        shape.setAsBox(50 / PPM,5 / PPM);

        // Create fixtures
        FixtureDef fdef = new FixtureDef();
        fdef.shape = shape;
        body.createFixture(fdef);

        // Create a falling box

        bdef.position.set(160 / PPM, 200 / PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bdef);

        // Create the shape
        shape.setAsBox(5 / PPM ,5 / PPM);

        // Create the fixture.
        fdef.shape = shape;
        fdef.restitution = .99f;
        body.createFixture(fdef);

        b2dCam = new OrthographicCamera();
        b2dCam.setToOrtho(false, MyHorrorGame.V_WIDTH / PPM, MyHorrorGame.V_HEIGHT / PPM);
    }

    public void handleInput() {}

    public void render() {
        // Clear screen
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Draw the box2d world.
        // Render the bodies in the world, using the cam.
        b2dr.render(world, b2dCam.combined);
    }

    public void update(float dt) {

        // How often does the world check itself, and the other 2 parameters set it so
        // that it checks every so often in the world
        world.step(dt,6,2);
    }

    @Override
    public void dispose() {}
}
