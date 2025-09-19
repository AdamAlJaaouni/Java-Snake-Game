package Src.Main;

import javax.swing.JFrame;

public class GameFrame extends JFrame{

    GameFrame(String gameMode){
        this.add(new GamePanel(gameMode));
        this.setTitle("EPIC SNAKE GAME");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);

    }
    
}
