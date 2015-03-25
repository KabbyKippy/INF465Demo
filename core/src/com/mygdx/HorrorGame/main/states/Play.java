package com.mygdx.HorrorGame.main.states;

import static com.mygdx.HorrorGame.main.handlers.B2DVars.PPM;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.HorrorGame.main.MyHorrorGame;
import com.mygdx.HorrorGame.main.TileMap.BackGround;
import com.mygdx.HorrorGame.main.entities.Player;
import com.mygdx.HorrorGame.main.handlers.B2DVars;
import com.mygdx.HorrorGame.main.handlers.GameStateManager;
import com.mygdx.HorrorGame.main.handlers.MyContactListener;
import com.mygdx.HorrorGame.main.handlers.MyInput;
import com.sun.javafx.sg.BackgroundImage;

/**
 * Created by Kai on 3/11/2015.
 */
public class Play extends GameState {

    // The world the game takes place in
    private World world;

    // This renders all the bodies we use for box2d
    private Box2DDebugRenderer b2dr;

    private OrthographicCamera b2dCam;

    private MyContactListener cl;

    private float tileSize;
    private TiledMap tileMap;
    private OrthogonalTiledMapRenderer tmr;

    private Player player;

    private BackGround[] backgrounds;

    public Play(GameStateManager gsm){
        super(gsm);

        // First parameter sets gravity up, 0 in the x direction, 9.81 m/s in the negative y direction
        // Second parameter sets the world up so that any body not active, is not account for.
        world = new World(new Vector2(0, -9.81f), true);
        cl = new MyContactListener(); //contact listener
        world.setContactListener(cl); // males the world use the contact listener
        // ******************************************************


        // Create player
        createPlayer();

        // Create tiles
        createTiles();

        b2dr = new Box2DDebugRenderer();

        Texture bgs = new Texture("Resources/Backgrounds/bgs.png");
        TextureRegion sky = new TextureRegion(bgs, 0, 0, 320, 240);
        TextureRegion clouds = new TextureRegion(bgs, 0, 240, 320, 240);
        TextureRegion mountains = new TextureRegion(bgs, 0, 480, 320, 240);
        backgrounds = new BackGround[3];
        backgrounds[0] = new BackGround(sky, cam, 0f);
        backgrounds[1] = new BackGround(clouds, cam, 0.1f);
        backgrounds[2] = new BackGround(mountains, cam, 0.2f);





        b2dCam = new OrthographicCamera();
        b2dCam.setToOrtho(false, MyHorrorGame.V_WIDTH / PPM, MyHorrorGame.V_HEIGHT / PPM);

        //////////////////////////////////////////////////////////////////////


    }

    public void handleInput() {

        // player jump
        if(MyInput.isPressed(MyInput.BUTTON1)){
            if(cl.isPlayerOnGround()){ //check to see if the foot is acutally on the ground
                player.getBody().applyForceToCenter(0, 200, true); // the player can jump a force of 200N upwards



            }
        }
        // Player run left
        if(MyInput.isDown(MyInput.BUTTON2)){
            player.getBody().applyForceToCenter(-5, 0, true);

        }

        // Player run right
        if(MyInput.isDown(MyInput.BUTTON3)){
            player.getBody().applyForceToCenter(5, 0, true);

        }





/* Test to see that the input works
        if(MyInput.isPressed(MyInput.BUTTON1)){
            System.out.println("The Z key is pressed");
        }
        if(MyInput.isDown(MyInput.BUTTON2))
        {
            System.out.println("The x button is held down");

        }




*/
    }

    public void render() {
        // Clear screen
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // draw bgs
        sb.setProjectionMatrix(hudCam.combined);
        for(int i = 0; i < backgrounds.length; i++) {
            backgrounds[i].render(sb);
        }

        // draw Tile map
        tmr.setView(cam);
        tmr.render();

        // draw player
        sb.setProjectionMatrix(cam.combined);
        player.render(sb);


        // Draw the box2d world.
        // Render the bodies in the world, using the cam.
        b2dr.render(world, b2dCam.combined);
    }

    public void update(float dt) {
        handleInput(); // checks the input
        // How often does the world check itself, and the other 2 parameters set it so
        // that it checks every so often in the world
        world.step(dt,6,2);

        player.update(dt);
    }

    @Override
    public void dispose() {}

    public void createPlayer(){

        BodyDef bdef = new BodyDef();
        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();

        // Create a Player

        bdef.position.set(160 / PPM, 200 / PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        Body body = world.createBody(bdef);

        // Create the shape
        shape.setAsBox(17/ PPM ,17 / PPM);

        // Create the fixture.
        fdef.shape = shape;
        fdef.restitution = 0f;
        fdef.filter.categoryBits = B2DVars.BIT_PLAYER; // type of box
        fdef.filter.maskBits = B2DVars.BIT_GROUND; //collides with ground
        body.createFixture(fdef).setUserData("player"); // sets it as the box


        // Create Foot Sensor creates the foot of the player
        shape.setAsBox(17 / PPM , 17/ PPM, new Vector2(0, (-17/PPM)), 0); //moves the foot lower than the player
        fdef.shape = shape;
        fdef.filter.categoryBits = B2DVars.BIT_PLAYER; // type of box
        fdef.filter.maskBits = B2DVars.BIT_GROUND; //collides with ground
        fdef.isSensor = true; // makes the foot a sensor  "ghost fixure" it passes through things its a fixture other things can pass through but it detects collisions
        body.createFixture(fdef).setUserData("foot");

        // create Player

        player = new Player(body);

    }

    public void createTiles(){

        // Load tile map
        tileMap = new TmxMapLoader().load("Resources/Maps/Level1Demov2.tmx");
        tmr = new OrthogonalTiledMapRenderer(tileMap);

        // Get this layer
        TiledMapTileLayer layer = (TiledMapTileLayer) tileMap.getLayers().get("Tile Layer 1");
        tileSize = layer.getTileWidth();

        createLayer(layer, B2DVars.BIT_GROUND);


    }

    private void createLayer(TiledMapTileLayer layer, short bits){

        BodyDef bdef = new BodyDef();
        FixtureDef fdef = new FixtureDef();

        // rows y direction, cols x direction
        for(int row = 0; row < layer.getHeight(); row++){
            for(int col = 0; col < layer.getWidth(); col++)
            {
                // Start creating bodies for each tiled
                // Get the cell
                TiledMapTileLayer.Cell cell = layer.getCell(col,row);

                // Check if cell exists
                if(cell == null) continue;
                if(cell.getTile() == null) continue;

                // Create a body + fixture from cell
                bdef.type = BodyDef.BodyType.StaticBody;

                bdef.position.set(
                        (col+0.5f) * tileSize / PPM,
                        (row+0.5f) * tileSize / PPM
                );

                ChainShape cs = new ChainShape();
                Vector2[] v = new Vector2[4];

                v[0] = new Vector2(
                        -tileSize / 2/ PPM, -tileSize / 2 / PPM
                );
                v[1] = new Vector2(
                        -tileSize / 2/ PPM, tileSize / 2 / PPM
                );
                v[2] = new Vector2(
                        tileSize / 2/ PPM, tileSize / 2 / PPM
                );
                v[3] = new Vector2(
                        tileSize / 2/ PPM, -tileSize / 2 / PPM
                );

                cs.createChain(v);
                fdef.friction = .5f;
                fdef.shape = cs;
                fdef.filter.categoryBits = bits;
                fdef.filter.maskBits = B2DVars.BIT_PLAYER;
                fdef.isSensor = false;

                world.createBody(bdef).createFixture(fdef);

            }
        }
    }
}
