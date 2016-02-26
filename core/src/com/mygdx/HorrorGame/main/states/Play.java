package com.mygdx.HorrorGame.main.states;

import static com.mygdx.HorrorGame.main.handlers.B2DVars.PPM;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.mygdx.HorrorGame.main.MyHorrorGame;
import com.mygdx.HorrorGame.main.TileMap.BackGround;
import com.mygdx.HorrorGame.main.entities.Items;
import com.mygdx.HorrorGame.main.entities.Player;
import com.mygdx.HorrorGame.main.entities.enemybat;
import com.mygdx.HorrorGame.main.entities.enemytengu;
import com.mygdx.HorrorGame.main.handlers.B2DVars;
import com.mygdx.HorrorGame.main.handlers.GameStateManager;
import com.mygdx.HorrorGame.main.handlers.MyContactListener;
import com.mygdx.HorrorGame.main.handlers.MyInput;

/**
 * Created by Kai on 3/11/2015.
 */
public class Play extends GameState {

    // The world the game takes place in
    private World world;
    private Music music = Gdx.audio.newMusic(Gdx.files.internal("Resources/Music/level1.mp3"));


    // This renders all the bodies we use for box2d
    private Box2DDebugRenderer b2dr;
    private boolean debug = false;

    private OrthographicCamera b2dCam;

    private MyContactListener cl;

    private float tileSize;
    private TiledMap tileMap;
    private OrthogonalTiledMapRenderer tmr;

    private Player player;
    private enemybat bat1;
    private enemybat bat2;
    private enemybat bat3;
    private enemytengu tengu;
    private enemytengu tengu2;
    private enemytengu tengu3;
    private boolean turnAround;
    private int steps;
    //private Array<enemybat> bat;
    private Array<Items> Item;

    private BackGround[] backgrounds;

    public static boolean onGround;
    public float accum = 0;


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
        createenemy();
       // createElevator();

        music.setVolume(0.5f);
        music.setLooping(true);
        music.play();
        turnAround = false;

        // Create tiles
        createTiles();

        b2dr = new Box2DDebugRenderer();


        // create Items
        createItems();





        b2dCam = new OrthographicCamera();
        b2dCam.setToOrtho(false, MyHorrorGame.V_WIDTH / PPM, MyHorrorGame.V_HEIGHT / PPM);


        //////////////////////////////////////////////////////////////////////




    }

    public boolean handleInput(float dt) {

        Vector2 vec;
        Boolean maxSpeed;
        // player jump
        if (MyInput.isPressed(MyInput.BUTTON1)) {
            if (cl.isPlayerOnGround()) { //check to see if the foot i s acutally on the ground
                Gdx.audio.newSound(Gdx.files.internal("Resources/SFX/playerjump.mp3")).play();
                player.getBody().applyForceToCenter(0, 225, true); // the player can jump a force of 200N upwards

            }
        }
        // Player run left
        if (MyInput.isDown(MyInput.BUTTON2)) {
            if (cl.isPlayerOnGround()) {
                accum = accum + dt;
                vec = player.getBody().getLinearVelocity();
                if (accum >= 30 * dt) {
                    player.getBody().setLinearVelocity(vec);
                    maxSpeed = true;
                    System.out.println("The velocity is set to: " + vec);
                    return maxSpeed;
                }
                else
                    player.getBody().applyForceToCenter(-6, 0, true);
            }

            else
                player.getBody().applyForceToCenter(-3, 0, true);


    }

        // Player run right
        if (MyInput.isDown(MyInput.BUTTON3)) {
            if (cl.isPlayerOnGround())
                player.getBody().applyForceToCenter(6, 0, true);

            else
                player.getBody().applyForceToCenter(3, 0, true);

        }

    return false;
    }


    public void render() {
        // Clear screen
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);


        // Set Camera To follow player
        cam.position.set(player.getPosition().x * PPM ,player.getPosition().y * PPM,0);//MyHorrorGame.V_HEIGHT/2,0);
        cam.update();
       // System.out.println(player.getPosition().x + "           " + player.getPosition().y );



        // draw bgs
        /*sb.setProjectionMatrix(hudCam.combined);
        for(int i = 0; i < backgrounds.length; i++) {
            backgrounds[i].render(sb);
        }*/

        // draw Tile map
        tmr.setView(cam);
        tmr.render();

        // draw player
        sb.setProjectionMatrix(cam.combined);
        player.render(sb);
        if(bat1.getBody().getUserData() != null){
        bat1.render(sb);}
        if(bat2.getBody().getUserData() != null){
            bat2.render(sb);}
        if (bat3.getBody().getUserData() != null){
            bat3.render(sb);}
        if(tengu.getBody().getUserData() != null)
        {  tengu.render(sb);}
        if(tengu2.getBody().getUserData() != null)
        {  tengu2.render(sb);}
        if(tengu3.getBody().getUserData() != null)
        {  tengu3.render(sb);}
       // bat2.render(sb);
        //bat3.render(sb);
        //
        /*for( int i = 0; i< bat.size; i++){
            bat.get(i).render(sb);


        }*/


        // items

        for( int i = 0; i< Item.size; i++){
            Item.get(i).render(sb);


        }


        // Draw the box2d world.
        // Render the bodies in the world, using the cam.
        // This lets you see the blocks
