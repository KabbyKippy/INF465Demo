package com.mygdx.HorrorGame.main.states;

/**
 * Created by Aaron on 4/9/2015.
 */
    import com.mygdx.HorrorGame.main.TileMap.BackGround;
    import com.mygdx.HorrorGame.main.handlers.GameStateManager;

    import java.awt.*;
    import java.awt.event.KeyEvent;

    public class Menu extends GameState {
        private Graphics2D g;

        private BackGround bg;
        private int currentChoice = 0;

        private String[] options ={"Start", "Help", "Quit"};
        private Color titleColor;
        private Font titleFont;
        private Font font;


        public Menu(GameStateManager gsm){
            super(gsm);
            try{

                //bg = new BackGround("/menubg.gif", 1);
               // bg.setVector(-.1,0);

                titleColor = new Color(100, 0, 0);
                titleFont = new Font("Century Gothic", Font.PLAIN, 28);
                font = new Font("Arial", Font.PLAIN, 12);

            }catch(Exception ex){
                ex.printStackTrace();
            }
        }

        public void init() {}

        public void update(float dt) {
           // bg.update();

        }

        public void render(){
            draw(g);
        }

        public void dispose(){

        }

        public void handleInput(){

        }
        public void draw(Graphics2D g){
            //draw bg
            //bg.draw(g);

            //draw title
            g.setColor(titleColor);
            g.setFont(titleFont);
            g.drawString("Chris is   v ghcxzxchnhmb yhjnmc fgva dickbutt", 5, 70);


            //draw menu options
            g.setFont(font);
            for(int i = 0; i < options.length; i++)
            {
                if(i == currentChoice){
                    g.setColor(Color.WHITE);
                }
                else
                {
                    g.setColor(Color.RED);
                }
                g.drawString(options[i], 145, 140 + i * 15);
            }
        }


        private void select(){
            if(currentChoice == 0){
                //start
                gsm.setState(GameStateManager.PLAY);

            }
            if(currentChoice == 1){
                //help
            }

            if(currentChoice == 2){
                System.exit(0);
            }

        }


        public void keyPressed(int k){

            if(k == KeyEvent.VK_ENTER){
                select();
            }

            if(k == KeyEvent.VK_UP){
                currentChoice--;
                if(currentChoice == -1){
                    currentChoice = options.length-1;
                }
            }

            if(k == KeyEvent.VK_DOWN){
                currentChoice++;
                if(currentChoice == options.length)
                {
                    currentChoice = 0;
                }
            }

        }
        public void keyReleased(int k){}


    }
