package Src.Main;


import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import Src.Tiles.TileManager;

import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;;

public class GamePanel extends JPanel implements ActionListener{

    String gameOverMessage = new String(); // String displayed at game over

    int OriginalSpritTileSize = 8; // each sprit is 16 x 16
    int screenScaling = 6;

    public int spritTileSize = OriginalSpritTileSize * screenScaling; // each displayed sprit will be 48 x 48 rather than 16 x 16

    public int numOfCol = 20; //num of collums which each are 1 tile
    public int numOfRow = 30; // num of rows which are each 1

    // resolution of 960 x 960 pixels
    public int screenWidth = numOfRow * spritTileSize; 
    public int screenHeight = numOfCol * spritTileSize;

    public int Delay = 75; // timer delay

    final int SnakeX[] = new int[numOfCol * numOfRow];
    final int SnakeY[] = new int[numOfCol * numOfRow];
    int bodyparts = 2; // num of body parts
    int appleX; // the position of the apple currently on screen
    int appleY;
    String lastDirection = "U"; //direction starting going right
    boolean running = false;
    Timer timer;
    KeyboardInput keyI = new KeyboardInput();
    TileManager tileM = new TileManager(this);

    //For 5 Player Gamemode

    String gameMode = "5 APPLE";

    int appleXtwo; 
    int appleYtwo;

    int appleXthree; 
    int appleYthree;

    int appleXfour; 
    int appleYfour;

    int appleXfive; 
    int appleYfive;

