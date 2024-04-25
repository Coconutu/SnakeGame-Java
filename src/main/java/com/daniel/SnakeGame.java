package com.daniel;

import javax.swing.*;
import java.awt.*;

public class SnakeGame extends JPanel {
    int boardWidth;
    int boardHeight;
    SnakeGame(int boardWidth,int boardHeight){
        this.boardWidth=boardWidth;
        this.boardHeight=boardHeight;
        setPreferredSize(new Dimension(this.boardWidth,this.boardHeight));
        setBackground(Color.BLACK);

    }

}