// draws the box world
        if(debug){
        b2dr.render(world, b2dCam.combined);}


    }

    public void update(float dt) {
        if(handleInput(dt) == true) // checks the input
            accum = 0;
        onGround = cl.isPlayerOnGround();
        // How often does the world check itself, and the other 2 parameters set it so
        // that it checks every so often in the world
        world.step(dt, 6, 2);

        // remove items

        Array<Body> bodies = cl.getBodiesToRemove();
        for (int i =0 ; i< bodies.size; i++){
            Body b = bodies.get(i);

            Item.removeValue((Items) b.getUserData(), true );
            world.destroyBody(b);
            player.collectHealth();
            System.out.println("The game is over.");
            System.exit(0);


        }
        bodies.clear();

        if( steps < 400)
        {
            steps += 1;
           // System.out.println("The step count is: " + " " + steps);
            if(bat1.getBody().getUserData() != null){
            bat1.update(dt);
            bat1.getBody().setLinearVelocity(20 / PPM, 10 / PPM);}
            if(bat2.getBody().getUserData() != null){
            bat2.update(dt);
            bat2.getBody().setLinearVelocity(-20 / PPM, 10 / PPM);}
            if(bat3.getBody().getUserData() != null) {
                bat3.update(dt);
                bat3.getBody().setLinearVelocity(40 / PPM, 10 / PPM);
            }

            if(tengu.getBody().getUserData() != null){
            tengu.getBody().setLinearVelocity(40 / PPM, 0);
            tengu.update(dt);}
            if(tengu2.getBody().getUserData() != null){
            tengu2.getBody().setLinearVelocity(40 / PPM, 0);
            tengu2.update(dt);}
            if(tengu3.getBody().getUserData() != null){
            tengu3.getBody().setLinearVelocity(40 / PPM, 0);
            tengu3.update(dt);}
        }
        else if((steps >= 400))
        {
            steps += 1;
           // System.out.println("Now the step count is " + " " + steps);
            if(bat1.getBody().getUserData() != null){
            bat1.update(dt);
            bat1.getBody().setLinearVelocity(-20 / PPM, 10 / PPM);}
            if(bat2.getBody().getUserData() != null){
            bat2.update(dt);
            bat2.getBody().setLinearVelocity(20 / PPM, 10 / PPM);}
            if(bat3.getBody().getUserData() != null){
            bat3.update(dt);
            bat3.getBody().setLinearVelocity(-40 / PPM, 10 / PPM);}


            if(tengu.getBody().getUserData() != null){
            tengu.getBody().setLinearVelocity(-40 / PPM, 0);
            tengu.update(dt);}
            if(tengu2.getBody().getUserData() != null){
            tengu2.getBody().setLinearVelocity(-40 / PPM, 0);
            tengu2.update(dt);}
            if(tengu3.getBody().getUserData() != null){
            tengu3.getBody().setLinearVelocity(-40 / PPM, 0);
            tengu3.update(dt);}

            if(steps > 600) steps = 0;
        }
        player.update(dt);
        Array<Body> denemy = cl.getEnemyToRemove();
        for ( int i =0 ; i< denemy.size; i++){
            Body E = denemy.get(i);


            world.destroyBody(E);

            //player.collectHealth();


        }

        if(cl.injured == true && !MyInput.isDown(MyInput.BUTTON4))
        {

            if(player.right == true)
                player.getBody().setLinearVelocity(-300 / PPM, 100 / PPM);

            if(player.right == false)
                player.getBody().setLinearVelocity(300 / PPM, 100 / PPM);



            //player.getBody().setLinearVelocity(0 / PPM, 0 / PPM);
        }






        /////items

        for( int i = 0; i< Item.size; i++){
            Item.get(i).update(dt);


        }





    }

    @Override
    public void dispose() {}
    public void createenemy(){


        BodyDef edef = new BodyDef();
        BodyDef edef2 = new BodyDef();
        BodyDef edef3 = new BodyDef();
        BodyDef edef4 = new BodyDef();
        BodyDef edef5 = new BodyDef();
        BodyDef edef6 = new BodyDef();

        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();


        // Create a Player
        // Player spawn position
        edef.position.set(3000/PPM, 2800/PPM);
        edef2.position.set(2600/PPM, 2800/PPM);
        edef3.position.set(3000/PPM, 2850/PPM);
        edef4.position.set(3126/PPM , 1586/PPM);
        edef5.position.set(4000/PPM , 1586/PPM);
        edef6.position.set(3800/PPM , 1586/PPM);
        //edef4.position.set(3000/PPM , 2800/PPM);




        edef.type = BodyDef.BodyType.DynamicBody;
        edef.linearVelocity.set(-3,3);

        Body body = world.createBody(edef);
        edef2.type = BodyDef.BodyType.DynamicBody;
        edef2.linearVelocity.set(-3,3);

        Body body2 = world.createBody(edef2);
        edef3.type = BodyDef.BodyType.DynamicBody;
        edef3.linearVelocity.set(-3,3);

        Body body3 = world.createBody(edef3);

        edef4.type = BodyDef.BodyType.DynamicBody;
        //edef4.linearVelocity.set(-3,3);

        Body body4 = world.createBody(edef4);
        edef5.type = BodyDef.BodyType.DynamicBody;
        //edef4.linearVelocity.set(-3,3);

        Body body5 = world.createBody(edef5);
        edef6.type = BodyDef.BodyType.DynamicBody;
        //edef4.linearVelocity.set(-3,3);

        Body body6 = world.createBody(edef6);

        // create enemy








        //
        // Create the shape
        shape.setAsBox(30/PPM, 17/PPM);

        // Create the fixture.
        fdef.shape = shape;
        fdef.restitution = 0f;
        fdef.filter.categoryBits = B2DVars.BIT_PLAYER; // type of box
        fdef.filter.maskBits = B2DVars.BIT_GROUND |  B2DVars.BIT_PLAYER; //collides with ground
        body.createFixture(fdef).setUserData("enemy"); // sets it as the box
        body2.createFixture(fdef).setUserData("enemy");
        body3.createFixture(fdef).setUserData("enemy");

        shape.setAsBox(15 / PPM, 12 / PPM);
        fdef.shape = shape;
        body4.createFixture(fdef).setUserData("enemy");
        body6.createFixture(fdef).setUserData("enemy");
        body5.createFixture(fdef).setUserData("enemy");


        // Create Foot Sensor creates the foot of the player
        shape.setAsBox(8 / PPM, 10 / PPM, new Vector2((-1 / PPM), (15 / PPM)), 0); //moves the foot lower than the player
        fdef.shape = shape;
        fdef.filter.categoryBits = B2DVars.BIT_PLAYER; // type of box
        fdef.filter.maskBits = B2DVars.BIT_GROUND ; //collides with ground
        fdef.isSensor = true; // makes the foot a sensor  "ghost fixure" it passes through things its a fixture other things can pass through but it detects collisions
        body.createFixture(fdef).setUserData("hit");
        body2.createFixture(fdef).setUserData("hit");
        body3.createFixture(fdef).setUserData("hit");
        body4.createFixture(fdef).setUserData("hit");



        // create Player

        bat1 = new enemybat(body);
        body.setUserData(bat1);
        bat2 = new enemybat(body2);
        body2.setUserData(bat2);
        bat3 = new enemybat(body3);
        body3.setUserData(bat3);
        tengu = new enemytengu(body4);
        body4.setUserData(tengu);
        tengu2 = new enemytengu(body5);
        body5.setUserData(tengu2);
        tengu3 = new enemytengu(body6);
        body6.setUserData(tengu3);





    }
    public void createPlayer(){

        BodyDef bdef = new BodyDef();
        BodyDef edef = new BodyDef();
        FixtureDef fdef = new FixtureDef();
        FixtureDef efdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();


        // Create a Player
        // Player spawn position
        // old: bdef.position.set(1200/ PPM, 4280 / PPM);
        bdef.position.set(2800/PPM, 2669/PPM);



        bdef.type = BodyDef.BodyType.DynamicBody;
        //bdef.linearVelocity.set(2,0);
        Body body = world.createBody(bdef);

        //
        // Create the shape
        shape.setAsBox(10/ PPM ,17 / PPM);

        // Create the fixture.
        fdef.shape = shape;
        fdef.restitution = 0f;
        fdef.filter.categoryBits = B2DVars.BIT_PLAYER; // type of box
        fdef.filter.maskBits = B2DVars.BIT_GROUND | B2DVars.Bit_Item1 |  B2DVars.BIT_PLAYER; //collides with ground
        body.createFixture(fdef).setUserData("player"); // sets it as the box


        // Create Foot Sensor creates the foot of the player
        shape.setAsBox(9/ PPM , 10/ PPM, new Vector2(0, (-10/PPM)), 0); //moves the foot lower than the player
        fdef.shape = shape;
        fdef.filter.categoryBits = B2DVars.BIT_PLAYER; // type of box
        fdef.filter.maskBits = B2DVars.BIT_GROUND |  B2DVars.BIT_PLAYER; //collides with ground
        fdef.isSensor = true; // makes the foot a sensor  "ghost fixure" it passes through things its a fixture other things can pass through but it detects collisions
        body.createFixture(fdef).setUserData("foot");



        // create Player

        player = new Player(body);
        body.setUserData(player);

    }

    public void createTiles(){

        // Load tile map
        tileMap = new TmxMapLoader().load("Resources/Maps/master map.tmx");
        tmr = new OrthogonalTiledMapRenderer(tileMap);

        // Get this layer
        TiledMapTileLayer layer = (TiledMapTileLayer) tileMap.getLayers().get("Walls/Floors");
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
                //  Where you change friction
                fdef.friction = .8f;
                fdef.shape = cs;
                fdef.filter.categoryBits = bits;
                fdef.filter.maskBits = B2DVars.BIT_PLAYER;
                fdef.isSensor = false;

                world.createBody(bdef).createFixture(fdef);

            }
        }
    }

   /* public void createElevator(){

        BodyDef bdef = new BodyDef();
        BodyDef edef = new BodyDef();
        FixtureDef fdef = new FixtureDef();
        FixtureDef efdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();


        // Create a Player
        // Player spawn position
        // old: bdef.position.set(1200/ PPM, 4280 / PPM);
        bdef.position.set(4200/PPM, 2669/PPM);



        bdef.type = BodyDef.BodyType.KinematicBody;
        //bdef.linearVelocity.set(2,0);
        Body body = world.createBody(bdef);

        // Create the shape
        shape.setAsBox(50/ PPM ,20 / PPM);

        // Create the fixture.
        fdef.shape = shape;
        fdef.restitution = 0f;
        fdef.filter.categoryBits = B2DVars.BIT_PLAYER; // type of box
        fdef.filter.maskBits = B2DVars.BIT_GROUND | B2DVars.Bit_Item1 |  B2DVars.BIT_PLAYER; //collides with ground
        body.createFixture(fdef).setUserData("elevator"); // sets it as the box


        // Create Foot Sensor creates the foot of the player
        shape.setAsBox(50/ PPM , 20/ PPM, new Vector2(0, (-10/PPM)), 0); //moves the foot lower than the player
        fdef.shape = shape;
        fdef.filter.categoryBits = B2DVars.BIT_PLAYER; // type of box
        fdef.filter.maskBits = B2DVars.BIT_GROUND |  B2DVars.BIT_PLAYER; //collides with ground
        fdef.isSensor = true; // makes the foot a sensor  "ghost fixure" it passes through things its a fixture other things can pass through but it detects collisions
        body.createFixture(fdef).setUserData("elevatorSensor");



    }*/
    private void createItems(){

        Item = new Array<Items>();
        MapLayer layer = tileMap.getLayers().get("Item");

        BodyDef bdef = new BodyDef();
        FixtureDef fdef = new FixtureDef();


        for(MapObject mo : layer.getObjects()){
            bdef.type = BodyDef.BodyType.StaticBody;
            float x = mo.getProperties().get("x", Float.class)/PPM;
            float y = mo.getProperties().get("y", Float.class)/PPM;

            bdef.position.set(x, y);

            CircleShape cshape = new CircleShape();
            cshape.setRadius(8/PPM);
            fdef.shape = cshape;
            fdef.isSensor = true;
            fdef.filter.categoryBits = B2DVars.Bit_Item1;
            fdef.filter.maskBits = B2DVars.BIT_PLAYER;

            Body body = world.createBody(bdef);
            body.createFixture(fdef).setUserData("lantern");

            Items c = new Items(body);
            Item.add(c);


            body.setUserData(c);




        }



    }

}