    GamePanel(String gameMode){ // Constructer, congigures JPanel along with inputing GameMode and starts the game
        this.gameMode = gameMode;
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyI);
        this.setFocusable(true);
        startGame();
    }

    public void startGame(){
        // Starts snake in the middle of the screen
        SnakeX[0] = screenWidth / 2;
        SnakeX[1] = screenWidth / 2;
        SnakeY[0] = screenHeight / 2;
        SnakeY[1] = (screenHeight / 2) - spritTileSize;
        // Starts Snake with 2 body parts
        bodyparts = 2; 
        // declares and starts timer along with sets runnning to true which is used in the actionPreformed method
        timer = new Timer(Delay, this);
        timer.start();
        running = true;

        //creates a new apples
        makeNewAppleCoordinatesOne();
        if(gameMode.equals("5 APPLE")){
            makeNewAppleCoordinatesTwo();
            makeNewAppleCoordinatesThree();
            makeNewAppleCoordinatesFour();
            makeNewAppleCoordinatesFive();
        }
    }
    // JPanel method which initializes 2D graphics
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        draw(g, g2);

    }

    public void draw(Graphics g, Graphics2D g2){
        
        if(checkCollisions()){
            gameOver();
        } // if collisions have occured, game is over and nothing needs to be drawn
        else{ 
            tileM.drawBackground(g2); // draws background tile using the tile Manager Class

            //Creates Apple depend
            if(gameMode.equals("5 APPLE")){
                tileM.drawApple(appleXtwo, appleYtwo, g2); 
                tileM.drawApple(appleXthree, appleYthree, g2); 
                tileM.drawApple(appleXfour, appleYfour, g2); 
                tileM.drawApple(appleXfive, appleYfive, g2); 
            }
            tileM.drawApple(appleX, appleY, g2); 

            // Creates Snake Head
            tileM.drawSnakeHead(SnakeX[0], SnakeY[0], lastDirection, g2);

            // Creates Snake Body
            for(int i = bodyparts; i > 0; i--){
                if(SnakeX[i] == SnakeX[i - 1]){
                    tileM.drawBody(SnakeX[i], SnakeY[i], "V", g2);
                }
                if(SnakeY[i] == SnakeY[i - 1]){
                    tileM.drawBody(SnakeX[i], SnakeY[i], "H", g2);
                }
            }
        }
    }

    public void makeNewAppleCoordinatesOne(){ // generates random coordinates for the Apple One
        appleX = spritTileSize * (((int) Math.ceil(numOfRow * Math.random())) - 1);
        appleY = spritTileSize * (((int) Math.ceil(numOfCol * Math.random())) - 1);
    }

     public void makeNewAppleCoordinatesTwo(){ // generates random coordinates for the Apple Two
        appleXtwo = spritTileSize * (((int) Math.ceil(numOfRow * Math.random())) - 1);
        appleYtwo = spritTileSize * (((int) Math.ceil(numOfCol * Math.random())) - 1);
     }
     public void makeNewAppleCoordinatesThree(){ // generates random coordinates for the Apple Three
        appleXthree = spritTileSize * (((int) Math.ceil(numOfRow * Math.random())) - 1);
        appleYthree = spritTileSize * (((int) Math.ceil(numOfCol * Math.random())) - 1);
     }
     public void makeNewAppleCoordinatesFour(){ // generates random coordinates for the Apple Four
        appleXfour = spritTileSize * (((int) Math.ceil(numOfRow * Math.random())) - 1);
        appleYfour = spritTileSize * (((int) Math.ceil(numOfCol * Math.random())) - 1);
     }
     public void makeNewAppleCoordinatesFive(){ // generates random coordinates for the Apple Five
        appleXfive = spritTileSize * (((int) Math.ceil(numOfRow * Math.random())) - 1);
        appleYfive = spritTileSize * (((int) Math.ceil(numOfCol * Math.random())) - 1);
     }

    public void move(){
        //Puts Snake body part to last location of next bodypart       
        for(int i = bodyparts; i > 0; i--){
            SnakeY[i] = SnakeY[i - 1];
            SnakeX[i] = SnakeX[i - 1];
        }
        // Keyboard Inputs to change in movement
        if(keyI.upPressed){
            if(lastDirection.equals("D")){ //if you try to go oppisate direction, it will continue going the current direction
                SnakeY[0] += spritTileSize;
            }
            else{
                SnakeY[0] -= spritTileSize;
                lastDirection = "U";
            }
        }
        else if(keyI.downPressed){
            if(lastDirection.equals("U")){
                SnakeY[0] -= spritTileSize;
            }
            else{
                SnakeY[0] += spritTileSize;
                lastDirection = "D";
            }
        }
        else if(keyI.leftPressed){
            if(lastDirection.equals("R")){
                SnakeX[0] += spritTileSize;
            }
            else{
                SnakeX[0] -= spritTileSize;
              lastDirection = "L";
            }
        }
        else if(keyI.rightPressed){
            if(lastDirection.equals("L")){
                 SnakeX[0] -= spritTileSize;
            }
            else{
                SnakeX[0] += spritTileSize;
                lastDirection = "R";
            }
        }
        else{
            if(lastDirection.equals("U")){ // keeps Snake moving even if no key is pressed
                SnakeY[0] -= spritTileSize;
            }
            if(lastDirection.equals("D")){
                SnakeY[0] += spritTileSize;
            }
            if(lastDirection.equals("L")){
                SnakeX[0] -= spritTileSize;
            }
            if(lastDirection.equals("R")){
                SnakeX[0] += spritTileSize;
            }
        }
    }
    
    public void checkApple(){ // checks if apples are eaten, then enlarges snake
        if(SnakeX[0] == appleX && SnakeY[0] == appleY){
            bodyparts++;  
            makeNewAppleCoordinatesOne();
        }
        if(gameMode.equals("5 APPLE")){ // if in 5 apple mode, it checks for al the other apples
            if(SnakeX[0] == appleXtwo && SnakeY[0] == appleYtwo){
                bodyparts++;  
                makeNewAppleCoordinatesTwo();
            }
            if(SnakeX[0] == appleXthree && SnakeY[0] == appleYthree){
                bodyparts++;  
                makeNewAppleCoordinatesThree();
            }
            if(SnakeX[0] == appleXfour && SnakeY[0] == appleYfour){
                bodyparts++;  
                makeNewAppleCoordinatesFour();
            }
            if(SnakeX[0] == appleXfive && SnakeY[0] == appleYfive){
                bodyparts++;  
                makeNewAppleCoordinatesFive();
            }
        }
        
        
    }

    public boolean checkCollisions(){ // Checks if Snake head is either out of bounds or hits a body part
        if(SnakeX[0] < -1 || SnakeX[0] >= screenWidth || SnakeY[0] < -1 || SnakeY[0] >= screenHeight){
            return true;
        }
        for(int i = 1; i < bodyparts; i++){ 
            if(SnakeX[0] == SnakeX[i] && SnakeY[0] == SnakeY[i]){
                return true;
            }
        }
        return false;
    }

    public void gameOver(){
        if(checkCollisions()){ // if Snake hits something
            timer.stop(); 

            //Creates Game Over message, depenging on if there is a new high score
            gameOverMessage = "Game Over, Score: " + (bodyparts - 2) + "\n" + "Would you like to play again?";
            if((bodyparts - 2) > getHighScore()){
                gameOverMessage += "\n" + "NEW " + gameMode + " HIGH SCORE, OLD " + gameMode + " HIGH SCORE: " + getHighScore();
                int highScore = bodyparts - 2;
                setNewHighScore(highScore);
            } else{
                gameOverMessage += "\n" + gameMode + " HIGH SCORE: " + getHighScore();
            }
            int playagain = JOptionPane.showConfirmDialog(null, gameOverMessage);
            // reruns game if yes, sends to homepage if no
            if(playagain == JOptionPane.YES_OPTION){
                new GameFrame(gameMode);
            }
            if(playagain == JOptionPane.NO_OPTION){
                new MainMenuScreen().runHomepage();
            }
            running = false;
        }
    }
 
    public int getHighScore(){ // Parses text file for Highscore
        int highScore = 0;
        if(gameMode.equals("5 APPLE")){
            try {
            File highScoreFile = new File("Src/res/HighScore5apple.txt");
            Scanner myReader = new Scanner(highScoreFile);
            highScore = Integer.parseInt(myReader.nextLine());
            myReader.close();
            }catch (FileNotFoundException e){} // just to prevent errors
        }
        else{
            try {
            File highScoreFile = new File("Src/res/HighScore1apple.txt");
            Scanner myReader = new Scanner(highScoreFile);
            highScore = Integer.parseInt(myReader.nextLine());
            myReader.close();
            }catch (FileNotFoundException e){} // just to prevent errors  
        }
        return highScore;
    }

    public void setNewHighScore(int highScore){ // Changes text file to new High Score
        if(gameMode.equals("5 APPLE")){
            try { //trycatch required for File Writer
                FileWriter fWriter = new FileWriter("Src/res/HighScore5apple.txt");
                fWriter.write(Integer.toString(highScore));
                fWriter.close();
            } catch (IOException e) { e.printStackTrace(); }
        }
        else{
            try { //trycatch required for File Writer
                FileWriter fWriter = new FileWriter("Src/res/HighScore1apple.txt");
                fWriter.write(Integer.toString(highScore));
                fWriter.close();
            } catch (IOException e) { e.printStackTrace(); }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) { // this runs repetatdly moving the snake, checking its state, and redoing visuals
        if(running){
            move();
            checkApple();
            repaint();
        }   
    }
}
