package Src.Tiles;

import java.io.IOException;

import java.awt.Graphics2D;

import javax.imageio.ImageIO;

import Src.Main.GamePanel;

public class TileManager {

    GamePanel gp;
    Tile backgroundTile = new Tile();
    Tile SnakeHeadU = new Tile();
    Tile SnakeHeadD = new Tile();
    Tile SnakeHeadL = new Tile();
    Tile SnakeHeadR = new Tile();
    Tile SnakeBodyH = new Tile();
    Tile SnakeBodyV = new Tile();
    Tile Apple = new Tile();


    
    public TileManager(GamePanel gp){
        this.gp = gp;

        getTilesImage();

    }

    public void getTilesImage(){

        try {
            backgroundTile.image = ImageIO.read(getClass().getResourceAsStream("../res/BackgroundTile.png"));

            SnakeHeadU.image = ImageIO.read(getClass().getResourceAsStream("../res/SnakeHeadU.png"));
            SnakeHeadD.image = ImageIO.read(getClass().getResourceAsStream("../res/SnakeHeadD.png"));
            SnakeHeadL.image = ImageIO.read(getClass().getResourceAsStream("../res/SnakeHeadL.png"));
            SnakeHeadR.image = ImageIO.read(getClass().getResourceAsStream("../res/SnakeHeadR.png"));

            SnakeBodyH.image = ImageIO.read(getClass().getResourceAsStream("../res/SnakeBodyH.png"));
            SnakeBodyV.image = ImageIO.read(getClass().getResourceAsStream("../res/SnakeBodyV.png"));

            Apple.image = ImageIO.read(getClass().getResourceAsStream("../res/Apple.png"));

            
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void drawBackground(Graphics2D g2){

        try {
            backgroundTile.image = ImageIO.read(getClass().getResourceAsStream("../res/BackgroundTile.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(int x = 0; x < gp.screenWidth; x += gp.spritTileSize){
            for(int y = 0; y < gp.screenHeight; y += gp.spritTileSize){
                g2.drawImage(backgroundTile.image, x, y, gp.spritTileSize, gp.spritTileSize, null);
            }
        }
    }
    public void drawBody(int x, int y, String direction, Graphics2D g2){
        if(direction.equals("H")){
            g2.drawImage(SnakeBodyH.image,x,y,gp.spritTileSize,gp.spritTileSize, null);
        }
        if(direction.equals("V")){
            g2.drawImage(SnakeBodyV.image,x,y,gp.spritTileSize,gp.spritTileSize, null);
        }
    }
    public void drawSnakeHead(int x, int y, String direction, Graphics2D g2){
        if(direction.equals("U")){
            g2.drawImage(SnakeHeadU.image,x,y,gp.spritTileSize,gp.spritTileSize, null);
        }
        if(direction.equals("D")){
            g2.drawImage(SnakeHeadD.image,x,y,gp.spritTileSize,gp.spritTileSize, null);
        }
        if(direction.equals("L")){
            g2.drawImage(SnakeHeadL.image,x,y,gp.spritTileSize,gp.spritTileSize, null);
        }
        if(direction.equals("R")){
            g2.drawImage(SnakeHeadR.image,x,y,gp.spritTileSize,gp.spritTileSize, null);
        }

    }

    public void drawApple(int x, int y, Graphics2D g2){
        g2.drawImage(Apple.image,x,y,gp.spritTileSize,gp.spritTileSize, null);
    }
}
