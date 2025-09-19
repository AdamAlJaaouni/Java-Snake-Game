package Src.Main;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class MainMenuScreen {

    
    final ImageIcon icon = new ImageIcon("Src/res/SnakeHeadDIcon.png"); // inserts image for icon
    String[] options = {"5 Apple Chaos Mode","1 Apple Classic Mode"}; 
    

    void runHomepage(){ //runs homescreen
        int choice = JOptionPane.showOptionDialog(
            null,
            "What Game Mode Would You Like?",
            "SNAKE !!! ",
            0,
            2,
            icon,
            options,
            options[1]
            );
            if(choice == 0){
                GameFrame gf = new GameFrame("5 APPLE");
            }
            if(choice == 1){
                GameFrame gf = new GameFrame("1 APPLE");
            }
    }
}
